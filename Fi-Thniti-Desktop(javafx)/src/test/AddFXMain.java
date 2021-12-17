/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author 1h3b
 */
public class AddFXMain extends Application {
    
    @Override
    public void start(Stage addStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/interfaceHome.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("/gui/AddCarpoolingFXML.fxml"));
        Scene scene =new Scene(root);
        //addStage.setTitle("affiche carpooling");
        addStage.setScene(scene);
        addStage.show();
 
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
