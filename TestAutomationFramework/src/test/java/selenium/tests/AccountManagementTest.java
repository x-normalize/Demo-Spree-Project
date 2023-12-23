package selenium.tests;

import jdk.jfr.Description;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.telerikacademy.testframework.pages.Constants.FOR_EDIT_PASSWORD;
import static com.telerikacademy.testframework.pages.Constants.FOR_EDIT_USERNAME;

public class AccountManagementTest extends BaseTestSetup {

    @BeforeEach
    public void loginAndNavigateToPage() {
        loginPage.navigateToPage();
        loginPage.login(FOR_EDIT_USERNAME, FOR_EDIT_PASSWORD);
        accountPage.navigateToPage();
    }

    @AfterEach
    public void logoutAndNavigateToHomePage() {
        accountPage.logout();
        loginPage.navigateToHomePage();
    }

    @Test
    @Description("SDP-27 [Account Management] Successful password update")
    public void shouldUpdatePasswordSuccessfully() {

    }


}
