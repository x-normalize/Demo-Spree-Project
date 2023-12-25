package com.telerikacademy.testframework.pages;

import org.openqa.selenium.WebDriver;

import static com.telerikacademy.testframework.pages.Constants.SPORTSWEAR_CATEGORIES_PAGE;

public class SportswearCategoriesPage extends BasePage{
    public SportswearCategoriesPage(WebDriver driver) {
        super(driver, SPORTSWEAR_CATEGORIES_PAGE);
    }
}
