package com.selman.api.tests;

import com.github.javafaker.Faker;
import com.selman.api.base.BaseTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.module.ResolutionException;

import static io.restassured.RestAssured.given;

public class DeleteAccountTest extends BaseTest {
    Faker faker = new Faker();

    @Test(description = "Verify DELETE account functionality")
    public void testDeleteAccount() {

        // --- PRE-CONDITION: Create a User to Delete ---
        String email =faker.internet().emailAddress();
        String password = "TestPassword123!";
        String name = faker.name().username();

        System.out.println("🆕 Creating a temporary user for deletion: " + email);

        given()
                .spec(spec)
                .contentType(ContentType.URLENC)
                .formParam("name", name)
                .formParam("email", email)
                .formParam("password", password)
                .formParam("title", "Mr")
                .formParam("birth_date", "10")
                .formParam("birth_month", "May")
                .formParam("birth_year", "1990")
                .formParam("firstname", "Test")
                .formParam("lastname", "User")
                .formParam("company", "Company")
                .formParam("address1", "Address")
                .formParam("country", "Canada")
                .formParam("zipcode", "10001")
                .formParam("state", "State")
                .formParam("city", "City")
                .formParam("mobile_number", "1234567890")
            .when()
                .post("/createAccount")
            .then()
                .statusCode(200);

        // --- THE ACTUAL TEST: DELETE REQUEST ---
        System.out.println("🗑️ Deleting the user...");

        Response response = given()
                .spec(spec)
                .contentType(ContentType.URLENC)
                .formParam("email", email)
                .formParam("password", password)
                .when()
                .delete("/deleteAccount");

        // --- VERIFICATION ---
        // Debugging the response
        System.out.println("Response Body: " + response.asString());

        // Assertions
        Assert.assertEquals(response.getStatusCode(), 200, "Status code mismatch!");

        // Message verification
        Assert.assertEquals(response.jsonPath().getString("message"), "Account deleted!", "Delete message mismatch!");

        System.out.println("✅ User deleted successfully!");
    }
}
