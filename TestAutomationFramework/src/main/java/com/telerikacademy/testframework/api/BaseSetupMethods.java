package com.telerikacademy.testframework.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static com.telerikacademy.testframework.api.utils.Endpoints.BASE_URL;
import static io.restassured.RestAssured.given;

public class BaseSetupMethods {

    public String CreateRefreshToken(String username, String password) {
        RestAssured.baseURI = BASE_URL;

        String requestBody = "{ " +
                "\"grant_type\": \"password\", " +
                "\"username\": \"" + username + "\", " +
                "\"password\": \"" + password + "\" }";

        Response response = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/spree_oauth/token")
                .then()
                .extract()
                .response();

        String token = response.jsonPath().getString("access_token");
        System.out.println("Token: " + token);
        return token;
    }

}



