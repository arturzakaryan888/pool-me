package com.rau.poolme.demo.controller;


import com.rau.poolme.demo.model.Admin;
import com.rau.poolme.demo.model.Users;
import com.rau.poolme.demo.service.admin.AdminService;
import com.rau.poolme.demo.service.users.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/admin/")
public class AdminRestController {
    @Autowired
    AdminService adminService;
    @Autowired
    UsersService usersService;


    @RequestMapping(value = "signin",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity signIn(@RequestBody Admin admin){
        if(adminService.signIn(admin.getUsername(), admin.getPassword()) != null ){
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
    @RequestMapping(value = "user/save",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity userSave(@RequestBody @Valid Users users){
        usersService.save(users);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(value = "user/delete/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity userDelete(@PathParam("id") @PathVariable int id){
        usersService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "user/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getUser(@PathParam("id") @PathVariable int id){
        Users findUser = usersService.getById(id);
        if (findUser == null){
            return new ResponseEntity<Users>(findUser,HttpStatus.CREATED);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
    @RequestMapping(value = "user/getAllUsers",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Users>> getAllUsers(){
        List<Users> usersList = usersService.getAll();
        return new ResponseEntity<>(usersList,HttpStatus.OK);
    }


    @RequestMapping(value = "user/getOnlyPassenger",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Users>> getOnlyPassenger(){
        List<Users> usersList = usersService.getAll();
        List<Users> passengerList = new ArrayList<>();
        for (Users passenger:usersList) {
            if (passenger.getCar() == null){
                passengerList.add(passenger);
            }
        }
        return new ResponseEntity<>(passengerList,HttpStatus.OK);
    }
    @RequestMapping(value = "user/getOnlyDriver",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Users>> getOnlyDriver(){
        List<Users> usersList = usersService.getAll();
        List<Users> driverList = new ArrayList<>();
        for (Users driver:usersList) {
            if (driver.getCar() != null){
                driverList.add(driver);
            }
        }
        return new ResponseEntity<>(driverList,HttpStatus.OK);
    }

}

