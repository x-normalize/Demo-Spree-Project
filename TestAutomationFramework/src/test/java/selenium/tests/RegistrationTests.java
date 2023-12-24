package selenium.tests;

import jdk.jfr.Description;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.telerikacademy.testframework.pages.Constants.*;

public class RegistrationTests extends BaseTestSetup {

    @BeforeEach
    public void navigateToPage() {
        registerPage.navigateToPage();
    }

    @AfterEach
    public void navigateToHomePage() {
        registerPage.navigateToHomePage();
    }

    @Test
    @Description("SDP-10 [Registration] Successful registration")
    public void registerSuccessfully() {
        registerPage.assertPageNavigated();
        RANDOM_USERNAME = actions.generateRandomText(MIN_LENGTH_USERNAME, MAX_LENGTH_USERNAME);
        RANDOM_EMAIL = RANDOM_USERNAME + EMAIL_END;
        RANDOM_PASSWORD = actions.generateRandomText(MIN_LENGTH_PASSWORD, MAX_LENGTH_PASSWORD);
        registerPage.fillRegisterForm(RANDOM_EMAIL, RANDOM_PASSWORD);
        registerPage.assertWelcomeMessageIsPresent();
        registerPage.assertEditProfileButtonIsPresent();
        loginPage.assertThatMyAccountSectionIsPresent();
    }

    @Test
    @Description("SDP-11 [Registration] Attempt registration with empty fields")
    public void shouldFailWhenRegistrationFieldsAreEmpty() {
        registerPage.assertPageNavigated();
        registerPage.fillRegisterForm(EMPTY_USERNAME, EMPTY_PASSWORD);
        registerPage.assertEmptyEmailErrorMessageIsPresent();
        registerPage.assertEmptyPasswordErrorMessageIsPresent();
        registerPage.assertCreateNewAccountTextIsPresent();
        registerPage.assertSignUpButtonIsPresent();
    }

    @Test
    @Description("SDP-12 [Registration] Attempt registration with 1-character password")
    public void shouldFailWhenPasswordIsSingleCharacter() {
        registerPage.assertPageNavigated();
        RANDOM_USERNAME = actions.generateRandomText(MIN_LENGTH_USERNAME, MAX_LENGTH_USERNAME);
        RANDOM_EMAIL = RANDOM_USERNAME + EMAIL_END;
        RANDOM_PASSWORD = actions.generateRandomTextExactLength(1);
        registerPage.fillRegisterForm(RANDOM_EMAIL, RANDOM_PASSWORD);
        registerPage.assertMinimumPasswordErrorMessageIsDisplayed();
        registerPage.assertCreateNewAccountTextIsPresent();
        registerPage.assertSignUpButtonIsPresent();
    }

    @Test
    @Description("SDP-13 [Registration] Attempt registration with 5-character password")
    public void shouldFailRegistrationWithShortPassword() {
        registerPage.assertPageNavigated();
        RANDOM_USERNAME = actions.generateRandomText(MIN_LENGTH_USERNAME, MAX_LENGTH_USERNAME);
        RANDOM_EMAIL = RANDOM_USERNAME + EMAIL_END;
        RANDOM_PASSWORD = actions.generateRandomTextExactLength(5);
        registerPage.fillRegisterForm(RANDOM_EMAIL, RANDOM_PASSWORD);
        registerPage.assertMinimumPasswordErrorMessageIsDisplayed();
        registerPage.assertSignUpButtonIsPresent();
        registerPage.assertCreateNewAccountTextIsPresent();
    }

    @Test
    @Description("SDP-14 [Registration] Successful registration with 6-character password")
    public void shouldSucceedRegistrationWithSixCharacterPassword() {
        registerPage.assertPageNavigated();
        RANDOM_USERNAME = actions.generateRandomText(MIN_LENGTH_USERNAME, MAX_LENGTH_USERNAME);
        RANDOM_EMAIL = RANDOM_USERNAME + EMAIL_END;
        RANDOM_PASSWORD = actions.generateRandomTextExactLength(6);
        registerPage.fillRegisterForm(RANDOM_EMAIL, RANDOM_PASSWORD);
        registerPage.assertWelcomeMessageIsPresent();
        registerPage.assertEditProfileButtonIsPresent();
        loginPage.assertThatMyAccountSectionIsPresent();
    }

    @Test
    @Description("SDP-15 [Registration] Successful registration with 127-character password")
    public void shouldSucceedRegistrationWithLongPassword() {
        registerPage.assertPageNavigated();
        RANDOM_USERNAME = actions.generateRandomText(MIN_LENGTH_USERNAME, MAX_LENGTH_USERNAME);
        RANDOM_EMAIL = RANDOM_USERNAME + EMAIL_END;
        RANDOM_PASSWORD = actions.generateRandomTextExactLength(127);
        registerPage.fillRegisterForm(RANDOM_EMAIL, RANDOM_PASSWORD);
        registerPage.assertWelcomeMessageIsPresent();
        registerPage.assertEditProfileButtonIsPresent();
        loginPage.assertThatMyAccountSectionIsPresent();
    }

    @Test
    @Description("SDP-18 [Registration] Attempt registration with 129-character password")
    public void shouldFailRegistrationWithOverLengthPassword() {
        registerPage.assertPageNavigated();
        RANDOM_USERNAME = actions.generateRandomText(MIN_LENGTH_USERNAME, MAX_LENGTH_USERNAME);
        RANDOM_EMAIL = RANDOM_USERNAME + EMAIL_END;
        RANDOM_PASSWORD = actions.generateRandomTextExactLength(129);
        registerPage.fillRegisterForm(RANDOM_EMAIL, RANDOM_PASSWORD);
        registerPage.assertMaximumPasswordErrorMessageIsDisplayed();
        registerPage.assertSignUpButtonIsPresent();
        registerPage.assertCreateNewAccountTextIsPresent();
        registerPage.assertPageNavigated();
    }

    @Test
    @Description("SDP-20 [Registration] Attempt registration with empty email and valid password")
    public void shouldFailRegistrationWithEmptyEmailAndValidPassword() {
        registerPage.assertPageNavigated();
        registerPage.fillRegisterForm(EMPTY_USERNAME, YOLANDA_WHEELER_PASSWORD);
        registerPage.assertEmptyEmailErrorMessageIsPresent();
        registerPage.assertSignUpButtonIsPresent();
        registerPage.assertPageNavigated();
        registerPage.assertCreateNewAccountTextIsPresent();
    }

    @Test
    @Description("SDP-21 [Registration] Attempt registration with whitespaces in email and password fields")
    public void shouldFailRegistrationWithWhitespacesInEmailAndPasswordField() {
        registerPage.assertPageNavigated();
        registerPage.fillOutRegistrationFormWithSpaces();
        registerPage.assertEmptyEmailErrorMessageIsPresent();
        registerPage.assertEmptyPasswordErrorMessageIsPresent();
        registerPage.assertCreateNewAccountTextIsPresent();
        registerPage.assertSignUpButtonIsPresent();
    }

    @Test
    @Description("SDP-22 [Registration] Attempt registration with mismatched passwords")
    public void shouldFailRegistrationWithMismatchedPasswords() {
        registerPage.assertPageNavigated();
        RANDOM_USERNAME = actions.generateRandomText(MIN_LENGTH_USERNAME, MAX_LENGTH_USERNAME);
        RANDOM_EMAIL = RANDOM_USERNAME + EMAIL_END;
        registerPage.fillOutRegistrationFormWithMismatchPasswords(RANDOM_EMAIL, LANDON_BUTLER_PASSWORD,
                YOLANDA_WHEELER_PASSWORD);
        registerPage.assertMismatchPasswordMessageIsPresent();
        registerPage.assertCreateNewAccountTextIsPresent();
        registerPage.assertSignUpButtonIsPresent();
        registerPage.assertPageNavigated();
    }

    @Test
    @Description("SDP-23 [Registration] Attempt registration with an already registered email")
    public void shouldFailRegistrationWithExistingEmail() {
        registerPage.assertPageNavigated();
        RANDOM_PASSWORD = actions.generateRandomText(MIN_LENGTH_PASSWORD, MAX_LENGTH_PASSWORD);
        registerPage.fillRegisterForm(LANDON_BUTLER_USERNAME, RANDOM_PASSWORD);
        registerPage.assertEmailIsAlreadyTakenErrorMessageIsPresent();
        registerPage.assertCreateNewAccountTextIsPresent();
        registerPage.assertSignUpButtonIsPresent();
        registerPage.assertPageNavigated();
    }
}
