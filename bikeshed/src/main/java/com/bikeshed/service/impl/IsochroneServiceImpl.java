package com.bikeshed.service.impl;

import com.bikeshed.client.ORSClient;
import com.bikeshed.service.IsochroneService;
import org.locationtech.jts.geom.Polygon;
import org.springframework.stereotype.Service;

@Service
public class IsochroneServiceImpl implements IsochroneService {
    ORSClient client;

    public IsochroneServiceImpl(ORSClient client) {
        this.client = client;
    }

    public Polygon getIsochrone(double[] latLon){
        assert latLon.length == 2;
        double[] lonLat = {latLon[1], latLon[0]};
        return client.getIsochrone(lonLat, 20);
    }
}
