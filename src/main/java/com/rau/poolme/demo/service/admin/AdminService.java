package com.rau.poolme.demo.service.admin;

import com.rau.poolme.demo.model.Admin;
public interface AdminService {
    Admin signIn(String login,String password);
    void update(Admin admin);
}
