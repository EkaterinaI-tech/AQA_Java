package ru.ivanbulgakov.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class WelcomePage {
    private final SelenideElement clickPriceButton = $$(".t-menu__list li").last();
    private final SelenideElement clickGoToButton = $x("/html/body/div[1]/div[42]/div/div/div[32]/div/a");
    private final SelenideElement clickBuyButton = $(byText("Бегу оплачивать"));

    public WelcomePage clickPrice() {
        clickPriceButton.click();
        return this;
    }

    public WelcomePage clickGoTo() {
        clickGoToButton.click();
        return this;
    }

    public BuyPage clickBuy() {
        clickBuyButton.click();

        switchTo().window(2);

        return new BuyPage();
    }

    private final SelenideElement searchCourseAuthorInput = $(".search-form__input");

    public WelcomePage searchAuthorCourse(String text) {
        searchCourseAuthorInput.setValue(text);
        return this;
    }

    private final SelenideElement clickAuthorButton = $(".button_with-loader");

    public WelcomePage clickAuthor() {
        clickAuthorButton.click();
        return this;
    }

    private final ElementsCollection courseTitles = $$(".course-card__title");

    public CoursePage openCourseByIndex(int index) {
        courseTitles.get(index).scrollTo().click();
        switchTo().window(2);
        return new CoursePage();
    }


    /*
    !пример оформления:!
        private final SelenideElement submitButton = $("[type=submit]");
    public YandexSearchResultsPage submit () {
             submitButton.click();
             return new YandexSearchResultsPage();
         }
     */
}
