package selenium.tests;

import jdk.jfr.Description;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginTests extends BaseTestSetup{

    @BeforeEach
    public void navigateToLoginPage() {
        loginPage.navigateToPage();
    }

    @Test
    @Description("SDP-2 [Login] Log in with valid credentials")
    public void loginWithValidCredentials(){
        loginPage.assertPageNavigated();

    }
}
