/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.User;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import services.userService;
import utils.myDB;

/**
 * FXML Controller class
 *
 * @author Ramzii
 */
public class interfaceAdminGesUserController implements Initializable {

    @FXML
    private Button UserTableViewBtnSearch;
    @FXML
    private TextField UserTableViewName;
    @FXML
    private DatePicker UserTableViewBirthday;
    @FXML
    private TextField UserTableViewNumber;
    @FXML
    private TextField UserTableViewLogins;
    @FXML
    private RadioButton UserTableViewPrivilegeAll;
    private ToggleGroup privilege;
    @FXML
    private RadioButton UserTableViewPrivilegeAdmin;
    @FXML
    private RadioButton UserTableViewPrivilegeUser;
    @FXML
    private RadioButton UserTableViewPrivilegeDriver;
    @FXML
    private TableView<User> UserTableViewOUT;
    @FXML
    private TableColumn<User, String> tablecolumnFirstName;
    @FXML
    private TableColumn<User, String> tablecolumnLastName;
    @FXML
    private TableColumn<User, String> tablecolumnCIN;
    @FXML
    private TableColumn<User, String> tablecolumnEmail;
    @FXML
    private TableColumn<User, String> tablecolumnUsername;
    @FXML
    private TableColumn<User, String> tablecolumnPhoneNumber;
    @FXML
    private TableColumn<User, Date> tablecolumnBirthday;
    @FXML
    private TableColumn<User, Integer> tablecolumnPrivilege;
    @FXML
    private Label numberofrows;
    @FXML
    private ToggleGroup inputprivilege;
    @FXML
    private Button usertableviewbtnadd;
    
    private boolean interfaceUseropen = false;
    @FXML
    private Pane gesuser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UpdateTableViewUser();
        // TODO
    }
    
    

    public void UpdateTableViewUser() {
        String inputName = UserTableViewName.getText();
        String inputNumber = UserTableViewNumber.getText();
        Date inputDate = null;
        try {
            inputDate = Date.valueOf(UserTableViewBirthday.getValue());
        } catch (Exception e) {
            inputDate = null;
        }
        String priv = ((RadioButton) inputprivilege.getSelectedToggle()).getText();
        int enumpriv;
        enumpriv = -1;
        if (null != priv) {
            switch (priv) {
                case "All":
                    enumpriv = -1;
                    break;
                case "Admin":
                    enumpriv = 0;
                    break;
                case "User":
                    enumpriv = 1;
                    break;
                case "Driver":
                    enumpriv = 2;
                    break;
                default:
                    break;
            }
        }

        String inputLogins = UserTableViewLogins.getText();
        myDB.getInstance();
        List<User> all;
        userService crud = new userService();
        all = crud.findAll(inputName, inputNumber, inputDate, inputLogins, enumpriv);
        numberofrows.setText("Number of rows : " + Integer.toString(all.size()));
        ObservableList<User> users = FXCollections.observableArrayList(all);
        //tablecolumnID.setCellValueFactory(new PropertyValueFactory<User,Integer>("user_id"));
        tablecolumnFirstName.setCellValueFactory(new PropertyValueFactory<>("first_name"));
        tablecolumnLastName.setCellValueFactory(new PropertyValueFactory<>("last_name"));
        tablecolumnCIN.setCellValueFactory(new PropertyValueFactory<>("cin"));
        tablecolumnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tablecolumnUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        tablecolumnPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phone_number"));
        tablecolumnBirthday.setCellValueFactory(new PropertyValueFactory<>("birth_date"));
        tablecolumnPrivilege.setCellValueFactory(new PropertyValueFactory<>("privilege"));
        UserTableViewOUT.setItems(users);
    }

    @FXML
    private void UserTableViewBtnSearchOnClick(ActionEvent event) {

        UpdateTableViewUser();

    }

    @FXML
    private void UserTableViewTableViewClicked(MouseEvent event) {
        if (event.getClickCount() == 2 && !(UserTableViewOUT.getSelectionModel().isEmpty())) {
            User u = UserTableViewOUT.getSelectionModel().getSelectedItem();
            FXMLLoader fxmlLoader = new FXMLLoader();
            try {
                if (!interfaceUseropen){
                userService crud = new userService();
                crud.holder(u);
                Parent root = FXMLLoader.load(getClass().getResource("/gui/interfaceUser.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setAlwaysOnTop(true);
                stage.setOnHiding(ee -> {
                    UpdateTableViewUser(); interfaceUseropen=false;
                });
                interfaceUseropen = true;
                stage.show();
                }
                //stage.setUserData(u);
            } catch (IOException e) {
                System.out.println(e);
            }
        }

    }

    @FXML
    private void usertableviewbtnaddClicked(MouseEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        try {
            if (!interfaceUseropen){

            userService crud = new userService();
            crud.holder(null);
            Parent root = FXMLLoader.load(getClass().getResource("/gui/interfaceUser.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            
            stage.setOnHiding(ee -> {
                UpdateTableViewUser();interfaceUseropen= false;
            });
            interfaceUseropen=true;
            stage.show();
            }
            //stage.setUserData(u);
        } catch (IOException e) {
            System.out.println(e);
        }

    }

    @FXML
    private void refreshUsersList(ActionEvent event) {
        UpdateTableViewUser();
    }

    @FXML
    private void openWindowCarpooling(ActionEvent event) {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/gui/AffichageCarpoolingFXML.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            Stage oldstage;
            oldstage = (Stage) usertableviewbtnadd.getScene().getWindow();
            oldstage.close();

            stage.show();            
        }catch(Exception e){
            System.out.println("erreur opening window");
        }

        
    }

    @FXML
    private void openWindowTransportation(ActionEvent event) {
                try{
            Parent root = FXMLLoader.load(getClass().getResource("/FXMLDocument.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            Stage oldstage;
            oldstage = (Stage) usertableviewbtnadd.getScene().getWindow();
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
            oldstage = (Stage) usertableviewbtnadd.getScene().getWindow();
            oldstage.close();

            stage.show();            
        }catch(Exception e){
            System.out.println("erreur opening window");
        }
    }

    @FXML
    private void openWindowReclamation(ActionEvent event)
    {
                try{
            Parent root = FXMLLoader.load(getClass().getResource("/gui/Reclamation3FXML.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            Stage oldstage;
            oldstage = (Stage) usertableviewbtnadd.getScene().getWindow();
            oldstage.close();

            stage.show();            
        }catch(Exception e){
            System.out.println(e+"erreur opening window");
        }
    }

}
