package org.example.validators.flight;

import org.example.model.Flight;
import org.example.validators.ValidationException;
import org.example.validators.Validator;

public class FlightValidator implements Validator<Flight> {
    @Override
    public boolean validate(Flight entity) throws ValidationException {
        if (entity.getDestination().isEmpty()) {
            throw new ValidationException("Destination cannot be empty");
        }
        if (entity.getDepartureDateTime() == null) {
            throw new ValidationException("Departure cannot be empty");
        }

        if (entity.getAirport().isEmpty()) {
            throw new ValidationException("Airport cannot be empty");
        }

        if (entity.getAvailableSeats() < 0) {
            throw new ValidationException("Seats cannot be negative");
        }

        return true;
    }
}