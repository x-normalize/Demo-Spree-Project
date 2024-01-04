package com.telerikacademy.testframework.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
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

    public static void createRequestBodyForUpdateAddress(JSONObject addressUpdateDetails) {
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


    public void deleteAddress(String token, int addressId) {
        given()
                .header("Authorization", "Bearer " + token)
                .header("Accept", "application/vnd.api+json")
                .when()
                .log().all()
                .delete(ADDRESS_ENDPOINT + "/" + addressId)
                .then()
                .statusCode(204);
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

}



