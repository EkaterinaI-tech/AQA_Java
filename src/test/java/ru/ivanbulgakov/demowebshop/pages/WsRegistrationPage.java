package ru.ivanbulgakov.demowebshop.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import net.datafaker.providers.base.ElectricalComponents;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class WsRegistrationPage {
    private final SelenideElement pageTitle = $("div.page-title");
    private final SelenideElement femaleGenderRadio = $("input#gender-female");
    private final SelenideElement firstNameInput = $("input#FirstName");
    private final SelenideElement lastNameInput = $("input#LastName");
    private final SelenideElement emailInput = $("input#Email");
    private final SelenideElement passwordInput = $("input#Password");
    private final SelenideElement confirmPasswordInput = $("input#ConfirmPassword");
    private final SelenideElement submitRegistrationButten = $("input#register-button");
    private final SelenideElement resultText = $("div.result");
    private final ElementsCollection headerLinks = $$("div.header-links ul li a");

    public WsRegistrationPage register(String firstName, String lastName, String email, String password) {
        selectFemaleGender()
                .enterFirstName(firstName)
                .enterLastName(lastName)
                .enterEmail(email)
                .enterPassword(password)
                .enterConfirmPassword(password)
                .submitRegistration()
                .checkRegistrationCompleted();
        return this;
    }

    public WsRegistrationPage verifyRegistrationOpened() {
        pageTitle.shouldHave(text("Register"));
        return this;
    }

    public WsRegistrationPage selectFemaleGender() {
        femaleGenderRadio.click();
        return this;
    }

    public WsRegistrationPage enterFirstName(String firstName) {
        firstNameInput.setValue(firstName);
        return this;
    }

    public WsRegistrationPage enterLastName(String lastName) {
        lastNameInput.setValue(lastName);
        return this;
    }

    public WsRegistrationPage enterEmail(String email) {
        emailInput.setValue(email);
        return this;
    }

    public WsRegistrationPage enterPassword(String password) {
        passwordInput.setValue(password);
        return this;
    }

    public WsRegistrationPage enterConfirmPassword(String confirmPassword) {
        confirmPasswordInput.setValue(confirmPassword);
        return this;
    }

    public WsRegistrationPage submitRegistration() {
        submitRegistrationButten.click();
        return this;
    }

    public WsRegistrationPage checkRegistrationCompleted() {
        resultText.shouldHave(text("Your registration completed"));
        return this;
    }

    public WsRegistrationPage checkEmailIsShow(String email) {
        headerLinks.get(0).shouldHave(text(email));
        return this;
    }
}
