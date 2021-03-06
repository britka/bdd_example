package org.bddpractice.demoblaze.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.interactions.Actions;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.actions;

/**
 * @author sbryt on 22/03/2021
 */
public class PageMenu {
    SelenideElement rootElement = $(".top-menu");

    @Step("Go to page {mainSection} -> {subSection}")
    public CategoryPage goToPage(String mainSection, String subSection) {
        SelenideElement li_a = rootElement.$$("li a").find(Condition.text(mainSection));
        actions().moveToElement(li_a).build().perform();
        li_a.parent().$$(".first-level a").find(Condition.text(subSection)).click();
        return new CategoryPage();
    }

    @Step("GO to page {mainSection}")
    public CategoryPage goToPage(String mainSection) {
        SelenideElement li_a = rootElement.$$("li a").find(Condition.text(mainSection));
        actions().moveToElement(li_a).build().perform();
        li_a.click();
        return new CategoryPage();
    }
}
