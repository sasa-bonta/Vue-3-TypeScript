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
    protected VehicleDao<T> vehicleDao;

    public List<T> getAll() {
        return vehicleDao.findAll();
    }

    public T create(T vehicle) {
        vehicle.setState(new AvailableState());
        vehicle.setDeleted(false);
        return vehicleDao.save(vehicle);
    }

    public void delete(Integer id) {
        T fetchedVehicle = vehicleDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Vehicle with ID " + id + " not found."));

        fetchedVehicle.setDeleted(true);

        vehicleDao.save(fetchedVehicle);
    }

    public T clone(Integer id, T vehicle) throws CloneNotSupportedException {
        T fetchedVehicle = vehicleDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Bike with ID " + id + " not found."));

        T newVehicle = (T) fetchedVehicle.clone();

        newVehicle.setId(null);
        newVehicle.setVin(vehicle.getVin());
        newVehicle.setYear(vehicle.getYear());
        newVehicle.setMileage(vehicle.getMileage());
        newVehicle.setPhoto(vehicle.getPhoto());

        vehicleDao.save(newVehicle);

        return newVehicle;
    }
}
