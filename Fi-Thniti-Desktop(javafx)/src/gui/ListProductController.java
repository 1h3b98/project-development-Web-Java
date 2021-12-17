/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import entities.Product;
import entities.User;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.ProductService;

/**
 * FXML Controller class
 *
 * @author Ramzii
 */
public class ListProductController implements Initializable {

    @FXML
    private TableView<Product> Product_list;
    @FXML
    private TableColumn<Product, Integer> Category;
    @FXML
    private TableColumn<Product, String> Name;
    @FXML
    private TableColumn<Product, String> Brand;
    @FXML
    private TableColumn<Product, Float> Unit;
    @FXML
    private TableColumn<Product, Float> Price;
    @FXML
    private TableColumn<Product, Integer> QTE;
    @FXML
    private TextField N_Search;
    @FXML
    private TextField B_Search;
    @FXML
    private ChoiceBox<String> C_Search;
    private ProductService PS;
    private String[] categoryType = {"All","Food", "Drink", "Medical","Other"};
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        PS = new ProductService();
        C_Search.getItems().addAll(categoryType);
        C_Search.setValue("All");
    }
    public void UpdateTable(){
           Category.setCellValueFactory(new PropertyValueFactory<Product,Integer>("category"));
           Name.setCellValueFactory(new PropertyValueFactory<Product,String>("name"));
           Brand.setCellValueFactory(new PropertyValueFactory<Product,String>("brand"));
           Unit.setCellValueFactory(new PropertyValueFactory<Product, Float>("unit"));
           Price.setCellValueFactory(new PropertyValueFactory<Product, Float>("price"));
           QTE.setCellValueFactory(new PropertyValueFactory<Product, Integer>("qte"));
           if (C_Search.getValue() != "All") {
               List<Product> list;
               list = PS.findbyCategory(C_Search.getSelectionModel().getSelectedIndex()-1);
               ObservableList<Product> products = FXCollections.observableArrayList(list);
               Product_list.setItems(products); 
           }else if (!(N_Search.getText().isEmpty())){
               List<Product> list;
               list = PS.findbyName(N_Search.getText());
               ObservableList<Product> products = FXCollections.observableArrayList(list);
               Product_list.setItems(products);               
           }else if (!(B_Search.getText().isEmpty())){
               List<Product> list;
               list = PS.findbyBrand(B_Search.getText());
               ObservableList<Product> products = FXCollections.observableArrayList(list);
               Product_list.setItems(products);                 
           }else{
               List<Product> list;
               list = PS.findAll();
               ObservableList<Product> products = FXCollections.observableArrayList(list);
               Product_list.setItems(products);                
           }
    }

    @FXML
    private void ConsultProduct(MouseEvent event) {
        if (event.getClickCount() == 2 && !(Product_list.getSelectionModel().isEmpty())){
            Product p =Product_list.getSelectionModel().getSelectedItem();
            PS.holder(p);
            System.out.println(p); 
            FXMLLoader fxmlLoader = new FXMLLoader();
            try {


                Parent root = FXMLLoader.load(getClass().getResource("/gui/EditProduct.fxml"));
                System.out.println("EditProduct.fxml");
                Scene scene = new Scene(root);
                Stage stage = new Stage();

                System.out.println("stage prepared");
                stage.setScene(scene);
                stage.setTitle("Edit product coordinates");
                stage.show();
                System.out.println("stage showed");


            }catch(IOException e){
                System.out.println(e);
            }
        }

    
        
    }

    @FXML
    private void DeleteID(MouseEvent event) {
        if (event.getClickCount() == 1 && !(Product_list.getSelectionModel().isEmpty())){
           Product p =Product_list.getSelectionModel().getSelectedItem();
           PS.holder(p);
        }
    }

    @FXML
    private void Search_Products(ActionEvent event) {
        UpdateTable();
    }

    @FXML
    private void Cancel_Search(ActionEvent event) {
        C_Search.setValue("All");
        N_Search.setText("");
        B_Search.setText("");
        UpdateTable();
    }

    @FXML
    private void Add_Product(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        try {
            
            Parent root = FXMLLoader.load(getClass().getResource("/gui/AddProduct.fxml"));
            System.out.println("AddProduct.fxml");
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            
            System.out.println("stage prepared");
            stage.setScene(scene);
            stage.setTitle("Add product coordinates");
            stage.show();
            System.out.println("stage showed");
            

        }catch(IOException e){
            System.out.println(e);
        }
    }

    @FXML
    private void Delete_Product(ActionEvent event) {
        Product product=PS.returnholder();
        PS.delete(product);
        C_Search.setValue("All");
        N_Search.setText("");
        B_Search.setText("");
        UpdateTable();    
    }

    @FXML
    private void openWindowUser(ActionEvent event) {
                try{
            Parent root = FXMLLoader.load(getClass().getResource("/gui/interfaceAdminGesUser.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            Stage oldstage;
            oldstage = (Stage) N_Search.getScene().getWindow();
            oldstage.close();

            stage.show();            
        }catch(Exception e){
            System.out.println("erreur opening window");
        }
    }

    @FXML
    private void openWindowCarpooling(ActionEvent event) {
                try{
            Parent root = FXMLLoader.load(getClass().getResource("/gui/AffichageCarpoolingFXML.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            Stage oldstage;
            oldstage = (Stage) N_Search.getScene().getWindow();
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
            oldstage = (Stage) N_Search.getScene().getWindow();
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
            oldstage = (Stage) N_Search.getScene().getWindow();
            oldstage.close();

            stage.show();            
        }catch(Exception e){
            System.out.println("erreur opening window");
        }
    }
    
}
