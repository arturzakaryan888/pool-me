package com.rau.poolme.demo.repository;

import com.rau.poolme.demo.model.Trips;
import com.rau.poolme.demo.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface TripsRepository extends JpaRepository<Trips,Integer> {

    @Query("SELECT t FROM Trips t WHERE t.fromLatitude = ?1 and t.fromLongitude = ?2 and t.toLatitude = ?3 and t.toLongitude = ?4")
    List<Trips> findTripsByCoordinates(String fromLatitude, String fromLongitude, String toLatitude, String toLongitude );

    @Query("SELECT t from Trips t WHERE t.endTime >= ?1 AND t.endTime  <= ?2")
    List<Trips> findByTrips(LocalDateTime localDateTimeStart,LocalDateTime localDateTimeEnd);

    @Query("SELECT t FROM Trips t JOIN t.usersSet es WHERE es.id = ?1")
    Trips findByUsers(int id);



}
