




import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Asus
 */
public class mysqlconnect {
    Connection conn = null;
   public static Connection ConnectDb(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/pidev_db","root","");
            JOptionPane.showMessageDialog(null, "Connection Established");
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    public static ObservableList<Colis> getDatausers(){
        Connection conn = ConnectDb();
        ObservableList<Colis> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from livrer");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new Colis(Integer.parseInt(rs.getString("colis_id")),
                        rs.getString("colis_name"), 
                       rs.getString("departure"), 
                        rs.getString("destination"),  
                       rs.getString("quantity"),
                       
                       rs.getString("prix"),
                         rs.getString("phone"),
                       rs.getString("mail"),
                        rs.getDate("date")
                                    ));               
            }
        } catch (Exception e) {
        }
        return list;
    }
   
    public static void main (String [] args){
    mysqlconnect.ConnectDb();
    }
}