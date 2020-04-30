package com.rau.poolme.demo.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Trips {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String fromLongitude;
    private String fromLatitude;
    private String toLongitude;
    private String toLatitude;
    private Date startTime;
    private Date endTime;
    private int distance;
    @Enumerated
    private StatusTrips statusTrips;
    @ManyToOne(fetch = FetchType.EAGER)
    private Users users;
    @OneToOne(fetch = FetchType.EAGER)
    private Payment payment;

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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}