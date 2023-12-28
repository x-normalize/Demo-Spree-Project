package com.telerikacademy.testframework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.telerikacademy.testframework.Utils.getUIMappingByKey;
import static com.telerikacademy.testframework.pages.Constants.*;

public class CategoriesPage extends BasePage{
    public CategoriesPage(WebDriver driver) {
        super(driver, CATEGORIES_PAGE);
    }

    public void performSearch() {
        clickButton(SEARCH_BUTTON_PATH);
        enterCredentials(SEARCH_FIELD_PATH, "Denim Shirt");
        clickButton(SEARCH_SUBMIT_BUTTON_PATH);
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
