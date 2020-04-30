package com.rau.poolme.demo.service.users;

import com.rau.poolme.demo.model.Users;

import java.util.List;

public interface UsersService {
    Users getById(int id);
    void save(Users users);
    void delete(int id);
    List<Users> getAll();
    Users signIn(String login,String password);
}
