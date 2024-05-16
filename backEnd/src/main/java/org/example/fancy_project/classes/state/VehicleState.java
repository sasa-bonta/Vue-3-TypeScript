package org.example.fancy_project.classes.state;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.Embeddable;
import org.example.fancy_project.classes.Vehicle;

@Embeddable
@JsonDeserialize(as = AvailableState.class)
public interface VehicleState {
    void startRent(Vehicle vehicle);

    void endRent(Vehicle vehicle);

    boolean isAvailable();
}
