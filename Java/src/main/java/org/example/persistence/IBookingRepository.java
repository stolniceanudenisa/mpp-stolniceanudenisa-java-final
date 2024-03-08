package org.example.persistence;

import org.example.model.Booking;

public interface IBookingRepository extends IRepository<Long, Booking>{
    int getNumberOfBookingsForFlight(Long flightId);

}