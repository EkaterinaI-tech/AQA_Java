package ru.ivanbulgakov.mentor.pages;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;
import static com.codeborne.selenide.Selenide.executeJavaScript;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class WelcomeStepik {

    private final SelenideElement courseAuthorInput = $(".search-form__input");
    private final SelenideElement authorButton = $(".button_with-loader");
    private static final String COURSE_TITLES = ".course-card__title";

    @Step("Искать курс по автору: {text}")
    public WelcomeStepik searchAuthorCourse(String text) {
        courseAuthorInput.setValue(text);
        return this;
    }

    @Step("Кнопка Поиск")
    public WelcomeStepik clickAuthor() {
        authorButton.click();
        return this;
    }

    @Step("Открыть курс с названием: {courseName}")
    public CoursePage openCourseByName(String courseName) {
        $$(COURSE_TITLES).shouldHave(sizeGreaterThan(0));
        sleep(3000);

        for (int i = 0; i < $$(COURSE_TITLES).size(); i++) {
            sleep(1000);
            SelenideElement title = $$(COURSE_TITLES).get(i);
            title.scrollTo();

            if (title.getText().contains(courseName)) {
                executeJavaScript("arguments[0].click();", title);
                //title.click();
                return new CoursePage();
            }
        }
        throw new RuntimeException("Курс '" + courseName + "' не найден");
    }
}


