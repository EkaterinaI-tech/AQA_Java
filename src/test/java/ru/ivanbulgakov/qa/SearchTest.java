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

        //open("https://yandex.by/");
        //$(".DistributionButtonClose_view_cross").click(); ->
        // закрыть всплывающее окно браузер по умолчанию
        //$("#text").setValue("ivanbulgakov.qa"); // яндекс поиск
        //before $("[type=submit]").click();
        //$(byText("ivanbulgakovqa.ru")).click(); //поисковая выдача

        // switchTo().window(1);
        //$$(".t-menu__list li").last().click(); //welcome страница обучения
        //$x("/html/body/div[1]/div[42]/div/div/div[32]/div/a").click();
        /*Это был поиск не через локатор, а через xpath(копируется часть кода).
        Плюс в том, что не нужно искать уникальный элемент,
        а минус в том, что если хоть одна часть кода поменяется,
        то весь путь обнулится и не будет работать.
        И минус в том, что не читабельно.
        Спустя время не поймешь что спрятано под этим кодом.
        */

        //Дальше идет поиск по совпадению текста "Бегу оплачивать"
        //$(byText("Бегу оплачивать")).click();

        //switchTo().window(2);
        //Один из способов: $(".styles-module-scss-module__t92_WG__price h2").shouldHave(text("₽ 47 000.00"));

        //Второй способ через поиск элемента в участке,
        //если мы знаем, что он один и выбирать больше нечего

        //$(".styles-module-scss-module__t92_WG__price").$("h2").shouldHave(text("₽ 47 000.00")); //страница оплаты
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

        /*
        Мой изначальный код:
        open("https://yandex.by/");
        Configuration.browserSize ="1920x1080";
        $(".DistributionButtonClose_view_cross").click();
        $("#text").setValue("stepik.org");
        $("[type=submit]").click();
        $(byText("stepik.org")).click();

        switchTo().window(1);
        sleep(3000);
        $(".search-form__input ").setValue("Artsiom Rusau");
        $(".button_with-loader").click();
        $$(".course-card__title").get(9).scrollTo().click();

        switchTo().window(2);
        $(".format-price_free").shouldHave(text("Бесплатно"));
        $$(".course-promo__head-widget").get(0).shouldBe(visible, Duration.ofSeconds(10)).scrollTo().shouldHave(text("Начальный уровень"));
        //$$(".course-promo__head-widget").get(0).scrollTo().shouldHave(text("Начальный уровень"));

         */
    }
    /*
     * 1. Открыть браузер Яндекс
     * 2. В поисковике найти сайт stepik.org
     * 3. На сайте найти автора Artsiom Rusau
     * 4. Найти курс "Тестирование ПО с нуля. Теория + Практика. Базовый уровень"
     * 5. Перейти по этому курсу
     * 6. Проверить, что стоимость курса бесплатная
     * 7. Проверить, что курс для начинающих
     */

}
