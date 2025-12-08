package com.selman.api.tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.selman.api.base.BaseTest;
import com.selman.api.pojo.Brand;
import com.selman.api.pojo.BrandListResponse;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class GetAllBrandsTest extends BaseTest {

    @Test(description = "Verify that all brands are retrieved successfully (API 3)")
    public void testGetAllBrands() throws JsonProcessingException {
        // STEP 1: Send GET Request
        System.out.println("🌍 Sending GET request to /brandsList...");

        Response response = given()
                .spec(spec)
                .when()
                .get("/brandsList");

        // STEP 2: Verify Status Code
        System.out.println("Status Code: " + response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 200);

        // STEP 3: Handle Response Parsing (Manual)
        // Since API returns HTML header for JSON body, we use ObjectMapper manually.
        String jsonString = response.asString();

        ObjectMapper mapper = new ObjectMapper();
        BrandListResponse responseBody = mapper.readValue(jsonString, BrandListResponse.class);

        // STEP 4: Data Validation
        List<Brand> brands = responseBody.getBrands();

        System.out.println("Total Brands  Found: " + brands.size());
        Assert.assertFalse(brands.isEmpty(), "Brands list should not be empty");

        // Iterate with a loop to find a specific brand (e.g., 'Polo')
        boolean isPoloFound = false;

        for (Brand brand : brands) {
            if (brand.getBrand().equalsIgnoreCase("Polo")) {
                isPoloFound = true;
                System.out.println("Found Brand: " + brand.getBrand() + " (ID: " + brand.getId() + ")");
                break;
            }
        }

        Assert.assertTrue(isPoloFound);
    }
}
