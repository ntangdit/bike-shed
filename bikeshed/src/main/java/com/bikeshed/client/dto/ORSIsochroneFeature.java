package com.bikeshed.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class ORSIsochroneFeature {
    @JsonProperty("type")
    private String type;

    @JsonProperty("properties")
    private Map<String, Object> properties;

    @JsonProperty("geometry")
    private ORSIsochroneGeometery geometry;
}
