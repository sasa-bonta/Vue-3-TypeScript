package org.example.fancy_project.service;

import org.example.fancy_project.classes.Rent;
import org.example.fancy_project.dao.RentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

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

    public T endRent(Integer id) {
        Optional<T> optionalRent = rentDao.findById(id);

        if (optionalRent.isPresent()) {
            T fetchedRent = optionalRent.get();
            fetchedRent.setFinishDate(getCurrentDateTimeString());
            rentDao.save(fetchedRent);

            return fetchedRent;
        } else {
            throw new IllegalArgumentException("Rent with ID " + id + " not found.");
        }
    }

    public void deleteRent(Integer id) {
        rentDao.deleteById(id);
    }

    public static String getCurrentDateTimeString() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }
}
