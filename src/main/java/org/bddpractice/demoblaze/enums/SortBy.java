package org.bddpractice.demoblaze.enums;

/**
 * @author sbryt on 22/03/2021
 */
public enum SortBy {
    POSITION("Position"),
    NAME_A_TO_Z("Name: A to Z"),
    NAME_Z_TO_A ("Name: Z to A"),
    PRICE_LOW_TO_HIGH ("Price: Low to High"),
    PRICE_HIGH_TO_LOW ("Price: High to Low"),
    CREATED_ON("Created on");

    private String value;

    SortBy(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
