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
    public void successfullyLoginWithValidCredentials() {
        loginPage.assertPageNavigated();
        loginPage.login(HEIDI_DIXON_USERNAME, HEIDI_DIXON_PASSWORD);
        loginPage.assertUrlsAreEquals(ACCOUNT_PAGE, ACCOUNT_PAGE);
        loginPage.assertThatLoginMessageIsSuccessful();
        actions.assertElementTextEquals(SUCCESSFUL_LOGIN_MESSAGE, "Logged in successfully");
        loginPage.assertThatMyAccountSectionIsPresent();
        loginPage.assertThatLogoutButtonIsVisible();
        accountPage.logout();
        accountPage.assertSuccessfulLogoutMessageIsPresent();
        actions.assertElementTextEquals(SUCCESSFUL_LOGOUT_MESSAGE, "Signed out successfully.");
    }

    @Test
    @Description("SDP-3 [Login] Try to log in with an empty email and an empty password")
    public void shouldShowErrorMessageWhenLoginWithEmptyCredentials() {
        loginPage.assertPageNavigated();
        loginPage.login(EMPTY_USERNAME, EMPTY_PASSWORD);
        loginPage.assertUrlsAreEquals(LOGIN_PAGE, LOGIN_PAGE);
        loginPage.assertThatLoginErrorMessageIsPresent();
        actions.assertElementTextEquals(LOGIN_ERROR_MESSAGE, "Invalid email or password.");
        loginPage.assertLoginButtonIsPresent();
    }

    @Test
    @Description("SDP-4 [Login] Try to log in with whitespaces in email and password")
    public void shouldShowErrorMessageWhenLoginWithWhitespaceCredentials() {
        loginPage.assertPageNavigated();
        loginPage.enterWhitespaceInLoginForm();
        loginPage.assertUrlsAreEquals(LOGIN_PAGE, LOGIN_PAGE);
        loginPage.assertThatLoginErrorMessageIsPresent();
        actions.assertElementTextEquals(LOGIN_ERROR_MESSAGE, "Invalid email or password.");
        loginPage.assertThatLoginPageTitleIsPresent();
        loginPage.assertLoginButtonIsPresent();
    }

    @Test
    @Description("SDP-5 [Login] Try to log in with a valid email and invalid password")
    public void shouldShowErrorMessageWhenLoginWithValidEmailAndInvalidPassword() {
        loginPage.assertPageNavigated();
        loginPage.login(HEIDI_DIXON_USERNAME, LANDON_BUTLER_PASSWORD);
        loginPage.assertUrlsAreEquals(LOGIN_PAGE, LOGIN_PAGE);
        loginPage.assertThatLoginErrorMessageIsPresent();
        loginPage.assertThatLoginPageTitleIsPresent();
        loginPage.assertLoginButtonIsPresent();
    }

    @Test
    @Description("SDP-6 [Login] Try to log in with an invalid email and a valid password")
    public void shouldShowErrorMessageWhenLoginWithInvalidEmailAndValidPassword() {
        loginPage.assertPageNavigated();
        loginPage.login(YOLANDA_WHEELER_USERNAME, HEIDI_DIXON_PASSWORD);
        loginPage.assertUrlsAreEquals(LOGIN_PAGE, LOGIN_PAGE);
        loginPage.assertThatLoginErrorMessageIsPresent();
        loginPage.assertThatLoginPageTitleIsPresent();
        loginPage.assertLoginButtonIsPresent();
    }
}
