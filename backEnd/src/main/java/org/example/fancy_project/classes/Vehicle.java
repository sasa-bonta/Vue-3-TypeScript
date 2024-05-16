package org.example.fancy_project.classes;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.fancy_project.classes.state.VehicleState;
import org.example.fancy_project.classes.state.VehicleStateConverter;

@Getter
@Setter
@MappedSuperclass
public abstract class Vehicle implements Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    @Column(unique = true)
    private String vin;
    private Integer year;
    private String brand;
    private String model;
    private Integer mileage;
    @Convert(converter = VehicleStateConverter.class)
    private VehicleState state;
    private String engine;
    private String fuel;
    private Integer power;
    private String type;
    private String photo;
    private boolean rent;
    private Integer price;
    @Column(name = "is_deleted")
    private boolean deleted;

    public Vehicle(String vin, Integer year, String brand, String model, Integer mileage, VehicleState state,
                   String engine, String fuel, Integer power, String type, String photo, boolean rent,
                   Integer price, boolean deleted) {
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
        this.deleted = deleted;
    }

    public Vehicle() {
    }

    public void startRent() {
        state.startRent(this);
    }

    public void endRent() {
        state.endRent(this);
    }

    public boolean isAvailable() {
        return state.isAvailable();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
