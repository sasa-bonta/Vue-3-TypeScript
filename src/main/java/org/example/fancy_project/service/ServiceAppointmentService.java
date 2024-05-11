package org.example.fancy_project.service;

import org.example.fancy_project.classes.ServiceAppointment;
import org.example.fancy_project.dao.ServiceAppointmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceAppointmentService {
    @Autowired
    ServiceAppointmentDao serviceAppointmentDao;
    public List<ServiceAppointment> getAllServiceAppointments() {
        return serviceAppointmentDao.findAll();
    }

    public ServiceAppointment createServiceAppointment(ServiceAppointment serviceAppointment){
        return serviceAppointmentDao.save(serviceAppointment);
    }

    public void deleteServiceAppointment(Integer id) {
        serviceAppointmentDao.deleteById(id);
    }
}
