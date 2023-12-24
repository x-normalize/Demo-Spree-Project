package com.telerikacademy.testframework.pages;

import com.telerikacademy.testframework.Utils;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

import static com.telerikacademy.testframework.pages.Constants.*;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver, LOGIN_PAGE);
    }

    public void login(String username, String password) {
        enterCredentials(LOGIN_EMAIL_FIELD_PATH, Utils.getConfigPropertyByKey(username));
        enterCredentials(LOGIN_PASSWORD_FIELD_PATH, Utils.getConfigPropertyByKey(password));
        clickButton(LOGIN_BUTTON_PATH);
    }

    public void enterWhitespaceInLoginForm() {
        enterCredentials(LOGIN_EMAIL_FIELD_PATH, "   ");
        enterCredentials(LOGIN_PASSWORD_FIELD_PATH, "   ");
        clickButton(LOGIN_BUTTON_PATH);
    }

    public void navigateToHomePage() {
        clickButton(HOME_BUTTON_PATH);
    }

    public void assertThatLoginMessageIsSuccessful() {
        assertElementPresent(SUCCESSFUL_LOGIN_MESSAGE);
    }

    public void assertUrlsAreEquals(String expectedUrl, String actualUrl) {
        Assertions.assertEquals(expectedUrl, actualUrl, "Expected URL is different than actual.");
        System.out.println("URLs are equal.");
    }

    public void assertThatMyAccountSectionIsPresent() {
        assertElementPresent(MY_ACCOUNT_SECTION_TEXT);
    }

    public void assertThatLogoutButtonIsVisible() {
        clickButton(ACCOUNT_BUTTON_PATH);
        assertElementPresent(LOGOUT_BUTTON_PATH);
    }

    public void assertThatLoginErrorMessageIsPresent() {
        assertElementPresent(LOGIN_ERROR_MESSAGE);
    }

    public void assertThatLoginPageTitleIsPresent() {
        assertElementPresent(LOGIN_PAGE_TITLE);
    }

    public void assertLoginButtonIsPresent() {
        assertElementPresent(LOGIN_BUTTON_PATH);
    }

}
