package rest.assured.tests;

import com.telerikacademy.testframework.api.BaseSetupMethods;
import io.restassured.RestAssured;
import jdk.jfr.Description;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.telerikacademy.testframework.api.utils.Constants.*;
import static com.telerikacademy.testframework.api.utils.Endpoints.ACCOUNT_ENDPOINT;
import static com.telerikacademy.testframework.api.utils.Endpoints.BASE_URL;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTests extends BaseSetupMethods {
    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void createTokenUserHeidi() {
        String token = createRefreshToken(HEIDI_DIXON_USERNAME, HEIDI_DIXON_PASSWORD);
        assertNotNull(token, "Token should not be null");
        assertFalse(token.isEmpty(), "Token should not be empty");
    }

    @Test
    public void createTokenUserYolanda() {
        String token = createRefreshToken(YOLANDA_WHEELER_USERNAME, YOLANDA_WHEELER_PASSWORD);
        assertNotNull(token, "Token should not be null");
        assertFalse(token.isEmpty(), "Token should not be empty");
    }

    @Test
    @Description("Test to verify unauthorized access (Response code 403).")
    public void testUnauthorizedAccessToAccount() {
        String errorMessage = unauthorizedAccess(ACCOUNT_ENDPOINT);
        assertEquals("You are not authorized to access this page.", errorMessage,
                "Unexpected error message");
    }


}
