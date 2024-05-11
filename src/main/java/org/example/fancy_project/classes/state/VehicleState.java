package org.example.fancy_project.classes.state;

import jakarta.persistence.Embeddable;
import org.example.fancy_project.classes.Vehicle;

@Embeddable
public interface VehicleState {
    void rent(Vehicle vehicle);

    void returnVehicle(Vehicle vehicle);

    boolean isAvailable();

}
