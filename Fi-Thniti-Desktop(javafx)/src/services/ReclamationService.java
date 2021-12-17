/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Reclamation;
//import static java.lang.String.format;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import static java.text.MessageFormat.format;
//import java.text.SimpleDateFormat;
import java.util.ArrayList;
//import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.myDB;

/**
 *
 * @author ouerf
 */
public class ReclamationService implements IReclamation {
    Connection connection;
    public ReclamationService(){
        connection=myDB.getInstance().getConnection();
    }
    
   
 
    
    public void ajouter_Reclamation(Reclamation p) { //WORKS
        
        //String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date()); 
//        String state="0";
//        int user_id=12345;

         
         try {   
            String sql = "INSERT INTO `complaint`( `complainttype_id` , `object`,`description`, `email`) VALUES(1,'" +p.getObject()+"','"+p.getDescription() +"','" +p.getEmail() +"')" ;
            System.out.println(sql);
            Statement pst= connection.createStatement();
                pst.executeUpdate(sql);
            } 
        catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    
    }   
        
////////////////////////////////////////////////////////////////////////////////
    @Override
    public void modifier_Reclamation(Reclamation p) { //WORKS
        
//        int user_id=0;
//        int state=0;
//        
        try {
            
            
     
            String sql2 = "UPDATE `complaint` SET `object`='" + p.getObject()+ "',`description`='" + p.getDescription()+ "' WHERE `id`='"+p.getComplaint_id()+"' ";
            System.out.println(sql2);
            Statement pstmt=connection.createStatement();
            pstmt.executeUpdate(sql2);
           
            
        } catch (SQLException ex) {
            
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
 
    }
////////////////////////////////////////////////////////////////////////////////
    @Override
    public void supprimer_Reclamation(Reclamation p) { //WORKS
        
        try {
            String sql4 = "DELETE FROM `complaint` WHERE `id`='"+p.getComplaint_id()+"' ";
            Statement pstmt= connection.createStatement();
            pstmt.executeUpdate(sql4);
           
   
            
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
////////////////////////////////////////////////////////////////////////////////
    @Override
    public List<Reclamation> getAllReclamation() { //WORKS
        
        List<Reclamation> listR = new ArrayList<>();
 

       
        try {
            String sql5= "SELECT * FROM `complaint`";
            Statement st  = connection.createStatement();
    
                ResultSet rs=st.executeQuery(sql5);
                while(rs.next()){
                    Reclamation p = new Reclamation();
                    p.setComplaint_id(rs.getInt(1));
                    p.setObject(rs.getString(3));
                    p.setDescription(rs.getString(4));
                    p.setEmail(rs.getString(5));
                    
                    listR.add(p);
                }
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }         
                
 
        return listR;
       
    }
////////////////////////////////////////////////////////////////////////////////
    @Override
    public void traiter_Reclamation(Reclamation p) { //WORKS
        /*try {
            String sql3 = "UPDATE `complaint` SET `state`='"+p.getState()+ "' WHERE `complaint_id`='"+p.getComplaint_id()+"' ";
            Statement pstmt=connection.createStatement();
            pstmt.executeUpdate(sql3);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }
/////////////////////////////////////////////////////////////////////////////////
    @Override
    public int getNbr_Reclamation() { //WORKS
        
        
        int countIdRec=0; 
        
        try {
            String sql6="SELECT COUNT(*) FROM `complaint`";
            Statement st= connection.createStatement();
            ResultSet rs = st.executeQuery(sql6);
            
            while(rs.next()){
            countIdRec=rs.getInt(1);
        }
          
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return countIdRec;
    }
}

  
///////////////////////////////////////////////////////////////////////////////////
//    @Override
//    public barChart loadChart() {
//        
//    }
//    
//
