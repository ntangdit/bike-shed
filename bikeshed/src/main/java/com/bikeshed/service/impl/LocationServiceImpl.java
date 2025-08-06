package com.bikeshed.service.impl;

import com.bikeshed.data.entity.Location;
import com.bikeshed.data.repository.LocationRepository;
import com.bikeshed.dto.LocationCreateArgs;
import com.bikeshed.service.LocationService;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

@Service
public class LocationServiceImpl implements LocationService {
    private final LocationRepository locationRepository;
    private final GeometryFactory geometryFactory = new GeometryFactory();

    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public void addLocation(LocationCreateArgs locationCreateArgs) {
        Location location = new Location();
        Point point = geometryFactory.createPoint(new Coordinate(locationCreateArgs.getX(), locationCreateArgs.getY()));
        location.setGeom(point);
        location.setName(locationCreateArgs.getName());
        locationRepository.save(location);
    }
}
