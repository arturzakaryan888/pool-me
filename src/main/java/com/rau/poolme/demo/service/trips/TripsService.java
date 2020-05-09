package com.rau.poolme.demo.service.trips;

import com.rau.poolme.demo.model.Trips;

import java.util.List;

public interface TripsService {
    void save(Trips trips);
    List<Trips> getAll();
}
