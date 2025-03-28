package com.mailauto.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import com.mailauto.webDriverFactory.WebDriverFactory;

public abstract class BasePage {

    protected static final Duration TEN_SECONDS = Duration.ofSeconds(10);

    public BasePage() {
        var driver = WebDriverFactory.getInstance().getDriver();
        PageFactory.initElements(driver, this);
    }

    public WebDriver getDriver() {
        return WebDriverFactory.getInstance().getDriver();
    }
}