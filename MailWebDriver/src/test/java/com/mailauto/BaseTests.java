package com.mailauto;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.mailauto.webDriverFactory.BrowserType;
import com.mailauto.webDriverFactory.WebDriverFactory;

public class BaseTests {

    @BeforeMethod
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        BrowserType browserType = BrowserType.valueOf(browser.toUpperCase());
        WebDriverFactory.getInstance().setDriver(browserType);
    }

    @AfterMethod
    public void tearDown() {
        WebDriverFactory.getInstance().closeBrowser();
    }

    public WebDriver getDriver() {
        return WebDriverFactory.getInstance().getDriver();
    }
}