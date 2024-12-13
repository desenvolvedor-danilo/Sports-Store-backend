package com.dkmo.integrationnextjs.EmailService;

import jakarta.mail.Session;

public class GetSessionMimeMessage {
    private Session session;
    public GetSessionMimeMessage(){
        
            Properts prop = new Properts();
           this.session = prop.createProp();
        
    }
    public Session getSession(){
        return this.session;
    }
}
