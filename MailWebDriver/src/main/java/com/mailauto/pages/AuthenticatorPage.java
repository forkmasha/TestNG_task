package com.mailauto.pages;

import org.openqa.selenium.By;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;

public class AuthenticatorPage extends BasePage {
    public AuthenticatorPage addNewAuthenticator(String name, String description, String code) { 
        return openAdd2FA()
            .enterName(name)
            .enterDescription(description)
            .enterCode(code)
            .confirmAdd();
    }

    private AuthenticatorPage openAdd2FA() {
        $("a[data-target='#auth_ac'").click();
        return this;
    }

    private AuthenticatorPage enterName(String name) {
        $(byId("auth_id")).sendKeys(name);
        return this;
    }

    private AuthenticatorPage enterDescription(String description) {
        $(byId("auth_desc")).sendKeys(description);
        return this;
    }

    private AuthenticatorPage enterCode(String code) {
        $(byId("auth_code")).sendKeys(code);
        return this;
    }

    private AuthenticatorPage confirmAdd() {
        $(By.xpath("//button[contains(text(), '+Add Generator')]")).click();
        return this;
    }

    public Boolean is2FAAdded(String name){
        try {
            $(By.xpath("//td[contains(text(), '" + name + "')]")).shouldBe(visible, TEN_SECONDS);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public AuthenticatorPage removeAuthConfig() {
        $("button[data-target='#auth_del_ac']").click();
        $("button[class='btn']").shouldBe(visible, TEN_SECONDS).click();
        $(By.xpath("//div[contains(text(), 'Enable 2-Factor Authentication')]"))
        .shouldBe(visible, TEN_SECONDS);
        return this;
    }
}
