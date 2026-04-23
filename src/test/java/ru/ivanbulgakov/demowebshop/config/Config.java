package ru.ivanbulgakov.demowebshop.config;

public class Config {

    public static final String WEBSHOP_URL = "https://demowebshop.tricentis.com/";
    public static final String WEBSHOP_URL_REGISTER_URL = WEBSHOP_URL + "register";
}

/*
1. Навигация (Поиск товара)
$$("ul.top-menu li a").get(1).hover(); — Ты заходишь на главную и наводишь мышку на категорию «Computers» (она вторая в списке). Выпадает меню.
$(byText("Desktops")).click(); — Ты выбираешь подкатегорию «Desktops» (Настольные ПК).
$$("div.product-grid div").get(0).click(); — Открывается список компьютеров, и ты кликаешь на самый первый (скорее всего, это модель «Build your own cheap computer»).
        2. Захват данных (Запоминание ценника)
$("[itemprop=name]").getText(); — Ты «фотографируешь» название компьютера.
$("[itemprop=price]").getText(); — Ты записываешь в блокнот его текущую цену (например, 800.00).
        3. Конфигурация (Настройка заказа)
$$("dl dd ul li").get(0)...click(); — Ты выбираешь первую опцию из списка характеристик. На этом сайте это обычно выбор процессора: «Slow [+0.00]».
$("input.qty-input").setValue("2"); — Ты стираешь «1» и вписываешь, что хочешь купить две штуки.
$("input.add-to-cart-button").click(); — Жмешь главную кнопку «Добавить в корзину».
        4. Проверка «быстрой реакции»
$(".bar-notification.success").shouldBe(visible); — Ты ждешь, когда сверху выскочит зеленая плашка: «The product has been added to your shopping cart».
$("span.cart-qty").shouldHave(text("(2)")); — Ты бросаешь взгляд наверх, на иконку корзины, и проверяешь, что счетчик сменился на (2).
        5. Финальный аудит (В корзине)
$("span.cart-label").click(); — Ты переходишь в саму корзину.
Твои финальные проверки: Ты сверяешь данные в таблице корзины с теми, что «записала в блокнот» в начале:
Имя товара то же?
Цена за штуку не изменилась?
Количество равно 2? (Тут мы применили мой .val() и assertEquals).
Итог: Сайт правильно умножил 800 на 2 и выдал 1600 в колонке Subtotal?
 */