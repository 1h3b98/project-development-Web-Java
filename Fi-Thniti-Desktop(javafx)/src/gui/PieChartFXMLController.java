/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import utils.myDB;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * FXML Controller class
 *
 * @author ouerf
 */
public class PieChartFXMLController implements Initializable {

    @FXML
    private AnchorPane label;
    @FXML
    private Button button;
    @FXML
    private Label label1;
    @FXML
    private PieChart pcReclamation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Connection connection = myDB.getInstance().getConnection();
        ObservableList<PieChart.Data>list=FXCollections.observableArrayList();
                   String sql5="SELECT COUNT(*), email, complaint_id FROM complaint GROUP BY email";
            Statement st;
        try {
            st = connection.createStatement();
            ResultSet rs=st.executeQuery(sql5);
            
                            while(rs.next()){
                                System.out.println(rs.getString("email")+"      "+Integer.toString(rs.getInt("COUNT(*)")));
                    list.add(new PieChart.Data(rs.getString("email")+" :"+rs.getInt("COUNT(*)"), rs.getInt("COUNT(*)")));
                }
                            pcReclamation.setData(list);
        } catch (SQLException ex) {
            Logger.getLogger(PieChartFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
                

    }    

    @FXML
    private void handle_Button_Action(ActionEvent event) {
    }
    
}
