package ru.ivanbulgakov.demowebshop;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import net.datafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationTest {
    private static final Faker faker = new Faker();

    @Test

    void registrationTest() {
        Configuration.holdBrowserOpen = true;
        Configuration.timeout = 25000;
        Configuration.browserSize = "1920x1080";
        String password = faker.harryPotter().character() + faker.number().positive();

        open("https://demowebshop.tricentis.com/");
        $("a.ico-register").click();

        $("div.page-title").shouldHave(text("Register"));
        $("input#gender-female").click();
        $("input#FirstName").setValue(faker.name().firstName());
        $("input#LastName").setValue(faker.name().lastName());
        $("input#Email").setValue(faker.internet().emailAddress());
        $("input#Password").setValue(password);
        $("input#ConfirmPassword").setValue(password);
        $("input#register-button").click();
    }
}
