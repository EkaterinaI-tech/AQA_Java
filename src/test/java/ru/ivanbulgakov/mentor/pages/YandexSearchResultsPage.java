package ru.ivanbulgakov.mentor.pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class YandexSearchResultsPage extends BasePage {

    @Step("Открыть ссылку в выдаче: {webSiteName}")
    public YandexSearchResultsPage openLink(String webSiteName) {
        $ (byText(webSiteName)).click();
        return this;
    }

}