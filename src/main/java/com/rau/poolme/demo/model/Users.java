package com.rau.poolme.demo.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.*;
import java.time.LocalDate;


@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String username;
    private String password;
    private String email;
    private String lastname;
    private boolean gender;
    private int age;
    private LocalDate dateOfBirth;
    @Enumerated
    private StatusAccept statusAccept;
    private int phone;
    private LocalDate dateOfRegistration;
    private String longitude;
    private String latitude;
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Car car;

    @JsonIgnore
    @ManyToMany(mappedBy = "usersSet")
    private Set<Trips> tripsSet  = new HashSet<>();
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    public StatusAccept getStatusAccept() {
        return statusAccept;
    }
    public void setStatusAccept(StatusAccept statusAccept) {
        this.statusAccept = statusAccept;
    }
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public LocalDate getDateOfRegistration() {
        return dateOfRegistration;
    }
    public void setDateOfRegistration(LocalDate dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }
    public Set<Trips> getTripsSet() {
        return tripsSet;
    }
    public void setTripsSet(Set<Trips> tripsSet) {
        this.tripsSet = tripsSet;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }
    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

}
