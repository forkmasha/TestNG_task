package com.mailauto.webDriverFactory;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class HighlightingChromeDriver extends ChromeDriver {

    public HighlightingChromeDriver(ChromeOptions options) {
        super(options);
    }

    @Override
    public WebElement findElement(By by) {
        WebElement element = super.findElement(by);
        highlightElement(element);
        return element;
    }

    @Override
    public void get(String url) {
        super.get(url);
    }

    private void highlightElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) this;
        js.executeScript("arguments[0].style.border='3px solid red'", element);
    }
}