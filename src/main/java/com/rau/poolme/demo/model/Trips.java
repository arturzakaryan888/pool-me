package com.rau.poolme.demo.model;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
public class Trips {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String fromLongitude;
    private String fromLatitude;
    private String toLongitude;
    private String toLatitude;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int seats;
    private int distance;
    @Enumerated
    private StatusTrips statusTrips;

    @ManyToMany
    @Cascade(CascadeType.SAVE_UPDATE)
    private Set<Users> usersSet = new HashSet<>();
    @OneToOne(fetch = FetchType.EAGER)
    private Payment payment;


    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public Set<Users> getUsersSet() {
        return usersSet;
    }

    public void setUsersSet(Set<Users> usersSet) {
        this.usersSet = usersSet;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFromLongitude() {
        return fromLongitude;
    }

    public void setFromLongitude(String fromLongitude) {
        this.fromLongitude = fromLongitude;
    }

    public String getFromLatitude() {
        return fromLatitude;
    }

    public void setFromLatitude(String fromLatitude) {
        this.fromLatitude = fromLatitude;
    }

    public String getToLongitude() {
        return toLongitude;
    }

    public void setToLongitude(String toLongitude) {
        this.toLongitude = toLongitude;
    }

    public String getToLatitude() {
        return toLatitude;
    }

    public void setToLatitude(String toLatitude) {
        this.toLatitude = toLatitude;
    }
    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public StatusTrips getStatusTrips() {
        return statusTrips;
    }

    public void setStatusTrips(StatusTrips statusTrips) {
        this.statusTrips = statusTrips;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
