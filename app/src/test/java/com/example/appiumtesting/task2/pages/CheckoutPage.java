package com.example.appiumtesting.task2.pages;

import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CheckoutPage extends BasePage {
    @FindBy(id = "com.saucelabs.mydemoapp.android:id/nameET") // Поле для імені
    private WebElement nameField;

    @FindBy(id = "com.saucelabs.mydemoapp.android:id/cardNumberET") // Поле для номера картки
    private WebElement cardNumberField;

    @FindBy(id = "com.saucelabs.mydemoapp.android:id/expirationDateET") // Поле для номера картки
    private WebElement expirationDate;

    @FindBy(id = "com.saucelabs.mydemoapp.android:id/securityCodeET") // Поле для номера картки
    private WebElement securityCode;

    @FindBy(id = "com.saucelabs.mydemoapp.android:id/paymentBtn") // Кнопка "Place Order"
    private WebElement placeOrderButton;

    @FindBy(id = "com.saucelabs.mydemoapp.android:id/confirmationTV") // Підтвердження замовлення
    private WebElement confirmationMessage;

    // Поля форми
    @FindBy(id = "com.saucelabs.mydemoapp.android:id/fullNameET")
    private WebElement fullNameField;

    @FindBy(id = "com.saucelabs.mydemoapp.android:id/address1ET")
    private WebElement addressLine1Field;

    @FindBy(id = "com.saucelabs.mydemoapp.android:id/address2ET")
    private WebElement addressLine2Field;

    @FindBy(id = "com.saucelabs.mydemoapp.android:id/cityET")
    private WebElement cityField;

    @FindBy(id = "com.saucelabs.mydemoapp.android:id/stateET")
    private WebElement stateField;

    @FindBy(id = "com.saucelabs.mydemoapp.android:id/zipET")
    private WebElement zipCodeField;

    @FindBy(id = "com.saucelabs.mydemoapp.android:id/countryET")
    private WebElement countryField;

    // Кнопки
    @FindBy(id = "com.saucelabs.mydemoapp.android:id/paymentBtn")
    private WebElement toPaymentButton;

    // Валідаційні повідомлення
    @FindBy(id = "com.saucelabs.mydemoapp.android:id/fullNameErrorTV")
    private WebElement fullNameError;

    @FindBy(id = "com.saucelabs.mydemoapp.android:id/address1ErrorTV")
    private WebElement addressLine1Error;

    @FindBy(id = "com.saucelabs.mydemoapp.android:id/cityErrorTV")
    private WebElement cityError;

    @FindBy(id = "com.saucelabs.mydemoapp.android:id/completeTV")
    private WebElement complete;

    public CheckoutPage(AndroidDriver driver) {
        super(driver);
    }

    public void enterName(String name) {
        nameField.sendKeys(name);
    }

    public void enterSecurityCode(String securityCode) {
        this.securityCode.sendKeys(securityCode);
    }

    public void enterExpirationDate(String expirationDate) {
        this.expirationDate.sendKeys(expirationDate);
    }
    public void enterCardNumber(String cardNumber) {
        cardNumberField.sendKeys(cardNumber);
    }

    public void placeOrder() {
        placeOrderButton.click();
    }


    public String getCompleteMessage() {
        return complete.getText();
    }

    public void enterFullName(String name) {
        fullNameField.clear();
        fullNameField.sendKeys(name);
    }

    public void enterAddressLine1(String address) {
        addressLine1Field.clear();
        addressLine1Field.sendKeys(address);
    }

    public void enterAddressLine2(String address) {
        addressLine2Field.clear();
        addressLine2Field.sendKeys(address);
    }

    public void enterCity(String city) {
        cityField.clear();
        cityField.sendKeys(city);
    }

    public void enterState(String state) {
        stateField.clear();
        stateField.sendKeys(state);
    }

    public void enterZipCode(String zip) {
        zipCodeField.clear();
        zipCodeField.sendKeys(zip);
    }

    public void enterCountry(String country) {
        countryField.clear();
        countryField.sendKeys(country);
    }

    // Перехід на сторінку оплати
    public void proceedToPayment() {
        toPaymentButton.click();
    }

    // Перевірка валідації
    public String getFullNameError() {
        return fullNameError.getText();
    }

    public String getAddressLine1Error() {
        return addressLine1Error.getText();
    }

    public String getCityError() {
        return addressLine1Error.getText();
    }

    // Заповнення всіх обов'язкових полів
    public void fillRequiredFields(String name, String address, String city, String zip, String country) {
        enterFullName(name);
        enterAddressLine1(address);
        enterCity(city);
        enterZipCode(zip);
        enterCountry(country);
    }

    public boolean checkout1Opened() {
        boolean result = false;
        List<WebElement> elements = driver.findElements(By.id("com.saucelabs.mydemoapp.android:id/fullNameTV"));
        for (WebElement e : elements) {
            result = e.isDisplayed();
        }
        return result;
    }
    public boolean checkout2Opened() {
        boolean result = false;
        List<WebElement> elements = driver.findElements(By.id("com.saucelabs.mydemoapp.android:id/nameTV"));
        for (WebElement e : elements) {
            result = e.isDisplayed();
        }
        return result;
    }
}
