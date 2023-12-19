package com.telerikacademy.testframework.pages;

import org.openqa.selenium.WebDriver;

import static com.telerikacademy.testframework.pages.Constants.REGISTRATION_PAGE;

public class RegistrationPage extends BasePage {

    public RegistrationPage(WebDriver driver) {
        super(driver, REGISTRATION_PAGE);
    }

}
