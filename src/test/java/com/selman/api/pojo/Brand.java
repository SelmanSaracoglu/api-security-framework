package com.selman.api.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Brand {

    private int id;
    private String brand;

    // Default Constructor
    public Brand(){
    }

    // --- Getters and Setters ---


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
