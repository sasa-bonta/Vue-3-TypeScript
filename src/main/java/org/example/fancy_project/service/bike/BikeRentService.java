package org.example.fancy_project.service.bike;

import org.example.fancy_project.classes.bike.Bike;
import org.example.fancy_project.classes.bike.BikeRent;
import org.example.fancy_project.dao.BikeDao;
import org.example.fancy_project.dao.BikeRentDao;
import org.example.fancy_project.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class BikeRentService extends RentService<BikeRent> {
    @Autowired
    BikeRentDao bikeRentDao;
    @Autowired
    BikeDao bikeDao;

    public List<BikeRent> getAllRents() {
        return bikeRentDao.findAll();
    }

    public BikeRent createRent(BikeRent bikeRent){
        bikeRent.setStartDate(getCurrentDateTimeString());
        bikeRent.setFinishDate(null);
        Optional<Bike> optionalBike = bikeDao.findById(bikeRent.getBike().getId());

        if (optionalBike.isPresent()) {
            Bike fetchedBike = optionalBike.get();
            bikeRent.setBike(fetchedBike);
        } else {
            throw new IllegalArgumentException("BikeRent with ID " + bikeRent.getBike().getId() + " not found.");
        }

        return bikeRentDao.save(bikeRent);
    }
    public void deleteRent(Integer id) {
        bikeRentDao.deleteById(id);
    }

    public static String getCurrentDateTimeString() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }
}
