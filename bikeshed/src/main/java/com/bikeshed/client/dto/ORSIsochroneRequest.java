package com.bikeshed.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ORSIsochroneRequest {
    // Getters and setters
    @JsonProperty("locations")
    private List<List<Double>> locations;

    @JsonProperty("range")
    private List<Integer> range;

    @JsonProperty("range_type")
    private String rangeType = "time";

    // Optional parameters
    @JsonProperty("interval")
    private Integer interval;

    @JsonProperty("units")
    private String units = "m"; // meters for distance, seconds for time

    @JsonProperty("smoothing")
    private Double smoothing;

    // Constructors
    public ORSIsochroneRequest() {}

    public ORSIsochroneRequest(List<List<Double>> locations, List<Integer> range) {
        this.locations = locations;
        this.range = range;
    }

    public ORSIsochroneRequest(double longitude, double latitude, int rangeInSeconds) {
        this.locations = List.of(List.of(longitude, latitude));
        this.range = List.of(rangeInSeconds);
    }

    // Static factory methods for convenience
    public static ORSIsochroneRequest forLocation(String locationString, int minutes) {
        String[] coords = locationString.split(",");
        double longitude = Double.parseDouble(coords[0]);
        double latitude = Double.parseDouble(coords[1]);
        return new ORSIsochroneRequest(longitude, latitude, minutes * 60);
    }

    public static ORSIsochroneRequest forCoordinates(double longitude, double latitude, int minutes) {
        return new ORSIsochroneRequest(longitude, latitude, minutes * 60);
    }

    @Override
    public String toString() {
        return "ORSIsochroneRequest{" +
                "locations=" + locations +
                ", range=" + range +
                ", rangeType='" + rangeType + '\'' +
                ", interval=" + interval +
                ", units='" + units + '\'' +
                ", smoothing=" + smoothing +
                '}';
    }
}
