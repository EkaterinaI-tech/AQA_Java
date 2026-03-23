package ru.ivanbulgakov.qa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SearchTest {
    @Test
    void mentoringPriceShouldBe47000Test() {
        /*
        * 1. Тест-кейс - Проверить, что цена предоплаты за обучение 47 000 рублей
        * 2. Открыть поисковик Яндекс
        * 3. Ввести данные сайта
        * 4. Нажать кнопку поиск
        * 5. В поисковой выдаче найти нужный сайт и кликнуть на него
        * 6. Нажать на кнопку Стоимость
        * 7. Нажать на кнопку Хочу вкатиться в QA
        * 8. Нажать на кнопку Бегу оплачивать
        * ...
        * 10. Проверить, что к оплате 47000 рублей
         */


        Configuration.holdBrowserOpen = true;
        open("https://yandex.by/");
        $(".DistributionButtonClose_view_cross").click();
        $("#text").setValue("ivanbulgakov.qa");
        $("[type=submit]").click();
        $(byText("ivanbulgakovqa.ru")).click();
        switchTo().window(1);
        $$(".t-menu__list li").last().click();
        $x("/html/body/div[1]/div[42]/div/div/div[32]/div/a").click();
        /*Это был поиск не через локатор, а через xpath(копируется часть кода).
        Плюс в том, что не нужно искать уникальный элемент,
        а минус в том, что если хоть одна часть кода поменяется,
        то весь путь обнулится и не будет работать.
        И минус в том, что не читабельно.
        Спустя время не поймешь что спрятано под этим кодом.
         */
        //Дальше идет поиск по совпадению текста "Бегу оплачивать"
        $(byText("Бегу оплачивать")).click();
        switchTo().window(2);
        //Один из способов: $(".styles-module-scss-module__t92_WG__price h2").shouldHave(text("₽ 47 000.00"));
        /*
        Второй способ через поиск элемента в участке,
        если мы знаем, что он один и выбирать больше нечего
         */
        $(".styles-module-scss-module__t92_WG__price").$("h2").shouldHave(text("₽ 47 000.00"));
    }

    @Test
    void myFirstTest() {
        /*
         * 1. Открыть браузер Яндекс
         * 2. В поисковике найти сайт stepik.org
         * 3. На сайте найти автора Artsiom Rusau
         * 4. Найти курс "Тестирование ПО с нуля. Теория + Практика. Базовый уровень"
         * 5. Перейти по этому курсу
         * 6. Проверить, что стоимость курса бесплатная
         * 7. Проверить, что курс для начинающих
         */


        Configuration.holdBrowserOpen = true;

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







    }




}
