package com.telerikacademy.testframework.pages;

import org.openqa.selenium.WebDriver;

import static com.telerikacademy.testframework.pages.Constants.CATEGORIES_PAGE;

public class CategoriesPage extends BasePage{
    public CategoriesPage(WebDriver driver) {
        super(driver, CATEGORIES_PAGE);
    }


}
