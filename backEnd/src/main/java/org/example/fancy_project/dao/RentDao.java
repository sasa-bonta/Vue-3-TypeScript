package org.example.fancy_project.dao;

import org.example.fancy_project.classes.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentDao<T extends Rent> extends JpaRepository<T, Integer> {
}
