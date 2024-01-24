package com.telerikacademy.testframework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CustomWebDriverManager {

    public enum CustomWebDriverManagerEnum {

        INSTANCE;
        private WebDriver driver = setupBrowser();

        public void quitDriver() {
            if (driver != null) {
                driver.quit();
                driver = null;
            }
        }

        public WebDriver getDriver() {
            if (driver == null) {
                setupBrowser();
            }
            return driver;
        }

        private WebDriver setupBrowser() {
            WebDriver chromeDriver = new ChromeDriver();
            chromeDriver.manage().window().maximize();
            driver = chromeDriver;
            return chromeDriver;
        }
    }
}
