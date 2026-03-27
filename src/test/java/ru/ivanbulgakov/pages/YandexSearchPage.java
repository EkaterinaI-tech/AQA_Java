package ru.ivanbulgakov.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class YandexSearchPage {
    private final SelenideElement searchInput = $("#text");
    private final SelenideElement submitButton = $("[type=submit]");
    private final SelenideElement closeWindow = $(".DistributionButtonClose_view_cross");

    public YandexSearchPage search (String query) {
        searchInput.setValue(query); // яндекс поиск

        return this;
         }

         public YandexSearchResultsPage submit () {
             submitButton.click();
             return new YandexSearchResultsPage();
         }

         public YandexSearchPage closeDefaultBrowserSelectWindow() {
             closeWindow.click();
             return this;
         }

    }

