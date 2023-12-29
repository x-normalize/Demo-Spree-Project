package com.telerikacademy.testframework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.telerikacademy.testframework.Utils.getUIMappingByKey;
import static com.telerikacademy.testframework.pages.Constants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MenCategoriesPage extends BasePage {
    public MenCategoriesPage(WebDriver driver) {
        super(driver, MEN_CATEGORIES_PAGE);
    }

    public void addItemsToCartFromMensCategory() {
        clickButton(PRODUCT_MEN_DENIM_SHIRT_PATH);
        clickButton(ADD_TO_CARD_BUTTON);
        clickButton(VIEW_CARD_BUTTON);
    }

    public void addBlueItemsToCartFromMensCategory() {
        clickButton(COLOR_FILTER_BUTTON);
        clickButton(COLOR_BLUE_BUTTON);
        clickButton(PRODUCT_PLACKET_SHIRT);
        clickButton(ADD_TO_CARD_BUTTON);
        clickButton(VIEW_CARD_BUTTON);
    }

    public void addLSizeItemToCardFromMensCategory() {
        clickButton(SIZE_FILTER_BUTTON);
        clickButton(SIZE_L_BUTTON);
        clickButton(PRODUCT_ANORAK);
        clickButton(ADD_PRODUCT_ANORAK);
        clickButton(ADD_TO_CARD_BUTTON);
        clickButton(VIEW_CARD_BUTTON);
    }

    public void AddItemsFromMensCategoryWithPriceLessThan50USD() {
        clickButton(PRICE_FILTER_BUTTON);
        clickButton(PRICE_LESS_BUTTON);
        clickButton(PRODUCT_POLO_SHIRT);
        clickButton(ADD_TO_CARD_BUTTON);
        clickButton(VIEW_CARD_BUTTON);
    }

    public void assertDenimShirtPrice(String locator, String expectedPrice) {
        actions.waitForElementPresent(PRODUCT_DENIM_SHIRT_UNIT_PRICE);
        String actualText = driver.findElement(By.xpath(getUIMappingByKey(locator))).getText();
        assertEquals(expectedPrice, actualText, "Price does not match for element: " + locator);
    }

    public void assertBlueColorShirtPrice(String locator, String expectedPrice) {
        actions.waitForElementPresent(PRODUCT_BLUE_COLOR_UNIT_PRICE);
        String actualText = driver.findElement(By.xpath(getUIMappingByKey(locator))).getText();
        assertEquals(expectedPrice, actualText, "Price does not match for element: " + locator);
    }

    public void assertProductAnorakIsPresent() {
        assertElementPresent(PRODUCT_NAME_TEXT_PATH);
    }

    public void assertProductShirtIsPresent() {
        assertElementPresent(PRODUCT_POLO_SHIRT_NAME_TEXT_PATH);
    }


}