package com.telerikacademy.testframework;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static com.telerikacademy.testframework.Utils.*;
import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserActions {

    final WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public UserActions() {
        this.driver = getWebDriver();
    }

    public static void loadBrowser(String baseUrlKey) {
        getWebDriver().get(getConfigPropertyByKey(baseUrlKey));
    }

    public static void quitDriver() {
        tearDownWebDriver();
    }

    public void clickElement(String key, Object... arguments) {
        String locator = getLocatorValueByKey(key, arguments);

        LOGGER.info("Clicking on element " + key);
        WebElement element = driver.findElement(By.xpath(locator));
        element.click();
    }

    public void pressKey(Keys key) {
        // TODO: Implement the method
        // 1. Initialize Actions
        Actions action = new Actions(driver);
        // 2. Perform key press
        action.sendKeys(key).build().perform();
    }

    public void typeValueInField(String value, String field, Object... fieldArguments) {
        String locator = getLocatorValueByKey(field, fieldArguments);
        WebElement element = driver.findElement(By.xpath(locator));
        element.sendKeys(value);
    }

    //############# WAITS #########
    public void waitForElementClickable(String locatorKey, Object... arguments) {
        int defaultTimeout = Integer.parseInt(getConfigPropertyByKey("config.defaultTimeoutSeconds"));

        waitForElementToBeClickableUntilTimeout(locatorKey, defaultTimeout, arguments);
    }

    public void waitForElementPresent(String locator, Object... arguments) {
        // TODO: Implement the method
        // 1. Initialize Wait utility with default timeout from properties
        int defaultTimeout = Integer.parseInt(getConfigPropertyByKey("config.defaultTimeoutSeconds"));
        // 2. Use the method that checks for Element present
        // 3. Fail the test with meaningful error message in case the element is not present
        waitForElementPresenceUntilTimeout(locator, defaultTimeout, arguments);
    }


    private void waitForElementToBeClickableUntilTimeout(String locator, int seconds, Object... locatorArguments) {
        Duration timeout = Duration.ofSeconds(seconds);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        String xpath = getLocatorValueByKey(locator, locatorArguments);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        } catch (Exception exception) {
            Assertions.fail("Element with locator: '" + xpath + "' was not found.");
        }
    }

    private void waitForElementPresenceUntilTimeout(String locator, int seconds, Object... locatorArguments) {
        Duration timeout = Duration.ofSeconds(seconds);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        String xpath = getLocatorValueByKey(locator, locatorArguments);
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        } catch (Exception exception) {
            Assertions.fail("Element with locator: '" + xpath + "' was not found.");
        }
    }

    //###################### Asserts ##################
    public void assertElementPresent(String locator) {
        Assertions.assertNotNull(driver.findElement(By.xpath(getUIMappingByKey(locator))),
                format("Element with %s doesn't present.", locator));
        System.out.println("Elements are present.");
    }

    public void assertElementTextEquals(String locator, String expectedText) {
        String actualText = driver.findElement(By.xpath(getUIMappingByKey(locator))).getText();
        Assertions.assertEquals(expectedText, actualText, "Text does not match for element: " + locator);
    }

    public void assertUrlsAreEquals(String expectedUrl, String actualUrl) {
        Assertions.assertEquals(expectedUrl, actualUrl, "Expected URL is different than actual.");
        System.out.println("URLs are equal.");
    }

    public List<WebElement> getElements(String key, Object... arguments) {
        String locator = getLocatorValueByKey(key, arguments);
        LOGGER.info("Hovering on element " + key);
        return driver.findElements(By.xpath(locator));
    }

    public String generateRandomText(int minLength, int maxLength) {
        int length = generateRandomNumber(minLength, maxLength);

        char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder randomText = new StringBuilder(length);
        ThreadLocalRandom random = ThreadLocalRandom.current();

        for (int i = 0; i < length; i++) {
            char randomChar = alphabet[random.nextInt(alphabet.length)];
            randomText.append(randomChar);
        }
        return randomText.toString();
    }

    public String generateRandomTextExactLength(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        StringBuilder builder = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            builder.append(chars.charAt(random.nextInt(chars.length())));

        }
        return builder.toString();
    }

    private int generateRandomNumber(int min, int max) {
        Random number = new Random();
        return number.nextInt(max - min) + min;
    }

    private String getLocatorValueByKey(String locator, Object[] arguments) {
        return format(getUIMappingByKey(locator), arguments);
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

    public void assertTotalPrice(String expectedTotalPrice) {
        WebElement totalPriceElement = driver.findElement(By.className("shopping-cart-total-amount"));
        String actualTotalPrice = totalPriceElement.getText().trim();
        assertEquals(expectedTotalPrice, actualTotalPrice, "Total price does not match!");
    }


}
