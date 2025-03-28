package com.mailauto;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestListener implements ITestListener {

    private static final Logger logger = LogManager.getLogger(TestListener.class);

    @Override
    public void onTestFailure(ITestResult result) {
        Object testClass = result.getInstance();
        WebDriver driver = ((BaseTests)testClass).getDriver();

        if (driver != null) {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                Files.createDirectories(Paths.get("screenshots"));
                
                String timestamp = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss").format(new java.util.Date());
                String screenshotName = result.getName() + "_" + timestamp + ".png";
                Files.copy(screenshot.toPath(), Paths.get("screenshots", screenshotName));
                logger.error("Screenshot saved at: " + Paths.get("screenshots", screenshotName).toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onStart(ITestContext context) {
        logger.info("Test Suite started: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("Test Suite is ending: " + context.getName());
    }
}