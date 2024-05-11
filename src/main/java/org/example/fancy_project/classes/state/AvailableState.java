package org.example.fancy_project.classes.state;

import org.example.fancy_project.classes.Vehicle;

public class AvailableState implements VehicleState {
    @Override
    public void rent(Vehicle vehicle) {
        vehicle.setState(new NotAvailableState());
    }

    @Override
    public void returnVehicle(Vehicle vehicle) {

    }

    @Override
    public boolean isAvailable() {
        return true;
    }

}
