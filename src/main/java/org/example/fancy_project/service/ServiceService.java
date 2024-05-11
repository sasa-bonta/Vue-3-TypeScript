package org.example.fancy_project.service;


import org.example.fancy_project.dao.ServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceService {
    @Autowired
    ServiceDao serviceDao;
    public List<org.example.fancy_project.classes.Service> getAllServices() {
        return serviceDao.findAll();
    }

    public org.example.fancy_project.classes.Service createService(org.example.fancy_project.classes.Service service){
        return serviceDao.save(service);
    }

    public void deleteService(Integer id) {
        serviceDao.deleteById(id);
    }
}
