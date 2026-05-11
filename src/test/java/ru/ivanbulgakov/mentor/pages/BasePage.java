package ru.ivanbulgakov.mentor.pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.switchTo;

public class BasePage {

    @Step("Переключиться на вкладку №{index}")
    public <T> T switchToPage(int index, Class<T> pageClass) {
        switchTo().window(index);
        return page(pageClass);
    }
}
