/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import java.io.IOException;
import java.sql.SQLException;
import javafx.scene.control.ButtonType;


import entities.Reclamation;
import services.ReclamationService;
import java.net.URL;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import utils.Mail;
import utils.myDB;


/**
 * FXML Controller class
 *
 * @author ouerf
 */
public class Reclamation3FXMLController implements Initializable {

    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfObject;
    @FXML
    private TextArea tfDescription;
    @FXML
    private Button buttonValider;
    @FXML
    private TableView<Reclamation> tableReclamation;
    @FXML
    private TableColumn<Reclamation, String> col_Email;
    @FXML
    private TableColumn<Reclamation, String> col_Object;
    @FXML
    private TableColumn<Reclamation, String> col_Description;
    @FXML
    private Button buttonDelete;
    @FXML
    private Button buttonEdit;
    
    Connection connection=null;
    PreparedStatement pst;
    ResultSet rs=null;
    ObservableList<Reclamation> list;
    ReclamationService Resc=new ReclamationService();
    int index=-1;
    
    //ObservableList<Reclamation> list=FXCollections.observableArrayList();
    @FXML
    private TableColumn<?, ?> col_Last_name;
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      // UpdateTable();
    }    

    @FXML
    private void ValiderReclamation(ActionEvent event) throws Exception{
        
        Reclamation rec = new Reclamation(tfEmail.getText(),tfObject.getText(),tfDescription.getText());
        
        ReclamationService RS = new ReclamationService();
        
        System.out.println(rec);
        if (tfEmail.getText().isEmpty() || tfObject.getText().isEmpty() || tfDescription.getText().isEmpty()){
        UpdateTable();
        }else{
         try {
                RS.ajouter_Reclamation(rec);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Réclamer");
                alert.setContentText("successful addition!");
                alert.show();
                
                UpdateTable();
                
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.show();
            }
             Mail maile=new Mail();
             maile.sendMail(tfEmail.getText());
             JOptionPane.showMessageDialog(null, "Your complaint has been registred");
        }
    }

////////////////////////////////////////////////////////////////////////////////
    @FXML
    private void SupprimerReclamation(ActionEvent event) {
        Reclamation rr = tableReclamation.getSelectionModel().getSelectedItem();
        ReclamationService crud = new ReclamationService();
        try {
            crud.supprimer_Reclamation(rr);
            JOptionPane.showMessageDialog(null, "Do you want to delete this reclamation?");
            UpdateTable();
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "ERREUR SUPPRESSION");
        }        
    }

    @FXML
    private void ModifierReclamation(ActionEvent event) {
        ReclamationService crud = new ReclamationService();
        try {
            Reclamation rr = tableReclamation.getSelectionModel().getSelectedItem();
            rr.setEmail(tfEmail.getText());
            rr.setObject(tfObject.getText());
            rr.setDescription(tfDescription.getText());
            crud.modifier_Reclamation(rr);
            JOptionPane.showMessageDialog(null, "Update");
            UpdateTable();
        } catch (Exception ex) {
            Logger.getLogger(Reclamation3FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
////////////////////////////////////////////////////////////////////////////////
    
    private void UpdateTable() {
        col_Email.setCellValueFactory(new PropertyValueFactory<>("Email"));
        col_Object.setCellValueFactory(new PropertyValueFactory<>("Object"));
        col_Description.setCellValueFactory(new PropertyValueFactory<>("Description"));
        myDB.getInstance();
        ReclamationService crud = new ReclamationService();
        List<Reclamation> all;
        all= crud.getAllReclamation()  ;
        System.out.println(all);
        ObservableList<Reclamation> reclamations = FXCollections.observableArrayList(all);
        tableReclamation.setItems(reclamations);
        System.out.println(all);
    }
 
 void getSelected (MouseEvent event){
    }

    @FXML
    private void TableViewRowClicked(MouseEvent event) {
            Reclamation rr = tableReclamation.getSelectionModel().getSelectedItem();
    if (rr != null) {
        tfEmail.setText(rr.getEmail());
        tfObject.setText(rr.getObject());
        tfDescription.setText(rr.getDescription());
    }
        
    }

    @FXML
    private void openWindowUser(ActionEvent event)  {
                try{
            Parent root = FXMLLoader.load(getClass().getResource("/gui/interfaceAdminGesUser.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            Stage oldstage;
            oldstage = (Stage) buttonDelete.getScene().getWindow();
            oldstage.close();

            stage.show();            
        }catch(Exception e){
            System.out.println("erreur opening window");
        }
    }
    

    @FXML
    private void openWindowCarpooling(ActionEvent event)  {
                try{
            Parent root = FXMLLoader.load(getClass().getResource("/gui/AffichageCarpoolingFXML.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            Stage oldstage;
            oldstage = (Stage) buttonDelete.getScene().getWindow();
            oldstage.close();

            stage.show();            
        }catch(Exception e){
            System.out.println("erreur opening window");
        }
    }
    

    @FXML
    private void openWindowProducts(ActionEvent event) {
                try{
            Parent root = FXMLLoader.load(getClass().getResource("/gui/ListProduct.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            Stage oldstage;
            oldstage = (Stage) buttonDelete.getScene().getWindow();
            oldstage.close();

            stage.show();            
        }catch(Exception e){
            System.out.println("erreur opening window");
        }
    }
    
}
