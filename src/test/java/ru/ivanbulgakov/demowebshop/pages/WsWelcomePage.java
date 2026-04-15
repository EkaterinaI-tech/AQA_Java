package ru.ivanbulgakov.demowebshop.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class WsWelcomePage {
    private final SelenideElement registerButten = $("a.ico-register");

    public WsRegistrationPage openRegistration() {
        registerButten.click();
        return new WsRegistrationPage();
    }
}
