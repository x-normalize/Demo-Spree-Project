package rest.assured.tests;

import com.telerikacademy.testframework.api.BaseSetupMethods;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.telerikacademy.testframework.api.utils.Constants.*;
import static com.telerikacademy.testframework.api.utils.Endpoints.BASE_URL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RegistrationTests extends BaseSetupMethods {
    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    @Description("SDP-10 [Registration] Successful registration")
    public void testCreateAccountSuccess() {
        String randomEmail = "user" + System.currentTimeMillis() + "@example.com";
        Response response = createAccount(randomEmail, "Password123");

        JSONObject jsonResponse = new JSONObject(response.asString());
        JSONObject data = jsonResponse.getJSONObject("data");
        JSONObject attributes = data.getJSONObject("attributes");
        assertNotNull(data.getString("id"), "User ID should not be null");
        assertEquals(randomEmail, attributes.getString("email"), "Email addresses do not match");

        System.out.println(jsonResponse.toString(4));
    }

    @Test
    @Description("Test to verify account creation with missing fields")
    public void testCreateAccountWithMissingFields() {
        JSONObject requestBody = new JSONObject();
        Response response = createAccountWithMissingFields(requestBody);

        assertEquals(400, response.getStatusCode(), "Expected status code 400 for missing fields");
        JSONObject jsonResponse = new JSONObject(response.asString());
        String errorMessage = jsonResponse.getString("error");
        assertEquals("param is missing or the value is empty: user", errorMessage,
                "Unexpected error message");

        System.out.println(jsonResponse.toString(4));
    }

    @Test
    @Description("Test to verify successful retrieval of user details (Response code 200)")
    public void testGetUserDetailsSuccess() {
        String validToken = createRefreshToken(HEIDI_DIXON_USERNAME, HEIDI_DIXON_PASSWORD);
        Response response = getUserDetails(validToken);
        assertEquals(200, response.getStatusCode(), "Expected status code 200 for missing fields");

        JSONObject jsonResponse = new JSONObject(response.asString());
        JSONObject data = jsonResponse.getJSONObject("data");
        JSONObject attributes = data.getJSONObject("attributes");
        assertEquals(HEIDI_ID, data.getString("id"), "Unexpected user ID");
        assertEquals(HEIDI_DIXON_USERNAME, attributes.getString("email"), "Unexpected email address");

        System.out.println(jsonResponse.toString(4));
    }
}
