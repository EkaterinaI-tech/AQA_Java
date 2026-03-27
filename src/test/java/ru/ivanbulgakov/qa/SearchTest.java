package ru.ivanbulgakov.qa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.ivanbulgakov.pages.YandexSearchPage;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.*;

public class SearchTest {
    @Test
    @DisplayName("Проверить, что цена обучения - 47000 рублей")
    @Tag("POSITIVE")

    void mentoringPriceShouldBe47000Test() {
        Configuration.holdBrowserOpen = true;
        open("https://yandex.by/", YandexSearchPage.class)

                .closeDefaultBrowserSelectWindow()
                .search("ivanbulgakov.qa")
                .submit()
                .openLink("ivanbulgakovqa.ru")
                .clickPrice()
                .clickGoTo()
                .clickBuy()
                .checkPrice("₽ 47 000.00");

        /*
        создаем класс под страницу ->
        выписываем методы для взаимодействия ->
        вытаскиваем из теста действия ->
        выносим локаторы в переменные
         */

    }

    @Test
    void myFirstTest() {

        Configuration.holdBrowserOpen = true;
        open("https://yandex.by/", YandexSearchPage.class)

                .closeDefaultBrowserSelectWindow()
                .search("stepik.org")
                .submit()
                .openLink("stepik.org")
                .searchAuthorCourse("Artsiom Rusau")
                .clickAuthor()
                .openCourseByIndex(9)
                .checkPriceCourse("Бесплатно")
                .checkLevel("Начальный уровень");
    }
}
