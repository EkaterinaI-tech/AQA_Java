package ru.ivanbulgakov.demowebshop.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class WsWelcomePage {
    private final SelenideElement registerButton = $("a.ico-register");
    private final SelenideElement openLogin = $("a.ico-login");
    private final ElementsCollection headerLinks = $$("div.header-links ul li a");
    private final ElementsCollection topMenuLinks = $$("ul.top-menu li a");
    private final SelenideElement anySubCategoryLink = $(byText("Desktops"));
    private final ElementsCollection productItems = $$("div.product-grid div");

    @Step("Нажать на ссылку Register")
    public WsRegistrationPage openRegistration() {
        registerButton.click();
        return new WsRegistrationPage();
    }

    @Step("Нажать на ссылку Log in")
    public WsLoginPage openLogIn() {
        openLogin.click();
        return new WsLoginPage();
    }

    @Step("Получить email авторизованного пользователя")
    public String getLoggedInUserEmail() {
        return headerLinks.get(0).text();
    }

    @Step("Навести курсор на главную категорию меню под номером {index}")
    public WsWelcomePage hoverCategory(int index) {
        topMenuLinks.get(index).hover();
        return this;
    }

    @Step("Выбрать подкатегорию: {categoryName}")
    public WsWelcomePage clickSubCategory(String categoryName) {
        anySubCategoryLink.click();
        return this;
    }

    @Step("Открыть карточку товара под номером {index}")
    public WsProductPage openProductByIndex(int index) {
        productItems.get(index).click();
        return new WsProductPage();
    }
}
