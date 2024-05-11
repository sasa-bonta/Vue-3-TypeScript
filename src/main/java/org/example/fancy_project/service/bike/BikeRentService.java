package org.example.fancy_project.service.bike;

import org.example.fancy_project.classes.bike.BikeRent;
import org.example.fancy_project.dao.BikeRentDao;
import org.example.fancy_project.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BikeRentService extends RentService<BikeRent> {
    @Autowired
    BikeRentDao bikeRentDao;
    public List<BikeRent> getAllRents() {
        return bikeRentDao.findAll();
    }

    public BikeRent createRent(BikeRent bikeRent){
        return bikeRentDao.save(bikeRent);
    }

    public void deleteRent(Integer id) {
        bikeRentDao.deleteById(id);
    }
}
