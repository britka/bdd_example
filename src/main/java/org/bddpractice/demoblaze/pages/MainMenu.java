package org.bddpractice.demoblaze.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.bddpractice.demoblaze.enums.MenuItem;

import static com.codeborne.selenide.Selenide.$;

/**
 * @author sbryt on 22/03/2021
 */
public class MainMenu {

    SelenideElement mainMenuRootElement = $(".header-links");

    public <T extends BasePage> T selectMenuItem(MenuItem menuItem, Class<T> pageToReturn) {
        mainMenuRootElement.$(".ico-" + menuItem.getValue()).click();
        try {
            return pageToReturn.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public LoginPage clickLoginMenuItem(){
        return selectMenuItem(MenuItem.LOG_IN, LoginPage.class);
    }

    public boolean isMenuItemVisible(MenuItem menuItem) {
        return mainMenuRootElement.$(".ico-" + menuItem.getValue()).is(Condition.visible);
    }

}
