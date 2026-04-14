package ru.ivanbulgakov.qa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.ivanbulgakov.pages.BuyPage;
import ru.ivanbulgakov.pages.WelcomePage;
import ru.ivanbulgakov.pages.WelcomeStepik;
import ru.ivanbulgakov.pages.YandexSearchPage;
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.*;

public class SearchTest {
    private static final String YANDEX_URL = "https://yandex.by/";

    @AfterEach
    void tearDown() {
        Selenide.closeWebDriver();
    }

    @Test
    @DisplayName("Проверить, что цена обучения - 47000 рублей")
    @Tag("POSITIVE")

    void mentoringPriceShouldBe47000Test() {

        Configuration.timeout = 25000;
        Configuration.browserSize = "1920x1080";

        open(YANDEX_URL, YandexSearchPage.class)
                .closeDefaultBrowserSelectWindow()
                .search("ivanbulgakovqa")
                .submit()

                .openLink("ivanbulgakovqa.ru")
                .switchToPage(1, WelcomePage.class)

                .clickPrice()
                .clickGoTo()
                .clickBuy()

                .switchToPage(2, BuyPage.class)

                .selectCurrency("RUB")
                .checkPrice("47 000");
       }


    private static final String COURSE_NAME = "Тестирование ПО с нуля. Теория + Практика. Базовый уровень";
    @Test
    void myFirstTest() {

        Configuration.timeout = 25000;
        Configuration.browserSize = "1920x1080";

        open(YANDEX_URL, YandexSearchPage.class)

                .closeDefaultBrowserSelectWindow()
                .search("stepik.org")
                .submit()

                .openLink("stepik.org")
                .switchToPage(1, WelcomeStepik.class)

                .searchAuthorCourse("Artsiom Rusau")
                .clickAuthor()
                .openCourseByName(COURSE_NAME)

                .switchToCourse(2)
                .checkPriceCourse("Бесплатно")
                .scrollToLevel()
                .checkLevel("Начальный уровень");
    }
}
