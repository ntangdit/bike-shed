package com.bikeshed.bikeshed;

import com.bikeshed.serialization.deserializer.PolygonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Polygon;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BikeshedApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void testJtsModuleWorking() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		SimpleModule module = new SimpleModule();
		module.addDeserializer(Polygon.class, new PolygonDeserializer());
		mapper.registerModule(module);

		String geoJson = """
            {
                "type": "Polygon",
                "coordinates": [[[0,0], [1,0], [1,1], [0,1], [0,0]]]
            }
            """;

		System.out.println("Testing GeoJSON deserialization:");
		System.out.println("Input JSON: " + geoJson);

		try {
			Polygon polygon = mapper.readValue(geoJson, Polygon.class);
			System.out.println("✅ SUCCESS: Deserialized polygon with " + polygon.getNumPoints() + " points");
			System.out.println("Polygon: " + polygon);
		} catch (Exception e) {
			System.out.println("❌ FAILED: " + e.getClass().getSimpleName() + ": " + e.getMessage());
			e.printStackTrace();
		}
	}
}
