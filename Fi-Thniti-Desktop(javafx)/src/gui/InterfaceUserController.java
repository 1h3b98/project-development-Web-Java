/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import java.sql.Date;
import java.time.ZoneId;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.userService;
import utils.myDB;

/**
 * FXML Controller class
 *
 * @author Ramzii
 */
public class InterfaceUserController implements Initializable {

    @FXML
    private TextField user_textfield_firstname;
    @FXML
    private TextField user_textfield_lastname;
    @FXML
    private TextField user_textfield_username;
    @FXML
    private TextField user_textfield_email;
    @FXML
    private TextField user_textfield_cin;
    @FXML
    private TextField user_textfield_password;
    @FXML
    private TextField user_textfield_phonenumber;
    @FXML
    private ChoiceBox<String> user_textfield_privilege;
    @FXML
    private DatePicker user_textfield_birthday;
    @FXML
    private TextField user_textfield_search_username;

    private String[] privilegeType = {"Admin", "User", "Driver"};
    @FXML
    private Button userbtninsert;
    @FXML
    private Button userbtnupdate;
    @FXML
    private Button userbtndelete;

    /**
     * Initializes the controller class.
     */
    private Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        user_textfield_privilege.getItems().addAll(privilegeType);

        userService crud = new userService();
        User u = crud.returnholder();
        userbtninsert.setVisible(u == null);
        userbtnupdate.setVisible(u != null);
        userbtndelete.setVisible(u != null);
        if (u != null) {

            user_textfield_firstname.setText(u.getFirst_name());
            user_textfield_lastname.setText(u.getLast_name());
            user_textfield_cin.setText(u.getCin());
            user_textfield_email.setText(u.getEmail());
            user_textfield_username.setText(u.getUsername());
            user_textfield_password.setText("");
            user_textfield_phonenumber.setText(u.getPhone_number());
            user_textfield_birthday.setValue(u.getBirth_date().toLocalDate());
            switch (u.getPrivilege()) {
                case 0:
                    user_textfield_privilege.setValue("Admin");
                    break;
                case 1:
                    user_textfield_privilege.setValue("User");
                    break;
                case 2:
                    user_textfield_privilege.setValue("Driver");
                    break;
            }
        }

        // TODO 
    }

    @FXML
    private void user_button_insert_pressed(ActionEvent event) {
        User u = new User();
        stage = (Stage) userbtnupdate.getScene().getWindow();
        try {
            u.setFirst_name(user_textfield_firstname.getText());
            u.setLast_name(user_textfield_lastname.getText());
            u.setCin(user_textfield_cin.getText());
            u.setEmail(user_textfield_email.getText());
            u.setUsername(user_textfield_username.getText());
            u.setPassword(user_textfield_password.getText());
            u.setPhone_number(user_textfield_phonenumber.getText());
            try {
                Date.valueOf(user_textfield_birthday.getValue());
            } catch (Exception error) {
                throw new Exception("Invalid Date");
            }
            u.setBirth_date(Date.valueOf(user_textfield_birthday.getValue()));
            u.setPrivilege(user_textfield_privilege.getSelectionModel().getSelectedIndex());
            myDB.getInstance();
            userService crud = new userService();
            crud.insert(u);
            stage.close();
        } catch (Exception e) {

            stage.setAlwaysOnTop(false);
            JOptionPane frame = new JOptionPane();
            JOptionPane.showMessageDialog(frame, e.getMessage());
            stage.setAlwaysOnTop(true);

        }

    }

    @FXML
    private void user_button_update_pressed(ActionEvent event) {
        stage = (Stage) userbtnupdate.getScene().getWindow();
        userService crud = new userService();
        User u = crud.returnholder();
        try {
            u.setFirst_name(user_textfield_firstname.getText());
            u.setLast_name(user_textfield_lastname.getText());
            u.setCin(user_textfield_cin.getText());
            u.setEmail(user_textfield_email.getText());
            u.setUsername(user_textfield_username.getText());
            if (!("".equals(user_textfield_password.getText()))) {
                u.setPassword(user_textfield_password.getText());
            }
            u.setPhone_number(user_textfield_phonenumber.getText());
            try {
                Date.valueOf(user_textfield_birthday.getValue());
            } catch (Exception error) {
                throw new Exception("Invalid Date");
            }
            u.setBirth_date(Date.valueOf(user_textfield_birthday.getValue()));
            u.setPrivilege(user_textfield_privilege.getSelectionModel().getSelectedIndex());
            crud.update(u);
            stage.close();
        } catch (Exception e) {
            stage.setAlwaysOnTop(false);
            JOptionPane frame = new JOptionPane();
            JOptionPane.showMessageDialog(frame, e.getMessage());
            stage.setAlwaysOnTop(true);
        }

    }

    @FXML
    private void user_button_delete_pressed(ActionEvent event) {
        stage = (Stage) userbtnupdate.getScene().getWindow();
        userService crud = new userService();
        User u = crud.returnholder();
        crud.delete(u);
        stage.close();

    }

}
