package ru.ivanbulgakov.pages;

import com.codeborne.selenide.SelenideElement;

import java.lang.reflect.InvocationTargetException;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class YandexSearchResultsPage extends BasePage {

    public YandexSearchResultsPage openLink(String webSiteName) {
        $ (byText(webSiteName)).click();
        return this;
    }

}