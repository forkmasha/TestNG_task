package com.mailauto.pages;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;

public class MainPage extends BasePage {

    public String getPrivateTeamInbox() {
        return $(byText("Private Team Inbox")).getText();
    }

    public String getUserEmail() {
        return $("a[href*='profile']").shouldBe(visible, TEN_SECONDS).getText();
    }

    public MessageRulesPage openMessageRules() {
        $("a[href*='rules']").shouldBe(visible, TEN_SECONDS).click();
        return page(MessageRulesPage.class);
    }

    public AuthenticatorPage openAuthenticator() {
        $("a[href*='authenticator']").shouldBe(visible, TEN_SECONDS).click();
        return new AuthenticatorPage();
    }

    public TeamSettingsPage openTeamSettings() {
        $("a[href*='team_settings']").shouldBe(visible, TEN_SECONDS).click();
        return page(TeamSettingsPage.class);
    }
}
