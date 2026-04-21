package ru.ivanbulgakov.demowebshop.test;

import com.codeborne.selenide.Configuration;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.ivanbulgakov.demowebshop.pages.WsRegistrationPage;
import ru.ivanbulgakov.demowebshop.pages.WsWelcomePage;

import static com.codeborne.selenide.Selenide.*;
import static ru.ivanbulgakov.demowebshop.config.Config.WEBSHOP_URL_REGISTER_URL;
import static ru.ivanbulgakov.demowebshop.config.Config.WEBSHOP_URL;

public class LoginTest {
    private static final Faker faker = new Faker();
    private String email;
    private String password;

    @BeforeEach
    void beforeEach() {
        Configuration.holdBrowserOpen = true;
        Configuration.timeout = 25000;
        Configuration.browserSize = "1920x1080";
        password = faker.harryPotter().character() + faker.number().positive();
        email = faker.internet().emailAddress();

        open(WEBSHOP_URL_REGISTER_URL, WsRegistrationPage.class)
                .register(
                        faker.name().firstName(),
                        faker.name().lastName(),
                        email,
                        password)
                .checkEmailIsShow(email);

        clearBrowserCookies();
        clearBrowserLocalStorage();
    }

    @Test
    void successLoginTest() {

        open(WEBSHOP_URL, WsWelcomePage.class)
                .openLogIn()
                .checkLoginPageOpened()
                .enterEmail(email)
                .enterPassword(password)
                .checkRememberMe()
                .submitLogin()
                .checkUserLoggedIn(email);
    }
}


