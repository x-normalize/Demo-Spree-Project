package selenium.tests;

import jdk.jfr.Description;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.telerikacademy.testframework.pages.Constants.*;

public class LoginTests extends BaseTestSetup {

    @BeforeEach
    public void navigateToLoginPage() {
        loginPage.navigateToPage();
    }

    @AfterEach
    public void logout() {
        loginPage.navigateToHomePage();
    }

    @Test
    @Description("SDP-2 [Login] Log in with valid credentials")
    public void loginWithValidCredentials() {
        loginPage.assertPageNavigated();
        loginPage.login(HEIDI_DIXON_USERNAME, HEIDI_DIXON_PASSWORD);
        loginPage.assertSuccessfullyLoginMessage();
        loginPage.assertMyAccountSection();
        loginPage.assertLogoutButtonIsVisible();
        accountPage.logout();
        accountPage.assertSuccessfulLogoutMessage();
    }

    @Test
    @Description("SDP-3 [Login] Try to log in with an empty email and an empty password")
    public void loginWithEmptyEmailAndPassword() {
        loginPage.assertPageNavigated();
        loginPage.login(EMPTY_USERNAME, EMPTY_PASSWORD);
        loginPage.assertLoginErrorMessage();
        loginPage.assertLoginPageTitle();
    }

    @Test
    @Description("SDP-4 [Login] Try to log in with whitespaces in email and password")
    public void loginWithWhitespaceInEmailAndPassword() {
        loginPage.assertPageNavigated();
        loginPage.login(WHITESPACE_USERNAME, WHITESPACE_PASSWORD);
        loginPage.assertLoginErrorMessage();
        loginPage.assertLoginPageTitle();
    }

}
