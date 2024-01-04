package rest.assured.tests;

import com.telerikacademy.testframework.api.BaseSetupMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.telerikacademy.testframework.api.utils.Constants.*;
import static com.telerikacademy.testframework.api.utils.Endpoints.ADDRESS_ENDPOINT;
import static com.telerikacademy.testframework.api.utils.Endpoints.BASE_URL;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AccountTests extends BaseSetupMethods {

    private String addressId;
    private String token;

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    @Description("SDP-33 [Account Management] Successful add new address")
    public void testCreateNewAddress() {
        String token = createRefreshToken(LANDON_BUTLER_USERNAME, LANDON_BUTLER_PASSWORD);

        JSONObject createRequestBody = createRequestBodyForNewAddress();

        Response response = given()
                .header("Authorization", "Bearer " + token)
                .header("Accept", "application/vnd.api+json")
                .header("Content-Type", "application/vnd.api+json")
                .body(createRequestBody.toString())
                .when()
                .post(ADDRESS_ENDPOINT)
                .then()
                .extract()
                .response();


        String addressId = JsonPath.from(response.asString()).get("id");

        JSONObject jsonResponse = new JSONObject(response.asString());
        System.out.println(jsonResponse.toString(4));

        response.then().statusCode(200);
        assertEquals("Mark", jsonResponse.getJSONObject("data").getJSONObject
                ("attributes").getString("firstname"), "First name does not match");
        assertEquals("Winterburn", jsonResponse.getJSONObject("data").getJSONObject
                ("attributes").getString("lastname"), "Last name does not match");
    }

    @Test
    @Description("Successful update new address")
    public void testUpdateAddress() {
        JSONObject updateRequestBody = new JSONObject();
    }

//        JSONObject updateRequestBody = new JSONObject();
//        updateRequestBody.put("address1", "456 Elm St");
//
//        given().auth().oauth2(token)
//                .contentType(ContentType.JSON)
//                .body(updateRequestBody.toString())
//                .when().patch("https://demo.spreecommerce.org/api/v2/storefront/account/addresses/" + addressId)
//                .then().statusCode(200);
//
//        // Delete the created address
//        given().auth().oauth2(token)
//                .when().delete("https://demo.spreecommerce.org/api/v2/storefront/account/addresses/" + addressId)
//                .then().statusCode(200);
    }












