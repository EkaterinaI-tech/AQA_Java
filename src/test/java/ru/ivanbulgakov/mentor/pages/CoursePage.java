package ru.ivanbulgakov.mentor.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class CoursePage {

    private final SelenideElement priceValueCourse = $(".format-price_free");
    private final SelenideElement levelCourse = $$(".course-promo__head-widget").get(0);

    public CoursePage switchToCourse(int index) {
        switchTo().window(index);
        return this;
    }

    public CoursePage checkPriceCourse(String expectedPrice) {
        priceValueCourse.shouldBe(visible)
                        .shouldHave(text(expectedPrice));
        return this;
    }

    public CoursePage scrollToLevel() {
        levelCourse.scrollTo();
        return this;
    }

    public CoursePage checkLevel(String expectedLevel) {
        levelCourse.shouldHave(text(expectedLevel));
        return this;
    }
}
