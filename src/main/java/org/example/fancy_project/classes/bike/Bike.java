package org.example.fancy_project.classes.bike;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import org.example.fancy_project.classes.Vehicle;
import org.example.fancy_project.classes.state.VehicleState;

@Data
@Entity
@Table(name = "bike")
public class Bike extends Vehicle {
    private boolean streetLegal;
    private boolean backSeat;

    public Bike(Integer id, String vin, Integer year, String brand, String model, Integer mileage, VehicleState state,
                String engine, String fuel, Integer power, String type, String photo, boolean rent, Integer price,
                boolean streetLegal, boolean backSeat) {
        super(vin, year, brand, model, mileage, state, engine, fuel, power, type, photo, rent, price);
        this.id = id;
        this.streetLegal = streetLegal;
        this.backSeat = backSeat;
    }

    public Bike() {

    }
}
