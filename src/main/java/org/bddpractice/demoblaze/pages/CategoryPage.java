package org.bddpractice.demoblaze.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.bddpractice.demoblaze.enums.SortBy;
import org.bddpractice.demoblaze.models.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

/**
 * @author sbryt on 22/03/2021
 */
public class CategoryPage extends BasePage {
    SelenideElement rootElement = $(".category-page");

    public CategoryPage sortBy(SortBy sortBy) {
        rootElement
                .$(".product-sorting")
                .$("#products-orderby")
                .selectOption(sortBy.getValue());
        sleep(2000);
        return new CategoryPage();
    }

    public List<Product> getListOfProducts() {
        ElementsCollection products = rootElement.$$(".product-item");
        return products.stream().map(Product::new).collect(Collectors.toList());
    }


}
