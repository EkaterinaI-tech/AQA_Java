package ru.ivanbulgakov.demowebshop.test;

import com.codeborne.selenide.Configuration;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.ivanbulgakov.demowebshop.pages.WsRegistrationPage;
import ru.ivanbulgakov.demowebshop.steps.AuthSteps;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static ru.ivanbulgakov.demowebshop.config.Config.WEBSHOP_URL;
import static ru.ivanbulgakov.demowebshop.config.Config.WEBSHOP_URL_REGISTER_URL;

public class CartTest {
    private static final Faker faker = new Faker();
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
    }
}
