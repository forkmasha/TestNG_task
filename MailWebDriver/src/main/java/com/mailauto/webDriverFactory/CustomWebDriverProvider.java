package com.mailauto.webDriverFactory;

import com.codeborne.selenide.WebDriverProvider;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

public class CustomWebDriverProvider implements WebDriverProvider {

    @Override
    public WebDriver createDriver(Capabilities capabilities) {
        return WebDriverFactory.getInstance().getDriver();
    }
}