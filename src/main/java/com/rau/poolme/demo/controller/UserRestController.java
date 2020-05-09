package com.rau.poolme.demo.controller;


import com.rau.poolme.demo.model.Users;
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
    /*https://poolme.herokuapp.com/user/save*/
    @RequestMapping(value = "save",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity save(@RequestBody @Valid Users users){
        usersService.save(users);
        return new ResponseEntity(HttpStatus.CREATED);
    }


}
