package com.rau.poolme.demo.service.trips;

import com.rau.poolme.demo.model.Trips;
import com.rau.poolme.demo.model.Users;

import java.time.LocalDateTime;
import java.util.List;

public interface TripsService {
    void save(Trips trips);
    List<Trips> getAll();
    List<Trips> findTripsByCoordinates(Trips trips);
    Trips findByUsers(int id);
    Trips findById(int id);
    List<Trips> findByTrips(LocalDateTime localDateTimeStart,LocalDateTime localDateTimeEnd);
}
