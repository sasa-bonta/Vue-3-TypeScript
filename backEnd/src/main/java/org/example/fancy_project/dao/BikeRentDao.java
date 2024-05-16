package org.example.fancy_project.dao;

import org.example.fancy_project.classes.bike.BikeRent;
import org.springframework.stereotype.Repository;

@Repository
public interface BikeRentDao extends RentDao<BikeRent> {
}
