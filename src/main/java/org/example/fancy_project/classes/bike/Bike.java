package org.example.fancy_project.classes.bike;

import jakarta.persistence.*;
import lombok.Data;
import org.example.fancy_project.classes.Vehicle;
import org.example.fancy_project.classes.state.VehicleState;

@Data
@Entity
@Table(name = "bike")
public class Bike extends Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private boolean streetLegal;
    private boolean backSeat;

    public Bike(Integer id, String vin, Integer year, String brand, String model, Integer mileage, String engine,
                String fuel, Integer power, String type, String photo, boolean rent, Integer price,
                boolean streetLegal, boolean backSeat) {
        super(vin, year, brand, model, mileage, engine, fuel, power, type, photo, rent, price);
        this.id = id;
        this.streetLegal = streetLegal;
        this.backSeat = backSeat;
    }

    public Bike() {

    }
}
