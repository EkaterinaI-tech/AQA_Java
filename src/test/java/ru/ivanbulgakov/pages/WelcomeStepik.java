package ru.ivanbulgakov.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;

public class WelcomeStepik {

    private final SelenideElement CourseAuthorInput = $(".search-form__input");
    private final SelenideElement authorButton = $(".button_with-loader");
    private final ElementsCollection courseTitles = $$(".course-card__title");

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

    /*
    public CoursePage openCourseByName(String courseName) {
        courseTitles.find(text(courseName)).scrollTo().click();
        return new CoursePage();
    }

     */
}


