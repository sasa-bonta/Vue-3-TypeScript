package org.example.fancy_project.classes.car;

import jakarta.persistence.*;
import lombok.Data;
import org.example.fancy_project.classes.Rent;


@Data
@Entity
@Table(name = "carRent")
public class CarRent extends Rent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private Car car;

    public CarRent(String startDate, String finishDate, Integer numberOfDays, Integer pricePerDay, 
                   String idnp, String tel, String email, Integer id, Car car) {
        super(startDate, finishDate, numberOfDays, pricePerDay, idnp, tel, email);
        this.id = id;
        this.car = car;
    }

    public CarRent() {
        
    }
}
