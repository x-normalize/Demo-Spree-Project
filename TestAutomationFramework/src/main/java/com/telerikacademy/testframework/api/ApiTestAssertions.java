package com.telerikacademy.testframework.api;

import org.junit.jupiter.api.Assertions;

import static org.apache.http.HttpStatus.SC_OK;

public class ApiTestAssertions extends BaseSetupMethods{

    public final BaseSetupMethods baseMethods = new BaseSetupMethods();

    public void assertStatusCode200(int statusCode) {
        Assertions.assertEquals(SC_OK, statusCode, "Incorrect status code. Expected 200.");
        System.out.println("Status Code is 200.");
    }
}
