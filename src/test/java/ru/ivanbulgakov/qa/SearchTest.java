package ru.ivanbulgakov.qa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.ivanbulgakov.pages.BuyPage;
import ru.ivanbulgakov.pages.WelcomePage;
import ru.ivanbulgakov.pages.WelcomeStepik;
import ru.ivanbulgakov.pages.YandexSearchPage;

import static com.codeborne.selenide.Selenide.*;

public class SearchTest {

    @Test
    @DisplayName("Проверить, что цена обучения - 47000 рублей")
    @Tag("POSITIVE")
    void mentoringPriceShouldBe47000Test() {

        Configuration.holdBrowserOpen = true;
        Configuration.timeout = 15000;

        open("https://yandex.by/", YandexSearchPage.class)
                .closeDefaultBrowserSelectWindow()
                .search("ivanbulgakov.qa")
                .submit()

                .openLink("ivanbulgakovqa.ru")
                .switchToPage(1, WelcomePage.class)

                .clickPrice()
                .clickGoTo()
                .clickBuy()

                .switchToPage(2, BuyPage.class)

                .selectCurrency("EUR")
                .checkPrice("€ 502.90");

       }

    @Test
    void myFirstTest() {

        Configuration.timeout = 25000;
        Configuration.browserSize = "1920x1080";

        open("https://yandex.by/", YandexSearchPage.class)

                .closeDefaultBrowserSelectWindow()
                .search("stepik.org")
                .submit()

                .openLink("stepik.org")
                .switchToPage(1, WelcomeStepik.class)

                .searchAuthorCourse("Artsiom Rusau")

                .clickAuthor()
                .openCourseByName("Тестирование ПО с нуля. Теория + Практика")

                .switchToCourse(2)
                .checkPriceCourse("Бесплатно")
                .scrollToLevel()
                .checkLevel("Начальный уровень");
    }
}
