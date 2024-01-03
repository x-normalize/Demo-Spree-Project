package rest.assured.tests;

import com.telerikacademy.testframework.api.BaseSetupMethods;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.telerikacademy.testframework.api.utils.Constants.*;
import static com.telerikacademy.testframework.api.utils.Endpoints.ACCOUNT_ENDPOINT;
import static com.telerikacademy.testframework.api.utils.Endpoints.BASE_URL;
import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTests extends BaseSetupMethods {
    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void createTokenUserHeidi() {
        String token = CreateRefreshToken(HEIDI_DIXON_USERNAME, HEIDI_DIXON_PASSWORD);
        assertNotNull(token, "Token should not be null");
        assertFalse(token.isEmpty(), "Token should not be empty");
    }

    @Test
    public void createTokenUserYolanda() {
        String token = CreateRefreshToken(YOLANDA_WHEELER_USERNAME, YOLANDA_WHEELER_PASSWORD);
        assertNotNull(token, "Token should not be null");
        assertFalse(token.isEmpty(), "Token should not be empty");
    }

    @Test
    public void testUnauthorizedAccessToAccount() {
        Response response = given()
                .when()
                .get(ACCOUNT_ENDPOINT)
                .andReturn();
        response.then().statusCode(403);

        JSONObject jsonResponse = new JSONObject(response.asString());
        String errorMessage = jsonResponse.getString("error");
        assertEquals("You are not authorized to access this page.", errorMessage,
                "Unexpected error message");
        System.out.println(jsonResponse.toString(4));
    }

    @Test
    public void testUnauthorizedAccessToAccountWithInvalidToken() {
        Response response = given().auth().oauth2(INVALID_BEARER_TOKEN)
                .when()
                .get(ACCOUNT_ENDPOINT)
                .then()
                .statusCode(403)
                .and()
                .body("error", equalTo("You are not authorized to access this page."))
                .extract()
                .response();


        System.out.println("Response: " + response.asString());
    }


}
