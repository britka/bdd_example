package org.bddpractice.demoblaze.enums;

/**
 * @author sbryt on 22/03/2021
 */
public enum MenuItem {
    REGISTER("register"),
    LOG_IN("login"),
    WISH_LIST("wishlist"),
    SOPPING_CART("cart"),
    LOG_OUT("logout"),
    MY_ACCOUNT("account");

    private String value;

    MenuItem(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
