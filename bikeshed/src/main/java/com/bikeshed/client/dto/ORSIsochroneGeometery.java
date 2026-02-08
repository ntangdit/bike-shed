package com.bikeshed.client.dto;

import com.bikeshed.serialization.deserializer.PolygonDeserializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import org.locationtech.jts.geom.Polygon;

import java.util.List;

@Data
public class ORSIsochroneGeometery {
    @JsonProperty("type")
    private String type;

    @JsonDeserialize(using = PolygonDeserializer.class)
    @JsonProperty("coordinates")
    private Polygon coordinates;
}
