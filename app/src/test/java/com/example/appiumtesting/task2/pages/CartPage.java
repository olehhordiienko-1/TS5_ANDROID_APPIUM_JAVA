package com.example.appiumtesting.task2.pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage {
    @FindBy(id = "com.saucelabs.mydemoapp.android:id/titleTV") // Назва продукту в корзині
    private WebElement productTitle;

    @FindBy(id = "com.saucelabs.mydemoapp.android:id/priceTV") // Ціна продукту
    private WebElement productPrice;

    @FindBy(id = "com.saucelabs.mydemoapp.android:id/cartBt") // Кнопка "Proceed to Checkout"
    private WebElement checkoutButton;

    public CartPage(AndroidDriver driver) {
        super(driver);
    }

    public String getProductTitle() {
        return productTitle.getText();
    }

    public String getProductPrice() {
        return productPrice.getText();
    }

    public void proceedToCheckout() {
        checkoutButton.click();
    }
}
