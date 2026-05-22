package ru.ivanbulgakov.mentor.qa;

import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.ivanbulgakov.demowebshop.TestBase;
import ru.ivanbulgakov.mentor.pages.BuyPage;
import ru.ivanbulgakov.mentor.pages.WelcomePage;
import ru.ivanbulgakov.mentor.pages.WelcomeStepik;
import ru.ivanbulgakov.mentor.pages.YandexSearchPage;
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.SeverityLevel.CRITICAL;
import static io.qameta.allure.SeverityLevel.NORMAL;

public class SearchTest extends TestBase {
    private static final String YANDEX_URL = "https://yandex.by/";

    @DisplayName("Проверить, что цена обучения - 47000 рублей")
    @Tag("UI")
    @Tag("positive")
    @Tag("BULGAKOV")
    @Severity(NORMAL)
    @Owner("EkaterinaI-tech")
    @Link(name = "TASK-120", url = "https://jira.example.com/browse/TASK-120")
    @Test

    void mentoringPriceShouldBe47000Test() {

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

    @DisplayName("Проверить, что курс Тестирование ПО с нуля. Теория + Практика. Базовый уровень бесплатный")
    @Tag("UI")
    @Tag("STEPIK")
    @Tag("positive")
    @Severity(NORMAL)
    @Owner("EkaterinaI-tech")
    @Link(name = "TASK-120", url = "https://jira.example.com/browse/TASK-120")
    @Test

    void myStepikTest() {

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
