package ru.ivanbulgakov.demowebshop.test;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import net.datafaker.Faker;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import ru.ivanbulgakov.demowebshop.TestBase;
import ru.ivanbulgakov.demowebshop.pages.WsLoginPage;
import ru.ivanbulgakov.demowebshop.pages.WsRegistrationPage;
import ru.ivanbulgakov.demowebshop.pages.WsWelcomePage;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.SeverityLevel.CRITICAL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.ivanbulgakov.demowebshop.config.Config.*;

public class LoginTest extends TestBase {
    private static final Faker faker = new Faker();
    private String email;
    private String password;

    @Nested
    public class PositiveTest {
        @BeforeEach
        void beforeEach() {
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

        @DisplayName("Проверка успешного входа залогиненного пользователя")
        @Tag("UI")
        @Tag("SMOKE")
        @Tag("LOGIN")
        @Tag("positive")
        @Severity(CRITICAL)
        @Owner("EkaterinaI-tech")
        @Link(name = "TASK-120", url = "https://jira.example.com/browse/TASK-120")
        @Test

        void successLoginTest() {

            String loggedInUserEmail = open(WEBSHOP_URL, WsWelcomePage.class)
                    .openLogIn()
                    .enterEmail(email)
                    .enterPassword(password)
                    .submitLogin()
                    .getLoggedInUserEmail();

            assertEquals(email, loggedInUserEmail, "Емейл залогиненного пользователя не совпадает");
        }
    }

    @Tag("UI")
    @Tag("LOGIN")
    @Tag("negative")
    @Severity(CRITICAL)
    @Owner("EkaterinaI-tech")
    @Link(name = "TASK-120", url = "https://jira.example.com/browse/TASK-120")
    @ParameterizedTest(name = "Авторизация с невалидным email: {0}")
    @CsvFileSource(resources = "/email.csv")

    void invalidEmailLoginTest(String email) {
        open(WEBSHOP_URL_LOGIN_URL, WsLoginPage.class)
                .enterEmail(email)
                .enterPassword(password)
                .verifyEmailValidationErrorAppear()
                .submitLogin();
    }
}