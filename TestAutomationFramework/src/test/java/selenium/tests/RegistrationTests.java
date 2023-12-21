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
    }
}
