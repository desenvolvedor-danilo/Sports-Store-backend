package com.dkmo.integrationnextjs.EmailService;

import java.util.Properties;


import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;

public class Properts {
    private String host = System.getenv("SMTP_SERVER");
    private String username = System.getenv("SMTP_USERNAME");
    private String password = System.getenv("SMTP_PASSWORD");    
    public Session createProp(){
        Properties prop = new Properties();
       prop.put("mail.smtp.port", 587);
       prop.put("mail.smtp.auth", true);
       prop.put("mail.smtp.starttls.enable", true);
       prop.put("mail.smtp.host", host);
       prop.put("mail.smtp.ssl.trust",host);
       Session session = Session.getInstance(prop,new Authenticator() {
        @Override
        protected PasswordAuthentication getPasswordAuthentication(){
           return new PasswordAuthentication(username, password);
        }
       });
       return session;
    }
}
