package com.rau.poolme.demo.controller;


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
        usersService.save(users);
        return new ResponseEntity(HttpStatus.CREATED);
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
        tripsService.save(trips);
        return new ResponseEntity(HttpStatus.CREATED);
    }






}
