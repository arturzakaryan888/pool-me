package com.rau.poolme.demo;

import com.rau.poolme.demo.model.Trips;
import com.rau.poolme.demo.model.Users;
import com.rau.poolme.demo.repository.TripsRepository;
import com.rau.poolme.demo.service.trips.TripsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);

    }
}
