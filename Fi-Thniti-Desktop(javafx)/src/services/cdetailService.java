/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entities.carpooling;
import entities.cdetails;
import java.sql.Connection;
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
import static services.cdetailService.passing;
import utils.myDB;

/**
 *
 * @author 1h3b
 */
public class cdetailService implements Service<cdetails> {
    static cdetails passing ;
    Connection connection;
    
    public cdetailService(){
        connection= myDB.getInstance().getConnection();
    }

 @Override
    public void insert(cdetails object) {
        int id_carpooling  = 53;
        try {
            
                String req1 = "SELECT * FROM carpooling WHERE id=(SELECT max(id) FROM carpooling)";
            Statement st1 = connection.createStatement();
            ResultSet rs1 =st1.executeQuery(req1);
            while(rs1.next()){
                id_carpooling=  rs1.getInt(1);
            }
            
            String req = "INSERT INTO `c_details`(`detail_id`, `driver_cin`, `music`, `climatisation`)VALUES ('"+id_carpooling+"','"+object.getDriverCin()+"','"+object.isMusic()+"','"+object.isClimatisation()+"')";
            Statement st= connection.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(covoiService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(cdetails object) {
        
       try {
            String req = "UPDATE `c_details` SET `driver_cin`='" + object.getDriverCin() + "',`music`='" + object.isMusic() + "',`climatisation`='" + object.isClimatisation() + "' WHERE `detail_id`='"+object.getDetail()+"' ";
            Statement st= connection.createStatement();
            int rows = st.executeUpdate(req);
            if (rows > 0) {
                System.out.println("The details information have been updated.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(covoiService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(cdetails object) {
        try {
            String req = "DELETE FROM `carpooling` WHERE `detail_id`='"+object.getDetail()+"' ";
            Statement st= connection.createStatement();
            int rows = st.executeUpdate(req);
            if (rows > 0) {
                System.out.println("The details post have been deleted.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(covoiService.class.getName()).log(Level.SEVERE, null, ex);
        }    }

    @Override
    public List findAll() {
             List<cdetails> listC = new ArrayList<>();
        String req = "SELECT * FROM `c_details`";
        try {
            Statement st = connection.createStatement();
            ResultSet rs =st.executeQuery(req);
            while(rs.next()){
                cdetails c = new cdetails();
                c.setDriverCin(rs.getInt(1));
                //System.out.println(rs.getDate(2));
                c.setMusic(rs.getInt(2));
                c.setClimatisation(rs.getInt(3));
                c.setDetail(rs.getInt(4));
                listC.add(c);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(covoiService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listC;
    }
    
}
