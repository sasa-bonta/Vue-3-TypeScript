package org.example.fancy_project.classes.state;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

// Custom converter to convert between VehicleState enum and its string representation
@Converter(autoApply = true)
public class VehicleStateConverter implements AttributeConverter<VehicleState, String>, VehicleStateConstants {

    @Override
    public String convertToDatabaseColumn(VehicleState state) {
        // Convert VehicleState enum to its string representation
        if (state instanceof AvailableState) {
            return AVAILABLE_STATE;
        } else if (state instanceof NotAvailableState) {
            return NOT_AVAILABLE_STATE;
        }
        // Handle other states if needed
        return null;
    }

    @Override
    public VehicleState convertToEntityAttribute(String stateString) {
        // Convert string representation to VehicleState enum
        if (AVAILABLE_STATE.equals(stateString)) {
            return new AvailableState();
        } else if (NOT_AVAILABLE_STATE.equals(stateString)) {
            return new NotAvailableState();
        }
        // Handle other states if needed
        return null;
    }
}
