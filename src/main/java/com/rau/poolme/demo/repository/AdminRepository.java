package com.rau.poolme.demo.repository;

import com.rau.poolme.demo.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface AdminRepository extends JpaRepository<Admin,Integer> {
    Admin getByUsernameAndPassword(String login,String password);
    Admin getByEmail(String email);
    Admin getByUsername(String username);
    Admin findByActivateCode(int activateCode);
}
