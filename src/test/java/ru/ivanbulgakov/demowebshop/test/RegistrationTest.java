package ru.ivanbulgakov.demowebshop.test;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.*;
import net.datafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.ivanbulgakov.demowebshop.TestBase;
import ru.ivanbulgakov.demowebshop.pages.WsWelcomePage;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.SeverityLevel.CRITICAL;
import static ru.ivanbulgakov.demowebshop.config.Config.WEBSHOP_URL;

public class RegistrationTest extends TestBase {
    private static final Faker faker = new Faker();


    @Owner("EkaterinaI-tech")
    @DisplayName("Успешная регистрация нового пользователя с использованием Faker")
    @Tag("UI")
    @Tag("REGISTRATION")
    @Tag("positive")
    @Severity(CRITICAL)
    @Epic("Авторизация")
    @Feature("Регистрация")
    @Description("Создаем нового пользователя через интерфейс с случайными данными")
    @Story("Регистрация нового пользователя")
    @Link(name = "TASK-120", url = "https://jira.example.com/browse/TASK-120")
    @Test

    void registrationTest() {

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
                //.enterConfirmPassword("pass")
                .submitRegistration()
                .checkRegistrationCompleted()
                .checkEmailIsShow(email);

    }
}
