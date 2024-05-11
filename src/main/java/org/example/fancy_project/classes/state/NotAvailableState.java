package org.example.fancy_project.classes.state;

import org.example.fancy_project.classes.Vehicle;

public class NotAvailableState implements VehicleState {
    @Override
    public void startRent(Vehicle vehicle) {

    }

    @Override
    public void endRent(Vehicle vehicle) {
        vehicle.setState(new AvailableState());
    }

    @Override
    public boolean isAvailable() {
        return false;
    }

    @Override
    public String toString() {
        return "not available";
    }
}
