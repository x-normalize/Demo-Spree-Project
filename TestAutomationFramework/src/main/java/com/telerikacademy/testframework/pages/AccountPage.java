package com.telerikacademy.testframework.pages;

import com.telerikacademy.testframework.Utils;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.telerikacademy.testframework.Utils.getUIMappingByKey;
import static com.telerikacademy.testframework.pages.Constants.*;

public class AccountPage extends BasePage {
    public AccountPage(WebDriver driver) {
        super(driver, ACCOUNT_PAGE);
    }

    public void logout() {
        actions.waitForElementClickable(HOME_BUTTON_PATH);
        actions.clickElement(HOME_BUTTON_PATH);
        actions.waitForElementClickable(ACCOUNT_BUTTON_PATH);
        actions.clickElement(ACCOUNT_BUTTON_PATH);
        actions.waitForElementClickable(LOGOUT_BUTTON_PATH);
        actions.clickElement(LOGOUT_BUTTON_PATH);
    }

    public void updatePassword(String password, String updatePassword) {
        actions.waitForElementPresent(EDIT_PROFILE_BUTTON);
        actions.clickElement(EDIT_PROFILE_BUTTON);
        actions.waitForElementPresent(PASSWORD_UPDATE_FIELD_PATH);
        actions.typeValueInField(Utils.getConfigPropertyByKey(password), PASSWORD_UPDATE_FIELD_PATH);
        actions.waitForElementPresent(PASSWORD_UPDATE_CONFIRMATION_FIELD_PATH);
        actions.typeValueInField(Utils.getConfigPropertyByKey(updatePassword), PASSWORD_UPDATE_CONFIRMATION_FIELD_PATH);
        actions.waitForElementPresent(PASSWORD_UPDATE_BUTTON);
        actions.clickElement(PASSWORD_UPDATE_BUTTON);
    }

    public void assertUrlsAreEquals(String expectedUrl, String actualUrl) {
        Assertions.assertEquals(expectedUrl, actualUrl, "Expected URL is different than actual.");
        System.out.println("URLs are equal.");
    }

    public void assertElementTextEquals(String locator, String expectedText) {
        String actualText = driver.findElement(By.xpath(getUIMappingByKey(locator))).getText();
        Assertions.assertEquals(expectedText, actualText, "Text does not match for element: " + locator);
    }

    public void assertSuccessfulLogoutMessageIsPresent() {
        actions.waitForElementPresent(SUCCESSFUL_LOGOUT_MESSAGE);
        actions.assertElementPresent(SUCCESSFUL_LOGOUT_MESSAGE);
    }

    public void assertThatMyAccountSectionIsPresent() {
        assertElementPresent(MY_ACCOUNT_SECTION_TEXT);
    }

}
