package ru.ivanbulgakov.demowebshop.test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.ivanbulgakov.demowebshop.TestBase;
import ru.ivanbulgakov.demowebshop.pages.WsCartPage;
import ru.ivanbulgakov.demowebshop.pages.WsProductPage;
import ru.ivanbulgakov.demowebshop.pages.WsWelcomePage;
import ru.ivanbulgakov.demowebshop.steps.AuthSteps;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.ivanbulgakov.demowebshop.config.Config.WEBSHOP_URL;

public class CartTest extends TestBase {
    private final AuthSteps authSteps = new AuthSteps();

    @BeforeEach
    void beforeEach() {
        authSteps.registerNewUser();
    }

    @DisplayName("Проверка добавления товара в корзину в соответствии с количеством и ценой")
    @Tag("SMOKE")
    @Tag("CART")
    @Tag("POSITIVE")
    @Test
    void addItemToCartTest() {
        WsProductPage productPage = open(WEBSHOP_URL, WsWelcomePage.class)
                .hoverCategory(1)
                .clickSubCategory("Desktops")
                .openProductByIndex(0);

        String itemName = productPage.getItemName();
        String itemPrice = productPage.getItemPrice();
        String itemQuantity = "2";
        int processorIndex = 1;
        WsCartPage cartPage = productPage.selectProcessor(processorIndex)
                .setQuantity(itemQuantity)
                .addToCart()
                .verifySuccessNotificationVisible()
                .verifyCartQuantity(itemQuantity)
                .goToCart();

        float basePrice = Float.parseFloat(itemPrice);
        float surcharge = getProcessorSurcharge(processorIndex);
        String expectedTotal = String.format(java.util.Locale.US, "%.2f",
                (basePrice + surcharge) * Float.parseFloat(itemQuantity));

        assertAll(
                () -> assertEquals(itemName, cartPage.getItemName()),
                () -> assertEquals(String.format(java.util.Locale.US, "%.2f", basePrice + surcharge), cartPage.getProductPrice(), "Цена за штуку не совпала"),
                () -> assertEquals(expectedTotal, cartPage.getSubtotal()),
                () -> assertEquals(itemQuantity, cartPage.getQuantity())
        );
    }

    private float getProcessorSurcharge(int processorIndex) {
        return switch (processorIndex) {
            case 0 -> 0f;      // slow - без надбавки
            case 1 -> 15f;     // medium - +15$
            case 2 -> 100f;    // fast - +100$
            default -> throw new IllegalArgumentException(
                    "Unknown processor index: " + processorIndex);
        };
    }
}
