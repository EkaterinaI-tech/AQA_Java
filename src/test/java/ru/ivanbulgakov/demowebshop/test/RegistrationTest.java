package ru.ivanbulgakov.demowebshop.test;

import com.codeborne.selenide.Configuration;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;
import ru.ivanbulgakov.demowebshop.pages.WsWelcomePage;

import static com.codeborne.selenide.Selenide.*;
import static ru.ivanbulgakov.demowebshop.config.Config.WEBSHOP_URL;

public class RegistrationTest {
    private static final Faker faker = new Faker();

    @Test
    void registrationTest() {
        Configuration.holdBrowserOpen = true;
        Configuration.timeout = 25000;
        Configuration.browserSize = "1920x1080";
        String password = faker.harryPotter().character() + faker.number().positive();
        String email = faker.internet().emailAddress();

        open(WEBSHOP_URL, WsWelcomePage.class)
                .openRegistration()
                .verifyRegistrationOpened()
                .selectFemaleGender()
                .enterFirstName(faker.name().firstName())
                .enterLastName(faker.name().lastName())
                .enterEmail(email)
                .enterPassword(password)
                .enterConfirmPassword(password)
                .submitRegistration()
                .checkRegistrationCompleted()
                .checkEmailIsShow(email);

    }
}
