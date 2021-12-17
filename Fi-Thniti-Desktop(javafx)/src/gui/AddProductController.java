/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Product;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import services.ProductService;

/**
 * FXML Controller class
 *
 * @author nadaa
 */
public class AddProductController implements Initializable {

    @FXML
    private TextField Add_brand;
    @FXML
    private TextField Add_name;
    @FXML
    private TextField Add_unit;
    @FXML
    private Button Exit_Add_Product_button;
    @FXML
    private TextField Add_price;
    @FXML
    private ImageView Photo_id_edit;
    @FXML
    private ChoiceBox<String> Add_category;
    @FXML
    private TextField Add_QTE;
    private String[] categoryType = {"Food", "Drink", "Medical","Other"};
    ProductService PS=new ProductService();
    Product p= PS.returnholder();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Add_category.getItems().addAll(categoryType);
        Add_category.setValue("Food");
    }    

    @FXML
    private void Add_product(ActionEvent event) {
        if ( (Add_brand.getText().isEmpty()) || (Add_name.getText().isEmpty()))
            System.out.print("empty textfield!!!");
        else
        {   
            p=PS.returnholder();
            Product product=new Product();
            product.setName(Add_name.getText());
            product.setBrand(Add_brand.getText());
            product.setCategory(Add_category.getSelectionModel().getSelectedIndex());
            product.setPrice(Float.parseFloat(Add_price.getText()));
            product.setQte(Integer.valueOf(Add_QTE.getText()) );
            product.setUnit(Float.parseFloat(Add_unit.getText()));
            PS.insert(product);
            Stage stage = (Stage) Add_brand.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void Exit_Add_Product(ActionEvent event) {
    }
    
}
