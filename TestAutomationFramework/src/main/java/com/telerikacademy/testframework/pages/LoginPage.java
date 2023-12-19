package com.telerikacademy.testframework.pages;

import org.openqa.selenium.WebDriver;

import static com.telerikacademy.testframework.pages.Constants.LOGIN_PAGE;

public class LoginPage extends BasePage{
    public LoginPage(WebDriver driver) {
        super(driver, LOGIN_PAGE);
    }


}
