package org.example.fancy_project.dao;

import org.example.fancy_project.classes.bike.BikeRent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BikeRentDao extends JpaRepository<BikeRent, Integer> {
}
