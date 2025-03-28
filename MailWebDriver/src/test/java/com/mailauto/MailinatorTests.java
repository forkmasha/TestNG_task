package com.mailauto;

import com.codeborne.selenide.Configuration;
import com.mailauto.entities.User;
import com.mailauto.pages.LoginMailPage;
import com.mailauto.pages.MainPage;
import com.mailauto.webDriverFactory.CustomWebDriverProvider;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class MailinatorTests extends BaseTests {
    static {
        Configuration.browser = CustomWebDriverProvider.class.getName();
    }

    private MainPage mainPage;
    private ConfigReader config;
    private User user;

    @BeforeMethod
    public void performLoginToMail() {
        config = new ConfigReader(System.getProperty("env", "dev"));
        var loginMailPage = new LoginMailPage();
        user = config.getUser();
        mainPage = loginMailPage.login(user);
    }

    @Test
    public void loginToMail() {
        var actualEmail = mainPage.getUserEmail();
        assertEquals(user.getEmail(), actualEmail);
    }

    @Test
    public void createMailRule() {
        var expectedDesc = "Description 1";
        var messageRulesPage = mainPage.openMessageRules();
        messageRulesPage.addNewMessageRule("Rule1", expectedDesc, "1", "Condition1");

        assertTrue(messageRulesPage.isRuleAdded(expectedDesc));
        messageRulesPage.removeRule();
    }

    @Test
    public void add2FAAuthenticator() {
        var authName = "Auth1";
        var authPage = mainPage.openAuthenticator();
        authPage.addNewAuthenticator(authName, "Description1", "123456");

        assertTrue(authPage.is2FAAdded(authName));
        authPage.removeAuthConfig();
    }

    @Test
    public void changeTeamName() {
        var expectedTeamName = "Team1";
        var teamPage = mainPage.openTeamSettings();
        var originalTeamName = teamPage.getTeamName();

        teamPage.setTeamName(expectedTeamName);
        var actualTeamName = teamPage.getTeamName();

        assertEquals(expectedTeamName, actualTeamName);
        teamPage.setTeamName(originalTeamName);
    }
}