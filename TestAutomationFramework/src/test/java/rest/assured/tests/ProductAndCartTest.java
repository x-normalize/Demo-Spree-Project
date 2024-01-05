package rest.assured.tests;

import com.telerikacademy.testframework.api.BaseSetupMethods;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.telerikacademy.testframework.api.utils.Endpoints.BASE_URL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ProductAndCartTest extends BaseSetupMethods {
    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void testListAllProducts() {
        Response response = listAllProducts();

        assertEquals(200, response.getStatusCode(), "Expected status code 200");
        JSONObject jsonResponse = new JSONObject(response.asString());
        JSONArray productsArray = jsonResponse.getJSONArray("data");
        assertFalse(productsArray.isEmpty(), "Product list should not be empty");

        System.out.println(jsonResponse.toString(4));
    }


}
