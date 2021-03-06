package com.rau.poolme.demo.service.trips.impl;

import com.rau.poolme.demo.model.Trips;
import com.rau.poolme.demo.model.Users;
import com.rau.poolme.demo.repository.TripsRepository;
import com.rau.poolme.demo.service.trips.TripsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class TripsServiceImpl implements TripsService  {

    @Autowired
    private TripsRepository tripsRepository;


    @Override
    public void save(Trips trips) {
        tripsRepository.save(trips);
    }

    @Override
    public List<Trips> getAll() {
        return tripsRepository.findAll();
    }

    @Override
    public List<Trips> findTripsByCoordinates(Trips trips) {
        return tripsRepository.findTripsByCoordinates(trips.getFromLatitude(),trips.getFromLongitude(),trips.getToLatitude(),trips.getToLongitude());
    }

    @Override
    public Trips findByUsers(int id) {
        return tripsRepository.findByUsers(id);
    }

    @Override
    public Trips findById(int id) {
        return tripsRepository.getOne(id);
    }

    @Override
    public List<Trips> findByTrips(LocalDateTime localDateTimeStart, LocalDateTime localDateTimeEnd) {
        return tripsRepository.findByTrips(localDateTimeStart,localDateTimeEnd);
    }

}
