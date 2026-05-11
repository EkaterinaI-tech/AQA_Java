package ru.ivanbulgakov.demowebshop;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import ru.ivanbulgakov.demowebshop.util.AttachManager;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.clearBrowserCookies;
import static com.codeborne.selenide.Selenide.clearBrowserLocalStorage;

public class TestBase {

    @BeforeAll
    static void setUp() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @BeforeAll
    static void before() {
        //Configuration.headless = true;
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

        AttachManager.takeScreenshot();
        AttachManager.pageSource();
        AttachManager.browserConsoleLogs();
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
