package org.bddpractice.demoblaze.tests;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author sbryt on 29/03/2021
 */
public class BaseTest {
    @BeforeSuite
    public void beforeSuite() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "chrome");
        capabilities.setCapability("browserVersion", "88.0");
        capabilities.setCapability("selenoid:options", new HashMap<String, Object>() {
            {
                put("enableVNC", true);
                put("enableVideo", true);
            }
        });
        RemoteWebDriver driver = new RemoteWebDriver(
                URI.create("http://localhost:4444/wd/hub").toURL(),
                capabilities
        );
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebDriverRunner.setWebDriver(driver);
    }

    @AfterSuite
    public void afterSuite(){
        WebDriverRunner.closeWebDriver();
    }
}
