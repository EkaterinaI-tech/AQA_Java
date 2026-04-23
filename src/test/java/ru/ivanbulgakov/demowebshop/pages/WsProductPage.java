package ru.ivanbulgakov.demowebshop.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

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

    public String getItemName() {
        return productNameLabel.getText();
    }

    public String getItemPrice() {
        return productPriceLabel.getText();
    }

    public WsProductPage selectFirstOption() {
        processorOptions.get(0).$$("input").get(0).click();
        return this;
    }

    public WsProductPage setQuantity(String itemQuantity) {
        quantityInput.setValue(itemQuantity);
        return this;
    }

    public WsProductPage addToCart() {
        addToCartButton.click();
        return this;
    }

    public WsProductPage verifySuccessNotificationVisible() {
        successNotification.shouldBe(visible);
        return this;
    }

    public WsProductPage verifyCartQuantity(String itemQuantity) {
        cartQuantityCounter.shouldHave(text("(" + itemQuantity + ")"));
        return this;
    }

    public WsCartPage goToCart() {
        cartLink.click();
        return new WsCartPage();
    }
}
