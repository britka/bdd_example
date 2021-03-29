package org.bddpractice.demoblaze.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

/**
 * @author sbryt on 22/03/2021
 */
public class LoginPage extends BasePage {
    SelenideElement rootElement = $(".page.login-page");
    SelenideElement loginFormRootElement = rootElement.$(".fieldset");

    public <T extends BasePage> T login(String userName, String userPass, Class<T> pageToReturn) {
        loginFormRootElement.$("#Email").setValue(userName);
        loginFormRootElement.$("#Password").setValue(userPass);
        loginFormRootElement.$(" .login-button").click();
        return returnPage(pageToReturn);
    }

    public BasePage login(String userName, String userPass) {
        loginFormRootElement.$("#Email").setValue(userName);
        loginFormRootElement.$("#Password").setValue(userPass);
        loginFormRootElement.$(" .login-button").click();
        return returnPage(BasePage.class);
    }

    public RegisterPage clickRegisterButton(){
        rootElement.$(".register-button").click();
        return returnPage(RegisterPage.class);
    }
}
