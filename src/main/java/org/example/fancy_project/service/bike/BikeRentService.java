package org.example.fancy_project.service.bike;

import org.example.fancy_project.classes.bike.Bike;
import org.example.fancy_project.classes.bike.BikeRent;
import org.example.fancy_project.dao.BikeDao;
import org.example.fancy_project.dao.BikeRentDao;
import org.example.fancy_project.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BikeRentService extends RentService<BikeRent> {
    final BikeRentDao bikeRentDao;
    final BikeDao bikeDao;

    public BikeRentService(BikeRentDao bikeRentDao, BikeDao bikeDao) {
        this.bikeRentDao = bikeRentDao;
        this.bikeDao = bikeDao;
    }

    public List<BikeRent> getAllRents() {
        return bikeRentDao.findAll();
    }

    public BikeRent createRent(BikeRent bikeRent){
        bikeRent.setStartDate(getCurrentDateTimeString());
        bikeRent.setFinishDate(null);

        Bike fetchedBike = bikeDao.findById(bikeRent.getBike().getId())
                .orElseThrow(() -> new IllegalArgumentException("BikeRent with ID " + bikeRent.getBike().getId() + " not found."));
        bikeRent.setBike(fetchedBike);

        return bikeRentDao.save(bikeRent);
    }

    public BikeRent endRent(Integer id) {
        Optional<BikeRent> optionalRent = bikeRentDao.findById(id);

        if (optionalRent.isPresent()) {
            BikeRent fetchedRent = optionalRent.get();
            fetchedRent.setFinishDate(getCurrentDateTimeString());
            bikeRentDao.save(fetchedRent);

            return fetchedRent;
        } else {
            throw new IllegalArgumentException("BikeRent with ID " + id + " not found.");
        }
    }
    public void deleteRent(Integer id) {
        bikeRentDao.deleteById(id);
    }
}
