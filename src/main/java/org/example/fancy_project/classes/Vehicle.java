package org.example.fancy_project.classes;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.example.fancy_project.classes.state.VehicleState;

@Getter
@MappedSuperclass
public abstract class Vehicle {
    @Column(unique = true)
    private String vin;
    private Integer year;
    private String brand;
    private String model;
    private Integer mileage;
    @Setter
    @Enumerated(EnumType.STRING)
    private VehicleState state;
    private String engine;
    private String fuel;
    private Integer power;
    private String type;
    private String photo;
    private boolean rent;
    private Integer price;

    public Vehicle(String vin, Integer year, String brand, String model, Integer mileage, VehicleState state,
                   String engine, String fuel, Integer power, String type, String photo, boolean rent, Integer price) {
        this.vin = vin;
        this.year = year;
        this.brand = brand;
        this.model = model;
        this.mileage = mileage;
        this.state = state;
        this.engine = engine;
        this.fuel = fuel;
        this.power = power;
        this.type = type;
        this.photo = photo;
        this.rent = rent;
        this.price = price;
    }

    public Vehicle() {

    }

    public void rent() {
        state.rent(this);
    }

    public void returnVehicle() {
        state.returnVehicle(this);
    }

    public boolean isAvailable() {
        return state.isAvailable();
    }

}
