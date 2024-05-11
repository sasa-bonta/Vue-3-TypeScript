package org.example.fancy_project.classes.bike;

import jakarta.persistence.*;
import lombok.Data;
import org.example.fancy_project.classes.Rent;

@Data
@Entity
@Table(name = "bikeRent")
public class BikeRent extends Rent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "bike_id", referencedColumnName = "id")
    private Bike bike;

    public BikeRent(String startDate, String finishDate, Integer numberOfDays, Integer pricePerDay,
                    String idnp, String tel, String email, Integer id, Bike bike) {
        super(startDate, finishDate, numberOfDays, pricePerDay, idnp, tel, email);
        this.id = id;
        this.bike = bike;
    }

    public BikeRent() {

    }
}
