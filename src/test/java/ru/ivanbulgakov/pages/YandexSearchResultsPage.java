package ru.ivanbulgakov.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;

public class YandexSearchResultsPage {


    public WelcomePage openLink (String webSiteName) {
        $(byText(webSiteName)).click();
        switchTo().window(1);

        return new WelcomePage();
    }


}
