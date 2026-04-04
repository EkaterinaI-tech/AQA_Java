package ru.ivanbulgakov.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.*;

public class WelcomeStepik {

    private final SelenideElement CourseAuthorInput = $(".search-form__input");
    private final SelenideElement authorButton = $(".button_with-loader");
    private final ElementsCollection courseTitles = $$(".course-card__title");
    private static final String COURSE_TITLES = ".course-card__title";

    public WelcomeStepik searchAuthorCourse(String text) {
        CourseAuthorInput.setValue(text);
        return this;
    }

    public WelcomeStepik clickAuthor() {
        authorButton.click();
        return this;
    }

    public CoursePage openCourseByIndex(int index) {
        courseTitles.get(index).scrollTo().click();
        return new CoursePage();
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


