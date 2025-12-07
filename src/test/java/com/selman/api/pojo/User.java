package com.selman.api.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Automatically generates Getters, Setters, toString, etc.
@Builder // Allows creating objects using method chaining (Builder pattern)
@NoArgsConstructor // Generates a no-arguments constructor
@AllArgsConstructor // Generates a constructor with all arguments
@JsonIgnoreProperties(ignoreUnknown = true) // Ignore any extra fields returned by the API to prevent errors
public class User {
    private String name;
    private String email;
    private String password;
    private String title;
    private String birth_date;
    private String birth_month;
    private String birth_year;
    private String firstname;
    private String lastname;
    private String company;
    private String address1;
    private String address2;
    private String country;
    private String zipcode;
    private String state;
    private String city;
    private String mobile_number;
}
