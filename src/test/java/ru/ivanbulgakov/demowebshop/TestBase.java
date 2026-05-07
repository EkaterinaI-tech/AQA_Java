package ru.ivanbulgakov.demowebshop;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.clearBrowserCookies;
import static com.codeborne.selenide.Selenide.clearBrowserLocalStorage;

public class TestBase {
    @BeforeAll
    static void before() {
        Configuration.timeout = 25000;
        Configuration.browserSize = "1920x1080";
    }

    @AfterEach
    void after() {
        if (WebDriverRunner.hasWebDriverStarted()) {
            clearBrowserCookies();
            clearBrowserLocalStorage();

            closeExtraTabs();
        }
    }

    private void closeExtraTabs() {
        // 1. Получаем живой список всех открытых вкладок
        List<String> handles = new ArrayList<>(WebDriverRunner.getWebDriver().getWindowHandles());

        // 2. Если вкладок больше одной
        if (handles.size() > 1) {
            // Идем циклом С КОНЦА списка. Мы закрываем вкладки [2], потом [1], но не трогаем [0].
            for (int i = handles.size() - 1; i > 0; i--) {
                Selenide.switchTo().window(i);
                Selenide.closeWindow();
            }
            // 3. Возвращаемся в единственную оставшуюся вкладку
            Selenide.switchTo().window(0);
        }
    }
}
