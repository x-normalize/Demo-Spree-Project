package com.telerikacademy.testframework.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;

import static com.telerikacademy.testframework.api.utils.Endpoints.BASE_URL;
import static com.telerikacademy.testframework.api.utils.Endpoints.TOKEN_ENDPOINT;
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

}



