package com.mailauto.pages;

import static com.codeborne.selenide.Selenide.*;
import com.mailauto.entities.User;

public class LoginMailPage extends BasePage {
    private static final String MAIL_LOGIN_URL = "https://www.mailinator.com/v4/login.jsp";

    public MainPage login(User user) {
        openPage()
            .enterEmail(user.getEmail())
            .enterPassword(user.getPassword())
            .submitLoginInput();
        return new MainPage();
    }

    private LoginMailPage openPage() {
        open(MAIL_LOGIN_URL);
        return this;
    }

    private LoginMailPage enterEmail(String email) {
        $("input[type='text']").setValue(email);
        return this;
    }

    private LoginMailPage enterPassword(String password) {
        $("input[type='password']").setValue(password);
        return this;
    }

    private MainPage submitLoginInput() {
        $("a.btn.btn-default.submit").click();
        return page(MainPage.class);
    }
}
