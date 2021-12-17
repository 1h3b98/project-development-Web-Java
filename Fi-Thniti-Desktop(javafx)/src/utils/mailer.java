package utils;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

/**
 *
 * @author Asus
 */
public class mailer {
    public mailer(){
    }

    public void SendMail(String mail) {if (false) {
        
        final String username = "tabaani.app@gmail.com";
        final String password = "tabaani2021";
 
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("tapaani.app@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO, InternetAddress.parse(mail)
            );
            message.setSubject("Concernat l ajout de votre zone de loisir dans l application guide touristique");
            message.setText("Dear Livraiseur," + "\n\n Cher client 😁, Nous vous remercions de la précieuse confiance que vous nous accordez, et nous espérons que nous vous avons bien servi et que vous êtes satisfait de notre application.😊 ");
            Transport.send(message);
            System.out.println("Done");
        } catch (MessagingException e) {
            e.printStackTrace();
                System.out.println("ereur mailer");
        }
    }}
}