package com.mailauto.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.visible;

public class MessageRulesPage extends BasePage {
    public MessageRulesPage addNewMessageRule(String ruleName, String description, String priority, String condition) {
        return openCreateRules()
            .enterRuleName(ruleName)
            .enterDescription(description)
            .enterPriority(priority)
            .enterCondition(condition)
            .selectDropAction()
            .confirmAddRule();
    }
    
    private MessageRulesPage openCreateRules() {
        $(By.xpath("//div[@class='wrapper-buttons']/a"))
        .shouldBe(visible, TEN_SECONDS).click();
        return this;
    }

    private MessageRulesPage enterRuleName(String ruleName) {
        $(byId("rulename"))
        .shouldBe(visible, TEN_SECONDS).sendKeys(ruleName);
        return this;
    }

    private MessageRulesPage enterDescription(String description) {
        $(byId("ruledesc")).sendKeys(description);
        return this;
    }

    private MessageRulesPage enterPriority(String priority) {
        $(byId("priority")).sendKeys(priority);
        return this;
    }

    private MessageRulesPage enterCondition(String condition) {
        $(byId("cond1_input")).sendKeys(condition);
        return this;
    }

    private MessageRulesPage selectDropAction() {
        $(byId("action_sel99"))
        .shouldBe(visible, TEN_SECONDS)
        .selectOptionContainingText("DROP");
        return this;
    }

    public Boolean isRuleAdded(String description){
        try {
            $(By.xpath("//span[contains(text(), '" + description + "')]"))
            .shouldBe(visible, TEN_SECONDS);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public MessageRulesPage removeRule() {
        By removeButton = By.xpath("//button[@data-target='#rule_dm']");
        scrollToElement(removeButton);
        $(removeButton).click();
        $(By.xpath("//button[@class='btn']"))
        .shouldBe(visible, TEN_SECONDS).click();
        return this;
    }

    private MessageRulesPage confirmAddRule() {
        By xpath = By.xpath("//button[contains(text(), '+Add Rule')]");
        scrollToElement(xpath);
        $(xpath).shouldBe(visible, TEN_SECONDS).click();
        return this;
    }

    private void scrollToElement(By xpath) {
        ((JavascriptExecutor)getDriver()).executeScript("arguments[0].scrollIntoView(true);",
        getDriver().findElement(xpath));
    }
}
