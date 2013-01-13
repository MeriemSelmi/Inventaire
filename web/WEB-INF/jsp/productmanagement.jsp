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
        <link type="text/css" rel="stylesheet" href="<c:url value="/inc/style.css"/>" />
        <link type="text/css" rel="stylesheet" href="<c:url value="/inc/ProductStyle.css"/>" />
        <link type="text/css" rel="stylesheet" href="<c:url value="/inc/jquery-ui.css"/>" />
        <title>Product | List</title>
    </head>
    <style>
        .nomProduit{
            color: #9cd46f;
            text-decoration: none;
            font-size: 25px;
            font-family: century gothic;
        }

    </style>
    <body>
        <table width="100%">
            <caption><div class="welcome">Welcome to Product Manager</div></caption>
            <tr>
                <td>
                    <div class="listProduct" >  
                        <table width="70%">
                            <tr>
                                <td>
                                    <div id="accordion">
                                        <c:forEach var="product" items='${requestScope.model.products}' >

                                            <p class="nomProduit"><c:out value="${product.name}"></c:out></p>

                                                <div class="productDiv">
                                                    <div >
                                                        <table class="productInfoTable">
                                                            <tr><td class="champ">Product:</td><td class="text">${product.name}</td></tr>  
                                                        <tr><td class="champ">Description:</td><td class="text">${product.description} </td></tr>
                                                        <tr><td class="champ">Price:</td><td class="text">${product.price}</td></tr>
                                                        <tr><td class="champ">Quantity:</td> <td class="text">${product.quantity}</td></tr>


                                                    </table>
                                                    <table >
                                                        <tr><td align="center"><div class="button" onclick="updateForm('${product.name}');">Update</div></td>
                                                            <td align="center"><a href="productdelete.htm" > <div class="button">Delete</div> </a></td></tr>
                                                    </table>
                                                </div>


                                                <div class="toggler">
                                                    <div id="${product.name}" class="ui-widget-content ui-corner-all" hidden="hidden">
                                                        <p class="title">Please enter new informations.</p>
                                                        <form:form method="post" action="productupdate.htm" commandName="productupdate" >
                                                            <table>
                                                                <tr><td>Name:</td>
                                                                    <td><form:input path="name"  /> </td></tr>
                                                                <tr><td>Description:</td>
                                                                    <td><form:input path="description" /></td></tr>
                                                                <tr><td>Quantity:</td>
                                                                    <td><form:input path="quantity" /></td></tr>
                                                                <tr><td>Price:</td>
                                                                    <td><form:input path="price" /></td></tr>
                                                                <tr><td>Supplier:</td>
                                                                    <td><form:input path="supplier" /></td></tr>
                                                                <tr colspan="2"><td><input type="submit" value="Validate" class="button"/></td></tr>
                                                            </table>

                                                            
                                                        </form:form>
                                                    </div>
                                                </div>



                                            </div>
                                        </c:forEach>   
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </div>
                </td>

            </tr>
        </table>

        <script src="<c:url value="/inc/jquery.js"/>"></script>
        <script src="<c:url value="/inc/jquery-ui.js"/>"></script>
        <script src="<c:url value="/inc/product-script.js"/>"></script>

    </body>
</html>
