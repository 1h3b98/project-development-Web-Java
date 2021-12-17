/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.User;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import utils.Mail;
import utils.myDB;

/**
 *
 * @author Ramzii
 */
public class userService implements Service<User> {

    static User passing;
    Connection connection;

    public userService() {
        connection = myDB.getInstance().getConnection();
    }
         static String crypter(String inputpassword) {
        String hashStr = "";
        try {

            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte hashBytes[] = messageDigest.digest(inputpassword.getBytes(StandardCharsets.UTF_8));
            BigInteger noHash = new BigInteger(1, hashBytes);
            hashStr = noHash.toString(16);
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
        }
        return hashStr;
    }

    @Override
    public void insert(User object) {
        try {
            String req = "INSERT INTO `users`( `first_name`, `last_name`, `cin`, `email`, `username`, `password`, `phone_number`, `birth_date`, `privilege`)VALUES ('" + object.getFirst_name() + "','" + object.getLast_name() + "','" + object.getCin() + "','" + object.getEmail() + "','" + object.getUsername() + "','" + object.getPassword() + "','" + object.getPhone_number() + "','" + object.getBirth_date() + "','" + object.getPrivilege() + "')";
            Statement st = connection.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
                        JOptionPane frame = new JOptionPane();
            JOptionPane.showMessageDialog(frame, ex.getMessage());
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(User object) {
        try {
            String req = "UPDATE `users` SET `first_name`='" + object.getFirst_name() + "',`last_name`='" + object.getLast_name() + "',`cin`='" + object.getCin() + "',`email`='" + object.getEmail() + "',`username`='" + object.getUsername() + "',`password`='" + object.getPassword() + "',`phone_number`='" + object.getPhone_number() + "',`birth_date`='" + object.getBirth_date() + "',`privilege`='" + object.getPrivilege() + "' WHERE `user_id`='" + object.getUser_id() + "' ";
            Statement st = connection.createStatement();
            int rows = st.executeUpdate(req);
            if (rows > 0) {
                //System.out.println("The user information have been updated.");
            }
        } catch (SQLException ex) {
                             JOptionPane frame = new JOptionPane();
            JOptionPane.showMessageDialog(frame, ex.getMessage());
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(User object) {
        try {
            String req = "DELETE FROM `users` WHERE `user_id`='" + object.getUser_id() + "' ";
            Statement st = connection.createStatement();
            int rows = st.executeUpdate(req);
            if (rows > 0) {
                //System.out.println("The user have been deleted.");
            }
        } catch (SQLException ex) {
                             JOptionPane frame = new JOptionPane();
            JOptionPane.showMessageDialog(frame, ex.getMessage());
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<User> findAll() {
        List<User> listU = new ArrayList<>();
        String req = "SELECT * FROM `users`";
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                try {
                 User u = new User();
                u.setUser_id(rs.getInt(1));
                u.setFirst_name(rs.getString(2));
                u.setLast_name(rs.getString(3));
                u.setCin(rs.getString(4));
                u.setEmail(rs.getString(5));
                u.setUsername(rs.getString(6));
                u.setPassword("none");
                u.setPhone_number(rs.getString(8));
                u.setBirth_date(rs.getDate(9));
                u.setPrivilege(rs.getInt(10));
                listU.add(u);
                }catch(Exception e){
                    System.out.println("erreur in setting user info"+e);
                }


            }
        } catch (SQLException ex) {
            Logger.getLogger(userService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listU;
    }

    public List<User> findAll(String inputName, String inputNumber, Date inputDate, String inputLogins, int enumprivilege) {
        List<User> listU = new ArrayList<>();
        String req = "SELECT * FROM `users` WHERE (first_name LIKE '%" + inputName + "%' or last_name LIKE '%" + inputName + "%') AND (cin LIKE '%" + inputNumber + "%' or phone_number LIKE '%" + inputNumber + "%') AND (email LIKE '%" + inputLogins + "%' or username LIKE '%" + inputLogins + "%')";
        if (inputDate != null) {
            req += " AND birth_date = '" + inputDate + "'";
        }
        if (enumprivilege != -1 ) {
            req += "AND privilege='" + Integer.toString(enumprivilege) + "'";
        }
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {try {
                 User u = new User();
                u.setUser_id(rs.getInt(1));
                u.setFirst_name(rs.getString(2));
                u.setLast_name(rs.getString(3));
                u.setCin(rs.getString(4));
                u.setEmail(rs.getString(5));
                u.setUsername(rs.getString(6));
                u.setPassword("none");
                u.setPhone_number(rs.getString(8));
                u.setBirth_date(rs.getDate(9));
                u.setPrivilege(rs.getInt(10));
                listU.add(u);
                }catch(Exception e){
                    System.out.println("erreur in setting user info"+e);
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(userService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listU;
    }

    public void holder(User u) {
        passing = u;

    }

    public User returnholder() {

        return passing;
    }
public int login(String username, String password) {
        List<User> listU = new ArrayList<>();
        String req = "SELECT * FROM `users` WHERE (email = '" + username + "' or username ='" + username + "') AND (password = '" + crypter(password) + "')";
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                try {
                    User u = new User();
                    u.setUser_id(rs.getInt(1));
                    u.setFirst_name(rs.getString(2));
                    u.setLast_name(rs.getString(3));
                    u.setCin(rs.getString(4));
                    u.setEmail(rs.getString(5));
                    u.setUsername(rs.getString(6));
                    u.setPassword("none");
                    u.setPhone_number(rs.getString(8));
                    u.setBirth_date(rs.getDate(9));
                    u.setPrivilege(rs.getInt(10));
                    listU.add(u);
                } catch (Exception e) {
                    System.out.println("erreur in setting user info" + e);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(userService.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (listU.isEmpty()){
            String req1 = "SELECT * FROM `users` WHERE (email = '" + username + "' or username ='" + username + "')";
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(req1);
            while (rs.next()) {
                try {
                    User u = new User();
                    u.setUser_id(rs.getInt(1));
                    u.setFirst_name(rs.getString(2));
                    u.setLast_name(rs.getString(3));
                    u.setCin(rs.getString(4));
                    u.setEmail(rs.getString(5));
                    u.setUsername(rs.getString(6));
                    u.setPassword("none");
                    u.setPhone_number(rs.getString(8));
                    u.setBirth_date(rs.getDate(9));
                    u.setPrivilege(rs.getInt(10));
                    listU.add(u);
                } catch (Exception e) {
                    System.out.println("erreur in setting user info" + e);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(userService.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (!listU.isEmpty()){
            return 20;
        }else{
            return 30;
        }
            
        }else {
            return 10;
        }

    }
static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
static SecureRandom rnd = new SecureRandom();

String randomString(int len){
   StringBuilder sb = new StringBuilder(len);
   for(int i = 0; i < len; i++)
      sb.append(AB.charAt(rnd.nextInt(AB.length())));
   return sb.toString();
}
public void resetPassword(String username){
        List<User> listU = new ArrayList<>();
        String req = "SELECT * FROM `users` WHERE (email = '" + username + "' or username ='" + username + "')";
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                try {
                    User u = new User();
                    u.setUser_id(rs.getInt(1));
                    u.setFirst_name(rs.getString(2));
                    u.setLast_name(rs.getString(3));
                    u.setCin(rs.getString(4));
                    u.setEmail(rs.getString(5));
                    u.setUsername(rs.getString(6));
                    u.setPassword("none");
                    u.setPhone_number(rs.getString(8));
                    u.setBirth_date(rs.getDate(9));
                    u.setPrivilege(rs.getInt(10));
                    listU.add(u);
                } catch (Exception e) {
                    System.out.println("erreur in setting user info" + e);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(userService.class.getName()).log(Level.SEVERE, null, ex);
        }
        User un = listU.get(0);
        JOptionPane frame = new JOptionPane();
        String newpassword = randomString(10);
        try{
            
            Mail mm = new Mail();
            
            mm.sendPasswordMail(un.getEmail(), newpassword);
            JOptionPane.showMessageDialog(frame, "Your new password is sent to : "+un.getEmail());
            un.setPassword(newpassword);
            update(un);            
        }catch(Exception e){
            System.out.println("erreur setting password");
            JOptionPane.showMessageDialog(frame, "Resetting password failed :( \n try later ?");

        }
        
        

    }

    
    
}
