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
        accountPage.assertElementTextEquals(SUCCESSFUL_ACCOUNT_UPDATE_MESSAGE, "Account updated");
        accountPage.assertUrlsAreEquals(ACCOUNT_PAGE, ACCOUNT_PAGE);
        accountPage.updatePassword(FOR_EDIT_PASSWORD, FOR_EDIT_PASSWORD);
        accountPage.assertElementTextEquals(SUCCESSFUL_ACCOUNT_UPDATE_MESSAGE, "Account updated");
        actions.assertUrlsAreEquals(ACCOUNT_PAGE, ACCOUNT_PAGE);
        accountPage.assertThatMyAccountSectionIsPresent();
    }

    @Test
    @Description("SDP-28 [Account Management] Successful password update with 128-Character")
    public void shouldUpdatePasswordSuccessfullyWith128Characters() {
        accountPage.assertPageNavigated();
        RANDOM_PASSWORD = actions.generateRandomTextExactLength(128);
        accountPage.updatePassword(RANDOM_PASSWORD, RANDOM_PASSWORD);
        accountPage.assertElementTextEquals(SUCCESSFUL_ACCOUNT_UPDATE_MESSAGE, "Account updated");
        accountPage.assertUrlsAreEquals(ACCOUNT_PAGE, ACCOUNT_PAGE);
        accountPage.updatePassword(FOR_EDIT_PASSWORD, FOR_EDIT_PASSWORD);
        accountPage.assertElementTextEquals(SUCCESSFUL_ACCOUNT_UPDATE_MESSAGE, "Account updated");
        accountPage.assertUrlsAreEquals(ACCOUNT_PAGE, ACCOUNT_PAGE);
        accountPage.assertThatMyAccountSectionIsPresent();
    }


}
