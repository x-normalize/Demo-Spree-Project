package com.telerikacademy.testframework.pages;

import com.telerikacademy.testframework.Utils;
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
        actions.typeValueInField(Utils.getConfigPropertyByKey(password), PASSWORD_FIELD_PATH);
        actions.waitForElementClickable(SIGN_UP_BUTTON_PATH);
        actions.clickElement(SIGN_UP_BUTTON_PATH);
    }

    public void assertWelcomeMessage() {
        actions.waitForElementPresent(WELCOME_MESSAGE_PATH);
        actions.assertElementPresent(WELCOME_MESSAGE_PATH);
    }
}
