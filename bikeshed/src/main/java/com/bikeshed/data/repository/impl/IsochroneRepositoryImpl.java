package com.bikeshed.data.repository.impl;


import com.bikeshed.client.dto.ORSIsochroneFeature;
import com.bikeshed.client.dto.ORSIsochroneGeometery;
import com.bikeshed.client.dto.ORSIsochroneResponse;
import org.geolatte.geom.Geometry;
import org.locationtech.jts.geom.Polygon;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class IsochroneRepositoryImpl{
//    @Value("${ORS_KEY}")
//    private String orsKey;
//
//    private String payload = null;
//
//    public IsochroneRepositoryImpl(String payload) {
//        this.payload = payload;
//    }
//
//    public Polygon getIsochroneMap(String payload) {
//        RestTemplate restTemplate = new RestTemplate();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.set("Authorization", "eyJvcmciOiI1YjNjZTM1OTc4NTExMTAwMDFjZjYyNDgiLCJpZCI6ImI2YjkyOTJmMDA0YTQwZjI4OWIxMjI3MGZhOTNmZDg1IiwiaCI6Im11cm11cjY0In0=");
//        headers.set("Accept", "application/json, application/geo+json, application/gpx+xml, img/png; charset=utf-8");
//
//        HttpEntity<String> request = new HttpEntity<>(payload, headers);
//
//        ResponseEntity<ORSIsochroneResponse> response = restTemplate.postForEntity(
//                "https://api.openrouteservice.org/v2/isochrones/cycling-regular",
//                request,
//                ORSIsochroneResponse.class
//        );
//
//        System.out.println("Status: " + response.getStatusCode());
//        System.out.println("Body: " + response.getBody());
//
//        ORSIsochroneResponse responseBody = response.getBody();
//
//        assert responseBody != null;
//        ORSIsochroneFeature feature = responseBody.getFeatures().getFirst();
//        ORSIsochroneGeometery geometry = feature.getGeometry();
//        return geometry.getCoordinates();
//    }
}
