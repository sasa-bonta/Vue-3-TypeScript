package org.example.fancy_project.controller;

import org.example.fancy_project.classes.Vehicle;
import org.example.fancy_project.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("vehicle")
public abstract class VehicleController<T extends Vehicle> {

    @Autowired
    VehicleService<T> vehicleService;

    @RequestMapping(value = "getAll", method = RequestMethod.GET)
    public List<T> getAll() {
        return vehicleService.getAll();
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public T create(@RequestBody T vehicle) {
        return vehicleService.create(vehicle);
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Integer id) {
        vehicleService.delete(id);
    }
}
