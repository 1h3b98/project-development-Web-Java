/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//
package utils;

//import java.net.PasswordAuthentication;
//import java.sql.Connection;
//import java.sql.Statement;
///import java.util.Properties;

//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.mail.Session;
//import javax.mail.Authenticator; 
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Transport;
//import javax.mail.internet.MimeMessage;
//import javax.mail.internet.InternetAddress;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
        



/**
 *
 * @author ouerf
 */
    public class Mail {
    //private  Connection con; 
    //private Statement ste; 
        public Mail(){}
    
    
    
        public static void sendMail(String mail) throws Exception { if (false) {

        final String moncompteEmail = "fithniti@outlook.com";
        final String psw = "passfiwordniti2025";
        
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.office365.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");

        Session ses = Session.getInstance(prop, new javax.mail.Authenticator() {

           
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(moncompteEmail, psw);
            }
        });

        try {

            Message msg = new MimeMessage(ses);
            msg.setFrom(new InternetAddress(moncompteEmail));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(mail));
            msg.setSubject("Confirmation de votre couvoiturage");
            msg.setText("Bonjour, Nous vous confirmons que votre couvoiturage a été bien ajouter, vous serez informé dès que cette dernière sera traitée. Bien à vous!!" );

            Transport.send(msg);
            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
  
         }   
    }}
                public static void sendPasswordMail(String mail,String Password) throws Exception {

        final String moncompteEmail = "fithniti@outlook.com";
        final String psw = "passfiwordniti2025";
        
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.office365.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");

        Session ses = Session.getInstance(prop, new javax.mail.Authenticator() {

           
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(moncompteEmail, psw);
            }
        });

        try {

            Message msg = new MimeMessage(ses);
            msg.setFrom(new InternetAddress(moncompteEmail));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(mail));
            msg.setSubject("Fi-Thniti Password Reset");
            msg.setText("Your New password is : "+Password );

            Transport.send(msg);
            System.out.println("Done");

        } catch (MessagingException e) {
            throw new Exception("Erreur sending mail");
  
         }   
    }
 }  

