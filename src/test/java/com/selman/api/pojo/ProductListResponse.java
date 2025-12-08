package com.selman.api.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductListResponse {
    private int responseCode;
    private List<Product> products; // This maps the JSON array to a Java List
}
