/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phatsmalone.controller;

import com.phatsmalone.business.Categories;
import com.phatsmalone.business.Products;
import com.phatsmalone.in.productsIN;
import com.phatsmalone.model.ProductsDB;
import com.phatsmalone.model.dbConnect;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author phatsmaloney
 */
public class SingleViewServlet extends HttpServlet {
    productsIN product = null;
    
    public SingleViewServlet(){

        product = new ProductsDB();
    }
   

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
               ServletContext sc = getServletContext();
                
               
               

                String action = request.getParameter("view");

                if(action == null){
                
                    action = "view";
                }
                
                if(action.equals("view")){
                    
                    List<Categories> categoryList = product.getCategoryNames();
                    request.setAttribute("category", categoryList);
                    String oneProduct = request.getParameter("productID");
                    String id = request.getParameter("id");
                    List<Products> single = product.getProductInfo(oneProduct);
                    request.setAttribute("singleview", single); 
                    
                    Connection connection = null;
            Statement statement = null;
            ResultSet resultSet = null;
            Products product = null;            String sql = "SELECT product_image FROM products\n" +
                            "WHERE product_id = " + oneProduct;

            try {
            connection = dbConnect.openDB();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            
            while(resultSet.next()){
                
                product = new Products();
                Blob blob = resultSet.getBlob("product_image");
                
 
                InputStream inputStream = blob.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;

                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                byte[] imageBytes = outputStream.toByteArray();

                String image = Base64.getEncoder().encodeToString(imageBytes);
                        
                inputStream.close();
                outputStream.close();
                
                if(id.equals("1"))
            {
                String str = "<img src=\"data:image/jpg;base64,";
                String middle = str + image + "\" width=\"240\" height=\"500\"/>";
                request.setAttribute("image", middle);
                
            }
                else{
                String str = "<img src=\"data:image/jpg;base64,";
                String middle = str + image + "\" width=\"550\" height=\"550\"/>";
                request.setAttribute("image", middle);
            }
                   
            }
            
            
            
            
        }
        catch(SQLException e){
        
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductsDB.class.getName()).log(Level.SEVERE, null, ex);
        }
                    
        }
                
                
   
                            
               RequestDispatcher dispatcher = request.getRequestDispatcher("/productView.jsp");
               dispatcher.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
                
                ServletContext sc = getServletContext();
                String action = request.getParameter("action");
                
                if(action.equals("addSingle")){
                
                    HttpSession session = request.getSession();
                    ArrayList cart = (ArrayList)session.getAttribute("mycart");

                    if(cart == null){
                    cart = new ArrayList();
                    double Total = 0;
                    session.setAttribute("total", Total);
                    session.setAttribute("mycart" , cart);
                    }
                    
                    String id = request.getParameter("pid");
                    String name = request.getParameter("pname");
                    String price = request.getParameter("pprice");
                    String qty = request.getParameter("qty");
                    double rPrice = Double.parseDouble(price);

                    //System.out.println(id + " " + name + " " + price + " " + qty);
                    int newid = Integer.parseInt(id);
                    int newqty = Integer.parseInt(qty);    
                    Products product = new Products(newid, name, rPrice, newqty);
                    cart.add(product);

                    session.setAttribute("mycart" , cart);

                    
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/cart.jsp");
                    dispatcher.forward(request, response);
                }
                RequestDispatcher dispatcher = request.getRequestDispatcher("/cart.jsp");
                    dispatcher.forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
