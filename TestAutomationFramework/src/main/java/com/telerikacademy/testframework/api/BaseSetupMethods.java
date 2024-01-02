package com.telerikacademy.testframework.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.number.OrderingComparison.greaterThan;

public class BaseSetupMethods {

    public String CreateRefreshToken() {
        // Set the base URI for the API
        RestAssured.baseURI = "https://demo.spreecommerce.org";

        // Prepare the request body
        String requestBody = "{ " +
                "\"grant_type\": \"password\", " +
                "\"username\": \"heidi.dixon@example.com\", " + // replace with actual username
                "\"password\": \"lightning\" }"; // replace with actual password

        // Make the POST request to create/refresh the token
        Response response = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/spree_oauth/token")
                .then()
                .extract()
                .response();

        // Extract and return the token
        String token = response.jsonPath().getString("access_token");
        System.out.println("Token: " + token);
        return token;
    }

    public Response testGetAddressesList() {

        // Retrieve the token and ensure login is successful
        String token = CreateRefreshToken();

        Response response = given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/api/v2/storefront/account/addresses")
                .then()
                .statusCode(200)
                .body("data", hasSize(greaterThan(0)))
                .body("data[0]", hasKey("id"))
                // Add more assertions as needed
                .extract()
                .response();

        // Convert the response to a JSONObject for pretty printing
        JSONObject jsonResponse = new JSONObject(response.asString());
        System.out.println(jsonResponse.toString(4)); // '4' is the indentation factor for pretty printing
        return response;
    }
}


