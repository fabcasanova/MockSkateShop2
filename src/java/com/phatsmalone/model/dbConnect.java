/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phatsmalone.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author phatsmaloney
 */
public class dbConnect {
    private static String url = "jdbc:mysql://localhost:3307/skate_shop_db";
    private static String driver = "com.mysql.jdbc.Driver";
    private static String username = "root";
    private static String pw = "sesame";
    private static Connection connection = null;
    
    public static Connection openDB() throws ClassNotFoundException, SQLException{
    
        if(connection != null){
            
            return connection;
        }
        else{
            try{
                
                Class.forName(driver);
                connection = DriverManager.getConnection(url, username, pw);
            }
            catch(ClassNotFoundException e){
            
                e.printStackTrace();
            }
            catch(SQLException e){
            
                e.printStackTrace();
            }
            
            return connection;
        }
    }
}
