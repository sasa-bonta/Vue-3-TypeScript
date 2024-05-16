package org.example.fancy_project.controller.bike;

import org.example.fancy_project.classes.bike.Bike;
import org.example.fancy_project.controller.VehicleController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("bike")
public class BikeController extends VehicleController<Bike> {
    // No additional methods required, as the template methods from VehicleController are used.
}
