package com.telerikacademy.testframework.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import static com.telerikacademy.testframework.api.utils.Endpoints.*;
import static io.restassured.RestAssured.given;

public class BaseSetupMethods {

    public String createRefreshToken(String username, String password) {
        RestAssured.baseURI = BASE_URL;

        String requestBody = "{ " +
                "\"grant_type\": \"password\", " +
                "\"username\": \"" + username + "\", " +
                "\"password\": \"" + password + "\" }";

        Response response = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post(TOKEN_ENDPOINT)
                .then()
                .extract()
                .response();

        String token = response.jsonPath().getString("access_token");
        System.out.println("Token: " + token);
        return token;
    }


    public String unauthorizedAccess(String endpoint) {
        Response response = given()
                .when()
                .get(endpoint)
                .andReturn();

        response.then().statusCode(403);

        JSONObject jsonResponse = new JSONObject(response.asString());
        String errorMessage = jsonResponse.getString("error");
        System.out.println(jsonResponse.toString(4));

        return errorMessage;
    }

    public Response getUserDetails(String token) {
        return given()
                .auth()
                .oauth2(token)
                .log().all()
                .when()
                .get(ACCOUNT_ENDPOINT)
                .andReturn();
    }

    public Response createAccount(String email, String password) {
        JSONObject userObject = new JSONObject();
        userObject.put("email", email);
        userObject.put("password", password);
        userObject.put("passwordConfirmation", password);

        JSONObject requestBody = new JSONObject();
        requestBody.put("user", userObject);

        return given()
                .contentType(ContentType.JSON)
                .body(requestBody.toString())
                .log().all()
                .when()
                .post(ACCOUNT_ENDPOINT)
                .andReturn();
    }

    public Response createAccountWithMissingFields(JSONObject requestBody) {
        return given()
                .contentType(ContentType.JSON)
                .body(requestBody.toString())
                .log().all()
                .when()
                .post(ACCOUNT_ENDPOINT)
                .andReturn();
    }

    public static JSONObject createRequestBodyForNewAddress() {
        JSONObject addressDetails = new JSONObject();
        addressDetails.put("firstname", "Mark");
        addressDetails.put("lastname", "Winterburn");
        addressDetails.put("company", "Paper Street Soap Co.");
        addressDetails.put("address1", "775 Old Georgetown Road");
        addressDetails.put("address2", "3rd Floor");
        addressDetails.put("city", "Qethesda");
        addressDetails.put("phone", "3488545445002");
        addressDetails.put("zipcode", "90210");
        addressDetails.put("state_name", "CA");
        addressDetails.put("country_iso", "US");
        addressDetails.put("label", "Work");

        JSONObject createRequestBody = new JSONObject();
        createRequestBody.put("address", addressDetails);
        return createRequestBody;
    }

    public static void prepareUpdateAddressRequestBody(JSONObject addressUpdateDetails) {
        addressUpdateDetails.put("firstname", "Stephen");
        addressUpdateDetails.put("address1", "234 Old Georgetown Road");
    }

    public JSONObject createAddress(String token, JSONObject requestBody) {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .header("Accept", "application/vnd.api+json")
                .header("Content-Type", "application/vnd.api+json")
                .body(requestBody.toString())
                .log().all()
                .when()
                .post(ADDRESS_ENDPOINT)
                .then()
                .statusCode(200)
                .extract()
                .response();

        return new JSONObject(response.asString());
    }

    public JSONObject updateAddress(String token, int addressId, JSONObject updateDetails) {
        JSONObject updateRequestBody = new JSONObject();
        updateRequestBody.put("address", updateDetails);

        Response response = given()
                .header("Authorization", "Bearer " + token)
                .header("Accept", "application/vnd.api+json")
                .header("Content-Type", "application/vnd.api+json")
                .body(updateRequestBody.toString())
                .log().all()
                .when()
                .patch(ADDRESS_ENDPOINT + "/" + addressId)
                .then()
                .statusCode(200)
                .extract()
                .response();

        return new JSONObject(response.asString());
    }


    public Response deleteAddress(String token, int addressId) {
        return given()
                .header("Authorization", "Bearer " + token)
                .header("Accept", "application/vnd.api+json")
                .when()
                .log().all()
                .delete(ADDRESS_ENDPOINT + "/" + addressId)
                .then()
                .extract()
                .response();
    }

    public static Response listUserAddress(String token) {
        return given()
                .header("Authorization", "Bearer " + token)
                .header("Accept", "application/vnd.api+json")
                .when()
                .get(ACCOUNT_ENDPOINT)
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response();
    }

    public static Response listAllProducts() {
        return given()
                .header("Accept", "application/vnd.api+json")
                .when()
                .log().all()
                .get(PRODUCT_ENDPOINT)
                .then()
                .extract()
                .response();
    }

    public static Response singleProductData(String productData) {
        return given()
                .header("Accept", "application/vnd.api+json")
                .when()
                .log().all()
                .get(PRODUCT_ENDPOINT + productData)
                .then()
                .extract()
                .response();
    }

    public static Response addItemToCart(String cartToken, String variantId, int quantity) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("variant_id", variantId);
        requestBody.put("quantity", quantity);
        requestBody.put("public_metadata", new JSONObject().put("first_item_order", true));
        requestBody.put("private_metadata", new JSONObject().put("recommended_by_us", false));

        return given()
                .header("Accept", "application/vnd.api+json")
                .header("Content-Type", "application/vnd.api+json")
                .header("X-Spree-Order-Token", cartToken)
                .body(requestBody.toString())
                .log().all()
                .when()
                .post(ADD_ITEM_ENDPOINT)
                .then()
                .extract()
                .response();
    }

    public static Response deleteItemToCart(String token, String lineItemId) {
        String url = DELETE_ITEM_ENDPOINT.replace("{id}", lineItemId);

        return given()
                .header("X-Spree-Order-Token", token)
                .header("Accept", "application/vnd.api+json")
                .header("Content-Type", "application/vnd.api+json")
                .when()
                .log().all()
                .delete(url)
                .then()
                .extract()
                .response();
    }

    public JSONObject createCart() {
        RestAssured.baseURI = BASE_URL;

        JSONObject requestBody = new JSONObject();
        requestBody.put("public_metadata", new JSONObject().put("total_weight", 3250));
        requestBody.put("private_metadata", new JSONObject().put("had_same_cart_items", true));

        Response response = given()
                .header("Accept", "application/vnd.api+json")
                .header("Content-Type", "application/vnd.api+json")
                .body(requestBody.toString())
                .log().all()
                .when()
                .post(BASE_URL + ADD_CART_TOKEN_ENDPOINT)
                .then()
                .statusCode(201)
                .extract()
                .response();

        return new JSONObject(response.asString());
    }

    public static String extractTheItemID(Response addItem) {
        JSONObject jsonResponse = new JSONObject(addItem.asString());
        JSONArray lineItems = jsonResponse.getJSONObject("data")
                .getJSONObject("relationships")
                .getJSONObject("line_items")
                .getJSONArray("data");
        return lineItems.getJSONObject(0).getString("id");
    }

}



