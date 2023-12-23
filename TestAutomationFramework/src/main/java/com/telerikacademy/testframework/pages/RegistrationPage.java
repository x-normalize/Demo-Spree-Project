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
        actions.waitForElementClickable(USERNAME_FIELD_PATH);
        actions.typeValueInField(Utils.getConfigPropertyByKey(username), USERNAME_FIELD_PATH);
        actions.waitForElementClickable(PASSWORD_FIELD_PATH);
        actions.typeValueInField(Utils.getConfigPropertyByKey(password), PASSWORD_FIELD_PATH);
        actions.waitForElementClickable(CONFIRM_PASSWORD_FIELD_PATH);
        actions.typeValueInField(Utils.getConfigPropertyByKey(password), CONFIRM_PASSWORD_FIELD_PATH);
        actions.waitForElementClickable(SIGN_UP_BUTTON_PATH);
        actions.clickElement(SIGN_UP_BUTTON_PATH);
    }

    public void fillOutRegistrationFormWithSpaces() {
        actions.waitForElementClickable(USERNAME_FIELD_PATH);
        actions.clickElement(USERNAME_FIELD_PATH);
        actions.pressKey(Keys.SPACE);
        actions.pressKey(Keys.SPACE);
        actions.pressKey(Keys.SPACE);
        actions.waitForElementClickable(PASSWORD_FIELD_PATH);
        actions.clickElement(PASSWORD_FIELD_PATH);
        actions.pressKey(Keys.SPACE);
        actions.pressKey(Keys.SPACE);
        actions.pressKey(Keys.SPACE);
        actions.waitForElementClickable(CONFIRM_PASSWORD_FIELD_PATH);
        actions.clickElement(CONFIRM_PASSWORD_FIELD_PATH);
        actions.pressKey(Keys.SPACE);
        actions.pressKey(Keys.SPACE);
        actions.pressKey(Keys.SPACE);
        actions.waitForElementClickable(SIGN_UP_BUTTON_PATH);
        actions.clickElement(SIGN_UP_BUTTON_PATH);
    }

    public void fillOutRegistrationFormWithMismatchPasswords(String username, String password, String confirmPassword) {
        actions.typeValueInField(Utils.getConfigPropertyByKey(username), USERNAME_FIELD_PATH);
        actions.typeValueInField(Utils.getConfigPropertyByKey(password), PASSWORD_FIELD_PATH);
        actions.typeValueInField(Utils.getConfigPropertyByKey(confirmPassword), CONFIRM_PASSWORD_FIELD_PATH);
        actions.waitForElementClickable(SIGN_UP_BUTTON_PATH);
        actions.clickElement(SIGN_UP_BUTTON_PATH);
    }

    public void navigateToHomePage() {
        actions.waitForElementClickable(HOME_BUTTON_PATH);
        actions.clickElement(HOME_BUTTON_PATH);
    }

    public void assertWelcomeMessageIsPresent() {
        actions.waitForElementPresent(WELCOME_MESSAGE_PATH);
        actions.assertElementPresent(WELCOME_MESSAGE_PATH);
    }

    public void assertEditProfileButtonIsPresent() {
        actions.waitForElementPresent(EDIT_PROFILE_BUTTON);
        actions.assertElementPresent(EDIT_PROFILE_BUTTON);
    }

    public void assertEmptyEmailErrorMessageIsPresent() {
        actions.waitForElementPresent(EMPTY_EMAIL_ERROR_MESSAGE);
        actions.assertElementPresent(EMPTY_EMAIL_ERROR_MESSAGE);
    }

    public void assertEmptyPasswordErrorMessageIsPresent() {
        actions.waitForElementPresent(EMPTY_PASSWORD_ERROR_MESSAGE);
        actions.assertElementPresent(EMPTY_PASSWORD_ERROR_MESSAGE);
    }

    public void assertCreateNewAccountTextIsPresent() {
        actions.waitForElementPresent(CREATE_NEW_ACCOUNT_TEXT);
        actions.assertElementPresent(CREATE_NEW_ACCOUNT_TEXT);
    }

    public void assertMinimumPasswordErrorMessageIsDisplayed() {
        actions.waitForElementPresent(MINIMUM_PASSWORD_ERROR_MESSAGE);
        actions.assertElementPresent(MINIMUM_PASSWORD_ERROR_MESSAGE);
    }

    public void assertMaximumPasswordErrorMessageIsDisplayed() {
        actions.waitForElementPresent(MAXIMUM_PASSWORD_ERROR_MESSAGE);
        actions.assertElementPresent(MAXIMUM_PASSWORD_ERROR_MESSAGE);
    }

    public void assertSignUpButtonIsPresent() {
        actions.waitForElementPresent(SIGN_UP_BUTTON_PATH);
        actions.assertElementPresent(SIGN_UP_BUTTON_PATH);
    }

    public void assertMismatchPasswordMessageIsPresent() {
        actions.waitForElementPresent(MISMATCH_PASSWORD_ERROR_MESSAGE);
        actions.assertElementPresent(MISMATCH_PASSWORD_ERROR_MESSAGE);
    }

    public void assertEmailIsAlreadyTakenErrorMessageIsPresent() {
        actions.waitForElementPresent(EMAIL_ALREADY_TAKEN_ERROR_MESSAGE);
        actions.assertElementPresent(EMAIL_ALREADY_TAKEN_ERROR_MESSAGE);
    }
}
