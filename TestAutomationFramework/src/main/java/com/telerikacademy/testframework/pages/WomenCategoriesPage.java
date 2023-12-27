package com.telerikacademy.testframework.pages;

import org.openqa.selenium.WebDriver;

import static com.telerikacademy.testframework.pages.Constants.*;

public class WomenCategoriesPage extends BasePage {

    public WomenCategoriesPage(WebDriver driver) {
        super(driver, WOMEN_CATEGORIES_PAGE);
    }

    public void addItemsToCartFromWomenSCategory() {
        clickButton(PRODUCT_WOMEN_DRESS_PATH);
        clickButton(ADD_TO_CARD_BUTTON);
        clickButton(VIEW_CARD_BUTTON);
    }

    public void addRedItemsToCartFromWomenCategory() {
        clickButton(COLOR_FILTER_BUTTON);
        clickButton(COLOR_RED_BUTTON);
        clickButton(PRODUCT_CROP_TOP);
        clickButton(ADD_TO_CARD_BUTTON);
        clickButton(VIEW_CARD_BUTTON);
    }

    public void assertProductDressIsPresent() {
        assertElementPresent(PRODUCT_WOMEN_DRESS_NAME_TEXT_PATH);
    }

    public void assertProductCropTopIsPresent() {
        assertElementPresent(PRODUCT_CROP_TOP_NAME_TEXT_PATH);
    }


}
