package org.example.fancy_project.controller.bike;

import org.example.fancy_project.classes.bike.BikeRent;
import org.example.fancy_project.controller.RentController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rent/bike")
public class BikeRentController extends RentController<BikeRent> {
    // No additional methods required, as the template methods from RentController are used.
}
