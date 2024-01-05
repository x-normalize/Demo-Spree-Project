package rest.assured.tests;

import com.telerikacademy.testframework.api.BaseSetupMethods;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.telerikacademy.testframework.api.utils.Constants.PRODUCT_DENIM_SHIRT_NAME;
import static com.telerikacademy.testframework.api.utils.Constants.PRODUCT_DENIM_SHIRT_PRICE;
import static com.telerikacademy.testframework.api.utils.Endpoints.BASE_URL;
import static com.telerikacademy.testframework.api.utils.Endpoints.PRODUCT_DENIM_SHIRT_ENDPOINT;
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
        JSONObject jsonResponse = new JSONObject(response.asString());

        assertEquals(200, response.getStatusCode(), "Expected status code 200");
        JSONArray productsArray = jsonResponse.getJSONArray("data");
        assertFalse(productsArray.isEmpty(), "Product list should not be empty");

        System.out.println(jsonResponse.toString(4));
    }

    @Test
    public void testFetchSingleProductData() {
        Response response = singleProductData(PRODUCT_DENIM_SHIRT_ENDPOINT);
        JSONObject jsonResponse = new JSONObject(response.asString());

        assertEquals(200, response.getStatusCode(), "Expected status code 200");
        assertEquals(PRODUCT_DENIM_SHIRT_NAME, jsonResponse.getJSONObject("data").getJSONObject
                ("attributes").getString("name"), "The product name does not match the expected value.");
        String actualPrice = jsonResponse.getJSONObject("data").getJSONObject("attributes").getString("price");
        assertEquals(PRODUCT_DENIM_SHIRT_PRICE, actualPrice, "The product price does not match the expected value.");

        System.out.println(jsonResponse.toString(4));
    }




}
