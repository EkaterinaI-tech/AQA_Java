package ru.ivanbulgakov.mentor.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.*;

public class WelcomeStepik {

    private final SelenideElement courseAuthorInput = $(".search-form__input");
    private final SelenideElement authorButton = $(".button_with-loader");
    private static final String COURSE_TITLES = ".course-card__title";

    public WelcomeStepik searchAuthorCourse(String text) {
        courseAuthorInput.setValue(text);
        return this;
    }

    public WelcomeStepik clickAuthor() {
        authorButton.click();
        return this;
    }

    public CoursePage openCourseByName(String courseName) {
        $$(COURSE_TITLES).shouldHave(sizeGreaterThan(0));
        sleep(3000);

        for (int i = 0; i < $$(COURSE_TITLES).size(); i++) {
            sleep(300);
            SelenideElement title = $$(COURSE_TITLES).get(i);
            title.scrollTo();

            if (title.getText().contains(courseName)) {
                title.scrollIntoCenter().click();
                return new CoursePage();
            }
        }
        throw new RuntimeException("Курс '" + courseName + "' не найден");
    }
}


