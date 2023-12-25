package com.telerikacademy.testframework.pages;

import com.telerikacademy.testframework.UserActions;
import com.telerikacademy.testframework.Utils;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class BasePage {

    protected String url;
    protected WebDriver driver;
    public UserActions actions;

    public BasePage(WebDriver driver, String urlKey) {
        String pageUrl = Utils.getConfigPropertyByKey(urlKey);
        this.driver = driver;
        this.url = pageUrl;
        actions = new UserActions();
    }

    public String getUrl() {
        return url;
    }

    public void navigateToPage() {
        this.driver.get(url);
    }

    public void assertPageNavigated() {
        String currentUrl = driver.getCurrentUrl();
        Assertions.assertTrue(currentUrl.contains(url),
                "Landed URL is not as expected. Actual URL: " + currentUrl + ". Expected URL: " + url);
    }

    public void enterCredentials(String fieldPath, String value) {
        actions.waitForElementClickable(fieldPath);
        actions.typeValueInField(value, fieldPath);
    }

    public void clickButton(String buttonPath) {
        actions.waitForElementClickable(buttonPath);
        actions.clickElement(buttonPath);
    }

    public void assertElementPresent(String elementPath) {
        actions.waitForElementPresent(elementPath);
        actions.assertElementPresent(elementPath);
    }

}
