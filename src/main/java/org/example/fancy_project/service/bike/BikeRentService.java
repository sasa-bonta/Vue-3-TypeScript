package org.example.fancy_project.service.bike;

import ch.qos.logback.core.joran.spi.ActionException;
import org.example.fancy_project.classes.bike.Bike;
import org.example.fancy_project.classes.bike.BikeRent;
import org.example.fancy_project.dao.BikeDao;
import org.example.fancy_project.dao.BikeRentDao;
import org.example.fancy_project.service.RentService;
import org.springframework.stereotype.Service;

@Service
public class BikeRentService extends RentService<BikeRent> {
    final BikeRentDao bikeRentDao;
    final BikeDao bikeDao;

    public BikeRentService(BikeRentDao bikeRentDao, BikeDao bikeDao) {
        this.bikeRentDao = bikeRentDao;
        this.bikeDao = bikeDao;
    }

    public BikeRent createRent(BikeRent bikeRent) {
        bikeRent.setStartDate(getCurrentDateTimeString());
        bikeRent.setFinishDate(null);

        Bike fetchedBike = bikeDao.findById(bikeRent.getBike().getId())
                .orElseThrow(() -> new IllegalArgumentException("BikeRent with ID " + bikeRent.getBike().getId() + " not found."));

        bikeRent.setBike(fetchedBike);

        return bikeRentDao.save(bikeRent);
    }

    public BikeRent endRent(Integer id) throws ActionException {
        BikeRent fetchedRent = bikeRentDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("BikeRent with ID " + id + " not found."));

        if (fetchedRent.getFinishDate() != null) {
            fetchedRent.setFinishDate(getCurrentDateTimeString());
            bikeRentDao.save(fetchedRent);

            return fetchedRent;
        } else {
            throw new ActionException("BikeRent already finished");
        }
    }
}
