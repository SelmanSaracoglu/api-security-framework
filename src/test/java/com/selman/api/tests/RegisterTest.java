package com.selman.api.tests;

import com.github.javafaker.Faker;
import com.selman.api.base.BaseTest;
import com.selman.api.pojo.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RegisterTest extends BaseTest {

    Faker faker = new Faker();

    @Test(description = "User should be able to create an account via API")
    public void testCreateAccount() {

        // STEP 1: Prepare Data (POJO & Builder Pattern)
        // Utilizing Object-Oriented principles to manage test data dynamically.
        User userRequest = User.builder()
                .name(faker.name().username())
                .email(faker.internet().emailAddress())
                .password(faker.internet().password())
                .title("Mr")
                .birth_date("10")
                .birth_month("May")
                .birth_year("1990")
                .firstname(faker.name().firstName())
                .lastname(faker.name().lastName())
                .company(faker.company().name())
                .address1(faker.address().fullAddress())
                .country("Canada")
                .zipcode(faker.address().zipCode())
                .state(faker.address().state())
                .city(faker.address().city())
                .mobile_number(faker.phoneNumber().cellPhone())
                .build();

        // STEP 2: Send Request (Rest Assured)
        // Note: The 'createAccount' endpoint expects Form-Data, not JSON body.
        // We map our POJO fields to form parameters.
        Response response = given()
                .spec(spec) // Use configuration from BaseTest
                .contentType(ContentType.URLENC) // Content-Type: application/x-www-form-urlencoded
                .formParam("name", userRequest.getName())
                .formParam("email", userRequest.getEmail())
                .formParam("password", userRequest.getPassword())
                .formParam("title", userRequest.getTitle())
                .formParam("birth_date", userRequest.getBirth_date())
                .formParam("birth_month", userRequest.getBirth_month())
                .formParam("birth_year", userRequest.getBirth_year())
                .formParam("firstname", userRequest.getFirstname())
                .formParam("lastname", userRequest.getLastname())
                .formParam("company", userRequest.getCompany())
                .formParam("address1", userRequest.getAddress1())
                .formParam("country", userRequest.getCountry())
                .formParam("zipcode", userRequest.getZipcode())
                .formParam("state", userRequest.getState())
                .formParam("city", userRequest.getCity())
                .formParam("mobile_number", userRequest.getMobile_number())
                .when()
                .post("/createAccount");
        // STEP 3: Verification (Assertion)
        response.prettyPrint(); // Print the response body to the console for debugging

        // Assert status code is 201 (Created)
        Assert.assertEquals(response.getStatusCode(), 200, "Status code is incorrect!");

        // Assert response message
        Assert.assertEquals(response.jsonPath().getString("message"), "User created!", "Success message mismatch!");

        System.out.println("✅ TEST PASSED: Created user -> " + userRequest.getEmail());
    }



}
