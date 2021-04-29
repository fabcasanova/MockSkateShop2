<%-- 
    Document   : cart.jsp
    Created on : May 7, 2020, 6:58:51 AM
    Author     : phatsmaloney
--%>

<%@page import="java.io.PrintWriter"%>
<%@page import="com.phatsmalone.business.Products"%>
<%@page import="java.util.ArrayList"%>
<jsp:include page="/components/header.jsp" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<main>

<%if(session.getAttribute("mycart") == null){%>
<div class="main-container">
<h1>There are no items in cart</h1>

</div>
<%}%>
<div class="main-container">
    <form action="Cart?action=update" method="post">
    

    <table>
            <tr>

                <th>Product Name</th>
                <th>Cost</th>
                <th>Quantity</th>
                <th>Item Total</th>
            </tr>
              
            <% if(session.getAttribute("mycart") != null){
                ArrayList mycart = (ArrayList)session.getAttribute("mycart");
                double total = (double)session.getAttribute("total");
                for (int i = 0; i < mycart.size(); i++){
                    Products product = (Products)mycart.get(i);
                    total += product.productPrice;
            %>
                    
            <tr> 
                <td><%out.print(product.productName);%></td>
                <td><%out.print(product.productPrice);%></td>
                <td></td>
                <input type="hidden" name="position" value='${i}'>
                <td><input type="text" name="${i}" 
                                value='<%out.print(product.qty);%>'></td>
                
                
                <td><%out.print(product.qty * product.productPrice);%></td>
            </tr>
            
            <%}%>

            <tr>
                <td colspan="4">SUBTOTAL</td>
                <td><%out.print(total);}%></td>
            </tr>
            <tr>
                <td colspan="4"><input type="submit" value="Update Cart"></td>
            </tr>
              
            </table>               
    </form>


        <p><a href="Cart?action=empty">Empty Cart</a></p>

         <p><a href="ViewProductsServlet">Keep Shopping</a></p>   

</div>
</main>
    
<jsp:include page="/components/footer.jsp" />
