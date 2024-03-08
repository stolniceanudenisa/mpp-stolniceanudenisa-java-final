package org.example.persistence;

import org.example.model.Flight;

import java.time.LocalDateTime;

public interface IFlightRepository extends IRepository<Long, Flight> {
    Iterable<Flight> findFlightByDestinationAndDate(String destination, LocalDateTime dateTime);
}