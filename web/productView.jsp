<%-- 
    Document   : productView
    Created on : May 6, 2020, 12:18:36 PM
    Author     : phatsmaloney
--%>

<jsp:include page="/components/header.jsp" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <main>
    <div class="layout layout-nav-side">
    <div class="navbar navbar-expand-lg sticky-top">
    <div class="d-flex align-items-center">
    
        
            <nav>
        <h1>Categories</h1>
            <ul class="navbar-nav d-lg-block">
                <c:forEach items="${category}" var="category">
                <li class="nav-item">
                    <a href="ViewProductsServlet?action=category&id=${category.categoryID}" >
                        ${category.categoryName}
                    </a>
                </li>
                </c:forEach>
            </ul>
        </nav>
    </div>
    </div>
    </div>   
    <div class="main-container">
         
    <form action="singleView?action=addSingle" method="post">
        <c:forEach items="${singleview}" var="single">
                <input type="hidden" name="pid" value="${single.productID}">
                <input type="hidden" name="pname" value="${single.productName}">
                <input type="hidden" name="pprice" value="${single.productPrice}">
                <input type="hidden" name="cart" value="${cart}">
                <input type="hidden" name="qty" value='1'>
        <div class="row">
        <h1>${single.companyName}</h1>
        </div>
        <div class="row">
        <h1>${single.productName}</h1>
        </div>
        <div class="row">
        <h1>$${single.productPrice}</h1>
        </div>
        <h1></h1>
        <img src=""/>
        </c:forEach>
        <h1>${image}</h1>
        <div id="left_column">
            
        </div>
        <td><input type="submit" value="Add Item"></td>           

        <div id="right_column">
            
            
        </div>
    </form>
    </div>
</main>
<jsp:include page="/components/footer.jsp" />
