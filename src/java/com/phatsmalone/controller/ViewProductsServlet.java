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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
public class ViewProductsServlet extends HttpServlet {
    
    int listAmount;
    productsIN product = null;
    
    public ViewProductsServlet(){
        listAmount = 1;
        product = new ProductsDB();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {    
                
                ServletContext sc = getServletContext();
                //for pagination
                
                String pageValue = getServletContext().getInitParameter("pValue");
                String action = request.getParameter("action");           
                String page = request.getParameter("pageNum");
                String id = request.getParameter("id");
                String sort = request.getParameter("sort");
                if(page == null){
                    page = "1";
                }
                if(id == null){
                    
                    id = "1";
                }
                if(sort == null){
                    
                    sort = "DESC";
                }
                    switch (sort) {
                    case "ASC":
                        sort = "DESC";
                        break;
                    case "DESC":
                        sort = "ASC";
                        break;
                    default:
                        sort = "ASC";
                        break;
                    }
                    
                    //paginate
                    int pNum = Integer.valueOf(page);
                    //System.out.println(pNum);
                    int pValue = Integer.valueOf(pageValue);
                    double Value = Integer.valueOf(pageValue);  
                    
                    double rowCount = product.getCount(id);
                    //System.out.println(rowCount);
                    //System.out.println(pValue);
                    double totalProductPage = Math.ceil(rowCount/Value);
                    //System.out.println(totalProductPage);
                    request.setAttribute("totalPage", totalProductPage);
                    
                    
                    
                    List<Products> productList = product.get(id, pValue, pNum, sort);
                    List<Categories> categoryList = product.getCategoryNames();
                    request.setAttribute("pageNum", pNum);
                    request.setAttribute("sort", sort);
                    request.setAttribute("products", productList);       
                    request.setAttribute("category", categoryList);
                    request.setAttribute("categoryID",id);
               RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
               dispatcher.forward(request, response);
                
        }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
                ServletContext sc = getServletContext();
                
                String action = request.getParameter("action");
                
                if(action.equals("add")){
                    
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
                            
        }
}



