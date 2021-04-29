<%-- 
    Document   : index
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
                    <a href="ViewProductsServlet?action=category&pageNum=${pageNum}&id=${category.categoryID}">
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
    
    <nav>
        <ul>
            <table>
            <h1>SkateBoard Decks</h1>
            <tr>
                <td>Company</td>
                <td>Product Name</td>
                <td>
                <a href="ViewProductsServlet?action=category&pageNum=${pageNum}&id=${categoryID}&sort=${sort}" >Price</a>
                </td>
            </tr>
              
            <c:forEach items="${products}" var="product">
                <form action="ViewProductsServlet?action=add" method="post">
                <input type="hidden" name="pid" value="${product.productID}">
                <input type="hidden" name="pname" value="${product.productName}">
                <input type="hidden" name="pprice" value="${product.productPrice}">
                <input type="hidden" name="qty" value='1'>
            <tr> 
                <td>${product.companyName}
                </td>
                <td><a href="singleView?action=view&productID=${product.productID}&id=${categoryID}">${product.productName}
                </td>
                <td>${product.productPrice}
                </td>
                
                <td><input type="submit" value="Add Item"></td>
            </tr>
            </form>
            </c:forEach>
              
            </table>                    
        </ul>
    </nav>   
    <div class="row">
            <div class="col">
            <nav aria-label="PageNumbers">
                <c:if test="${totalPage > 0}">
                
            <ul class="pagination">
                <c:if test="${pageNum > 1}">
                    <li id="currentpage"><a class="previous" href="ViewProductsServlet?action=category&pageNum=${pageNum - 1}&id=${categoryID}">Prev</a></li>
                </c:if>
                

                <c:if test="${pageNum - 2 > 0}">
                    <li><a class="previous" href="ViewProductsServlet?action=category&pageNum=${pageNum - 2}&id=${categoryID}">${pageNum - 2}</a></li>
                </c:if>
                
                <c:if test="${pageNum - 1 > 0}">
                    <li><a class="previous" href="ViewProductsServlet?action=category&pageNum=${pageNum - 1}&id=${categoryID}">${pageNum - 1}</a></li>
                </c:if>

                <li class="currentpage"><a href="ViewProductsServlet?pageNum=${pageNum}">
                ${pageNum}</a></li>

                <c:if test="${pageNum + 1 < totalPage + 1}">
                    <li><a class="previous" href="ViewProductsServlet?action=category&pageNum=${pageNum + 1}&id=${categoryID}">${pageNum + 1}</a></li>
                </c:if>

                <c:if test="${pageNum + 2 < totalPage + 1}">
                    <li><a class="previous" href="ViewProductsServlet?action=category&pageNum=${pageNum + 2}&id=${categoryID}">${pageNum + 2}</a></li>
                </c:if>

                <c:if test="${pageNum  < totalPage}">
                <li><a class="next" href="ViewProductsServlet?action=category&pageNum=${pageNum + 1}&id=${categoryID}">Next</a></li>
                </c:if>
            </ul>
            </c:if>
            </nav> 
            </div>
            
            <div class="col">
             
            <p><a href="cart.jsp">View Cart</a></p>
            </div>    
        
    </div>            
    
        
    </div>
</main>

<jsp:include page="/components/footer.jsp" />