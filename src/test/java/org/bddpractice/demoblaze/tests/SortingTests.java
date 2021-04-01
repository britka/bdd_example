package org.bddpractice.demoblaze.tests;

import io.qameta.allure.Step;
import org.bddpractice.demoblaze.enums.MenuItem;
import org.bddpractice.demoblaze.enums.SortBy;
import org.bddpractice.demoblaze.models.Product;
import org.bddpractice.demoblaze.pages.BasePage;
import org.bddpractice.demoblaze.pages.CategoryPage;
import org.bddpractice.demoblaze.pages.LoginPage;
import org.bddpractice.demoblaze.utils.PropertiesReader;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.open;

/**
 * @author sbryt on 29/03/2021
 */

@Listeners(MyListener.class)
public class SortingTests extends BaseTest {
    @BeforeClass
    public void beforeTestSorting() {
        open("http://54.37.125.177/");
        new BasePage()
                .mainMenu()
                .selectMenuItem(MenuItem.LOG_IN, LoginPage.class)
                .login(appConfig.user_name(), appConfig.user_pass());
//                .login(PropertiesReader.getInstance().getProperty("user.name"),
//                        PropertiesReader.getInstance().getProperty("user.pass"));
    }

    @AfterClass
    public void afterClass() {
        if (new BasePage().mainMenu().isMenuItemVisible(MenuItem.LOG_OUT)) {
            new BasePage().mainMenu().selectMenuItem(MenuItem.LOG_OUT, BasePage.class);
        }
    }

    @Test(dataProvider = "dataProvider")
    public void testSort(SortBy sortBy) {
        new BasePage()
                .pageMenu()
                .goToPage("Computers", "Notebooks")
                .sortBy(sortBy);
        Assert.assertTrue(productsShouldBeSortedAs(sortBy));
    }


    @DataProvider
    public Object[] dataProvider() {
        return SortBy.values();
    }


    @Step("Checking sorting for {sortBy} type")
    public boolean productsShouldBeSortedAs(SortBy sortBy) {
        List<Product> listOfProducts = new CategoryPage().getListOfProducts();
        List<Product> clonedList = new ArrayList<>(listOfProducts);

        switch (sortBy) {
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
                return titlesFromClone.equals(titlesFromPage);
            }
            case NAME_Z_TO_A: {
                List<String> titlesFromClone = clonedList.stream().map(Product::getTitle).collect(Collectors.toList());
                List<String> titlesFromPage = listOfProducts.stream().map(Product::getTitle).collect(Collectors.toList());
                titlesFromClone.sort(Comparator.reverseOrder());
                return titlesFromClone.equals(titlesFromPage);
            }
            case PRICE_LOW_TO_HIGH: {
                List<Double> pricesFromClone = clonedList.stream().map(Product::getActualPrice).collect(Collectors.toList());
                List<Double> pricesFromPage = listOfProducts.stream().map(Product::getActualPrice).collect(Collectors.toList());
                pricesFromClone.sort(Comparator.naturalOrder());
                return pricesFromClone.equals(pricesFromPage);
            }
            case PRICE_HIGH_TO_LOW: {
                List<Double> pricesFromClone = clonedList.stream().map(Product::getActualPrice).collect(Collectors.toList());
                List<Double> pricesFromPage = listOfProducts.stream().map(Product::getActualPrice).collect(Collectors.toList());
                pricesFromClone.sort(Comparator.reverseOrder());
                return pricesFromClone.equals(pricesFromPage);
            }

        }
        return false;
    }

}
