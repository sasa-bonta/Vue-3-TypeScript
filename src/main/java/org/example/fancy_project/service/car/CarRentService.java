package org.example.fancy_project.service.car;

import ch.qos.logback.core.joran.spi.ActionException;
import org.example.fancy_project.classes.car.Car;
import org.example.fancy_project.classes.car.CarRent;
import org.example.fancy_project.classes.state.AvailableState;
import org.example.fancy_project.classes.state.NotAvailableState;
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

    public CarRent createRent(CarRent carRent) throws ActionException {
        carRent.setStartDate(getCurrentDateTimeString());
        carRent.setFinishDate(null);

        Car fetchedCar = carDao.findById(carRent.getCar().getId())
                .orElseThrow(() -> new IllegalArgumentException("CarRent with ID " + carRent.getCar().getId() + " not found."));

        if (fetchedCar.getState().isAvailable()) {
            fetchedCar.setState(new NotAvailableState());
            carRent.setCar(fetchedCar);

            return carRentDao.save(carRent);
        }

        throw new ActionException("Car not available");
    }

    public CarRent endRent(Integer id) throws ActionException {
        CarRent fetchedRent = carRentDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("CarRent with ID " + id + " not found."));

        if (fetchedRent.getFinishDate() == null) {
            fetchedRent.setFinishDate(getCurrentDateTimeString());
            carRentDao.save(fetchedRent);

            Car fetchedCar = carDao.findById(fetchedRent.getCar().getId())
                    .orElseThrow(() -> new IllegalArgumentException("CarRent with ID " + fetchedRent.getCar().getId() + " not found."));

            if (!fetchedCar.getState().isAvailable()) {
                fetchedCar.setState(new AvailableState());
                carDao.save(fetchedCar);
            }

            return fetchedRent;
        } else {
            throw new ActionException("CarRent already finished");
        }
    }
}
