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
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountTests extends BaseSetupMethods {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void testGetUserDetailsSuccess() {
        Response response = given()
                .auth()
                .oauth2(HEIDI_TOKEN)
                .when()
                .get(ACCOUNT_ENDPOINT)
                .andReturn(); // Use andReturn() to get the Response object

        response.then().statusCode(200);

        JSONObject jsonResponse = new JSONObject(response.asString());
        JSONObject data = jsonResponse.getJSONObject("data");
        JSONObject attributes = data.getJSONObject("attributes");
        assertEquals(HEIDI_ID, data.getString("id"), "Unexpected user ID");
        assertEquals(HEIDI_DIXON_USERNAME, attributes.getString("email"), "Unexpected email address");
        System.out.println(jsonResponse.toString(4));
    }

}



