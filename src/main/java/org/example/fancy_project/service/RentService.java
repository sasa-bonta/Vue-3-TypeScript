package org.example.fancy_project.service;

import ch.qos.logback.core.joran.spi.ActionException;
import org.example.fancy_project.classes.Rent;
import org.example.fancy_project.dao.RentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public abstract class RentService<T extends Rent> {

    @Autowired
    RentDao<T> rentDao;

    public List<T> getAllRents() {
        return rentDao.findAll();
    }

    public abstract T createRent(T rent);

    public abstract T endRent(Integer id) throws ActionException;

    public void deleteRent(Integer id) {
        rentDao.deleteById(id);
    }

    public static String getCurrentDateTimeString() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }
}
