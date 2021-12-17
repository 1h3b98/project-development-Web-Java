/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import gui.AffichageCarpoolingFXMLController;
import entities.carpooling;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.covoiService;

/**
 * FXML Controller class
 *
 * @author 1h3b
 */
public class EditCarpoolingFXMLController implements Initializable {

    @FXML
    private Button cancel_button_id;
    @FXML
    private DatePicker departure_date_id;
    @FXML
    private TextField departure_location_id;
    @FXML
    private TextField drop_off_location_id;
    @FXML
    private TextField places_number_id;
    @FXML
    private TextField phone_id;
    @FXML
    private TextArea preference_id;
    @FXML
    private ComboBox combo_baggage;
    private covoiService cs;
    @FXML
    private Button edit_button_id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cs=new covoiService();
        ObservableList<String> list= FXCollections.observableArrayList("Yes","No");
        
        carpooling u=cs.returnholder();
        
        departure_location_id.setText(u.getDeparture_location());
        departure_date_id.setValue(u.getDeparture_date().toLocalDate());
        drop_off_location_id.setText(u.getDrop_off_location());;
        phone_id.setText(Integer.toString(u.getPhone_number()));
        places_number_id.setText(Integer.toString(u.getPlaces_number()));
        combo_baggage.setValue(u.getBaggage());
        preference_id.setText(u.getPreference());
            
        
        
        
        combo_baggage.setItems(list);
        
    }    

    @FXML
    private void CloseAdd(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

    @FXML
    private void EditCarpooling(ActionEvent event) {
         if (departure_date_id.getValue() == null){
            System.out.println("date is empty!!!!!");
            JOptionPane.showMessageDialog(null, "date is empty!!!!!");
        } else if (departure_location_id.getText().isEmpty()){
            System.out.println("departure locaton is empty!!!");
            JOptionPane.showMessageDialog(null, "departure locaton is empty!!!");
        } else if (drop_off_location_id.getText().isEmpty()){
            System.out.println("drop off location is empty!!!");
            JOptionPane.showMessageDialog(null, "drop off location is empty!!!");
        } else if (phone_id.getText().isEmpty()){
            System.out.println("phone number is empty!!!");
            JOptionPane.showMessageDialog(null, "phone number is empty!!!");
        } else if (String.valueOf(phone_id.getText()).length()!= 8){
            System.out.println("phone number must have 8 degits");
            JOptionPane.showMessageDialog(null, "phone number must have 8 degits");
        } else if (places_number_id.getText().isEmpty()){
            System.out.println("places number is empty!!!");
            JOptionPane.showMessageDialog(null, "places number is empty!!!");
        } else if (Integer.parseInt(places_number_id.getText())>4 && Integer.parseInt(places_number_id.getText())<1 ){
            System.out.println("places number is bigger then 4 or lower then 0!!!");
            JOptionPane.showMessageDialog(null, "places number is bigger then 4 or lower then 0!!!");
        } else if (combo_baggage.getValue()==null) {
            System.out.println("baggages is empty!!!");
            JOptionPane.showMessageDialog(null, "baggages is empty!!!");
        
        } else {
            
           
            
           carpooling u= new carpooling();
           u=cs.returnholder();
            u.setDeparture_date(Date.valueOf(departure_date_id.getValue()));
            u.setDeparture_location(departure_location_id.getText());
            u.setDrop_off_location(drop_off_location_id.getText());
            u.setPhone_number(Integer.parseInt(places_number_id.getText()));
            u.setPlaces_number(Integer.parseInt(places_number_id.getText()));
            u.setBaggage(String.valueOf(combo_baggage.getValue()));
            u.setPreference(preference_id.getText());
            System.out.println("done");
            
            cs.update(u);
           JOptionPane.showMessageDialog(null, "EDIT DONE");
            
            ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
        }
    }
    
}
