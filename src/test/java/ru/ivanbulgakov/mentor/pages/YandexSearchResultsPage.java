package ru.ivanbulgakov.mentor.pages;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class YandexSearchResultsPage extends BasePage {

    public YandexSearchResultsPage openLink(String webSiteName) {
        $ (byText(webSiteName)).click();
        return this;
    }

}