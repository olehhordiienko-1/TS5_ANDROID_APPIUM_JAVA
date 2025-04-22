package com.example.appiumtesting.task2.pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends BasePage {
    @FindBy(id = "com.saucelabs.mydemoapp.android:id/productTV") // Заголовок сторінки
    private WebElement pageTitle;

    @FindBy(xpath = "//android.widget.ImageView[@content-desc=\"Sauce Labs Backpack\"]")
    private WebElement backpack;

    @FindBy(xpath = "//android.widget.ImageView[@content-desc=\"Test.sllTheThings() T-Shirt\"]")
    private WebElement tshirt;

    @FindBy(id = "com.saucelabs.mydemoapp.android:id/cartRL") // Іконка корзини
    private WebElement cartIcon;

    @FindBy(id = "com.saucelabs.mydemoapp.android:id/cartBt") // Кнопка "Add to Cart"
    private WebElement addToCartButton;

    public ProductPage(AndroidDriver driver) {
        super(driver);
    }

    public void selectBackpackProduct() {
        backpack.click();
    }
    public void selectTshirtProduct() {
        tshirt.click();
    }

    public void addToCart() {
        addToCartButton.click();
    }

    public void openCart() {
        cartIcon.click();
    }

    public boolean isProductPageDisplayed() {
        return pageTitle.isDisplayed();
    }
}
