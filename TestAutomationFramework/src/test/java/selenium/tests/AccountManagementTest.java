package selenium.tests;

import jdk.jfr.Description;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.telerikacademy.testframework.pages.Constants.*;

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
        accountPage.assertPageNavigated();
        accountPage.updatePassword(EDITED_PASSWORD, EDITED_PASSWORD);
        accountPage.assertUpdateAccountMessageIsPresent();
        accountPage.assertUrlsAreEquals(ACCOUNT_PAGE, ACCOUNT_PAGE);
        accountPage.updatePassword(FOR_EDIT_PASSWORD, FOR_EDIT_PASSWORD);
        accountPage.assertUpdateAccountMessageIsPresent();
        accountPage.assertUrlsAreEquals(ACCOUNT_PAGE, ACCOUNT_PAGE);
        loginPage.assertThatMyAccountSectionIsPresent();
    }




}
