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
        assertEquals(200, response.getStatusCode(),
                "Expected status code 200 for Successful registration");
        assertNotNull(data.getString("id"), "User ID should not be null");
        assertEquals(randomEmail, attributes.getString("email"), "Email addresses do not match");

        System.out.println(jsonResponse.toString(4));
    }

    @Test
    @Description("SDP-12 [Registration] Attempt registration with 1-character password")
    public void testCreateAccountWhenPasswordIsSingleCharacter() {
        String randomEmail = "user" + System.currentTimeMillis() + "@example.com";
        Response response = createAccount(randomEmail, "s");

        JSONObject jsonResponse = new JSONObject(response.asString());
        String errorMessage = jsonResponse.getString("error");
        assertEquals("Password is too short (minimum is 6 characters)", errorMessage,
                "Unexpected error message");
        assertEquals(422, response.getStatusCode(),
                "Expected status code 422 for single character password");

        System.out.println(jsonResponse.toString(4));
    }

    @Test
    @Description("SDP-13 [Registration] Attempt registration with 5-character password")
    public void testCreateAccountWhenPasswordIsFiveCharacter() {
        String randomEmail = "user" + System.currentTimeMillis() + "@example.com";
        Response response = createAccount(randomEmail, "sBdBA");

        JSONObject jsonResponse = new JSONObject(response.asString());
        String errorMessage = jsonResponse.getString("error");
        assertEquals("Password is too short (minimum is 6 characters)", errorMessage,
                "Unexpected error message");
        assertEquals(422, response.getStatusCode(),
                "Expected status code 422 for single character password");

        System.out.println(jsonResponse.toString(4));
    }

    @Test
    @Description("SDP-18 [Registration] Attempt registration with 129-character password")
    public void testCreateAccountWithOverLengthPassword() {
        String randomEmail = "user" + System.currentTimeMillis() + "@example.com";
        Response response = createAccount(randomEmail, OVER_LENGTH_PASSWORD);

        JSONObject jsonResponse = new JSONObject(response.asString());
        String errorMessage = jsonResponse.getString("error");
        assertEquals("Password is too long (maximum is 128 characters)", errorMessage,
                "Unexpected error message");
        assertEquals(422, response.getStatusCode(),
                "Expected status code 422 for over length password");

        System.out.println(jsonResponse.toString(4));
    }

    @Test
    @Description("SDP-23 [Registration] Attempt registration with an already registered email")
    public void testRegistrationWithExistingEmail() {
        Response response = createAccount(YOLANDA_WHEELER_USERNAME, YOLANDA_WHEELER_PASSWORD);

        JSONObject jsonResponse = new JSONObject(response.asString());
        String errorMessage = jsonResponse.getString("error");
        assertEquals("Email has already been taken", errorMessage,
                "Unexpected error message");
        assertEquals(422, response.getStatusCode(),
                "Expected status code 422 for over length password");

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
