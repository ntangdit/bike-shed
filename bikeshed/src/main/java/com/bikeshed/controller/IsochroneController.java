package com.bikeshed.controller;

import com.bikeshed.dto.IsochroneGetArgs;
import com.bikeshed.service.IsochroneService;
import org.locationtech.jts.geom.Polygon;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class IsochroneController {
    private final IsochroneService isochroneService;

    public IsochroneController(IsochroneService isochroneService) {
        this.isochroneService = isochroneService;
    }

    @GetMapping("/isochrone")
    public String IsochroneEndpoint(@RequestBody IsochroneGetArgs location) {
        Polygon result =  isochroneService.getIsochrone(location.getLatLon());
        return result.toString();
    }
}
