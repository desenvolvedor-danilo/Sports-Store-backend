package com.dkmo.integrationnextjs.EmailService;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.dkmo.integrationnextjs.interfaces.ISenderEmail;
@Component
public class SenderEmail implements ISenderEmail {
    @Autowired
    private JavaMailSender sender;
    
    @SuppressWarnings("null")
    @Override
    public String sendEmail(String to) {
        var message = new SimpleMailMessage();
        Random random = new Random();
        message.setTo(to);
        message.setText("CÓDIGO DE \nSEGURANÇA \n\nUse o código de segurança para confirmar sua conta \n\nclique no link abaixo \n\nhttp://localhost:3000/confirm \n\ncódigo de segurança:"+random.nextInt(1000,9999));
        sender.send(message);
        return message.getText().replace("CÓDIGO DE \nSEGURANÇA \n\nUse o código de segurança para confirmar sua conta \n\nclique no link abaixo \n\nhttp://localhost:3000/confirm \n\ncódigo de segurança:", "").trim();
    }
    
}
