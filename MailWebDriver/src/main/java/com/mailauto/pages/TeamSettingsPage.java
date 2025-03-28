package com.mailauto.pages;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selectors.byId;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;

public class TeamSettingsPage extends BasePage {

    public TeamSettingsPage() { }

    public TeamSettingsPage setTeamName(String teamName) {
        clickSetNameButton();
        setTeamNameInput(teamName);
        clickSaveNameButton();
        return this;
    }

    private void clickSaveNameButton() {
        $(By.xpath("//button[contains(text(), 'Save')]"))
        .click();
    }

    private void setTeamNameInput(String teamName) {
        $(byId("new-teamname-field"))
        .shouldBe(visible, Duration.ofSeconds(20))
        .sendKeys(teamName);
    }

    private void clickSetNameButton() {
        clickButtonWithRetry(By.xpath("//button[contains(text(), 'Set Team Name')]"));
    }

    public String getTeamName() {
        return $(By.xpath("//div[contains(@class, 'wrapper-primary-input')]/p"))
        .shouldBe(visible, Duration.ofSeconds(20)).getText();
    }
    

    private void clickButtonWithRetry(By by) {
        int retries = 3;
        while (retries > 0) {
            try {
                $(by).shouldBe(clickable, TEN_SECONDS).click();
                return;
            } catch (StaleElementReferenceException e) {
                retries--;
            }
        }
        throw new StaleElementReferenceException("Element is not clickable");
    }
}
