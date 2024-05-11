package org.example.fancy_project.dao;

import org.example.fancy_project.classes.bike.Bike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface BikeDao extends JpaRepository <Bike, Integer> {

}
