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
public class Categories {

    private  int categoryID;
    private  String categoryName;
    
    public Categories(){
        this.categoryID = 0;
        this.categoryName ="";
    }
    
    public void setCategoryID(int id){
    
        categoryID = id;
    }
    
    public int getCategoryID(){
    
        return categoryID;
    }
    
    public void setCategoryName(String name){
    
        categoryName = name;
    }
    
    public String getCategoryName(){
    
        return categoryName;
    }
}
