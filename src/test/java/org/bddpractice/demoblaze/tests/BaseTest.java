package org.bddpractice.demoblaze.tests;

import com.codeborne.selenide.WebDriverRunner;
import org.aeonbits.owner.ConfigFactory;
import org.bddpractice.demoblaze.AppConfig;
import org.bddpractice.demoblaze.webdriver.WebDriverFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

/**
 * @author sbryt on 29/03/2021
 */
public class BaseTest {
    protected AppConfig appConfig = ConfigFactory.create(AppConfig.class);

    @BeforeSuite
    public void beforeSuite() throws Exception {
        WebDriverFactory.initDriver();
    }

    @AfterSuite
    public void afterSuite() {
        WebDriverRunner.closeWebDriver();
    }
}
