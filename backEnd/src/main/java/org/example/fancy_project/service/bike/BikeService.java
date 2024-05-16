package org.example.fancy_project.service.bike;

import org.example.fancy_project.classes.bike.Bike;
import org.example.fancy_project.service.VehicleService;
import org.springframework.stereotype.Service;

@Service
public class BikeService extends VehicleService<Bike> {
    // No additional methods required, as the template methods from VehicleService are used.
}
