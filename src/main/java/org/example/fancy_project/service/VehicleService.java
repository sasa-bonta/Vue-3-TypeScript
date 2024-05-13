package org.example.fancy_project.service;

import org.example.fancy_project.classes.Vehicle;
import org.example.fancy_project.classes.state.AvailableState;
import org.example.fancy_project.dao.VehicleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class VehicleService<T extends Vehicle> {
    @Autowired
    VehicleDao<T> vehicleDao;

    public List<T> getAll() {
        return vehicleDao.findAll();
    }

    public T create(T vehicle) {
        vehicle.setState(new AvailableState());
        return vehicleDao.save(vehicle);
    }

    public void delete(Integer id) {
        vehicleDao.deleteById(id);
    }
}
