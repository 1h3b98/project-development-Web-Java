/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

//import java.sql.Blob;
import java.sql.Date;

/**
 *
 * @author ouerf
 */
public class Reclamation {
    private String object;
    private String description;
    private int complaint_id;
    private String email; 

    public Reclamation(String email, String object, String descritpion) {
        this.email=email;
        this.object=object;
        this.description= descritpion;
    }

    public Reclamation(int user_id, Date date, String state, String object, String description, String email) {
        this.object=object;
        this.description= description;
        this.email=email;
        
    }

    public Reclamation() {
         
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getComplaint_id() {
        return complaint_id;
    }

    public void setComplaint_id(int complaint_id) {
        this.complaint_id = complaint_id;
    }


//    public Blob getImage() {
//        return image;
//    }

//    public void setImage(Blob image) {
//        this.image = image;
//    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "object=" + object + ", description=" + description +  ", complaint_id=" + complaint_id +  ", email=" + email + '}';
    }
    
    
    
}
