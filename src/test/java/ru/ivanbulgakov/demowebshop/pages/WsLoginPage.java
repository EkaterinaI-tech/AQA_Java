package ru.ivanbulgakov.demowebshop.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class WsLoginPage {
    private final SelenideElement pageTitleLogin = $("div.page-title h1");
    private final SelenideElement emailInput = $("input#Email");
    private final SelenideElement passwordInput = $("input#Password");
    private final SelenideElement inputRememberMe = $("input#RememberMe");
    private final SelenideElement loginButton =  $("input.login-button");

    public WsLoginPage checkLoginPageOpened() {
        pageTitleLogin.shouldHave(text("Welcome, Please Sign In!"));
        return this;
    }

    @Step("Ввести email {email}")
    public WsLoginPage enterEmail(String email) {
        emailInput.setValue(email).pressTab();
        return this;
    }

    @Step("Ввести password")
    public WsLoginPage enterPassword(String password) {
        passwordInput.setValue(password);
        return this;
    }

    @Step("Проверить, что появилось сообщение с ошибкой валидации почты")
    public WsLoginPage verifyEmailValidationErrorAppear() {
        $("span.field-validation-error").shouldBe(visible);
        return this;
    }

    public WsLoginPage checkRememberMe() {
        inputRememberMe.click();
        return this;
    }

    @Step("Подтвердить авторизацию")
    public WsWelcomePage submitLogin() {
        loginButton.click();
        return new WsWelcomePage();
    }
}
