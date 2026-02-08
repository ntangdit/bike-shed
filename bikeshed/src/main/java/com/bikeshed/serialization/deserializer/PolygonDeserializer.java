package com.bikeshed.serialization.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LinearRing;
import org.locationtech.jts.geom.Polygon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Custom Jackson deserializer for org.locationtech.jts.geom.Polygon
 * Handles GeoJSON format polygon deserialization
 */
public class PolygonDeserializer extends JsonDeserializer<Polygon> {

    private final GeometryFactory geometryFactory;

    public PolygonDeserializer() {
        this.geometryFactory = new GeometryFactory();
    }

    public PolygonDeserializer(GeometryFactory geometryFactory) {
        this.geometryFactory = geometryFactory != null ? geometryFactory : new GeometryFactory();
    }

    @Override
    public Polygon deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        if (parser.getCurrentToken() == JsonToken.START_OBJECT) {
            return deserializeGeoJsonPolygon(parser, context);
        } else if (parser.getCurrentToken() == JsonToken.START_ARRAY) {
            return deserializeCoordinateArray(parser, context);
        } else {
            throw new IOException("Expected JSON object or array for Polygon deserialization");
        }
    }

    /**
     * Deserializes a GeoJSON Polygon object
     * Expected format: {"type": "Polygon", "coordinates": [[[x,y], [x,y], ...]]}
     */
    private Polygon deserializeGeoJsonPolygon(JsonParser parser, DeserializationContext context) throws IOException {
        String type = null;
        List<List<Coordinate>> coordinatesList = null;

        while (parser.nextToken() != JsonToken.END_OBJECT) {
            String fieldName = parser.getCurrentName();
            parser.nextToken();

            switch (fieldName) {
                case "type":
                    type = parser.getValueAsString();
                    if (!"Polygon".equals(type)) {
                        throw new IOException("Expected type 'Polygon', got: " + type);
                    }
                    break;
                case "coordinates":
                    coordinatesList = parseCoordinatesArray(parser);
                    break;
                default:
                    parser.skipChildren(); // Skip unknown fields
                    break;
            }
        }

        if (coordinatesList == null || coordinatesList.isEmpty()) {
            throw new IOException("Polygon coordinates are required");
        }

        return createPolygon(coordinatesList);
    }

    /**
     * Deserializes a coordinate array directly
     * Expected format: [[[x,y], [x,y], ...], [[x,y], [x,y], ...]]
     */
    private Polygon deserializeCoordinateArray(JsonParser parser, DeserializationContext context) throws IOException {
        List<List<Coordinate>> coordinatesList = parseCoordinatesArray(parser);

        if (coordinatesList.isEmpty()) {
            throw new IOException("Polygon coordinates cannot be empty");
        }

        return createPolygon(coordinatesList);
    }

    /**
     * Parses the coordinates array from JSON
     */
    private List<List<Coordinate>> parseCoordinatesArray(JsonParser parser) throws IOException {
        List<List<Coordinate>> coordinatesList = new ArrayList<>();

        if (parser.getCurrentToken() != JsonToken.START_ARRAY) {
            throw new IOException("Expected array for coordinates");
        }

        while (parser.nextToken() != JsonToken.END_ARRAY) {
            if (parser.getCurrentToken() != JsonToken.START_ARRAY) {
                throw new IOException("Expected array for ring coordinates");
            }

            List<Coordinate> ring = new ArrayList<>();
            while (parser.nextToken() != JsonToken.END_ARRAY) {
                if (parser.getCurrentToken() != JsonToken.START_ARRAY) {
                    throw new IOException("Expected array for coordinate pair");
                }

                Coordinate coord = parseCoordinate(parser);
                ring.add(coord);
            }

            coordinatesList.add(ring);
        }

        return coordinatesList;
    }

    /**
     * Parses a single coordinate from JSON array [x, y] or [x, y, z]
     */
    private Coordinate parseCoordinate(JsonParser parser) throws IOException {
        double x = 0, y = 0;
        int coordinateIndex = 0;

        while (parser.nextToken() != JsonToken.END_ARRAY) {
            double value = parser.getDoubleValue();

            switch (coordinateIndex) {
                case 0:
                    x = value;
                    break;
                case 1:
                    y = value;
                    break;
                default:
                    // Ignore additional coordinates beyond z
                    break;
            }
            coordinateIndex++;
        }

        if (coordinateIndex < 2) {
            throw new IOException("Coordinate must have at least x and y values");
        }

        return  new Coordinate(x, y);
    }

    /**
     * Creates a JTS Polygon from the parsed coordinates
     */
    private Polygon createPolygon(List<List<Coordinate>> coordinatesList) throws IOException {
        if (coordinatesList.isEmpty()) {
            throw new IOException("Polygon must have at least one ring");
        }

        // First ring is the exterior (shell)
        List<Coordinate> shellCoords = coordinatesList.get(0);
        if (shellCoords.size() < 4) {
            throw new IOException("Polygon exterior ring must have at least 4 coordinates");
        }

        // Ensure the ring is closed
        if (!shellCoords.get(0).equals2D(shellCoords.get(shellCoords.size() - 1))) {
            shellCoords.add(new Coordinate(shellCoords.get(0)));
        }

        LinearRing shell = geometryFactory.createLinearRing(shellCoords.toArray(new Coordinate[0]));

        return geometryFactory.createPolygon(shell);
    }
}