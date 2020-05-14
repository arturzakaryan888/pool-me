package com.rau.poolme.demo.repository;

import com.rau.poolme.demo.model.Trips;
import com.rau.poolme.demo.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TripsRepository extends JpaRepository<Trips,Integer> {

    @Query("SELECT t FROM Trips t WHERE t.fromLatitude = ?1 and t.fromLongitude = ?2 and t.toLatitude = ?3 and t.toLongitude = ?4")
    List<Trips> findTripsByCoordinates(String fromLatitude, String fromLongitude, String toLatitude, String toLongitude );

    @Query("SELECT u FROM Trips u WHERE u.usersSet = ?1")
    Trips findByUsers(Users users);



}
