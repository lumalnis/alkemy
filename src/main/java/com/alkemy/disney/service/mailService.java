package com.alkemy.disney.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;


@Service
public class mailService {
    
    @Autowired
    private JavaMailSenderImpl mailSender;
    
    public void mailSender(String to, String subject, String text){
        SimpleMailMessage simpleMessage = new SimpleMailMessage();
        simpleMessage.setTo(to);
        simpleMessage.setSubject(subject);
        simpleMessage.setText(text);
        simpleMessage.setFrom("alkemylula@gmail.com");
        mailSender.send(simpleMessage);
    }
}
//