package org.example.fancy_project.controller.car;

import org.example.fancy_project.classes.car.Car;
import org.example.fancy_project.service.car.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("car")
public class CarController {

    @Autowired
    CarService carService;

    @RequestMapping(value = "getAllCars", method = RequestMethod.GET)
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    @RequestMapping(value = "createCar", method = RequestMethod.POST)
    public Car createCar(@RequestBody Car car){
        return carService.createCar(car);
    }

    @RequestMapping(value = "deleteCar/{carId}", method = RequestMethod.DELETE)
    public void deleteCar(@PathVariable Integer id){
        carService.deleteCar(id);
    }
}
