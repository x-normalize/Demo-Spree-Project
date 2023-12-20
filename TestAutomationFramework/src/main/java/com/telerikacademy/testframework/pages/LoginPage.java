package com.telerikacademy.testframework.pages;

import com.telerikacademy.testframework.Utils;
import org.openqa.selenium.WebDriver;

import static com.telerikacademy.testframework.pages.Constants.*;

public class LoginPage extends BasePage{
    public LoginPage(WebDriver driver) {
        super(driver, LOGIN_PAGE);
    }

    public void login(String username, String password) {
        actions.waitForElementClickable(LOGIN_EMAIL_FIELD_PATH);
        actions.typeValueInField(Utils.getConfigPropertyByKey(username), LOGIN_EMAIL_FIELD_PATH);
        actions.waitForElementClickable(LOGIN_PASSWORD_FIELD_PATH);
        actions.typeValueInField(Utils.getConfigPropertyByKey(password), LOGIN_PASSWORD_FIELD_PATH);
        actions.waitForElementClickable(LOGIN_BUTTON_PATH);
        actions.clickElement(LOGIN_BUTTON_PATH);
    }

    public void navigateToHomePage() {
        actions.waitForElementClickable(HOME_BUTTON_PATH);
        actions.clickElement(HOME_BUTTON_PATH);
    }

    public void assertSuccessfullyLoginMessage() {
        actions.waitForElementPresent(SUCCESSFUL_LOGIN_MESSAGE);
        actions.assertElementPresent(SUCCESSFUL_LOGIN_MESSAGE);
    }

    public void assertMyAccountSection() {
        actions.waitForElementPresent(MY_ACCOUNT_SECTION_TEXT);
        actions.assertElementPresent(MY_ACCOUNT_SECTION_TEXT);
    }

    public void assertLogoutButtonIsVisible() {
        actions.waitForElementPresent(ACCOUNT_BUTTON_PATH);
        actions.clickElement(ACCOUNT_BUTTON_PATH);
        actions.waitForElementPresent(LOGOUT_BUTTON_PATH);
        actions.assertElementPresent(LOGOUT_BUTTON_PATH);
    }

    public void assertLoginErrorMessage() {
        actions.waitForElementPresent(LOGIN_ERROR_MESSAGE);
        actions.assertElementPresent(LOGIN_ERROR_MESSAGE);
    }

    public void assertLoginPageTitle() {
        actions.waitForElementPresent(LOGIN_PAGE_TITLE);
        actions.assertElementPresent(LOGIN_PAGE_TITLE);
    }


}
