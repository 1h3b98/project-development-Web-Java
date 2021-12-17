/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author 1h3b
 */
public class myDB {
    String url = "jdbc:mysql://localhost:3306/pidev_db";
    String user = "root";
    String pwd = "";
    Connection connection;
    static myDB instance;
    
    private myDB(){
        try {
            connection = DriverManager.getConnection(url,user,pwd);
            System.out.println("connection etablie");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());        }
    }
    public static myDB getInstance(){
        if(instance == null){
            instance = new myDB();
        }
        return instance;
 
   }
     public Connection getConnection(){
         return connection;
     }
}
