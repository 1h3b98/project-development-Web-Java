//152327707
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Desktop;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import static javafx.scene.text.Font.font;
import static javafx.scene.text.Font.font;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
/**
 * FXML Controller class
 *
 * @author Asus
 */
public class FXMLDocumentController implements Initializable {
    
    
    @FXML
    private TableView<Colis> table1;
   

//   public String imagecomp; 
    @FXML Pane pane_add; @FXML   Pane pane_menu;    @FXML  Pane pane_modify;  
    @FXML
    private TableColumn<Colis, Integer> col_id;
     @FXML
    private TableColumn<Colis, String> col_phone;
      @FXML
    private TableColumn<Colis, String> col_mail;
 @FXML
    private TableColumn<Colis, String> col_name;
  @FXML
    private TableColumn<Colis, String> col_departure;
   @FXML
    private TableColumn<Colis, String> col_destination;
    @FXML
    private TableColumn<Colis, String> col_quantity;
 @FXML
    private TableColumn<Colis, String> col_weight;
@FXML
    private TableColumn<Colis, String> col_date;
 @FXML
    private TableColumn<Colis, String> col_price;
  
     @FXML
    private TextField txt_id;
    @FXML
    private TextField txt_name;


    @FXML
    private TextField txt_quantity;
         
    @FXML
    private TextField txt_weight;
        @FXML
    private TextField txt_price;
          @FXML
    private TextField txt_mail;
        @FXML
    private TextField txt_phone;
@FXML
private DatePicker date1;
@FXML
private TextField txt_id2;
    @FXML
    private TextField txt_name2;
  @FXML
    private TextField txt_quantity2;
      @FXML      
    private TextField txt_weight2;
        @FXML
    private TextField txt_price2;
          @FXML
    private TextField txt_mail2;
        @FXML
    private TextField txt_phone2;
@FXML
private DatePicker date2;
    @FXML
    private TextField filterField;
   
    ObservableList<Colis> options1;
      ObservableList<Colis> dataList;
         @FXML
     ComboBox combo1;
           
     @FXML
     ComboBox combo2;
      @FXML
     ComboBox combo11;
           
     @FXML
     ComboBox combo21;
        
  
    int index = -1;
    
    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    
     
    public void Add_users (){    
        conn = mysqlconnect.ConnectDb();
        String sql = "insert into livrer (transporter_id,transporter_name,departure,destination,quantity,weight,price,phone,mail,date)values(?,?,?,?,?,?,?,?,? ,? )";
        try {
        pst = conn.prepareStatement(sql);
            pst.setString(1, txt_id.getText());
            pst.setString(2, txt_name.getText());
            pst.setString(3,combo1.getSelectionModel().getSelectedItem().toString());
            pst.setString(4,combo2.getSelectionModel().getSelectedItem().toString());
            pst.setString(5, txt_quantity.getText());
            pst.setString(6, txt_weight.getText());
            pst.setString(7, txt_price.getText());     
            pst.setString(8, txt_phone.getText());
            pst.setString(9, txt_mail.getText());
            pst.setString(10, String.valueOf(date1.getValue()));
            pst.execute();
           
            UpdateTable();    
      search_user();
         clear ();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public void clear (){
txt_id.clear();
txt_name.clear();
txt_phone.clear();
txt_mail.clear();
txt_quantity.clear();
 date1.setValue(null);
txt_price.clear();
txt_weight.clear();
combo1.getSelectionModel().select(-1);
combo2.getSelectionModel().select(-1);

}

    //////// methode select livraison ///////
   @FXML
    void getSelected (MouseEvent event){
    index = table1.getSelectionModel().getSelectedIndex();
    if (index <= -1){
            return;
    }
    txt_id2.setText(col_id.getCellData(index).toString());
    txt_name2.setText(col_name.getCellData(index).toString());
     txt_quantity2.setText(col_quantity.getCellData(index).toString());
    combo11.getSelectionModel().select(col_departure.getCellData(index));
   combo21.getSelectionModel().select(col_destination.getCellData(index));
   txt_weight2.setText(col_weight.getCellData(index).toString());
    txt_price2.setText(col_price.getCellData(index).toString());
    txt_phone2.setText(col_phone.getCellData(index).toString());
   txt_mail2.setText(col_mail.getCellData(index).toString());  
         }
  
    public void Edit (){   
        try {
            conn = mysqlconnect.ConnectDb();
            String value1 = txt_id2.getText();
            String value2 = txt_name2.getText();
            String value3 = combo11.getSelectionModel().getSelectedItem().toString();
            String value4 = combo21.getSelectionModel().getSelectedItem().toString();
            String value5= txt_quantity2.getText();
            String value6 = txt_weight2.getText();
            String value7 = txt_price2.getText();
            String value8 = txt_phone2.getText();
            String value9 = txt_mail2.getText();
                        String value10 = date2.getValue().toString();
            String sql = "update livrer set transporter_id= '"+value1+"',transporter_name= '"+value2+"',departure= '"+
                    value3+"',destination= '" +value4+ "',quantity= '"+value5+
                    "',weight= '"
                    +value6+
                    "',price= '"
                    +value7+
                     "',phone= '"
                    +value8+
                     "',mail= '"
                    +value9+
                    "',date= '"
                    +value10+
                    "' where transporter_id='"+value1+"' ";
            pst= conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Update");
            UpdateTable();
            clear ();
          search_user();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        } 
    }
    
    public void Delete(){
    conn = mysqlconnect.ConnectDb();
    String sql = "delete from livrer where transporter_id = ?";
               int reponse = JOptionPane.showConfirmDialog(null, "Do you want to delete this one");
    if (reponse ==  JOptionPane.YES_OPTION) {
      try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_id2.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Delete");
            UpdateTable();
        }catch (Exception e) {
           JOptionPane.showMessageDialog(null, e);
        }}
        else if (reponse ==  JOptionPane.NO_OPTION){}
        else if (reponse ==  JOptionPane.CLOSED_OPTION){}
    }

    public void UpdateTable(){
        col_id.setCellValueFactory(new PropertyValueFactory<Colis,Integer>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<Colis,String>("name"));
         col_departure.setCellValueFactory(new PropertyValueFactory<Colis,String>("departure"));   
        col_destination.setCellValueFactory(new PropertyValueFactory<Colis,String>("destination"));
        col_quantity.setCellValueFactory(new PropertyValueFactory<Colis,String>("quantity"));
        col_price.setCellValueFactory(new PropertyValueFactory<Colis,String>("price"));
        col_weight.setCellValueFactory(new PropertyValueFactory<Colis,String>("weight"));   
        col_phone.setCellValueFactory(new PropertyValueFactory<Colis,String>("phone"));   
        col_mail.setCellValueFactory(new PropertyValueFactory<Colis,String>("mail")); 
        col_date.setCellValueFactory(new PropertyValueFactory<Colis,String>("date")); 
        options1 = mysqlconnect.getDatausers();
        System.out.println(options1);
        table1.setItems(options1);
                 }
     @FXML
    private void uploadimg() throws IOException {
    }
  
 @FXML
    void search_user() {  
         col_id.setCellValueFactory(new PropertyValueFactory<Colis,Integer>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<Colis,String>("name"));
         col_departure.setCellValueFactory(new PropertyValueFactory<Colis,String>("departure"));   
        col_destination.setCellValueFactory(new PropertyValueFactory<Colis,String>("destination"));
        col_quantity.setCellValueFactory(new PropertyValueFactory<Colis,String>("quantity"));
        col_price.setCellValueFactory(new PropertyValueFactory<Colis,String>("price"));
        col_weight.setCellValueFactory(new PropertyValueFactory<Colis,String>("weight"));  
        col_phone.setCellValueFactory(new PropertyValueFactory<Colis,String>("phone"));   
       col_mail.setCellValueFactory(new PropertyValueFactory<Colis,String>("mail"));   

    dataList = mysqlconnect.getDatausers();
        table1.setItems(dataList);
        FilteredList<Colis> filteredData = new FilteredList<>(dataList, b -> true);  
 filterField.textProperty().addListener((observable, oldValue, newValue) -> {
 filteredData.setPredicate(person -> {
    if (newValue == null || newValue.isEmpty()) {
     return true;
    }  

    String lowerCaseFilter = newValue.toLowerCase();
   if (person.getDestination().toLowerCase().indexOf(lowerCaseFilter) != -1) {
     return true; // Filter matches password
    }else if (person.getDeparture().toLowerCase().indexOf(lowerCaseFilter) != -1) {
     return true; // Filter matches password
    }
                                
         else  
          return false; // Does not match.
   });
  });  
  SortedList<Colis> sortedData = new SortedList<>(filteredData);  
  sortedData.comparatorProperty().bind(table1.comparatorProperty());  
  table1.setItems(sortedData);      
     }
    public void menu() {
    pane_menu.setVisible(true);
    pane_add.setVisible(false);   
    }
       public void add() {
       pane_menu.setVisible(false);
       pane_add.setVisible(true);
               }
      
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    UpdateTable();
        search_user();
          ObservableList options1 = FXCollections.observableArrayList(
                "Tunis",
                "Seliana",
                "Nabeul",
                "Sousse",
                "Sfax","Touzeur","Kebili","Monastir","Gabés",
                "Mednine","Ariana","Ben Arous","Zaghouan","Manouba",
                "Bizert","Jendouba","Beja","Mahdia","Sidi Bouzid","Kairouan","Gafsa","Tataouine","Kef","Gasrine"
                                 );
    combo1.setItems(options1); combo21.setItems(options1); combo11.setItems(options1); combo2.setItems(options1);
          
              

    }
}
