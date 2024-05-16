package org.example.fancy_project.controller.car;

import org.example.fancy_project.classes.car.CarRent;
import org.example.fancy_project.controller.RentController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rent/car")
public class CarRentController extends RentController<CarRent> {
    // No additional methods required, as the template methods from RentController are used.
}
