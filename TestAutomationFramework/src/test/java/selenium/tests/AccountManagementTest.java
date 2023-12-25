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

    @Test
    @Description("SDP-32 [Account Management] Attempt to change password with 129 characters")
    public void shouldFailToUpdatePasswordWith129Characters() {
        accountPage.assertPageNavigated();
        RANDOM_PASSWORD = actions.generateRandomTextExactLength(129);
        accountPage.updatePassword(RANDOM_PASSWORD, RANDOM_PASSWORD);
        accountPage.assertElementTextEquals(MAXIMUM_PASSWORD_ERROR_MESSAGE,
                "Password is too long (maximum is 128 characters)");
        accountPage.assertMaximumPasswordErrorMessageIsDisplayed();
        accountPage.assertUrlsAreEquals(EDIT_USER_ACCOUNT_PAGE, EDIT_USER_ACCOUNT_PAGE);
        accountPage.assertPasswordUpdateButtonIsDisplayed();
    }

    @Test
    @Description("SDP-33 [Account Management] Successful add new address")
    public void shouldSuccessfullyAddNewAddress() {
        accountPage.assertPageNavigated();
        loginPage.login(FOR_EDIT_USERNAME, FOR_EDIT_PASSWORD);
        accountPage.assertUrlsAreEquals(ACCOUNT_PAGE, ACCOUNT_PAGE);
        accountPage.assertSuccessfulLogoutMessageIsPresent();
        accountPage.addNewAddress();
        accountPage.assertElementTextEquals(SUCCESSFUL_ADD_NEW_ADDRESS_MESSAGE,
                "New address has been successfully created");
        accountPage.assertElementTextEquals(ADDRESS_NAME_TEXT_PATH, "HOME");
        accountPage.assertElementTextEquals(FULL_NAME_TEXT_PATH, "George Bush");
        accountPage.assertElementTextEquals(ADDRESS_TEXT_PATH, "793 Bell Street ,");
        accountPage.assertElementTextEquals(CITY_NAME_TEXT_PATH, "New York, AL 10018,");
        accountPage.assertUrlsAreEquals(ACCOUNT_PAGE, ACCOUNT_PAGE);
    }

}
