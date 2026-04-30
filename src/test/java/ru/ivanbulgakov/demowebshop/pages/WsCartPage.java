package ru.ivanbulgakov.demowebshop.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class WsCartPage {
    private final SelenideElement productName = $("a.product-name");
    private final SelenideElement productPrice = $("span.product-unit-price");
    private final SelenideElement qtyInput = $("input.qty-input");
    private final SelenideElement subtotalLabel = $("span.product-subtotal");

    public String getItemName() {
        return productName.text();
    }

    public String getProductPrice() {
        return productPrice.text();
    }

    public String getQuantity() {
        return qtyInput.val();
    }

    public String getSubtotal() {
        return subtotalLabel.text();
    }
}