package ru.ivanbulgakov.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class BuyPage {

    private final SelenideElement priceValue = $("[class*='t92_WG__price'] h3"),
            currencyList = $(".ant-select-selector");

    public BuyPage checkPrice(String expectedPrice) {
        priceValue.shouldBe(visible)
                  .shouldHave(text(expectedPrice));
        return this;
    }

    public BuyPage selectCurrency(String currency) {
        currencyList.click();
        $ (byText(currency)).click();
        return this;
    }

}
