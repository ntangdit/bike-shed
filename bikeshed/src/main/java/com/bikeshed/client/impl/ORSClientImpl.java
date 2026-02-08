package com.bikeshed.client.impl;

import com.bikeshed.client.ORSClient;
import com.bikeshed.client.dto.ORSIsochroneFeature;
import com.bikeshed.client.dto.ORSIsochroneGeometery;
import com.bikeshed.client.dto.ORSIsochroneResponse;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Polygon;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class ORSClientImpl implements ORSClient {
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${ORS_KEY}")
    private String orsKey;


    @Override
    public Polygon getIsochrone(double[] location, int minutes) {
        try {
            String url = "https://api.openrouteservice.org/v2/isochrones/cycling-regular";
            HttpHeaders headers = createHeaders();
            Object requestBody = buildRequestBody(location, minutes);
            HttpEntity<Object> entity = new HttpEntity<Object>(requestBody, headers);

            ResponseEntity<ORSIsochroneResponse> response = restTemplate.exchange(
                    url, HttpMethod.POST, entity, ORSIsochroneResponse.class
            );
            System.out.println(response.getBody());

            if(response.getStatusCode().is2xxSuccessful()) {
//                ORSIsochroneResponse responseBody = response.getBody();
//                ORSIsochroneFeature feature = responseBody.getFeatures().getFirst();
//                ORSIsochroneGeometery geometry = feature.getGeometry();
                GeometryFactory gf = new GeometryFactory();
                return gf.createPolygon(new Coordinate[] {
                        new Coordinate(0, 0),
                        new Coordinate(4, 0),
                        new Coordinate(4, 4),
                        new Coordinate(0, 4),
                        new Coordinate(0, 0)
                });
            } else {
                throw new RuntimeException();
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            throw new RuntimeException();
        }
    }

    @Override
    public boolean isServiceAvailable() {
        try {
            String url = "https://api.openrouteservice.org/v2/health";
            HttpHeaders headers = createHeaders();
            HttpEntity<?> entity = new HttpEntity<>(headers);

            ResponseEntity<String> response = restTemplate.exchange(
                    url, HttpMethod.GET, entity, String.class
            );

            return response.getStatusCode().is2xxSuccessful();

        } catch (Exception e) {
            return false;
        }
    }

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", orsKey);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Accept", "application/json, application/geo+json, application/gpx+xml, img/png; charset=utf-8");
        return headers;
    }

    private Object buildRequestBody(double[] location, int minutes) {
        double[][] matrix = {
                Arrays.copyOf(location, 2)
        };
        return Map.of(
                "locations", matrix,
                "range", List.of(minutes * 60), // Convert minutes to seconds
                "range_type", "time"
        );
    }
}
