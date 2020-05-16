package com.rau.poolme.demo.controller;


import com.rau.poolme.demo.model.Admin;
import com.rau.poolme.demo.model.Trips;
import com.rau.poolme.demo.model.Users;
import com.rau.poolme.demo.service.admin.AdminService;
import com.rau.poolme.demo.service.mail.MailSenderService;
import com.rau.poolme.demo.service.trips.TripsService;
import com.rau.poolme.demo.service.users.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.time.LocalDate;
import java.util.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/admin/")
public class AdminRestController {
    @Autowired
    AdminService adminService;
    @Autowired
    UsersService usersService;
    @Autowired
    TripsService tripsService;
    @Autowired
    MailSenderService mailSenderService;


    @RequestMapping(value = "signIn",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity signIn(@RequestBody Admin admin){

        if(adminService.signIn(admin.getUsername(), admin.getPassword()) != null){
            Admin authorized = adminService.signIn(admin.getUsername(), admin.getPassword());
            return new ResponseEntity<Admin>(authorized,HttpStatus.OK);
        }
        else {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping(value = "update",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity update(@RequestBody @Valid Admin admin){
        adminService.update(admin);
        return new ResponseEntity(HttpStatus.CREATED);
    }
    @RequestMapping(value = "updateUser",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateUser(@RequestBody @Valid Users users){
        if (users.getDateOfRegistration() == null){
            users.setDateOfRegistration(LocalDate.now());
        }
        usersService.save(users);
        return new ResponseEntity(HttpStatus.CREATED);
    }



    @RequestMapping(value = "save",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity adminSave(@RequestBody @Valid Admin admin){
        if (adminService.getByEmail(admin.getEmail()) != null && adminService.getByUsername(admin.getUsername()) != null)
        {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        mailSenderService.sendMessage(admin);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(value = "activateCode/{activateCode}",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity activateCode(@PathVariable int activateCode){
        Admin admin = adminService.getByActivateCode(activateCode);
        if (admin == null){
             return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        admin.setStatusActivate(true);
        adminService.update(admin);
        return new ResponseEntity(HttpStatus.OK);
    }


    @RequestMapping(value = "deleteUser/{id}",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List> userDelete(@PathParam("id") @PathVariable int id){
        usersService.delete(id);
        List<Users> usersList = usersService.getAll();
        return new ResponseEntity(usersList,HttpStatus.OK);
    }

    @RequestMapping(value = "getUser/{id}",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getUser(@PathParam("id") @PathVariable int id){
        Users findUser = usersService.getById(id);
        if (findUser == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Users>(findUser,HttpStatus.OK);
    }
    @RequestMapping(value = "getUsers",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Users>> getUsers(){
        List<Users> usersList = usersService.getAll();
        return new ResponseEntity<>(usersList,HttpStatus.OK);
    }

    @RequestMapping(value = "getPassengers",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Users>> getPassengers(){
        List<Users> usersList = usersService.getAll();
        List<Users> passengerList = new ArrayList<>();
        for (Users passenger:usersList) {
            if (passenger.getCar() == null){
                passengerList.add(passenger);
            }
        }
        return new ResponseEntity<>(passengerList,HttpStatus.OK);
    }
    @RequestMapping(value = "getDrivers",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Users>> getDrivers(){
        List<Users> usersList = usersService.getAll();
        List<Users> driverList = new ArrayList<>();
        for (Users driver:usersList) {
            if (driver.getCar() != null){
                driverList.add(driver);
            }
        }
        return new ResponseEntity<>(driverList,HttpStatus.OK);
    }

    @RequestMapping(value = "changeDriverStatus",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity changeDriverStatus(@RequestBody @Valid Users users){
        usersService.save(users);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "getTrips",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Trips>> getTrips(){
        List<Trips> tripsList = tripsService.getAll();
        return new ResponseEntity<>(tripsList,HttpStatus.OK);
    }

    @RequestMapping(value = "getCancelTrips",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Trips>> getCancelTrips(){
        List<Trips> tripsList = tripsService.getAll();
        List<Trips> tripsCancelList = new ArrayList<>();
        for (Trips tripsCancel:tripsList) {
            if (tripsCancel.getStatusTrips().ordinal() == 3){
                tripsCancelList.add(tripsCancel);
            }
        }
        return new ResponseEntity<>(tripsCancelList,HttpStatus.OK);
    }

    @RequestMapping(value = "getBookedTrips",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Trips>> getBookedTrips(){
        List<Trips> tripsList = tripsService.getAll();
        List<Trips> tripsBookedList = new ArrayList<>();
        for (Trips tripsCancel:tripsList) {
            if (tripsCancel.getStatusTrips().ordinal() == 0){
                tripsBookedList.add(tripsCancel);
            }
        }
        return new ResponseEntity<>(tripsBookedList,HttpStatus.OK);
    }

    @RequestMapping(value = "getCompletedTrips",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Trips>> getCompletedTrips(){
        List<Trips> tripsList = tripsService.getAll();
        List<Trips> tripsCompletedList = new ArrayList<>();
        for (Trips tripsCancel:tripsList) {
            if (tripsCancel.getStatusTrips().ordinal() == 2){
                tripsCompletedList.add(tripsCancel);
            }
        }
        return new ResponseEntity<>(tripsCompletedList,HttpStatus.OK);
    }

    @RequestMapping(value = "getInProgressTrips",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Trips>> getInProgressTrips(){
        List<Trips> tripsList = tripsService.getAll();
        List<Trips> tripsInProgressList = new ArrayList<>();
        for (Trips tripsCancel:tripsList) {
            if (tripsCancel.getStatusTrips().ordinal() == 1){
                tripsInProgressList.add(tripsCancel);
            }
        }
        return new ResponseEntity<>(tripsInProgressList,HttpStatus.OK);
    }



    @RequestMapping(value = "getMessage",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getMessage(@RequestBody Admin admin){
        mailSenderService.sendMessage(admin);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "get",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Users>> get(){
        LocalDate localDate = LocalDate.of(2020,5,12);
        List<Users> usersList = usersService.getByUsers(localDate);

        return new ResponseEntity<List<Users>>(usersList,HttpStatus.OK);
    }


}

