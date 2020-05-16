package com.rau.poolme.demo.service.admin.impl;

import com.rau.poolme.demo.model.Admin;
import com.rau.poolme.demo.repository.AdminRepository;
import com.rau.poolme.demo.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminRepository adminRepository;

    @Override
    public Admin signIn(String login, String password) {
        return adminRepository.getByUsernameAndPassword(login,password); }

    @Override
    public void update(Admin admin) {
        adminRepository.save(admin);
    }

    @Override
    public Admin getByEmail(String email) {
        return adminRepository.getByEmail(email);
    }

    @Override
    public Admin getByUsername(String username) {
        return adminRepository.getByUsername(username);
    }

    @Override
    public Admin getByActivateCode(int activateCode) {
        return adminRepository.findByActivateCode(activateCode);
    }
}
