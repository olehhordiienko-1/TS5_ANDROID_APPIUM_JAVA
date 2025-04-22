package com.example.appiumtesting.task2.pages;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LoginPage extends BasePage {

    public LoginPage(AndroidDriver driver) {
        super(driver);
    }

    public void enterUsername(String username) {
        WebElement usernameField = driver.findElement(By.id("com.saucelabs.mydemoapp.android:id/nameET"));
        usernameField.click();
        usernameField.clear();
        usernameField.sendKeys(username);
    }

        public void enterPassword(String password) {
        WebElement passwordField = driver.findElement(By.id("com.saucelabs.mydemoapp.android:id/passwordET"));
        passwordField.click();
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickLogin() {
        WebElement loginButton = driver.findElement(By.id("com.saucelabs.mydemoapp.android:id/loginBtn"));
        loginButton.click();
    }

    public String getNameErrorMessage() {
        WebElement errorMessage = driver.findElement(By.id( "com.saucelabs.mydemoapp.android:id/nameErrorTV"));
        return errorMessage.getText();
    }
    public String getPasswordErrorMessage() {
        WebElement errorMessage = driver.findElement(By.id( "com.saucelabs.mydemoapp.android:id/passwordErrorTV"));
        return errorMessage.getText();
    }

    public void login(String username, String password) {
        boolean alreadyOnLoginPage = false;
        try {
            WebElement login = driver.findElement(By.id("com.saucelabs.mydemoapp.android:id/loginTV"));
            alreadyOnLoginPage = login != null;
        } catch (Exception e) {
            //ignore
        }

        if (!alreadyOnLoginPage) {
            List<WebElement> elements = driver.findElements(By.id("//android.widget.TextView[@resource-id=\"com.saucelabs.mydemoapp.android:id/itemTV\" and @text=\"Log In\"]"));
            if (!elements.isEmpty()) {
                WebElement menu = elements.get(0);
                menu.click();
            } else {
                System.out.println("Елемент меню не знайдено");
            }
            try {
                logout();
            } catch (NoSuchElementException e) {
                // ignore
            }
            WebElement menu = driver.findElement(By.id("com.saucelabs.mydemoapp.android:id/menuIV"));
            menu.click();

            WebElement login = driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.saucelabs.mydemoapp.android:id/itemTV\" and @text=\"Log In\"]"));
            login.click();
        }

        enterUsername(username);
        enterPassword(password);
        clickLogin();

    }

    public void logout() {
        WebElement menu = driver.findElement(By.id("com.saucelabs.mydemoapp.android:id/menuIV"));
        menu.click();
        WebElement logout = driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"Logout Menu Item\"]"));
        logout.click();
        WebElement button1 = driver.findElement(By.id("android:id/button1"));
        button1.click();
    }
}
