
import java.sql.Date;




/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Asus
 */
public class Colis {
    

    
    int id;
   private String name, departure, destination ,quantity,prix,phone,email,image;

    Colis(int parseInt, String string, String string0, String string1, String string2, String string3, String string4, String string5, Date date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDeparture() {
        return departure;
    }

    public String getDestination() {
        return destination;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getPrix() {
        return prix;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getImage() {
        return image;
    }

    public Date getDate() {
        return date;
    }
  private Date date;

    public Colis(int id, String name, String departure, String destination, String quantity, String prix, String phone, String email, String image, Date date) {
        this.id = id;
        this.name = name;
        this.departure = departure;
        this.destination = destination;
        this.quantity = quantity;
        this.prix = prix;
        this.phone = phone;
        this.email = email;
        this.image = image;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Colis{" + "id=" + id + ", name=" + name + ", departure=" + departure + ", destination=" + destination + ", quantity=" + quantity + ", prix=" + prix + ", phone=" + phone + ", email=" + email + ", image=" + image + ", date=" + date + '}';
    }

  
 

}
    