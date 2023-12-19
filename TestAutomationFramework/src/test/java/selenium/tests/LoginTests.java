package selenium.tests;

import jdk.jfr.Description;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.telerikacademy.testframework.pages.Constants.HEIDI_DIXON_PASSWORD;
import static com.telerikacademy.testframework.pages.Constants.HEIDI_DIXON_USERNAME;

public class LoginTests extends BaseTestSetup{

    @BeforeEach
    public void navigateToLoginPage() {
        loginPage.navigateToPage();
    }

    @Test
    @Description("SDP-2 [Login] Log in with valid credentials")
    public void loginWithValidCredentials(){
        loginPage.assertPageNavigated();
        loginPage.login(HEIDI_DIXON_USERNAME, HEIDI_DIXON_PASSWORD);
        loginPage.assertSuccessfullyLoginMessage();
        loginPage.assertMyAccountSection();

    }
}
