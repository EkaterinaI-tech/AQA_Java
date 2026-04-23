package ru.ivanbulgakov.demowebshop.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class WsCartPage {
    private final SelenideElement productName = $("a.product-name");
    private final SelenideElement productPrice = $("span.product-unit-price");
    private final SelenideElement qtyInput = $("input.qty-input");
    private final SelenideElement subtotalLabel = $("span.product-subtotal");

    public WsCartPage verifyProductName(String expectedName) {
        productName.shouldHave(text(expectedName));
        return this;
    }

    public WsCartPage verifyProductPrice(String expectedPrice) {
        productPrice.shouldHave(text(expectedPrice));
        return this;
    }

    public String getActualQuantity() {
        return qtyInput.val();
    }

    public void verifySubtotal(String expectedSubtotal) {
        subtotalLabel.shouldHave(text(expectedSubtotal));
    }
}

/*
$("a.product-name").shouldHave(text(itemName));
        $("span.product-unit-price").shouldHave(text(itemPrice));
        String actualQuantity = $("input.qty-input").val();
        assertEquals(itemQuantity, actualQuantity);
        $("span.product-subtotal").shouldHave(text(String.valueOf(
                Float.parseFloat(itemPrice) * Float.parseFloat(itemQuantity))));
 */
