package com.telerikacademy.testframework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.telerikacademy.testframework.pages.Constants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WomenCategoriesPage extends BasePage {

    public WomenCategoriesPage(WebDriver driver) {
        super(driver, WOMEN_CATEGORIES_PAGE);
    }

    public void addItemsToCartFromWomenSCategory() {
        clickButton(PRODUCT_WOMEN_DRESS_PATH);
        clickButton(ADD_TO_CARD_BUTTON);
        clickButton(VIEW_CARD_BUTTON);
    }

    public void assertProductDressIsPresent() {
        assertElementPresent(PRODUCT_WOMEN_DRESS_NAME_TEXT_PATH);
    }



}
