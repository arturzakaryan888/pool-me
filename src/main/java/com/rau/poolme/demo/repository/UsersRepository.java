package com.rau.poolme.demo.repository;


import com.rau.poolme.demo.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

import static org.hibernate.loader.Loader.SELECT;


@Repository
public interface UsersRepository extends JpaRepository<Users,Integer> {
    Users getByUsernameAndPassword(String login, String password);
    Users getByEmail(String email);


    @Query("SELECT u from Users u WHERE u.dateOfRegistration >= ?1")
    List<Users> getByUsers(LocalDate localDate);

}
