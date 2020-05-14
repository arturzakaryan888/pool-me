package com.rau.poolme.demo.repository;


import com.rau.poolme.demo.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface UsersRepository extends JpaRepository<Users,Integer> {
    Users getByUsernameAndPassword(String login, String password);
    Users getByEmail(String email);

}
