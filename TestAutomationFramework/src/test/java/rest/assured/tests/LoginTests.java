package rest.assured.tests;

import com.telerikacademy.testframework.api.BaseSetupMethods;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LoginTests extends BaseSetupMethods {

    @Test
    public void createToken() {
        String token = CreateRefreshToken();
        assertNotNull(token, "Token should not be null");
        assertFalse(token.isEmpty(), "Token should not be empty");
    }


}
