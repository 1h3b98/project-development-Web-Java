/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.cdetails;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.cdetailService;

/**
 * FXML Controller class
 *
 * @author 1h3b
 */
public class AddCdetailsFXMLController implements Initializable {
     @FXML
    private Button add_details;
    @FXML
    private TextField cin_field;
    @FXML
    private CheckBox clima_check;
    @FXML
    private CheckBox music_check;
    
    private cdetailService cs;
    @FXML
    private Label id_carpooling;
     
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    cs=new cdetailService();

    }


   

    @FXML
    private void AddCdetails(ActionEvent event) {
        if (cin_field.getText() == null){
            System.out.println("cin is empty!!!!!");
            JOptionPane.showMessageDialog(null, "cin is empty!!!!!");
        
        
        } else {
            cdetails c = new cdetails();
            
            
            c.setDriverCin(Integer.parseInt(cin_field.getText()));
            if(music_check.isSelected()==true)c.setMusic(1);
            else c.setMusic(0);
            if(clima_check.isSelected()==true)c.setClimatisation(1);
            else c.setClimatisation(0);
            
            System.out.println("done");
            
            cs.insert(c);
            JOptionPane.showMessageDialog(null, "ADD DONE");
            
            ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
            
    }
    
}
}
