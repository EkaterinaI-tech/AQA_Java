package ru.ivanbulgakov.mentor.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class WelcomePage extends BasePage{
    private final SelenideElement PriceButton = $$(".t-menu__list li").last();
    private final SelenideElement GoToButton = $x("/html/body/div[1]/div[42]/div/div/div[32]/div/a");
    private final SelenideElement BuyButton = $(byText("Бегу оплачивать"));

    @Step("Перейти в раздел Стоимость в меню")
    public WelcomePage clickPrice() {
        PriceButton.click();
        return this;
    }

    @Step("Нажать на кнопку Хочу вкатиться в QA")
    public WelcomePage clickGoTo() {
        GoToButton.click();
        return this;
    }

    @Step("Нажать на кнопку 'Бегу оплачивать'")
    public WelcomePage clickBuy() {
        BuyButton.click();
        return this;
    }
}
