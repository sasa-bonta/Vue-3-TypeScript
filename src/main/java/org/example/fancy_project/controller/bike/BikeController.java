package org.example.fancy_project.controller.bike;

import org.example.fancy_project.classes.bike.Bike;
import org.example.fancy_project.service.bike.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("bike")
public class BikeController {

    @Autowired
    BikeService bikeService;

    @RequestMapping(value = "getAllBikes", method = RequestMethod.GET)
    public List<Bike> getAllBikes(){
        return bikeService.getAllBikes();
    }

    @RequestMapping(value = "createRent", method = RequestMethod.POST)
    public Bike createBike(@RequestBody Bike bike){
        return bikeService.createBike(bike);
    }

    @RequestMapping(value = "deleteBike/{bikeId}", method = RequestMethod.DELETE)
    public void deleteBike(@PathVariable Integer id){
        bikeService.deleteBike(id);
    }
}
