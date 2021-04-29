/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phatsmalone.business;

/**
 *
 * @author phatsmaloney
 */
public class Products{
    
    int productID, categoryID;
    public String companyName,productName;
    public double productPrice;
    public int qty;

    
    public Products(){
    
        this.productID = 0;
        this.categoryID = 0;
        this.productName = "";
        this.productPrice = 0;

    }
    
    public Products(int productID, String productName, double productPrice, int qty){
    
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.qty = qty;
    }

    public void setqty(int qty){
    
        this.qty = qty;
    }
    
    public int getqty(){
    
        return qty;
    }
    public void setProductID(int productID){
    
        this.productID = productID;
    }
    
    public int getProductID(){
    
        return productID;
    }
    
    public void setCategoryID(int categoryID){
    
        this.categoryID = categoryID;
    }
    
    public int getcategoryID(){
    
        return categoryID;
    }
    public void setCompanyName(String name){
    
        this.companyName = name;
    }
    
    public String getCompanyName(){
    
        return companyName;
    }
    
    public void setProductName(String name){
    
        this.productName = name;
    }
    
    public String getProductName(){
    
        return productName;
    }
    
    public void setProductPrice(double price){
    
        this.productPrice = price;
    }
    
    public double getProductPrice(){
    
        return productPrice;
    }
}
