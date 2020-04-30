package com.rau.poolme.demo.repository;

import com.rau.poolme.demo.model.Admin;
import com.rau.poolme.demo.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsersRepository extends JpaRepository<Users,Integer> {
    Users getByUsernameAndPassword(String login, String password);
}
