package ru.ivanbulgakov.demowebshop.test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.ivanbulgakov.demowebshop.pages.WsCartPage;
import ru.ivanbulgakov.demowebshop.pages.WsProductPage;
import ru.ivanbulgakov.demowebshop.pages.WsWelcomePage;
import ru.ivanbulgakov.demowebshop.steps.AuthSteps;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.ivanbulgakov.demowebshop.config.Config.WEBSHOP_URL;

public class CartTest {
    private final AuthSteps authSteps = new AuthSteps();

    @BeforeEach
    void beforeEach() {
        Configuration.holdBrowserOpen = true;
        Configuration.timeout = 25000;
        Configuration.browserSize = "1920x1080";

        authSteps.registerNewUser();
    }

    @Test
    void addItemToCartTest() {
        WsProductPage productPage = open(WEBSHOP_URL, WsWelcomePage.class)
                .hoverCategory(1)
                .clickSubCategory("Desktops")
                .openProductByIndex(0);

        String itemName = productPage.getItemName();
        String itemPrice = productPage.getItemPrice();
        String itemQuantity = "2";

        productPage.selectFirstOption()

                .setQuantity(itemQuantity)
                .addToCart()
                .verifySuccessNotificationVisible()
                .verifyCartQuantity(itemQuantity)
                .goToCart()

                .verifyProductName(itemName)
                .verifyProductPrice(itemPrice);

        WsCartPage cartPage = productPage.goToCart();
        String actualQuantity = cartPage.getActualQuantity();
        assertEquals(itemQuantity, actualQuantity);

        cartPage.verifySubtotal(String.valueOf(
                Float.parseFloat(itemPrice) * Float.parseFloat(itemQuantity)));

    }
}
