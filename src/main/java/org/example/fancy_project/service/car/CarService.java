package org.example.fancy_project.service.car;

import org.example.fancy_project.classes.car.Car;
import org.example.fancy_project.dao.CarDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    @Autowired
    CarDao carDao;
    public List<Car> getAllCars() {
        return carDao.findAll();
    }

    public Car createCar(Car car){
        return carDao.save(car);
    }

    public void deleteCar(Integer id) {
        carDao.deleteById(id);
    }
}
