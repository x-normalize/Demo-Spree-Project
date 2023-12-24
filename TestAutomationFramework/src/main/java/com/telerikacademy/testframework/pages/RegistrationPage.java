package com.telerikacademy.testframework.pages;

import com.telerikacademy.testframework.Utils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import static com.telerikacademy.testframework.pages.Constants.*;
import static com.telerikacademy.testframework.pages.Constants.WELCOME_MESSAGE_PATH;

public class RegistrationPage extends BasePage {

    public RegistrationPage(WebDriver driver) {
        super(driver, REGISTRATION_PAGE);
    }

    public void fillRegisterForm(String username, String password) {
        enterCredentials(USERNAME_FIELD_PATH, Utils.getConfigPropertyByKey(username));
        enterCredentials(PASSWORD_FIELD_PATH, Utils.getConfigPropertyByKey(password));
        enterCredentials(CONFIRM_PASSWORD_FIELD_PATH, Utils.getConfigPropertyByKey(password));
        clickButton(SIGN_UP_BUTTON_PATH);
    }

    public void fillOutRegistrationFormWithSpaces() {
        clickButton(USERNAME_FIELD_PATH);
        actions.pressKey(Keys.SPACE);
        actions.pressKey(Keys.SPACE);
        actions.pressKey(Keys.SPACE);
        clickButton(PASSWORD_FIELD_PATH);
        actions.pressKey(Keys.SPACE);
        actions.pressKey(Keys.SPACE);
        actions.pressKey(Keys.SPACE);
        clickButton(CONFIRM_PASSWORD_FIELD_PATH);
        actions.pressKey(Keys.SPACE);
        actions.pressKey(Keys.SPACE);
        actions.pressKey(Keys.SPACE);
        clickButton(SIGN_UP_BUTTON_PATH);
    }

    public void fillOutRegistrationFormWithMismatchPasswords(String username, String password, String confirmPassword) {
        enterCredentials(USERNAME_FIELD_PATH, Utils.getConfigPropertyByKey(username));
        enterCredentials(PASSWORD_FIELD_PATH, Utils.getConfigPropertyByKey(password));
        enterCredentials(CONFIRM_PASSWORD_FIELD_PATH, Utils.getConfigPropertyByKey(confirmPassword));
        clickButton(SIGN_UP_BUTTON_PATH);
    }

    public void navigateToHomePage() {
        clickButton(HOME_BUTTON_PATH);
    }

    public void assertWelcomeMessageIsPresent() {
        assertElementPresent(WELCOME_MESSAGE_PATH);
    }

    public void assertEditProfileButtonIsPresent() {
        assertElementPresent(EDIT_PROFILE_BUTTON);
    }

    public void assertEmptyEmailErrorMessageIsPresent() {
        assertElementPresent(EMPTY_EMAIL_ERROR_MESSAGE);
    }

    public void assertEmptyPasswordErrorMessageIsPresent() {
        assertElementPresent(EMPTY_PASSWORD_ERROR_MESSAGE);
    }

    public void assertCreateNewAccountTextIsPresent() {
        assertElementPresent(CREATE_NEW_ACCOUNT_TEXT);
    }

    public void assertMinimumPasswordErrorMessageIsDisplayed() {
        assertElementPresent(MINIMUM_PASSWORD_ERROR_MESSAGE);
    }

    public void assertMaximumPasswordErrorMessageIsDisplayed() {
        assertElementPresent(MAXIMUM_PASSWORD_ERROR_MESSAGE);
    }

    public void assertSignUpButtonIsPresent() {
        assertElementPresent(SIGN_UP_BUTTON_PATH);
    }

    public void assertMismatchPasswordMessageIsPresent() {
        assertElementPresent(MISMATCH_PASSWORD_ERROR_MESSAGE);
    }

    public void assertEmailIsAlreadyTakenErrorMessageIsPresent() {
        assertElementPresent(EMAIL_ALREADY_TAKEN_ERROR_MESSAGE);
    }
}
