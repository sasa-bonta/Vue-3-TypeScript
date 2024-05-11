package org.example.fancy_project.dao;

import org.example.fancy_project.classes.ServiceAppointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceAppointmentDao extends JpaRepository<ServiceAppointment, Integer> {
}
