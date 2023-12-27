package com.telerikacademy.testframework.pages;

import com.telerikacademy.testframework.Utils;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;

import static com.telerikacademy.testframework.Utils.getUIMappingByKey;
import static com.telerikacademy.testframework.pages.Constants.*;

public class AccountPage extends BasePage {
    public AccountPage(WebDriver driver) {
        super(driver, ACCOUNT_PAGE);
    }

    public void logout() {
        try {
            clickButton(HOME_BUTTON_PATH);
            clickButton(ACCOUNT_BUTTON_PATH);
            clickButton(LOGOUT_BUTTON_PATH);
        } catch (StaleElementReferenceException e) {
            // Retry the logout operation
            logout();
        }
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

    public void addNewAddress() {
        clickButton(ADD_NEW_ADDRESS_BUTTON);
        enterCredentials(ADDRESS_NAME_FIELD_PATH, ADDRESS_NAME);
        enterCredentials(FIRST_NAME_FIELD_PATH, FIRST_NAME);
        enterCredentials(LAST_NAME_FIELD_PATH, LAST_NAME);
        enterCredentials(ADDRESS_FIELD_PATH, ADDRESS);
        enterCredentials(CITY_FIELD_PATH, CITY);
        enterCredentials(ZIP_CODE_FIELD_PATH, ZIP_CODE);
        enterCredentials(PHONE_FIELD_PATH, PHONE);
        clickButton(SAVE_BUTTON_FIELD_PATH);
    }

    public void deleteAddress() {
        clickButton(DELETE_ADDRESS_BUTTON);
        clickButton(DELETE_ADDRESS_POPUP_BUTTON);
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
        assertElementPresent(SUCCESSFUL_LOGOUT_MESSAGE);
    }

    public void assertThatMyAccountSectionIsPresent() {
        assertElementPresent(MY_ACCOUNT_SECTION_TEXT);
    }

    public void assertMaximumPasswordErrorMessageIsDisplayed() {
        assertElementPresent(MAXIMUM_PASSWORD_ERROR_MESSAGE);
    }

    public void assertPasswordUpdateButtonIsDisplayed() {
        assertElementPresent(PASSWORD_UPDATE_BUTTON);
    }

    public void assertDeleteAddressMessageIsDisplayed() {
        assertElementPresent(DELETE_ADDRESS_MESSAGE);
    }

}
