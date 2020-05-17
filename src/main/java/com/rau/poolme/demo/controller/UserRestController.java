package com.rau.poolme.demo.controller;


import com.rau.poolme.demo.model.StatusAccept;
import com.rau.poolme.demo.model.StatusTrips;
import com.rau.poolme.demo.model.Trips;
import com.rau.poolme.demo.model.Users;
import com.rau.poolme.demo.service.trips.TripsService;
import com.rau.poolme.demo.service.users.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/user/")
public class UserRestController {
    @Autowired
    private UsersService usersService;
    @Autowired
    private TripsService tripsService;

    /*https://poolme.herokuapp.com/user/save*/
    @RequestMapping(value = "save",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity save(@RequestBody @Valid Users users){
        if (users.getDateOfRegistration() == null){
            users.setDateOfRegistration(LocalDate.now());
        }
        if (users.getCar() != null){
            users.getCar().setStatus(false);
        }
        usersService.save(users);
        Users users1 = usersService.getByEmail(users.getEmail());
        return new ResponseEntity<Users>(users1,HttpStatus.CREATED);
    }


    /*https://poolme.herokuapp.com/user/signIn*/
    @RequestMapping(value = "sighIn",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity signIn(@RequestBody @Valid Users users){
        if(usersService.signIn(users.getUsername(), users.getPassword()) != null ){
            Users authorized = usersService.signIn(users.getUsername(), users.getPassword());
            return new ResponseEntity<Users>(authorized,HttpStatus.OK);

        }
        else {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
    }

    /*https://poolme.herokuapp.com/user/createTrip*/
    @RequestMapping(value = "createTrip",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createTrip(@RequestBody @Valid Trips trips){
        trips.setStatusTrips(StatusTrips.BOOKED);
        if(trips.getStartTime() == null){
            trips.setStartTime(LocalDateTime.now());
        }
        tripsService.save(trips);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    /*https://poolme.herokuapp.com/user/findTrips*/
    @RequestMapping(value = "findTrips",method = RequestMethod.POST ,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity findTrips(@RequestBody @Valid Trips trips){
        List<Trips> tripsArrays = tripsService.findTripsByCoordinates(trips);
            if (tripsArrays.size() == 0){
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }

        return new ResponseEntity(tripsArrays,HttpStatus.OK);
    }

    /*https://poolme.herokuapp.com/user/acceptTrip*/
    @RequestMapping(value = "acceptTrip",method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity acceptTrip(@RequestBody @Valid Trips trips){
        Trips trips1 = tripsService.findById(trips.getId());
        Set<Users> tripsSet = new HashSet<>(trips1.getUsersSet());
        for (Users passanger:trips.getUsersSet()) {
            if (passanger.getCar() == null){
                if (passanger.getStatusAccept() == null){
                    passanger.setStatusAccept(StatusAccept.INPROGRESS);
                }
            }
        }
        tripsSet.addAll(trips.getUsersSet());
        trips1.setUsersSet(tripsSet);
        tripsService.save(trips1);
        return new ResponseEntity(HttpStatus.OK);
    }

    /*https://poolme.herokuapp.com/user/getTrip*/
    @RequestMapping(value = "getTrip",method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getTrip(@RequestBody @Valid Users users){
        Trips trips = tripsService.findByUsers(users.getId());
        if (trips == null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        else {

            for (Users passenger: trips.getUsersSet()) {
                if (passenger.getCar() == null){
                    return new ResponseEntity(trips,HttpStatus.OK);
                }

            }
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

    }

    /*https://poolme.herokuapp.com/user/ignorPassanger*/
    @RequestMapping(value = "ignorPassenger",method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity ignorPassenger(@RequestBody @Valid Trips trips){
        Trips trips1 = tripsService.findById(trips.getId());
        Set<Users> tripsSet = new HashSet<>(trips1.getUsersSet());

        for (Users passenger:tripsSet) {
            for (Users passengerStatus:trips.getUsersSet()) {
                if (passenger.getId() == passengerStatus.getId() && passenger.getStatusAccept() != passengerStatus.getStatusAccept()) {
                    passenger.setStatusAccept(passengerStatus.getStatusAccept());
                }
            }
        }

        tripsSet.addAll(trips.getUsersSet());
        trips1.setUsersSet(tripsSet);
        tripsService.save(trips1);
        return new ResponseEntity(HttpStatus.OK);
    }

    /*https://poolme.herokuapp.com/user/ignorPassanger*/
    @RequestMapping(value = "AcceptPassenger",method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity AcceptPassenger(@RequestBody @Valid Trips trips){
        Trips trips1 = tripsService.findById(trips.getId());
        Set<Users> tripsSet = new HashSet<>(trips1.getUsersSet());
        for (Users passenger:tripsSet) {
            for (Users passengerStatus:trips.getUsersSet()){
                if (passenger.getId() == passengerStatus.getId() && passenger.getStatusAccept() != passengerStatus.getStatusAccept()) {
                        passenger.setStatusAccept(passengerStatus.getStatusAccept());
                }
                if(passenger.getCar() != null && passenger.getId() == passengerStatus.getId()){
                    passenger.setLatitude(passengerStatus.getLatitude());
                    passenger.setLongitude(passengerStatus.getLongitude());
                }
            }

        }
        tripsSet.addAll(trips.getUsersSet());
        trips1.setUsersSet(tripsSet);
        tripsService.save(trips1);
        return new ResponseEntity(HttpStatus.OK);
    }

    /*https://poolme.herokuapp.com/user/ignorPassanger*/
    @RequestMapping(value = "AcceptDriver",method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity AcceptDriver(@RequestBody @Valid Users users){
        Trips trips = tripsService.findByUsers(users.getId());
        if (trips == null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        for (Users passenger: trips.getUsersSet()) {
            if (passenger.getCar() == null){
                if (passenger.getId() == users.getId()){
                    if (passenger.getStatusAccept() == StatusAccept.ACCEPT){
                        return new ResponseEntity(trips,HttpStatus.ACCEPTED);
                    }
                    if (passenger.getStatusAccept() == StatusAccept.INPROGRESS){
                        return new ResponseEntity(HttpStatus.OK);
                    }
                    else {
                        return new ResponseEntity(HttpStatus.BAD_REQUEST);
                    }

                }
            }

        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }













}
