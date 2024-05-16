package org.example.fancy_project.dao;

import org.example.fancy_project.classes.bike.Bike;
import org.springframework.stereotype.Repository;

@Repository
public interface BikeDao extends VehicleDao<Bike> {
}
