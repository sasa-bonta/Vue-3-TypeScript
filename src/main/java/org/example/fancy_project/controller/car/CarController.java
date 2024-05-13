package org.example.fancy_project.controller.car;

import org.example.fancy_project.classes.car.Car;
import org.example.fancy_project.controller.VehicleController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("car")
public class CarController extends VehicleController<Car> {
    // No additional methods required, as the template methods from VehicleController are used.
}
