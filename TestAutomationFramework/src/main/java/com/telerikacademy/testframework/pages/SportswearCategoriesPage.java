package com.telerikacademy.testframework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.telerikacademy.testframework.Utils.getUIMappingByKey;
import static com.telerikacademy.testframework.pages.Constants.*;
import static com.telerikacademy.testframework.pages.Constants.VIEW_CARD_BUTTON;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SportswearCategoriesPage extends BasePage {
    public SportswearCategoriesPage(WebDriver driver) {
        super(driver, SPORTSWEAR_CATEGORIES_PAGE);
    }

    public void addItemsToCartFromSportWearCategory() {
        clickButton(PRODUCT_RUNNING_SWEATSHIRT_PATH);
        clickButton(ADD_TO_CARD_BUTTON);
        clickButton(VIEW_CARD_BUTTON);
    }

    public void assertRunningSweatshirtPrice(String locator, String expectedPrice) {
        actions.waitForElementPresent(PRODUCT_RUNNING_SWEATSHIRT_UNIT_PRICE);
        String actualText = driver.findElement(By.xpath(getUIMappingByKey(locator))).getText();
        assertEquals(expectedPrice, actualText, "Price does not match for element: " + locator);
    }
}
