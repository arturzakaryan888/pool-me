package com.rau.poolme.demo.service.trips;

import com.rau.poolme.demo.model.Trips;
import com.rau.poolme.demo.model.Users;

import java.util.List;

public interface TripsService {
    void save(Trips trips);
    List<Trips> getAll();
    Trips[] findTripsByCoordinates(Trips trips);
    Trips findByUsers(Users users);
    Trips findById(int id);
}
