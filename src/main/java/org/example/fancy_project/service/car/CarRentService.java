package org.example.fancy_project.service.car;

import ch.qos.logback.core.joran.spi.ActionException;
import org.example.fancy_project.classes.car.Car;
import org.example.fancy_project.classes.car.CarRent;
import org.example.fancy_project.dao.CarDao;
import org.example.fancy_project.dao.CarRentDao;
import org.example.fancy_project.service.RentService;
import org.springframework.stereotype.Service;

@Service
public class CarRentService extends RentService<CarRent> {
    final CarRentDao carRentDao;
    final CarDao carDao;

    public CarRentService(CarRentDao carRentDao, CarDao carDao) {
        this.carRentDao = carRentDao;
        this.carDao = carDao;
    }

    public CarRent createRent(CarRent carRent) {
        carRent.setStartDate(getCurrentDateTimeString());
        carRent.setFinishDate(null);

        Car fetchedCar = carDao.findById(carRent.getCar().getId())
                .orElseThrow(() -> new IllegalArgumentException("CarRent with ID " + carRent.getCar().getId() + " not found."));
        carRent.setCar(fetchedCar);

        return carRentDao.save(carRent);
    }

    public CarRent endRent(Integer id) throws ActionException {
        CarRent fetchedRent = carRentDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("CarRent with ID " + id + " not found."));

        if (fetchedRent.getFinishDate() != null) {
            fetchedRent.setFinishDate(getCurrentDateTimeString());
            carRentDao.save(fetchedRent);

            return fetchedRent;
        } else {
            throw new ActionException("CarRent already finished");
        }
    }
}
