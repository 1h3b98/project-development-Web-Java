/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import entities.Product;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
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
public class EditProductController implements Initializable {

    @FXML
    private TextField Edit_brand;
    @FXML
    private TextField Edit_name;
    @FXML
    private TextField Edit_unit;
    @FXML
    private Button Exit_Edit_Product_button;
    @FXML
    private TextField Edit_price;
    @FXML
    private ImageView Photo_id_edit;
    @FXML
    private ChoiceBox<String> Edit_category;
    @FXML
    private TextField Edit_QTE;
private String[] categoryType = {"Food", "Drink", "Medical","Other"};
    ProductService PS=new ProductService();
    Product p= PS.returnholder();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Edit_category.getItems().addAll(categoryType);
        Edit_brand.setText(p.getBrand());
        Edit_name.setText(p.getName());
        Edit_unit.setText(String.valueOf(p.getUnit()));
        Edit_price.setText(String.valueOf(p.getPrice()));
        Edit_QTE.setText(String.valueOf(p.getQte()));
        Edit_category.getSelectionModel().select(p.getCategory());
        
    }    

    @FXML
    private void Edit_product(ActionEvent event) {
        
        if ( (Edit_brand.getText().isEmpty()) || (Edit_name.getText().isEmpty()))
            System.out.print("empty textfield!!!");
        else
        {   
            p=PS.returnholder();
            Product product=new Product();
            product.setProduct_id(p.getProduct_id());
            product.setName(Edit_name.getText());
            product.setBrand(Edit_brand.getText());
            product.setCategory(Edit_category.getSelectionModel().getSelectedIndex());
            product.setPrice(Float.parseFloat(Edit_price.getText()));
            product.setQte(Integer.valueOf(Edit_QTE.getText()) );
            product.setUnit(Float.parseFloat(Edit_unit.getText()));
            PS.update(product);
            Stage stage = (Stage) Edit_brand.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void Exit_Edit_Product(ActionEvent event) {
    }
    
}
