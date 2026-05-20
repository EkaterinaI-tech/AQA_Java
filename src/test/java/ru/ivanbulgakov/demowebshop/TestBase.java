package ru.ivanbulgakov.demowebshop;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.ivanbulgakov.demowebshop.config.WebDriverConfig;
import ru.ivanbulgakov.demowebshop.util.AttachManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Selenide.clearBrowserCookies;
import static com.codeborne.selenide.Selenide.clearBrowserLocalStorage;
import static ru.ivanbulgakov.demowebshop.config.Config.*;

public class TestBase {

    private static final WebDriverConfig config = getWebDriverConfig();

    @BeforeAll
    static void setUp() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        //Configuration.headless = true;
        Configuration.timeout = 25000;
        Configuration.browserSize = config.browserSize();
        Configuration.browser = config.browser() ;

        if ("remote".equals(System.getProperty("run"))) {
            Configuration.remote =
                    "https://" + config.selenoidUser() + ":" + config.selenoidPassword() + "@" + config.selenoidUrl();
            Configuration.browserCapabilities = getSelenoidChromeOptions();
        }
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
        if ("remote".equals(config.run())) {
            AttachManager.addVideo();
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
