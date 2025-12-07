package com.selman.api.base;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected RequestSpecification spec;

    @BeforeMethod
    public void setUp() {

        RestAssured.baseURI = "https://automationexercise.com/api";

        spec = new RequestSpecBuilder()
                .setBaseUri("https://automationexercise.com/api")
                .addHeader("Content-Type", "application/json")
                .build();
    }
}
