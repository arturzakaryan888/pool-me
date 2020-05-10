package com.rau.poolme.demo.service.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class MailSenderService {
    private JavaMailSender javaMailSender;

    public MailSenderService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendMessage(String email){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(email);
        simpleMailMessage.setFrom("arturzakaryan888@gmail.com");
        simpleMailMessage.setSubject("AAAAAAAAAAAAAAAA");
        simpleMailMessage.setText("Hello Email");

        javaMailSender.send(simpleMailMessage);
    }
}
