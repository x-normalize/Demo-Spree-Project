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

}



