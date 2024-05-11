package org.example.fancy_project.classes.car;

import jakarta.persistence.*;
import lombok.Data;
import org.example.fancy_project.classes.Vehicle;
import org.example.fancy_project.classes.state.VehicleState;

@Data
@Entity
@Table(name = "car")
public class Car extends Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String transmission;
    private String drivetrain;
    private Integer seats;

    public Car(Integer id, String vin, Integer year, String brand, String model, Integer mileage, VehicleState state,
               String engine, String fuel, Integer power, String type, String photo, boolean rent, Integer price,
               String transmission, String drivetrain, Integer seats) {
        super(vin, year, brand, model, mileage, state, engine, fuel, power, type, photo, rent, price);
        this.id = id;
        this.transmission = transmission;
        this.drivetrain = drivetrain;
        this.seats = seats;
    }

    public Car() {

    }

}