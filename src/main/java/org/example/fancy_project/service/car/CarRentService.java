package org.example.fancy_project.service.car;

import org.example.fancy_project.classes.car.CarRent;
import org.example.fancy_project.dao.CarRentDao;
import org.example.fancy_project.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarRentService extends RentService<CarRent> {
    @Autowired
    CarRentDao carRentDao;
    public List<CarRent> getAllRents() {
        return carRentDao.findAll();
    }

    public CarRent createRent(CarRent carRent){
        return carRentDao.save(carRent);
    }

    public void deleteRent(Integer id) {
        carRentDao.deleteById(id);
    }
}
