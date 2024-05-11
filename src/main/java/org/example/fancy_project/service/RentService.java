package org.example.fancy_project.service;

import org.example.fancy_project.classes.Rent;
import org.example.fancy_project.dao.RentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class RentService<T extends Rent> {

    @Autowired
    RentDao<T> rentDao;

    public List<T> getAllRents() {
        return rentDao.findAll();
    }

    public T createRent(T rent){
        return rentDao.save(rent);
    }

    public void deleteRent(Integer id) {
        rentDao.deleteById(id);
    }
}
