package ru.ivanbulgakov.demowebshop.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class WsWelcomePage {
    private final SelenideElement registerButtоn = $("a.ico-register");
    private final SelenideElement openLogin = $("a.ico-login");
    private final ElementsCollection headerLinks = $$("div.header-links ul li a");
    private final ElementsCollection topMenuLinks = $$("ul.top-menu li a");
    private final SelenideElement anySubCategoryLink = $(byText("Desktops"));
    private final ElementsCollection productItems = $$("div.product-grid div");

    public WsRegistrationPage openRegistration() {
        registerButtоn.click();
        return new WsRegistrationPage();
    }

    public WsLoginPage openLogIn() {
        openLogin.click();
        return new WsLoginPage();
    }

    public String getLoggedInUserEmail() {
        return headerLinks.get(0).text();
    }

    public WsWelcomePage hoverCategory(int index) {
        topMenuLinks.get(index).hover();
        return this;
    }

    public WsWelcomePage clickSubCategory(String categoryName) {
        anySubCategoryLink.click();
        return this;
    }

    public WsProductPage openProductByIndex(int index) {
        productItems.get(index).click();
        return new WsProductPage();
    }

}
