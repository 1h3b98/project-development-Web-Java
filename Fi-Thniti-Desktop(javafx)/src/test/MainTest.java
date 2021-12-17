/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.carpooling;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.covoiService;
import utils.myDB;

/**
 *
 * @author 1h3b
 */
public class MainTest {
 public static void main(String[] args){
      myDB.getInstance(); 
     
      //SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
      //DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
      //Date date;
      //String dateInString = "2021-01-02";
      covoiService ps=new covoiService();
      
      /*try { 
            covoiService ps=new covoiService();
            date = dateFormat.parse("2021-12-4");
            String output = dateFormat.format(date);
            //Date date = formatter.parse(dateInString);
              //carpooling c = new carpooling(6,"2021-01-01","kélibia","tunis",1,1,"BBBBBB");
            //ps.insert(c);
            
            carpooling c = new carpooling(6,date,"kélibia","tunis",1,1,"BBBBBB");
            ps.insert(c);
            //System.out.println(ps.findAll().toString());
            //ps.update(c);
            //ps.delete(c);
        } catch (ParseException e) {
            e.printStackTrace();
        }*/

      
      
      
      
    }   
}
