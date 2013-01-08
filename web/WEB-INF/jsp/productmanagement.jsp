<%-- 
    Document   : productmanagement
    Created on : 6 déc. 2012, 22:44:41
    Author     : Balkis
--%>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html >
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--<link type="text/css" rel="stylesheet" href="<c:url value="/inc/style.css"/>" />-->
        <link type="text/css" rel="stylesheet" href="<c:url value="/inc/jquery-ui.css"/>" />
        <title>Product | List</title>
    </head>
    <style>
        .product{
            font-family: Century Gothic;
            font-size: 20px;
            color:#3B5998;      
        }
        
        .listProduct{
            border-width: 3px;
            border-style: double;
            border-color:beige ;
            
            
        }
        
    </style>
    <body>
        <table width="90%">
            <caption><h1>WELCOME!</h1></caption>
            <tr>
                <td>
                  <div class="listProduct" >  
                      <table>
                          <tr>
                              <td>
                                  <div id="products">
                                    <c:forEach var="product" items='${requestScope.model.products}' >
                                      
                                        <h3> <c:out value="${product.name}"></c:out></h3>
                                        
                                        <div class="productDiv">
                                            <ul type="none">
                                                <li class="champ">Product:${product.name}</li>  
                                                <li>Description:${product.description} </li>
                                                 <li>Price:${product.price}</li>
                                                <li>Quantity: ${product.quantity}</li>
                                                
                                            </ul>
                                                <button> Update </button>
                                        </div>
                                    </c:forEach>   
                                  </div>
                              </td>
                          </tr>
                      </table>
                  </div>
                </td>
                <td>
                    <div> PRODUCT SPACE!</div>
                </td>
            </tr>
        </table>
        
        <script src="<c:url value="/inc/jquery.js"/>"></script>
        <script src="<c:url value="/inc/jquery-ui.js"/>"></script>
        <script src="<c:url value="/inc/product-script.js"/>"></script>

    </body>
</html>
