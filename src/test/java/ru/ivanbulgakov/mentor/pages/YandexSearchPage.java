package ru.ivanbulgakov.mentor.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class YandexSearchPage {

    private final SelenideElement searchInput = $("#text");
    private final SelenideElement submitButton = $ (".search3__button");
   private final SelenideElement closeWindow = $(".DistributionButtonClose_view_cross");


   @Step("Закрыть всплывающее окно - Установить браузер по умолчанию?")
   public YandexSearchPage closeDefaultBrowserSelectWindow() {
         if (closeWindow.is(visible, Duration.ofMillis(500))) {
             closeWindow.click();
         }
         return this;
    }

    @Step("Поиск в Яндексе по запросу: {query}")
    public YandexSearchPage search(String query) {
        searchInput.setValue(query); // яндекс поиск
        return this;
         }

    @Step("Нажать кнопку поиска")
    public YandexSearchResultsPage submit() {
        submitButton.click();
        return new YandexSearchResultsPage();
        }
    }

