package org.example.fancy_project.service.car;

import org.example.fancy_project.classes.car.Car;
import org.example.fancy_project.service.VehicleService;
import org.springframework.stereotype.Service;

@Service
public class CarService extends VehicleService<Car> {
    // No additional methods required, as the template methods from VehicleService are used.
}
