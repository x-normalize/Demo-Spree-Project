package com.telerikacademy.testframework.pages;

import org.openqa.selenium.WebDriver;

import static com.telerikacademy.testframework.pages.Constants.MEN_CATEGORIES_PAGE;

public class MenCategoriesPage extends BasePage{
    public MenCategoriesPage(WebDriver driver) {
        super(driver, MEN_CATEGORIES_PAGE);
    }
}
