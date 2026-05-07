package ru.ivanbulgakov.demowebshop.pages;

import com.codeborne.selenide.SelenideElement;

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

    public WsLoginPage enterEmail(String email) {
        emailInput.setValue(email);
        return this;
    }

    public WsLoginPage enterPassword(String password) {
        passwordInput.setValue(password);
        return this;
    }

    public WsLoginPage verifyEmailValidationErrorAppear() {
        $("span.field-validation-error").shouldBe(visible);
        return this;
    }

    public WsLoginPage checkRememberMe() {
        inputRememberMe.click();
        return this;
    }

    public WsWelcomePage submitLogin() {
        loginButton.click();
        return new WsWelcomePage();
    }
}
