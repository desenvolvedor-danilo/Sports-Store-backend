package com.dkmo.integrationnextjs.EmailService;






import java.util.Random;

import com.dkmo.integrationnextjs.interfaces.ISenderEmail;
// import com.dkmo.integrationnextjs.interfaces.ISenderMimeEmail;


import jakarta.mail.Message;
import jakarta.mail.Multipart;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SenderEmailMimeMessage implements ISenderEmail {

    
    @Override
    public String sendEmail(String email) {
        Session session = new GetSessionMimeMessage().getSession();
        try {
            var message = new MimeMessage(session);
            message.setFrom(new InternetAddress( "danilokelvemeireles45@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Verificação de email");
            String msg = "<h1 style=\"color:blue;font-size:1.8em;text-align:center;\">Código de segurança</h1><div style=\"width:300px;heigth:300px;margin:auto;text-align:center;\"><p style=\"font-weight:bolder;font-size:1.2em;\">Digite o código de segurança abaixo para verificar a sua conta <p/></div>"+"<p style=\"color:yellow;text-align:center;font-size:1.3em\">Código: "+new Random().nextInt(1000,9999)+"</p>";
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(msg, "text/html; charset=utf-8");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            message.setContent(multipart);
            Transport.send(message);
            return msg.substring(337,341);    
        } catch (Exception e) {
            throw new RuntimeException("Error to the send e-mail " + e.getMessage());
        }
        


    }

}
