package org.example.fancy_project.service.bike;

import ch.qos.logback.core.joran.spi.ActionException;
import org.example.fancy_project.classes.bike.Bike;
import org.example.fancy_project.classes.bike.BikeRent;
import org.example.fancy_project.classes.state.AvailableState;
import org.example.fancy_project.classes.state.NotAvailableState;
import org.example.fancy_project.dao.BikeDao;
import org.example.fancy_project.dao.BikeRentDao;
import org.example.fancy_project.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BikeRentService extends RentService<BikeRent> {
    @Autowired
    BikeRentDao bikeRentDao;
    @Autowired
    BikeDao bikeDao;

    public BikeRent createRent(BikeRent bikeRent) throws ActionException {
        bikeRent.setStartDate(getCurrentDateTimeString());
        bikeRent.setFinishDate(null);

        Bike fetchedBike = bikeDao.findById(bikeRent.getBike().getId())
                .orElseThrow(() -> new IllegalArgumentException("BikeRent with ID " + bikeRent.getBike().getId() + " not found."));

        if (fetchedBike.getState().isAvailable() && !fetchedBike.isDeleted()) {
            fetchedBike.setState(new NotAvailableState());
            bikeRent.setBike(fetchedBike);

            return bikeRentDao.save(bikeRent);
        }

        throw new ActionException("Bike not available or deleted");
    }

    public BikeRent endRent(Integer id) throws ActionException {
        BikeRent fetchedRent = bikeRentDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("BikeRent with ID " + id + " not found."));

        if (fetchedRent.getFinishDate() == null) {
            fetchedRent.setFinishDate(getCurrentDateTimeString());
            bikeRentDao.save(fetchedRent);

            Bike fetchedBike = bikeDao.findById(fetchedRent.getBike().getId())
                    .orElseThrow(() -> new IllegalArgumentException("BikeRent with ID " + fetchedRent.getBike().getId() + " not found."));

            if (!fetchedBike.getState().isAvailable()) {
                fetchedBike.setState(new AvailableState());
                bikeDao.save(fetchedBike);
            }

            return fetchedRent;
        } else {
            throw new ActionException("BikeRent already finished");
        }
    }
}
