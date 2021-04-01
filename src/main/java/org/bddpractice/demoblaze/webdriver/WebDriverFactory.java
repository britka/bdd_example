package org.bddpractice.demoblaze.webdriver;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author sbryt on 01/04/2021
 */
public class WebDriverFactory {
    public static void initDriver() throws Exception {
        String browser = System.getProperty("browserType");
        if (StringUtils.isEmpty(browser)) {
            throw new Exception("You should specify browser using 'browserName' property");
        }
        Configuration.timeout = 10000;
        Configuration.startMaximized = true;
        switch (browser.toLowerCase()) {
            case "chrome":
                Configuration.browser = WebDriverRunner.CHROME;
                break;
            case "firefox":
                Configuration.browser = WebDriverRunner.FIREFOX;
                break;
            case "selenoid_chrome": {
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability("browserName", "chrome");
                capabilities.setCapability("browserVersion", "89.0");
                capabilities.setCapability("selenoid:options", new HashMap<String, Object>() {{
                            put("enableVNC", true);
                            put("enableVideo", true);
                        }}
                );
                RemoteWebDriver driver = null;
                try {
                    driver = new RemoteWebDriver(
                            URI.create("http://localhost:4444/wd/hub").toURL(),
                            capabilities
                    );
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                WebDriverRunner.setWebDriver(driver);
                break;
            }
            case "selenium_grid": {
                DesiredCapabilities capabilities = new DesiredCapabilities();
                String grid_browser = System.getProperty("gridBrowser");
                String grid_platform = System.getProperty("gridPlatform");

                if (StringUtils.isEmpty(grid_platform)) {
                    throw new Exception("Please specify platform for grid");
                }
                if (StringUtils.isEmpty(grid_browser)) {
                    throw new Exception("Please specify browser for grid");
                }

                capabilities.setCapability("browserName", "chrome");
                capabilities.setCapability("platformName", Platform.fromString(grid_platform));
                RemoteWebDriver driver = null;
                try {
                    driver = new RemoteWebDriver(
                            URI.create("http://localhost:44441/wd/hub").toURL(),
                            capabilities
                    );
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                WebDriverRunner.setWebDriver(driver);
            }

        }

    }
}
