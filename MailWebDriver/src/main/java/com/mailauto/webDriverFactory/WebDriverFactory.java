package com.mailauto.webDriverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Objects;
import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverFactory {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private static WebDriverFactory instance;
    private WebDriverFactory() { }

    public static synchronized WebDriverFactory getInstance() {
        if (instance == null) {
            instance = new WebDriverFactory();
        }
        return instance;
    }

    public void setDriver(BrowserType browser) {
        WebDriver webDriver;
        switch (browser) {
            case FIREFOX:
                webDriver = getFirefoxDriver();
                break;
            case EDGE:
                webDriver = getEdgeDriver();
                break;
            case CHROME:
            default:
                webDriver = getChromeDriver();
                break;
        }
        webDriver.manage().window().maximize();
        driver.set(Objects.requireNonNull(webDriver));
    }

    private WebDriver getChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        return new HighlightingChromeDriver(options);
    }

    private WebDriver getEdgeDriver() {
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--remote-allow-origins=*");
        return new HighlightingEdgeDriver(options);
    }

    private WebDriver getFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    }

    public WebDriver getDriver(){
        return Objects.requireNonNull(driver.get());
    }

    public void closeBrowser() {
        getDriver().quit();
        driver.remove();
    }
}