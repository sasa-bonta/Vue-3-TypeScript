package org.example.fancy_project.controller;

import org.example.fancy_project.classes.Rent;
import org.example.fancy_project.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rent")
public abstract class RentController<T extends Rent> {

    @Autowired
    RentService<T> rentService;

    @RequestMapping(value = "getAllRents", method = RequestMethod.GET)
    public List<T> getAllRents(){
        return rentService.getAllRents();
    }

    @RequestMapping(value = "createRent", method = RequestMethod.POST)
    public T createRent(@RequestBody T rent){
        return rentService.createRent(rent);
    }

    @RequestMapping(value = "deleteRent/{rentId}", method = RequestMethod.DELETE)
    public void deleteRent(@PathVariable Integer rentId){
        rentService.deleteRent(rentId);
    }
}
