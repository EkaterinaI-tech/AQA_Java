package ru.ivanbulgakov.demowebshop.test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.ivanbulgakov.demowebshop.steps.AuthSteps;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
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
        open(WEBSHOP_URL);
        $$("ul.top-menu li a").get(1).hover();
        $(byText("Desktops")).click();
        $$("div.product-grid div").get(0).click();

        String itemName = $("[itemprop=name]").getText();
        String itemPrice = $("[itemprop=price]").getText();
        String itemQuantity = "2";

        $$("dl dd ul li").get(0).$$("input").get(0).click();
        $("input.qty-input").setValue(itemQuantity);
        $("input.add-to-cart-button").click();
        $(".bar-notification.success").shouldBe(visible);
        $("span.cart-qty").shouldHave(text("(" + itemQuantity + ")"));
        $("span.cart-label").click();

        $("a.product-name").shouldHave(text(itemName));
        String actualQuantity  = $(".qty-input").val();
        assertEquals(itemQuantity, actualQuantity);
        $("span.product-subtotal").shouldHave(text(String.valueOf(
                Float.parseFloat(itemPrice) * Float.parseFloat(itemQuantity))));

    }
}
