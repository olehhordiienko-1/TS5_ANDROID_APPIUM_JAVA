package com.example.appiumtesting.task2;

import junit.framework.TestCase;
import androidx.annotation.NonNull;

import com.example.appiumtesting.task2.pages.CartPage;
import com.example.appiumtesting.task2.pages.CheckoutPage;
import com.example.appiumtesting.task2.pages.LoginPage;
import com.example.appiumtesting.task2.pages.ProductPage;
import com.example.appiumtesting.task2.pages.ProductsPage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;

public class ComplexTest extends TestCase {

    private AndroidDriver driver;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        DesiredCapabilities caps = getDesiredCapabilities();

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), caps);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @NonNull
    private static DesiredCapabilities getDesiredCapabilities() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("deviceName", "Android Emulator");
        caps.setCapability("automationName", "UiAutomator2");

        caps.setCapability("app", "C:\\Users\\OLEH-HOME\\AppData\\Local\\Android\\Sdk\\platform-tools\\mda-2.2.0-25.apk");


        caps.setCapability("appPackage", "com.saucelabs.mydemoapp.android");
        caps.setCapability("appActivity", "com.saucelabs.mydemoapp.android.view.activities.MainActivity");

        caps.setCapability("autoGrantPermissions", true);
        caps.setCapability("unicodeKeyboard", true);
        caps.setCapability("resetKeyboard", true);
        caps.setCapability("noReset", true);


        return caps;
    }

    public void testCompletePurchase() {
        // Логін (якщо потрібно)
//        LoginPage loginPage = new LoginPage(driver);
//        loginPage.login("bod@example.com", "10203040");

        ProductsPage catalog = new ProductsPage(driver);
        catalog.openCatalog();

        ProductPage backpack = new ProductPage(driver);
        backpack.selectBackpackProduct();
        backpack.addToCart();
        driver.pressKey(new KeyEvent(AndroidKey.BACK));

        catalog.scrollDown();

        ProductPage productPage = new ProductPage(driver);
        productPage.selectTshirtProduct();
        productPage.addToCart();

        productPage.openCart();

        // Перехід до оформлення
        CartPage cartPage = new CartPage(driver);
        cartPage.proceedToCheckout();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("bod@example.com", "10203040");

        // Оплата
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.fillRequiredFields(
                "Rebecca Winter",
                "Mandorley 112",
                "Truro",
                "89750",
                "United Kingdom"
        );
        checkoutPage.enterAddressLine2("Entrance 1");
        checkoutPage.enterState("Cornwall");

        // Перехід до оплати
        checkoutPage.proceedToPayment();

        checkoutPage.enterName("John Doe");
        checkoutPage.enterCardNumber("4111111111111111");
        checkoutPage.enterExpirationDate("0427");
        checkoutPage.enterSecurityCode("689");
        checkoutPage.placeOrder();
        checkoutPage.placeOrder();

        assertTrue(checkoutPage.getCompleteMessage().toLowerCase().contains("checkout complete"));
    }

    public void testLoginWithDifferentCredentials() {

        ProductsPage catalog = new ProductsPage(driver);
        catalog.openCatalog();

        ProductPage backpack = new ProductPage(driver);
        backpack.selectBackpackProduct();
        backpack.addToCart();
        driver.pressKey(new KeyEvent(AndroidKey.BACK));

        catalog.scrollDown();

        ProductPage productPage = new ProductPage(driver);
        productPage.selectTshirtProduct();
        productPage.addToCart();

        productPage.openCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.proceedToCheckout();

        LoginPage loginPage = new LoginPage(driver);

        // Валідні дані

//        assertFalse(loginPage.getErrorMessage().toLowerCase().contains("error"));

        // Невалідний email
        loginPage.login("alice@example.com", "10203040");
        assertTrue(loginPage.getPasswordErrorMessage().toLowerCase().contains("locked out"));

        // Невалідний пароль
        loginPage.login("", "wrong");
        assertTrue(loginPage.getNameErrorMessage().toLowerCase().contains("username"));

        // Пусті поля
        loginPage.login("abcd", "");
        assertTrue(loginPage.getPasswordErrorMessage().toLowerCase().contains("password"));

        loginPage.login("bod@example.com", "10203040");
//        assertTrue(new ProductsPage(driver).isProductsPageDisplayed());
    }

    public void testCheckoutWithDifferentFieldCombinations() {
        ProductsPage catalog = new ProductsPage(driver);
        catalog.openCatalog();

        ProductPage backpack = new ProductPage(driver);
        backpack.selectBackpackProduct();
        backpack.addToCart();
        driver.pressKey(new KeyEvent(AndroidKey.BACK));

        catalog.scrollDown();

        ProductPage productPage = new ProductPage(driver);
        productPage.selectTshirtProduct();
        productPage.addToCart();

        productPage.openCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.proceedToCheckout();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("bod@example.com", "10203040");

        CheckoutPage checkoutPage = new CheckoutPage(driver);

        checkoutPage.fillRequiredFields("John Doe", "123 Main St", "Kyiv", "02000", "Ukraine");
        checkoutPage.proceedToPayment();
        assertTrue(checkoutPage.checkout2Opened());
        driver.pressKey(new KeyEvent(AndroidKey.BACK));

        checkoutPage.fillRequiredFields("", "123 Main St", "Kyiv", "02000", "Ukraine");
        checkoutPage.proceedToPayment();
        assertTrue(checkoutPage.getFullNameError().toLowerCase().contains("please"));

        checkoutPage.fillRequiredFields("John Doe", "", "Kyiv", "02000", "Ukraine");
        checkoutPage.proceedToPayment();
        assertTrue(checkoutPage.getAddressLine1Error().toLowerCase().contains("please"));

        checkoutPage.fillRequiredFields("John Doe", "123 Main St", "", "02000", "Ukraine");
        checkoutPage.proceedToPayment();
        assertTrue(checkoutPage.getAddressLine1Error().toLowerCase().contains("please"));
        // Тут потрібно додати перевірку помилки для міста, якщо вона є в додатку
    }

    public void testPaymentFieldBoundaries() {
        ProductsPage catalog = new ProductsPage(driver);
        catalog.openCatalog();

        ProductPage backpack = new ProductPage(driver);
        backpack.selectBackpackProduct();
        backpack.addToCart();
        driver.pressKey(new KeyEvent(AndroidKey.BACK));

        catalog.scrollDown();

        ProductPage productPage = new ProductPage(driver);
        productPage.selectTshirtProduct();
        productPage.addToCart();

        productPage.openCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.proceedToCheckout();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("bod@example.com", "10203040");

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.fillRequiredFields(
                "Rebecca Winter",
                "Mandorley 112",
                "Truro",
                "89750",
                "United Kingdom"
        );
        checkoutPage.enterAddressLine2("Entrance 1");
        checkoutPage.enterState("Cornwall");

        checkoutPage.proceedToPayment();

        checkoutPage.enterCardNumber("4111111111111111"); // Валідний номер (16 цифр)
        checkoutPage.enterCardNumber("411111111111111");  // Невалідний (15 цифр)
        checkoutPage.enterCardNumber("");                // Пусте поле
        checkoutPage.placeOrder();

        checkoutPage.enterSecurityCode("123");  // Валідний (3 цифри)
        checkoutPage.enterSecurityCode("12");   // Невалідний (2 цифри)
        checkoutPage.placeOrder();

        checkoutPage.enterZipCode("02000");     // Валідний (5 цифр)
        checkoutPage.enterZipCode("0200");      // Невалідний (4 цифри) - очікуємо помилку
        checkoutPage.enterZipCode("020000");    // Невалідний (6 цифр) - очікуємо помилку
        checkoutPage.enterZipCode("02A00");     // Невалідний (букви) - очікуємо помилку
        checkoutPage.placeOrder();

        checkoutPage.enterExpirationDate("1225"); // Валідний (грудень 2025)
        checkoutPage.enterExpirationDate("1325"); // Невалідний (місяць 13)
        checkoutPage.enterExpirationDate("012");  // Невалідний (неповний формат)
        checkoutPage.placeOrder();
    }

    @Override
    protected void tearDown() throws Exception {
        if (driver != null) {
            driver.quit();
        }
        super.tearDown();
    }
}
