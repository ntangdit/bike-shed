package com.bikeshed.service;

import org.locationtech.jts.geom.Polygon;


public interface IsochroneService {
    Polygon getIsochrone(double[] latLon);
}
