package com.telerikacademy.testframework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.telerikacademy.testframework.Utils.getUIMappingByKey;
import static com.telerikacademy.testframework.pages.Constants.*;

public class MenCategoriesPage extends BasePage{
    public MenCategoriesPage(WebDriver driver) {
        super(driver, MEN_CATEGORIES_PAGE);
    }

    public void addItemsToCartFromMensCategory() {
        clickButton(PRODUCT_MEN_DENIM_SHIRT_PATH);
        clickButton(ADD_TO_CARD_BUTTON);
        clickButton(VIEW_CARD_BUTTON);
    }

    public void deleteItemFromCard() {
        clickButton(DELETE_ITEM_BUTTON);
    }

    public void assertElementTextEquals(String locator, String expectedText) {
        actions.waitForElementPresent(PRODUCT_PRICE_PATH);
        String actualText = driver.findElement(By.xpath(getUIMappingByKey(locator))).getText();
        Assertions.assertEquals(expectedText, actualText, "Text does not match for element: " + locator);
    }

    public void assertQuantity() {
        actions.waitForElementPresent(PRODUCT_QUANTITY_PATH);
        WebElement inputElement = driver.findElement(By.id("order_line_items_attributes_0_quantity"));
        String quantity = inputElement.getAttribute("value");
        Assertions.assertEquals("1", quantity, "Quantity does not match for element: " +
                "order_line_items_attributes_0_quantity");
    }

    public void assertSuccessfulAddToCardMessageIsPresent() {
        assertElementPresent(SUCCESSFUL_ADD_TO_CARD_MESSAGE);
    }

    public void assertSuccessfulDeleteItemMessageIsPresent() {
        assertElementPresent(EMPTY_CARD_MESSAGE);
    }




}
