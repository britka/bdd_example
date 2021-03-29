import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.bddpractice.demoblaze.enums.MenuItem;
import org.bddpractice.demoblaze.enums.SortBy;
import org.bddpractice.demoblaze.models.Product;
import org.bddpractice.demoblaze.pages.BasePage;
import org.bddpractice.demoblaze.pages.CategoryPage;
import org.bddpractice.demoblaze.pages.LoginPage;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.open;

/**
 * @author sbryt on 22/03/2021
 */
public class MyStepdefs {
    private static boolean wasHere = false;

    @Before
    public void beforeSuite() throws MalformedURLException {
        if (!wasHere) {
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
            wasHere = true;
        }
    }

    @Given("^User is on main page$")
    public void user_on_the_main_page() {
        open("http://54.37.125.177/");
    }

    //@When("^User login with \"([^\"]*)\" user name and \"([^\"]*)\" password$")
    public void user_login_with(String userName, String userPass) {
        new BasePage().mainMenu().selectMenuItem(MenuItem.LOG_IN, LoginPage.class)
                .login(userName, userPass);
    }

    @Then("^User should be logged in$")
    public void user_should_be_logged_in() {
        Assert.assertTrue(new BasePage().mainMenu().isMenuItemVisible(MenuItem.LOG_OUT));
    }

    @When("User log out")
    public void userLogOut() {
        new BasePage()
                .mainMenu()
                .selectMenuItem(MenuItem.LOG_OUT, BasePage.class);
    }

    @Then("User should be logged out")
    public void userShouldBeLoggedOut() {
        Assert.assertTrue(new BasePage().mainMenu().isMenuItemVisible(MenuItem.LOG_IN));
    }


//    @Before
//    public void beforeEach() {
//        open("http://54.37.125.177/");
//        Selenide.clearBrowserCookies();
//        Selenide.clearBrowserLocalStorage();
//        refresh();
//    }

    @When("User choose sort by {string} direction")
    public void userChooseSortByDirection(String sortBy) {
        new CategoryPage()
                .sortBy(SortBy.valueOf(sortBy));

    }

    @When("User go to {string} main category and {string} subcategory")
    public void userGoToMainCategoryAndSubcategory(String mainCategory, String subCategory) {
        new BasePage()
                .pageMenu()
                .goToPage(mainCategory, subCategory);
    }

    @Then("Products should be sorted as {string}")
    public void productsShouldBeSortedAs(String sortBy) {
        List<Product> listOfProducts = new CategoryPage().getListOfProducts();
        List<Product> clonedList = new ArrayList<>(listOfProducts);

        switch (SortBy.valueOf(sortBy)) {
            case NAME_A_TO_Z: {
                List<String> titlesFromClone =
                        clonedList
                                .stream()
                                .map(Product::getTitle)
                                .collect(Collectors.toList());
                List<String> titlesFromPage = listOfProducts
                        .stream()
                        .map(Product::getTitle)
                        .collect(Collectors.toList());
                titlesFromClone.sort(Comparator.naturalOrder());
                Assert.assertEquals(titlesFromClone, titlesFromPage);
                break;
            }
            case NAME_Z_TO_A: {
                List<String> titlesFromClone = clonedList.stream().map(Product::getTitle).collect(Collectors.toList());
                List<String> titlesFromPage = listOfProducts.stream().map(Product::getTitle).collect(Collectors.toList());
                titlesFromClone.sort(Comparator.reverseOrder());
                Assert.assertEquals(titlesFromClone, titlesFromPage);
                break;
            }
            case PRICE_LOW_TO_HIGH: {
                List<Double> pricesFromClone = clonedList.stream().map(Product::getActualPrice).collect(Collectors.toList());
                List<Double> pricesFromPage = listOfProducts.stream().map(Product::getActualPrice).collect(Collectors.toList());
                pricesFromClone.sort(Comparator.naturalOrder());
                Assert.assertEquals(pricesFromClone, pricesFromPage);
                break;
            }
            case PRICE_HIGH_TO_LOW: {
                List<Double> pricesFromClone = clonedList.stream().map(Product::getActualPrice).collect(Collectors.toList());
                List<Double> pricesFromPage = listOfProducts.stream().map(Product::getActualPrice).collect(Collectors.toList());
                pricesFromClone.sort(Comparator.reverseOrder());
                Assert.assertEquals(pricesFromClone, pricesFromPage);
                break;
            }

        }
    }

    @After
    public void afterEveryScenario() {
        if (new BasePage().mainMenu().isMenuItemVisible(MenuItem.LOG_OUT)) {
            new BasePage().mainMenu().selectMenuItem(MenuItem.LOG_OUT, BasePage.class);
        }
    }

    @And("User login with {string} user name and {string} password")
    public void userLoginWithUserNameAndPassword(String userName, String userPass) {
        new BasePage().mainMenu().selectMenuItem(MenuItem.LOG_IN, LoginPage.class)
                .login(userName, userPass);
    }
}
