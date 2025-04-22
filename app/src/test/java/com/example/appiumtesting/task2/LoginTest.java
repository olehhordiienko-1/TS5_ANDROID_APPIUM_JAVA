package com.example.appiumtesting.task2;

import androidx.annotation.NonNull;

import com.example.appiumtesting.task2.pages.LoginPage;
import com.example.appiumtesting.task2.pages.ProductsPage;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.TestCase;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;

public class LoginTest extends TestCase {
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

    public void testSuccessfulLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.logout();
        loginPage.login("bod@example.com", "10203040");

        ProductsPage productsPage = new ProductsPage(driver);
        assertTrue("Products page should be displayed", productsPage.isProductsPageDisplayed());

    }

    public void testInvalidLogin() {
        LoginPage loginPage = new LoginPage(driver);
//        loginPage.logout();
        loginPage.login("alice@example.com", "qwe");

        assertTrue(loginPage.getPasswordErrorMessage().toLowerCase().contains("sorry this user"));
    }

    @Override
    protected void tearDown() throws Exception {
        if (driver != null) {
            driver.quit();
        }
        super.tearDown();
    }
}
