package com.example.appiumtesting.task1;

import io.appium.java_client.android.AndroidDriver;
import junit.framework.TestCase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;

public class FirstTaskTest extends TestCase {
    private AndroidDriver driver;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        DesiredCapabilities caps = getDesiredCapabilities();
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), caps);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public void testAccessibilityElementIsDisplayed() {
        WebElement element = driver.findElement(By.xpath("//android.widget.TextView[@text='Accessibility']"));

        assertTrue("Accessibility element should be displayed", element.isDisplayed());
        assertEquals("Accessibility", element.getText());
    }

    @Override
    protected void tearDown() throws Exception {
        if (driver != null) {
            driver.quit();
        }
        super.tearDown();
    }

    private DesiredCapabilities getDesiredCapabilities() {
        DesiredCapabilities caps = new DesiredCapabilities();

        caps.setCapability("platformName", "Android");
        caps.setCapability("deviceName", "Medium_Phone_API_36");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("app", "C:\\Users\\OLEH-HOME\\AppData\\Local\\Android\\Sdk\\platform-tools\\ApiDemos-debug.apk");
        caps.setCapability("appium:androidSdkPath", "C:\\Users\\OLEH-HOME\\AppData\\Local\\Android\\Sdk");
        caps.setCapability("appium:avd", "Medium_Phone_API_36");

        return caps;
    }
}