package org.example.fancy_project.service.car;

import org.example.fancy_project.classes.car.Car;
import org.example.fancy_project.classes.car.CarRent;
import org.example.fancy_project.dao.CarDao;
import org.example.fancy_project.dao.CarRentDao;
import org.example.fancy_project.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class CarRentService extends RentService<CarRent> {
    @Autowired
    CarRentDao carRentDao;
    @Autowired
    CarDao carDao;

    public List<CarRent> getAllRents() {
        return carRentDao.findAll();
    }

    public CarRent createRent(CarRent carRent){
        carRent.setStartDate(getCurrentDateTimeString());
        carRent.setFinishDate(null);
        Optional<Car> optionalCar = carDao.findById(carRent.getCar().getId());

        if (optionalCar.isPresent()) {
            Car fetchedCar = optionalCar.get();
            carRent.setCar(fetchedCar);
        } else {
            throw new IllegalArgumentException("CarRent with ID " + carRent.getCar().getId() + " not found.");
        }

        return carRentDao.save(carRent);
    }

    public void deleteRent(Integer id) {
        carRentDao.deleteById(id);
    }

    public static String getCurrentDateTimeString() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }
}
