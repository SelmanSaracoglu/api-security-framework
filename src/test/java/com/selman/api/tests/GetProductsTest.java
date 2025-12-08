package com.selman.api.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.selman.api.base.BaseTest;
import com.selman.api.pojo.Product;
import com.selman.api.pojo.ProductListResponse;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GetProductsTest extends BaseTest {

    @Test(description = "Verify all products list and check if 'Blue Top' exists")
    public void testGetAllProducts() throws Exception {
        // STEP 1: Send GET Request
        System.out.println("🌍 Sending GET request to /productsList...");

        Response response = given()
                .spec(spec)
                .when()
                .get("/productsList");

        // STEP 2: Verify Status Code
        Assert.assertEquals(response.getStatusCode(), 200, "Status Code is not 200!");

        // STEP 3: Handle Response Parsing
        // The API returns 'text/html' in header but the body is JSON.
        // RestAssured cannot auto-parse this, so I am using Jackson ObjectMapper manually.
        String jsonString = response.asString();

        ObjectMapper mapper = new ObjectMapper();
        ProductListResponse responseBody = mapper.readValue(jsonString, ProductListResponse.class);

        // Get the list of products
        List<Product> productList = responseBody.getProducts();

        // Basic Validation: Is list empty?
        System.out.println("📦 Total Products Found: " + productList.size());
        Assert.assertFalse(productList.isEmpty(), "Product list should not be empty!");

        // STEP 4: Find specific product using a Loop (Junior/Mid approach)
        // I used a classic For-Loop instead of Streams to clearly show the logic.
        boolean isProductFound = false;

        for (Product product : productList) {
            // Case-insensitive comparison
            if (product.getName().equalsIgnoreCase("Blue Top")) {
                isProductFound = true;
                System.out.println("✅ Found the product: " + product.getName());
                System.out.println("💰 Price: " + product.getPrice());
                break; // Exit loop once found to save resources
            }
        }

        // Final Assertion
        Assert.assertTrue(isProductFound, "Expected product 'Blue Top' was not found in the list!");
    }
}