package com.bikeshed.controller;

import com.bikeshed.dto.LocationCreateArgs;
import com.bikeshed.service.LocationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/location")
public class LocationController {
    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping
    public void addLocation(@RequestBody LocationCreateArgs location) {
        locationService.addLocation(location);
    }
}
