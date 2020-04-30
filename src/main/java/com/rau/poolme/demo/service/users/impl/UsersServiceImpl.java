package com.rau.poolme.demo.service.users.impl;

import com.rau.poolme.demo.model.Users;
import com.rau.poolme.demo.repository.UsersRepository;
import com.rau.poolme.demo.service.users.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    UsersRepository usersRepository;

    @Override
    public Users getById(int id) {
        return usersRepository.getOne(id);
    }

    @Override
    public void save(Users users) {
        usersRepository.save(users);
    }

    @Override
    public void delete(int id) {
        usersRepository.deleteById(id);
    }

    @Override
    public Users signIn(String login, String password) {
        return usersRepository.getByUsernameAndPassword(login,password);
    }

    @Override
    public List<Users> getAll() {
        return usersRepository.findAll();
    }
}
