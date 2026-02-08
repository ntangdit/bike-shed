package com.bikeshed.client.dto;


import lombok.Data;
import java.util.List;

@Data
public class ORSIsochroneResponse {
    private String type;
    private double[] bbox;
    private List<ORSIsochroneFeature> features;
}
