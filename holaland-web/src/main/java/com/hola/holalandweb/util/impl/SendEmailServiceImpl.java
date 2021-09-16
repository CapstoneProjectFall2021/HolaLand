package com.hola.holalandweb.util.impl;

import com.hola.holalandweb.util.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendEmailServiceImpl implements SendEmailService {

    private JavaMailSender javaMailSender;
    //fix bug email
    @Autowired
    public SendEmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void send(String subject, String text, String... toEmails) {

        SimpleMailMessage msg = new SimpleMailMessage();

        msg.setTo(toEmails);
        msg.setSubject(subject);
        msg.setText(text);
        javaMailSender.send(msg);
    }
}
