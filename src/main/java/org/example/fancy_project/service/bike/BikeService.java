package org.example.fancy_project.service.bike;

import org.example.fancy_project.classes.bike.Bike;
import org.example.fancy_project.classes.state.AvailableState;
import org.example.fancy_project.dao.BikeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BikeService {
    @Autowired
    BikeDao bikeDao;

   public List<Bike> getAllBikes() {
        return bikeDao.findAll();
   }

    public Bike createBike(Bike bike){
        bike.setState(new AvailableState());
        return bikeDao.save(bike);
    }

    public void deleteBike(Integer id) {
        bikeDao.deleteById(id);
    }
}
