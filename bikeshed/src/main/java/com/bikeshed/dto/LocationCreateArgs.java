package com.bikeshed.dto;

import lombok.Data;

@Data
public class LocationCreateArgs {

    private String name;

    private double x;

    private double y;
}
