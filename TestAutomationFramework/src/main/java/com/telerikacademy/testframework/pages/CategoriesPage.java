package com.telerikacademy.testframework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.telerikacademy.testframework.Utils.getUIMappingByKey;
import static com.telerikacademy.testframework.pages.Constants.*;

public class CategoriesPage extends BasePage{
    public CategoriesPage(WebDriver driver) {
        super(driver, CATEGORIES_PAGE);
    }

    public void performSearchDenimShirt() {
        clickButton(SEARCH_BUTTON_PATH);
        enterCredentials(SEARCH_FIELD_PATH, "Denim Shirt");
        clickButton(SEARCH_SUBMIT_BUTTON_PATH);
    }

    public void performSearchCoveredPlacketShirt() {
        clickButton(SEARCH_BUTTON_PATH);
        enterCredentials(SEARCH_FIELD_PATH, "Covered Placket Shirt");
        clickButton(SEARCH_SUBMIT_BUTTON_PATH);
    }

    public void performSearchWithInvalidInput() {
        clickButton(SEARCH_BUTTON_PATH);
        enterCredentials(SEARCH_FIELD_PATH, "###");
        clickButton(SEARCH_SUBMIT_BUTTON_PATH);
    }

    public void assertNoResultMessage(String locator, String message) {
        String actualText = driver.findElement(By.xpath(getUIMappingByKey(locator))).getText();
        Assertions.assertEquals(message, actualText, "The message is different than expected: "
                + locator);
    }

    public void assertErrorMessageIsPresent(String message) {
        assertElementPresent(message);
    }

    public void assertSearchedProductIsPresent(String productPath) {
        assertElementPresent(productPath);
    }

    public void assertElementTextEquals(String locator, String expectedProduct) {
        String actualText = driver.findElement(By.xpath(getUIMappingByKey(locator))).getText();
        Assertions.assertEquals(expectedProduct, actualText, "Searched product does not match for element: "
                + locator);
    }


}
