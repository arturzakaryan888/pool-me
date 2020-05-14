package com.rau.poolme.demo.repository;

import com.rau.poolme.demo.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AdminRepository extends JpaRepository<Admin,Integer> {
    Admin getByUsernameAndPassword(String login,String password);
}
