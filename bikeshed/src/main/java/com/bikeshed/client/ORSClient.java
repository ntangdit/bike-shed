package com.bikeshed.client;

import org.locationtech.jts.geom.Polygon;
import org.springframework.stereotype.Component;

public interface ORSClient {
    /**
     * Get isochrone data for a location with default biking profile
     *
     * @param latLon Coordinates as "longitude,latitude" (e.g., "8.5,47.4")
     * @param minutes Travel time in minutes
     * @return GeoJSON string containing isochrone polygon
     */
    Polygon getIsochrone(double[] latLon, int minutes);

    /**
     * Check if the OpenRoute Service is available
     *
     * @return true if service is responsive, false otherwise
     */
    boolean isServiceAvailable();
}
