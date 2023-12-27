package com.telerikacademy.testframework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.telerikacademy.testframework.Utils.getUIMappingByKey;
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

    public void addRedItemsToCartFromWomenCategory() {
        clickButton(COLOR_FILTER_BUTTON);
        clickButton(COLOR_RED_BUTTON);
        clickButton(PRODUCT_CROP_TOP);
        clickButton(ADD_TO_CARD_BUTTON);
        clickButton(VIEW_CARD_BUTTON);
    }

    public void addSSizeItemToCardFromWomensCategory() {
        clickButton(SIZE_FILTER_BUTTON);
        clickButton(SIZE_S_BUTTON);
        clickButton(PRODUCT_FLARED_SKIRT);
        clickButton(ADD_PRODUCT_FLARED_SKIRT);
        clickButton(ADD_TO_CARD_BUTTON);
        clickButton(VIEW_CARD_BUTTON);
    }

    public void assertSizeSFlaredSkirtPrice(String locator, String expectedPrice) {
        actions.waitForElementPresent(PRODUCT_FLARED_SKIRT_UNIT_PRICE);
        String actualText = driver.findElement(By.xpath(getUIMappingByKey(locator))).getText();
        assertEquals(expectedPrice, actualText, "Price does not match for element: " + locator);
    }

    public void assertProductDressIsPresent() {
        assertElementPresent(PRODUCT_WOMEN_DRESS_NAME_TEXT_PATH);
    }

    public void assertProductCropTopIsPresent() {
        assertElementPresent(PRODUCT_CROP_TOP_NAME_TEXT_PATH);
    }


}
