package org.bddpractice.demoblaze.models;

import com.codeborne.selenide.SelenideElement;

/**
 * @author sbryt on 22/03/2021
 */
public class Product {
    String title;
    double actualPrice;

    public Product(SelenideElement product) {
        String title = product.$(".product-title a").text();
        String parseAsString = product.$(".price.actual-price")
                .text().substring(1)
                .replace(",", "");
        this.title = title;
        this.actualPrice = Double.parseDouble(parseAsString);
    }

    public String getTitle() {
        return title;
    }

    public Product setTitle(String title) {
        this.title = title;
        return this;
    }

    public double getActualPrice() {
        return actualPrice;
    }

    public Product setActualPrice(double actualPrice) {
        this.actualPrice = actualPrice;
        return this;
    }
}
