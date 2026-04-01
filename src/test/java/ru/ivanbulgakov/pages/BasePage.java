package ru.ivanbulgakov.pages;

import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.switchTo;

public class BasePage {
    public <T> T switchToPage(int index, Class<T> pageClass) {
        switchTo().window(index);
        return page(pageClass);
    }
}
