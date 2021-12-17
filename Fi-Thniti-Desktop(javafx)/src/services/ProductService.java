/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entities.Product;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import static java.sql.Types.NULL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.myDB;
import java.sql.Date;
import java.util.Arrays;
/**
 *
 * @author nadaa
 */
public class ProductService implements Service<Product>{
    static Product passing ;
    Connection connection;
    
    public ProductService(){
        connection=myDB.getInstance().getConnection();
    }
    @Override
    public void insert(Product obj) {
////        Double salary=obj.getSalary();
        try {
            String req="INSERT INTO `product`(`category`,`brand`,`name`,`unit`,`price`, `qte`)VALUES ('"+obj.getCategory()+"','"+obj.getBrand()+"','"+obj.getName()+"',"+obj.getUnit()+","+obj.getPrice()+","+obj.getQte()+")";
            System.out.println(req);
            Statement st= connection.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
               
        
    }

    /**
     *
     * @param obj
     * @param id
     */
    
    public void update(Product obj){
        try {
            String req="UPDATE `product` SET `category`='"+obj.getCategory()+"',`brand`='"+obj.getBrand()+"',`name`='"+obj.getName()+"',`unit`="+obj.getUnit()+",`price`='"+obj.getPrice()+"',`qte`="+obj.getQte()+" WHERE `product_id`="+obj.getProduct_id()+"";
            Statement st= connection.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }    }

    /**
     *
     * @param obj
     */
    @Override
    public void delete(Product obj) {
        try {
            String req="DELETE FROM `product` WHERE `product_id`='"+obj.getProduct_id()+"'";
            Statement st= connection.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }

    
    public Product findbyID(int id) {
        Product d=new Product();
        try {
            String req="SELECT * FROM `product` WHERE `product_id`='"+id+"'";
            Statement st= connection.createStatement();
            ResultSet rs=st.executeQuery(req);
            while(rs.next()){
                d.setProduct_id(rs.getInt(1));
                d.setCategory(rs.getInt(2));
                d.setBrand(rs.getString(3));
                d.setName(rs.getString(4));
                d.setUnit(rs.getFloat(5));
                d.setPrice(rs.getFloat(6));
                d.setQte(rs.getInt(7));
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return d;    
    }

    
    
    public List<Product> findbyCategory(int Category){
        List<Product> ListProduct =new ArrayList();
        try {
            String req="SELECT * FROM `product` WHERE `category`="+Category;
            Statement st= connection.createStatement();
            ResultSet rs=st.executeQuery(req);
            while(rs.next()){
                Product d=new Product();
                d.setProduct_id(rs.getInt(1));
                d.setCategory(rs.getInt(2));
                d.setBrand(rs.getString(3));
                d.setName(rs.getString(4));
                d.setUnit(rs.getFloat(5));
                d.setPrice(rs.getFloat(6));
                d.setQte(rs.getInt(7));
            ListProduct.add(d);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return ListProduct;      
    }

    /**
     *
     * @param Name
     * @return
     */
    
    public List<Product> findbyName(String Name){
        List<Product> ListProduct =new ArrayList();
        try {
            String req="SELECT * FROM `product` WHERE `name`='"+Name+"'" ;
            Statement st= connection.createStatement();
            ResultSet rs=st.executeQuery(req);
            while(rs.next()){
                Product d=new Product();
                d.setProduct_id(rs.getInt(1));
                d.setCategory(rs.getInt(2));
                d.setBrand(rs.getString(3));
                d.setName(rs.getString(4));
                d.setUnit(rs.getFloat(5));
                d.setPrice(rs.getFloat(6));
                d.setQte(rs.getInt(7));
            
                ListProduct.add(d);
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return ListProduct;    
    }

    /**
     *
     * @param LName
     * @return
     */
    
    public List<Product> findbyBrand(String Brand){
        List<Product> ListProduct =new ArrayList();

        try {
            String req="SELECT * FROM `product` WHERE `brand`='"+Brand+"'" ;
            Statement st= connection.createStatement();
            ResultSet rs=st.executeQuery(req);
            while(rs.next()){
                Product d=new Product();
                d.setProduct_id(rs.getInt(1));
                d.setCategory(rs.getInt(2));
                d.setBrand(rs.getString(3));
                d.setName(rs.getString(4));
                d.setUnit(rs.getFloat(5));
                d.setPrice(rs.getFloat(6));
                d.setQte(rs.getInt(7));
            
                ListProduct.add(d);
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return ListProduct;    
    }

    @Override
    public List<Product> findAll() {
        List<Product> ListProduct =new ArrayList<>();
        
        try {
            String req="SELECT * FROM `product`";
            Statement st= connection.createStatement();
            ResultSet rs=st.executeQuery(req);
            while(rs.next()){
                Product d=new Product();
                d.setProduct_id(rs.getInt(1));
                d.setCategory(rs.getInt(2));
                d.setBrand(rs.getString(3));
                d.setName(rs.getString(4));
                d.setUnit(rs.getFloat(5));
                d.setPrice(rs.getFloat(6));
                d.setQte(rs.getInt(7));
            
                ListProduct.add(d);
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }    
        return ListProduct;    
    }
    public void holder(Product d){
        passing = d;
        System.out.println("User stored in holder");
        System.out.println(d);
    }
    public Product returnholder(){
        System.out.println("return holder");
        System.out.println(passing);
                
        return passing;
    }


}
