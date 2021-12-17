/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;
import java.util.*;
import java.sql.Date;

/**
 *
 * @author 1h3b
 */

public class carpooling {
    private int carpooling_id;
    private Date departure_date;
    private String departure_location;
    private String drop_off_location;
    private String driver_name;
    private int phone_number;
    private int places_number;
    private String baggage;
    private String preference;
    
    public carpooling(int carpooling_id,Date departure_date,String departure_location,String drop_off_location,String driver_name,int phone_number,int places_number,String baggage,String preference) {
           this.carpooling_id=carpooling_id;
           this.departure_date=departure_date;
           this.departure_location=departure_location;
           this.drop_off_location=drop_off_location;
           this.driver_name=driver_name;
           this.phone_number=phone_number;
           this.places_number=places_number;
           this.baggage=baggage;
           this.preference=preference;
          
}

    public carpooling() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getCarpooling_id() {
        return carpooling_id;
    }

    public void setCarpooling_id(int carpooling_id) {
        this.carpooling_id = carpooling_id;
    }

    public Date getDeparture_date() {
        return departure_date;
    }

    public void setDeparture_date(Date departure_date) {
        this.departure_date = departure_date;
    }

    public String getDeparture_location() {
        return departure_location;
    }

    public void setDeparture_location(String departure_location) {
        this.departure_location = departure_location;
    }

    public String getDrop_off_location() {
        return drop_off_location;
    }

    public void setDrop_off_location(String drop_off_location) {
        this.drop_off_location = drop_off_location;
    }

    public String getDriver_name() {
        return driver_name;
    }

    public void setDriver_name(String driver_name) {
        this.driver_name = driver_name;
    }

    public int getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(int phone_number) {
        this.phone_number = phone_number;
    }

    public int getPlaces_number() {
        return places_number;
    }

    public void setPlaces_number(int places_number) {
        this.places_number = places_number;
    }

    public String getBaggage() {
        return baggage;
    }

    public void setBaggage(String baggage) {
        this.baggage = baggage;
    }

    public String getPreference() {
        return preference;
    }

    public void setPreference(String preference) {
        this.preference = preference;
    }

    @Override
    public String toString() {
        return "carpooling{" + "carpooling_id=" + carpooling_id + ", departure_date=" + departure_date + ", departure_location=" + departure_location + ", drop_off_location=" + drop_off_location + ", driver_name=" + driver_name + ", phone_number=" + phone_number + ", places_number=" + places_number + ", baggage=" + baggage + ", preference=" + preference + '}';
    }

    

   
    
   
    
    
}

