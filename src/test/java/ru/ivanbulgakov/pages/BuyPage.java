package ru.ivanbulgakov.pages;

import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class BuyPage {

    private final SelenideElement priceValue = $(".styles-module-scss-module__t92_WG__price").$("h2");

    public CoursePage checkPrice(String expectedPrice) {
        // Добавляем 15 секунд ожидания
        priceValue.shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(text(expectedPrice));
        switchTo().window(2);

        return new CoursePage();
    }
}