package com.rau.poolme.demo.repository;

import com.rau.poolme.demo.model.Trips;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripsRepository extends JpaRepository<Trips,Integer> {


}
