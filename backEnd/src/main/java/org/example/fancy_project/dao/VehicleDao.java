package org.example.fancy_project.dao;

import org.example.fancy_project.classes.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleDao<T extends Vehicle> extends JpaRepository<T, Integer> {
}
