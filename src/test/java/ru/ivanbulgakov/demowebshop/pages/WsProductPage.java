package ru.ivanbulgakov.demowebshop.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class WsProductPage {
    private final SelenideElement productNameLabel = $("[itemprop=name]");
    private final SelenideElement productPriceLabel = $("[itemprop=price]");
    private final ElementsCollection processorOptions = $$("dl dd ul li");
    private final SelenideElement quantityInput = $("input.qty-input");
    private final SelenideElement addToCartButton = $("input.add-to-cart-button");
    private final SelenideElement successNotification = $(".bar-notification.success");
    private final SelenideElement cartQuantityCounter = $("span.cart-qty");
    private final SelenideElement cartLink = $("span.cart-label");

    @Step("Получить название товара")
    public String getItemName() {

        return productNameLabel.getText();
    }

    @Step("Получить цену товара")
    public String getItemPrice() {

        return productPriceLabel.getText();
    }

    @Step("Выбрать тип процессора под номером {index}")
    public WsProductPage selectProcessor(int index) {
        processorOptions.get(index).$("input").click();
        return this;
    }

    @Step("Установить количество товара: {itemQuantity}")
    public WsProductPage setQuantity(String itemQuantity) {
        quantityInput.setValue(itemQuantity);
        return this;
    }

    @Step("Нажать кнопку Добавить в корзину")
    public WsProductPage addToCart() {
        addToCartButton.click();
        return this;
    }

    @Step("Проверить, что появилось уведомление об успешном добавлении")
    public WsProductPage verifySuccessNotificationVisible() {
        successNotification.shouldBe(visible);
        return this;
    }

    @Step("Проверить, что в корзине отображается количество: {expectedQuantity}")
    public WsProductPage verifyCartQuantity(String itemQuantity) {
        cartQuantityCounter.shouldHave(text("(" + itemQuantity + ")"));
        return this;
    }

    @Step("Перейти в корзину")
    public WsCartPage goToCart() {
        cartLink.click();
        return new WsCartPage();
    }
}
