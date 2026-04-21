package ru.ivanbulgakov.demowebshop.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class WsWelcomePage {
    private final SelenideElement registerButtоn = $("a.ico-register");
    private final SelenideElement openLogin = $("a.ico-login");
    private final ElementsCollection headerLinks = $$("div.header-links ul li a");

    public WsRegistrationPage openRegistration() {
        registerButtоn.click();
        return new WsRegistrationPage();
    }

    public WsLoginPage openLogIn() {
        openLogin.click();
        return new WsLoginPage();
    }

    public WsWelcomePage checkUserLoggedIn(String email) {
        headerLinks.get(0).shouldHave(text(email));
        return this;
    }
}
