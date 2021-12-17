/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.userService;
import utils.Mail;

/**
 * FXML Controller class
 *
 * @author Ramzii
 */
public class InterfaceHomeController implements Initializable {

    @FXML
    private TextField hometextfieldto;
    @FXML
    private TextField hometextfieldfrom;
    @FXML
    private DatePicker homedateday;
    @FXML
    private TableView<?> hometableviewcarpooling;
    @FXML
    private TableView<?> hometableviewdelivery;
    @FXML
    private TextField hometextfieldlogin;
    @FXML
    private PasswordField hometextfieldpassword;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void homebtnsearchclicked(MouseEvent event) {
    }

    @FXML
    private void homebtnloginclicked(MouseEvent event) {
        String username;
        String password;
        username = hometextfieldlogin.getText();
        password = hometextfieldpassword.getText();
        userService crud = new userService();
        JOptionPane frame = new JOptionPane();
        switch (crud.login(username, password)) {
            case 10:
                JOptionPane.showMessageDialog(frame, "Access Granded");
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/gui/interfaceAdminGesUser.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                } catch (Exception e) {
                    System.out.println("erreur" + e);
                }

                break;
            case 20:
                JOptionPane.showMessageDialog(frame, "Wrong Password");
                break;
            case 30:
                JOptionPane.showMessageDialog(frame, "Invalid Username/E-Mail");
                break;
        }
    }

    @FXML
    private void homebtnsignupclicked(MouseEvent event) {
    }

    @FXML
    private void homebtnresetpassword(MouseEvent event) {
        String username;
        String password;
        username = hometextfieldlogin.getText();
        password = hometextfieldpassword.getText();
        Mail m = new Mail();
        userService crud = new userService();
        JOptionPane frame = new JOptionPane();
        switch (crud.login(username, password)) {
            case 10:
                JOptionPane.showMessageDialog(frame, "Your password is correct why resetting it ? :O");
                break;
            case 20:
                try{
                    crud.resetPassword(username);
                }catch(Exception e){
                    System.out.println("Erreur sending mail");
                }
                
                break;
            case 30:
                JOptionPane.showMessageDialog(frame, "Invalid Username/E-Mail");
                break;
        }
    }

}
