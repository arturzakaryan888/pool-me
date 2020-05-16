package com.rau.poolme.demo.service.mail;

import com.rau.poolme.demo.model.Admin;
import com.rau.poolme.demo.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class MailSenderService {
    private JavaMailSender javaMailSender;

    @Autowired
    AdminService adminService;

    public MailSenderService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendMessage(Admin admin){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(admin.getEmail());
        simpleMailMessage.setFrom("arturzakaryan888@gmail.com");
        simpleMailMessage.setSubject("Hello poolMe application");

        int activateCode = (int)(Math.random() * (9999 - 1000 + 1) + 1000);
        simpleMailMessage.setText(String.valueOf(activateCode));
        javaMailSender.send(simpleMailMessage);
        admin.setActivateCode(activateCode);
        admin.setStatusActivate(false);
        adminService.update(admin);
    }
}
