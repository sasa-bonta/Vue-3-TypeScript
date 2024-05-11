package org.example.fancy_project.dao;

import org.example.fancy_project.classes.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceDao extends JpaRepository<Service, Integer> {
}
