package org.example.fancy_project.classes.state;

import org.example.fancy_project.classes.Vehicle;

public class NotAvailableState implements VehicleState {
    @Override
    public void rent(Vehicle vehicle) {

    }

    @Override
    public void returnVehicle(Vehicle vehicle) {
        vehicle.setState(new AvailableState());
    }

    @Override
    public boolean isAvailable() {
        return false;
    }

}
