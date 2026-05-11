package ru.ivanbulgakov.mentor.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class CoursePage {

    private final SelenideElement priceValueCourse = $(".format-price_free");
    private final SelenideElement levelCourse = $("[data-type='difficulty']"); //$$(".course-promo__head-widget").get(0);

    @Step("Переключиться на вкладку №{index}")
    public CoursePage switchToCourse(int index) {
        switchTo().window(index);
        return this;
    }

    @Step("Проверить, что стоимость курса: {expectedPrice}")
    public CoursePage checkPriceCourse(String expectedPrice) {
        priceValueCourse.shouldBe(visible)
                        .shouldHave(text(expectedPrice));
        return this;
    }

    @Step("Перейти к уровню сложности курса")
    public CoursePage scrollToLevel() {
        levelCourse.scrollTo();
        return this;
    }

    @Step("Проверить, что уровень курса: {expectedLevel}")
    public CoursePage checkLevel(String expectedLevel) {
        levelCourse.shouldHave(text(expectedLevel));
        return this;
    }
}
