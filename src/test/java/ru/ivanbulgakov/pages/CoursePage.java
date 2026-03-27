package ru.ivanbulgakov.pages;

import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CoursePage {
    private final SelenideElement priceValueCourse = $(".format-price_free");

    public CoursePage checkPriceCourse (String expectedPrice) {
        priceValueCourse.shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(text(expectedPrice));
        return this;
    }

    private final SelenideElement checkLevelCourse = $$(".course-promo__head-widget").get(0);
    public CoursePage checkLevel(String expectedLevel) {
        checkLevelCourse.shouldBe(visible, Duration.ofSeconds(10)) // Ждем тут
                .scrollTo() // Скроллим тут
                .shouldHave(text(expectedLevel)); // Проверяем тут
        return this;
    }


}
