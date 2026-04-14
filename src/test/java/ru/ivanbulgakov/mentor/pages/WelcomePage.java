package ru.ivanbulgakov.mentor.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class WelcomePage extends BasePage{
    private final SelenideElement PriceButton = $$(".t-menu__list li").last();
    private final SelenideElement GoToButton = $x("/html/body/div[1]/div[42]/div/div/div[32]/div/a");
    private final SelenideElement BuyButton = $(byText("Бегу оплачивать"));

    public WelcomePage clickPrice() {
        PriceButton.click();
        return this;
    }

    public WelcomePage clickGoTo() {
        GoToButton.click();
        return this;
    }

    public WelcomePage clickBuy() {
        BuyButton.click();
        return this;
    }
}
