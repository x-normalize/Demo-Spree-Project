package com.telerikacademy.testframework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.telerikacademy.testframework.Utils.getUIMappingByKey;
import static com.telerikacademy.testframework.pages.Constants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MenCategoriesPage extends BasePage {
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

    public void addBlueItemsToCartFromMensCategory() {
        clickButton(COLOR_FILTER_BUTTON);
        clickButton(COLOR_BLUE_BUTTON);
        clickButton(PRODUCT_PLACKET_SHIRT);
    }

    public void assertItemPresentInCart(String itemName) {
        List<WebElement> itemsInCart = driver.findElements(By.cssSelector(".item-title a"));
        boolean itemFound = false;
        for (WebElement item : itemsInCart) {
            if (item.getText().equals(itemName)) {
                itemFound = true;
                break;
            }
        }
        assertTrue(itemFound, "Item '" + itemName + "' not found in cart!");
    }

    public void assertElementTextEquals(String locator, String expectedText) {
        actions.waitForElementPresent(PRODUCT_PRICE_PATH);
        String actualText = driver.findElement(By.xpath(getUIMappingByKey(locator))).getText();
        assertEquals(expectedText, actualText, "Text does not match for element: " + locator);
    }

    public void assertTotalPrice(String expectedTotalPrice) {
        WebElement totalPriceElement = driver.findElement(By.className("shopping-cart-total-amount"));
        String actualTotalPrice = totalPriceElement.getText().trim();
        assertEquals(expectedTotalPrice, actualTotalPrice, "Total price does not match!");
    }

    public void assertSuccessfulAddToCardMessageIsPresent() {
        assertElementPresent(SUCCESSFUL_ADD_TO_CARD_MESSAGE);
    }

    public void assertSuccessfulDeleteItemMessageIsPresent() {
        assertElementPresent(EMPTY_CARD_MESSAGE);
    }

    public void assertItemQuantityInCart(String itemName, int expectedQuantity) {
        List<WebElement> itemsInCart = driver.findElements(By.cssSelector(".item-name-css-selector"));
        // Initialize a variable to store the actual quantity
        int actualQuantity = -1;
        // Loop through each item and check if the name matches the given item name
        for (WebElement item : itemsInCart) {
            if (item.getText().equals(itemName)) {
                // If the names match, find the quantity input element for this item
                WebElement quantityElement = item.findElement(By.xpath(
                        "../following-sibling::div/input[@class='shopping-cart-item-quantity-input']"));
                // Get the value of the quantity input element
                actualQuantity = Integer.parseInt(quantityElement.getAttribute("value"));
                break;
            }
        }

    }


}