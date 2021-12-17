/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.carpooling;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Date;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.swing.JOptionPane;
import services.covoiService;
import sun.print.resources.serviceui;

/**
 * FXML Controller class
 *
 * @author 1h3b
 */
public class AffichageCarpoolingFXMLController implements Initializable {

    @FXML
    private TableView<carpooling> table_id;
    @FXML
    private TableColumn<carpooling, Date> col_departure_d;
    @FXML
    private TableColumn<carpooling, String> col_departure_l;
    @FXML
    private TableColumn<carpooling, String> col_drop;
    @FXML
    private TableColumn<carpooling, String> col_driver;
    @FXML
    private TableColumn<carpooling, Integer> col_phone;
    @FXML
    private TableColumn<carpooling, Integer> col_places;
    @FXML
    private TableColumn<carpooling, String> col_baggages;
    @FXML
    private TableColumn<carpooling, String> col_preference;
    

    
    
    
    @FXML
    private Button delete_id;
    @FXML
    private Button update_id;
    @FXML
    private Button add_id;
    @FXML
    private TextField search_id;
    @FXML
    private Button refresh_id;
    
    /**
     * Initializes the controller class.
    
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        col_departure_d.setCellValueFactory(new PropertyValueFactory<carpooling,Date>("departure_date"));
        col_departure_l.setCellValueFactory(new PropertyValueFactory<carpooling,String>("departure_location"));
        col_drop.setCellValueFactory(new PropertyValueFactory<carpooling,String>("drop_off_location"));
        col_driver.setCellValueFactory(new PropertyValueFactory<carpooling,String>("driver_name"));
        col_phone.setCellValueFactory(new PropertyValueFactory<carpooling,Integer>("phone_number"));
        col_places.setCellValueFactory(new PropertyValueFactory<carpooling,Integer>("places_number"));
        col_baggages.setCellValueFactory(new PropertyValueFactory<carpooling,String>("baggage"));
        col_preference.setCellValueFactory(new PropertyValueFactory<carpooling,String>("preference"));
         UpdateTable(table_id);
       /*   FilteredList<carpooling> filteredData = new FilteredList<>(dataList, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		search_id.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(employee -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (carpooling.getDeparture_location().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (employee.getDepartment().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				else if (String.valueOf(employee.getSalary()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<carpooling> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(table_id.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		table_id.setItems(sortedData);
     */    
    }
     public void UpdateTable(TableView object) {
        covoiService tt = new covoiService();
        List<carpooling> lr = tt.findAll();
         System.out.println(lr);
        ObservableList<carpooling> datalist= FXCollections.observableArrayList(lr);
        object.setItems(datalist);
          
    }
    
   

     /*private SortedList<carpooling> search(ObservableList<carpooling> liste)
          {
    FilteredList<carpooling> filteredData = new FilteredList<> (liste,b -> true);
       search_id.textProperty().addListener((Observable, oldValue , newValue)-> {
              filteredData.setPredicate(carpooling-> {
              if(newValue == null || newValue.isEmpty()) {
                  return true;
              }
              
                      String lowerCaseFilter = newValue.toLowerCase();
                      if(carpooling.getDrop_off_location().toLowerCase().indexOf(lowerCaseFilter) != -1){return true;}
                      else if (carpooling.getDeparture_location().toLowerCase().indexOf(lowerCaseFilter) != -1)
                      {return true;}
                      
                          
                      else return false;
            
          });
          });
          SortedList<carpooling> sorteddata = new SortedList<>(filteredData);
          sorteddata.comparatorProperty().bind(table_id.comparatorProperty());
          table_id.setItems(sorteddata);
          
          
              return sorteddata;

          
          }*/
     

    @FXML
    private void searchUpdate(InputMethodEvent event) {/*
        List<carpooling> l=cs.findAll();
     
     ObservableList<carpooling> db=FXCollections.observableArrayList(l);
        col_id.setCellValueFactory(new PropertyValueFactory<carpooling,Integer>("carpooling_id"));
        col_departure_d.setCellValueFactory(new PropertyValueFactory<carpooling,String>("departure_date"));
        col_departure_l.setCellValueFactory(new PropertyValueFactory<carpooling,String>("departure_location"));
        col_drop.setCellValueFactory(new PropertyValueFactory<carpooling,String>("drop_off_location"));
        col_driver.setCellValueFactory(new PropertyValueFactory<carpooling,String>("driver_name"));
        col_phone.setCellValueFactory(new PropertyValueFactory<carpooling,Integer>("phone_number"));
        col_places.setCellValueFactory(new PropertyValueFactory<carpooling,Integer>("places_number"));
        col_baggages.setCellValueFactory(new PropertyValueFactory<carpooling,String>("baggage"));
        col_preference.setCellValueFactory(new PropertyValueFactory<carpooling,String>("preference"));
        List<carpooling> r=search(db);
         ObservableList<carpooling> db1=FXCollections.observableArrayList(db);
          table_id.setItems(db1);
          search(db);*/
        
    }

    @FXML
    private void deleteaction(ActionEvent event) {
        carpooling d= table_id.getSelectionModel().getSelectedItem();
        covoiService crud =new covoiService();
        try{
            crud.delete(d);
            JOptionPane.showMessageDialog(null, "DELETE Done");
            UpdateTable(table_id);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "DELETE ERROR");
        }
    }

    @FXML
    private void updateaction(ActionEvent event) {
         
         Parent root;
       
        try {
            root = FXMLLoader.load(getClass().getResource("EditCarpoolingFXML.fxml"));
            Stage stage = new Stage();
            stage.setTitle("edit carppoling");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AffichageCarpoolingFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void addaction(ActionEvent event) {
        
        Parent root;
       
        try {
            root = FXMLLoader.load(getClass().getResource("AddCarpoolingFXML.fxml"));
            Stage stage = new Stage();
            stage.setTitle("add carppoling");
            stage.setScene(new Scene(root));
            stage.show();
            
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "ADD ERROR");
        }
            
            // Hide this current window (if this is what you want)
            //((Node)(event.getSource())).getScene().getWindow().hide();
       
        }

    @FXML
    private void holder(MouseEvent event) {
        
        
           
           if ( !(table_id.getSelectionModel().isEmpty())){
                   carpooling tt = table_id.getSelectionModel().getSelectedItem();
                   System.out.println(table_id.getSelectionModel().getSelectedItem());
                   covoiService allo = new covoiService();
                   allo.holder(tt);

   }}

    @FXML
    private void Refresh(ActionEvent event) {
        UpdateTable(table_id);
    }

    @FXML
    private void openWindowUser(ActionEvent event) {
                try{
            Parent root = FXMLLoader.load(getClass().getResource("/gui/interfaceAdminGesUser.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            Stage oldstage;
            oldstage = (Stage) delete_id.getScene().getWindow();
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
            oldstage = (Stage) delete_id.getScene().getWindow();
            oldstage.close();

            stage.show();            
        }catch(Exception e){
            System.out.println("erreur opening window");
        }
    }

    @FXML
    private void openWindowReclamation(ActionEvent event) {
                try{
            Parent root = FXMLLoader.load(getClass().getResource("/gui/Reclamation3FXML.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            Stage oldstage;
            oldstage = (Stage) delete_id.getScene().getWindow();
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
            oldstage = (Stage) delete_id.getScene().getWindow();
            oldstage.close();

            stage.show();            
        }catch(Exception e){
            System.out.println("erreur opening window");
        }
    }
}
     
     
    

