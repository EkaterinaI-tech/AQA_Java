package ru.ivanbulgakov.mentor.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class BuyPage {

    private final SelenideElement priceValue = $("[class*='t92_WG__price'] h3"),
            currencyList = $("div.ant-select-selector");

    @Step("Проверить, что цена на странице покупки равна {expectedPrice}")
    public BuyPage checkPrice(String expectedPrice) {
        priceValue.shouldBe(visible)
                  .shouldHave(text(expectedPrice));
        return this;
    }

    @Step("Выбрать валюту: {currency}")
    public BuyPage selectCurrency(String currency) {
        currencyList.click();
        $ (byText(currency)).click();
        return this;
    }

}
