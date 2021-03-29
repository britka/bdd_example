package org.bddpractice.demoblaze.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

/**
 * @author sbryt on 21/03/2021
 */
public class BasePage {
    public MainMenu mainMenu(){
        return new MainMenu();
    }

    public <T extends BasePage> T search(String searchCriteria, Class<T> clazz){
        SelenideElement searchForm = $(".small-search-box-form");
        searchForm.$("#small-searchterms").setValue(searchCriteria);
        searchForm.$("button").click();
        return returnPage(clazz);
    }

    protected <T extends BasePage> T returnPage(Class<T> pageToReturn){
        try {
            return pageToReturn.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public PageMenu pageMenu(){
        return new PageMenu();
    }
}
