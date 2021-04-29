/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phatsmalone.model;

import com.phatsmalone.business.Categories;
import com.phatsmalone.business.Products;
import com.phatsmalone.in.productsIN;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author phatsmaloney
 */
public class ProductsDB implements productsIN{
    
    @Override
    public List<Products> get(String id, int pValue, int pageNumber, String sort){
        
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<Products> productList = null;
        Products products = null;
        int offset = (pageNumber -1) * pValue;
        //System.out.println(pValue);
        try{
        
            productList = new ArrayList<Products>();
            String sql = "SELECT * FROM products WHERE category_id = " + id +
                            " ORDER BY product_price "+ sort + " LIMIT " + offset + ", " + pValue;
            connection = dbConnect.openDB();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            
            while(resultSet.next()){
            
                products = new Products();
                products.setProductID(resultSet.getInt("product_id"));
                products.setCategoryID(resultSet.getInt("category_id"));
                products.setCompanyName(resultSet.getString("company_name"));
                products.setProductName(resultSet.getString("product_name"));
                products.setProductPrice(resultSet.getDouble("product_price"));
                productList.add(products);
            }
            
        }
        catch(SQLException e){
        
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductsDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        return productList;
    }

    @Override
    public List<Categories> getCategoryNames() {
            
        Connection connection = null;
            Statement statement = null;
            ResultSet resultSet = null;
            ArrayList<Categories> categoryList = null;
            Categories category = null;
            String sql = "SELECT * FROM categories";
        
            try {
            connection = dbConnect.openDB();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            categoryList = new ArrayList<Categories>();
            
            while(resultSet.next()){
                
                category = new Categories();
                category.setCategoryID(resultSet.getInt("category_id"));
                category.setCategoryName(resultSet.getString("category_name"));
                categoryList.add(category);
            }
            
        }
        catch(SQLException e){
        
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductsDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        return categoryList; 
    }
   
    
    @Override
    public List<Products> getProductInfo(String id) {
            
            Connection connection = null;
            Statement statement = null;
            ResultSet resultSet = null;
            ArrayList<Products> singleProduct = null;
            Products product = null;
            String sql = "SELECT * FROM products WHERE product_id = " + id;
            System.out.println(sql);
            try {
            connection = dbConnect.openDB();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            singleProduct = new ArrayList<Products>();
            
            while(resultSet.next()){
                
                product = new Products();
                product.setProductID(resultSet.getInt("product_id"));
                int name = product.getProductID();
                System.out.println(name);
                product.setCompanyName(resultSet.getString("company_name"));
                product.setProductName(resultSet.getString("product_name"));
                product.setProductPrice(resultSet.getDouble("product_price"));
     
                
                singleProduct.add(product);
            }
            
        }
        catch(SQLException e){
        
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductsDB.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
            String name = product.getCompanyName();
            System.out.println(name);
            
        return singleProduct; 
    }
    
    @Override
    public int getCount(String id){
        
            int malone = 0;
            Connection connection = null;
            Statement statement = null;
            ResultSet resultSet = null;
            String sql = "SELECT COUNT(category_id)\n" +
                            "FROM products\n" +
                            "WHERE category_id = " + id;
            
            //System.out.println(sql);
            try {
            connection = dbConnect.openDB();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            
            while(resultSet.next()){
                
                malone = resultSet.getInt(1);
            }
            //System.out.println(malone);
        }
        catch(SQLException e){
        
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductsDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
        return malone;
    }

}
