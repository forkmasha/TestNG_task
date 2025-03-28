package com.mailauto.webDriverFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class HighlightingEdgeDriver extends EdgeDriver {

    public HighlightingEdgeDriver(EdgeOptions options) {
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
