/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phatsmalone.controller;

import com.phatsmalone.business.Products;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author phatsmaloney
 */
public class CartServletController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CartServletController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CartServletController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        
        String action = request.getParameter("action");
        
        if(action.equals("empty")){
                    HttpSession session = request.getSession();
                    if(session.getAttribute("mycart") != null){
                    ArrayList mycart = (ArrayList)session.getAttribute("mycart");
                    double total = (double)session.getAttribute("total");
                    for (int i = 0; i <= mycart.size(); i++){
                    Products product = (Products) mycart.remove(i);
                    }
                    total = 0;
                    session.setAttribute("mycart", mycart);
                    session.setAttribute("total", total);
                    session.invalidate();
                    }   
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/cart.jsp");
                    dispatcher.forward(request, response);
        }
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
            
        String action = request.getParameter("action");
        if(action.equals("update")){
        
            HttpSession session = request.getSession();
            if(session.getAttribute("mycart") != null){
                    ArrayList mycart = (ArrayList)session.getAttribute("mycart");
                    double total = (double)session.getAttribute("total");
                    for (int i = 0; i < mycart.size(); i++){
                    Products product = (Products)mycart.get(i);
                    String qty =(String) request.getAttribute(Integer.toString(i));
                    //System.out.println(qty);

                        if(qty == null){
                        mycart.remove(i);
                        }
                        qty = "1";
                    }}
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/cart.jsp");
        dispatcher.forward(request, response);
        
        }   
        
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
