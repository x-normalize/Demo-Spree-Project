package selenium.tests;

import jdk.jfr.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.telerikacademy.testframework.pages.Constants.*;

public class RegistrationTests extends BaseTestSetup{

    @BeforeEach
    public void navigateToPage() {
        registerPage.navigateToPage();
    }

    @Test
    @Description("SDP-10 [Registration] Successful registration")
    public void registerSuccessfully() {
        registerPage.assertPageNavigated();
        RANDOM_USERNAME = actions.generateRandomText(MIN_LENGTH_USERNAME, MAX_LENGTH_USERNAME);
        RANDOM_EMAIL = RANDOM_USERNAME + EMAIL_END;
        RANDOM_PASSWORD = actions.generateRandomText(MIN_LENGTH_PASSWORD, MAX_LENGTH_PASSWORD);
        registerPage.fillRegisterForm(RANDOM_EMAIL, RANDOM_PASSWORD);
        registerPage.assertWelcomeMessage();
        registerPage.assertEditProfileButton();
        loginPage.assertMyAccountSection();
    }

    @Test
    @Description("SDP-11 [Registration] Attempt registration with empty fields")
    public void shouldFailWhenRegistrationFieldsAreEmpty() {
        registerPage.assertPageNavigated();
        registerPage.fillRegisterForm(EMPTY_USERNAME, EMPTY_PASSWORD);
        registerPage.assertEmptyEmailErrorMessage();
        registerPage.assertEmptyPasswordErrorMessage();
        registerPage.assertCreateNewAccountText();
        registerPage.assertSignUpButton();
    }

    @Test
    @Description("SDP-12 [Registration] Attempt registration with 1-character password")
    public void shouldFailWhenPasswordIsSingleCharacter() {
        registerPage.assertPageNavigated();
        RANDOM_USERNAME = actions.generateRandomText(MIN_LENGTH_USERNAME, MAX_LENGTH_USERNAME);
        RANDOM_EMAIL = RANDOM_USERNAME + EMAIL_END;
        RANDOM_PASSWORD = actions.generateRandomTextExactLength(1);
        registerPage.fillRegisterForm(RANDOM_EMAIL, RANDOM_PASSWORD);
        registerPage.assertMinimumPasswordErrorMessageDisplayed();
        registerPage.assertCreateNewAccountText();
        registerPage.assertSignUpButton();
    }

    @Test
    @Description("SDP-13 [Registration] Attempt registration with 5-character password")
    public void shouldFailRegistrationWithShortPassword() {
        registerPage.assertPageNavigated();
        RANDOM_USERNAME = actions.generateRandomText(MIN_LENGTH_USERNAME, MAX_LENGTH_USERNAME);
        RANDOM_EMAIL = RANDOM_USERNAME + EMAIL_END;
        RANDOM_PASSWORD = actions.generateRandomTextExactLength(5);
        registerPage.fillRegisterForm(RANDOM_EMAIL, RANDOM_PASSWORD);
        registerPage.assertMinimumPasswordErrorMessageDisplayed();
        registerPage.assertSignUpButton();
        registerPage.assertCreateNewAccountText();
    }

    @Test
    @Description("SDP-14 [Registration] Successful registration with 6-character password")
    public void shouldSucceedRegistrationWithSixCharacterPassword() {
        registerPage.assertPageNavigated();
        RANDOM_USERNAME = actions.generateRandomText(MIN_LENGTH_USERNAME, MAX_LENGTH_USERNAME);
        RANDOM_EMAIL = RANDOM_USERNAME + EMAIL_END;
        RANDOM_PASSWORD = actions.generateRandomTextExactLength(6);
        registerPage.fillRegisterForm(RANDOM_EMAIL, RANDOM_PASSWORD);
        registerPage.assertWelcomeMessage();
        registerPage.assertEditProfileButton();
        loginPage.assertMyAccountSection();
    }

    @Test
    @Description("SDP-15 [Registration] Successful registration with 127-character password")
    public void shouldSucceedRegistrationWithLongPassword() {
        registerPage.assertPageNavigated();
        RANDOM_USERNAME = actions.generateRandomText(MIN_LENGTH_USERNAME, MAX_LENGTH_USERNAME);
        RANDOM_EMAIL = RANDOM_USERNAME + EMAIL_END;
        RANDOM_PASSWORD = actions.generateRandomTextExactLength(127);
        registerPage.fillRegisterForm(RANDOM_EMAIL, RANDOM_PASSWORD);
        registerPage.assertWelcomeMessage();
        registerPage.assertEditProfileButton();
        loginPage.assertMyAccountSection();
    }

    @Test
    @Description("SDP-18 Registration] Attempt registration with 129-character password")
    public void shouldFailRegistrationWithOverLengthPassword() {
        registerPage.assertPageNavigated();
        RANDOM_USERNAME = actions.generateRandomText(MIN_LENGTH_USERNAME, MAX_LENGTH_USERNAME);
        RANDOM_EMAIL = RANDOM_USERNAME + EMAIL_END;
        RANDOM_PASSWORD = actions.generateRandomTextExactLength(129);
        registerPage.fillRegisterForm(RANDOM_EMAIL, RANDOM_PASSWORD);
        registerPage.assertMaximumPasswordErrorMessageDisplayed();
        registerPage.assertSignUpButton();
        registerPage.assertCreateNewAccountText();
        registerPage.assertPageNavigated();
    }

}
