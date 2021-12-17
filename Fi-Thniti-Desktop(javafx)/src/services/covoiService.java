/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entities.carpooling;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import static services.covoiService.passing;
import utils.myDB;
/**
 *
 * @author 1h3b
 */
public class covoiService implements Service<carpooling> {
    static carpooling passing ;
    Connection connection;
    
    public covoiService(){
        connection= myDB.getInstance().getConnection();
    }

    
    /*@Override
    public void insertC(carpooling object) {
         try {
            String req = "INSERT INTO `carpooling`( `departure_date`, `departure_location`, `drop_off_location`, `places_number`, `baggage`, `preference`)VALUES (?,?,?,?,?,?)";
            PreparedStatement pt = connection.prepareStatement(req);
            pt.setString(1, object.getDeparture_date());
            pt.setString(2, object.getDeparture_location());
            pt.setString(3, object.getDrop_off_location());
            pt.setInt(4, object.getPlaces_number());
            pt.setInt(5, object.getBaggage());
            pt.setString(6, object.getPreference());
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(covoiService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
     
    
    @Override
    public void insert(carpooling object) {
        try {
            String req = "INSERT INTO `carpooling`(`userid`,`departure_date`, `departure_location`, `drop_off_location`, `driver_name`, `phone_number`, `places_number`, `baggage`, `preference`)VALUES (47,'"+object.getDeparture_date()+"','"+object.getDeparture_location()+"','"+object.getDrop_off_location()+"','"+object.getDriver_name()+"','"+object.getPhone_number()+"','"+object.getPlaces_number()+"','"+object.getBaggage()+"','"+object.getPreference()+"')";
            Statement st= connection.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(covoiService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(carpooling object) {
        
        try {
            String req = "UPDATE `carpooling` SET `userid`=47 `departure_date`='" + object.getDeparture_date() + "',`departure_location`='" + object.getDeparture_location() + "',`drop_off_location`='" + object.getDrop_off_location() + "',`driver_name`='" +object.getDriver_name()+ "',`phone_number`='" +object.getPhone_number()+ "',`places_number`='" + object.getPlaces_number() + "',`baggage`='" + object.getBaggage() + "',`preference`='" + object.getPreference() +"' WHERE `carpooling_id`='"+object.getCarpooling_id()+"' ";
            Statement st= connection.createStatement();
            int rows = st.executeUpdate(req);
            if (rows > 0) {
                System.out.println("The carpooling information have been updated.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(covoiService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void delete(carpooling object) {
         try {
            String req = "DELETE FROM `carpooling` WHERE `carpooling_id`='"+object.getCarpooling_id()+"' ";
            Statement st= connection.createStatement();
            int rows = st.executeUpdate(req);
            if (rows > 0) {
                System.out.println("The carpooling post have been deleted.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(covoiService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<carpooling> findAll() {//To change body of generated methods, choose Tools | Templates.
        List<carpooling> listC = new ArrayList<>();
        String req = "SELECT * FROM `carpooling`";
        try {
            Statement st = connection.createStatement();
            ResultSet rs =st.executeQuery(req);
            while(rs.next()){
                carpooling c = new carpooling();
                c.setCarpooling_id(rs.getInt(1));
                System.out.println(rs.getInt(2));
                c.setDeparture_date(rs.getDate(3));
                c.setDeparture_location(rs.getString(4));
                c.setDrop_off_location(rs.getString(5));
                c.setDriver_name(rs.getString(6));
                c.setPhone_number(rs.getInt(7));
                c.setPlaces_number(rs.getInt(8));
                c.setBaggage(rs.getString(9));
                c.setPreference(rs.getString(10));
                listC.add(c);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(covoiService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listC;
    }
     public void holder(carpooling u){
        passing = u;
        System.out.println("User stored in holder");
        System.out.println(u);
    }
    public carpooling returnholder(){
        System.out.println("return holder");
        System.out.println(passing);
                
        return passing;
    }

    
    
}


