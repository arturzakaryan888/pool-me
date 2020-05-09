package com.rau.poolme.demo.service.trips.impl;

import com.rau.poolme.demo.model.Trips;
import com.rau.poolme.demo.repository.TripsRepository;
import com.rau.poolme.demo.service.trips.TripsService;
import org.springframework.beans.factory.annotation.Autowired;

public class TripsServiceImpl implements TripsService {

    @Autowired
    private TripsRepository tripsRepository;

    @Override
    public void save(Trips trips) {
        tripsRepository.save(trips);
    }
}