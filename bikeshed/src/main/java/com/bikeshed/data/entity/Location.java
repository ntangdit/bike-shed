package com.bikeshed.data.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.locationtech.jts.geom.Point;

@Entity
@Table(name="locations")
@Data
@ToString
public class Location {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(columnDefinition = "geometry(Point,4326)")
    private Point geom;

    @Column(name = "name")
    private String name;
}
