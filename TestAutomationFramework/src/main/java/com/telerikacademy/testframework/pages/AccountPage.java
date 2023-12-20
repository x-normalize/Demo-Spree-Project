package com.telerikacademy.testframework.pages;

import org.openqa.selenium.WebDriver;

import static com.telerikacademy.testframework.pages.Constants.*;
import static com.telerikacademy.testframework.pages.Constants.LOGOUT_BUTTON_PATH;

public class AccountPage extends BasePage{
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
}
