<%-- 
    Document   : productmanagement
    Created on : 6 déc. 2012, 22:44:41
    Author     : Balkis
--%>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
            color: #4927e7;
            text-decoration: none;
            font-weight: 300;
            font-size: 20px;
            font-family: century gothic;
        }

        
    </style>
    
    
    <body>
        <%@ include file="/WEB-INF/jsp/header.jsp" %>
        <table id="global">
            <caption><div class="welcome">Product Manager</div></caption>
            <!--**********************************SEARCH TOOL*************************************************-->
            <caption align="center">
                <form:form method="post" action="productfind.htm" commandName="productfind">
                    <table>
                        <tr>
                            <td class="champ" align="center">Please enter criterion: &nbsp;&nbsp;&nbsp;&nbsp; <form:input path="key" class="textareas" /> <span class="error">${errorFind}</span></td>
                            <td align="center"><input type="submit" value="find" class="button" /></td>
                        </tr>              
                    </table>                   
                </form:form>
            </caption>
            <!--**********************************ERRORS SPACE************************************************-->
            <caption><div class="error">${exception}</div></caption>
            <!--**********************************PRODUCT LIST***************************************************-->
            <tr>
                <td>
                    <div class="listProduct" >                                              
                        <table width="70%">
                            <tr>
                                <td>
                                    <div id="accordion">
                                        <c:forEach var="product" items='${products}' >

                                            <p class="nomProduit"><c:out value="${product.name}"></c:out></p>

                                                <div class="productDiv">
                                                    <div >
                                                        <table class="productInfoTable">
                                                            <tr><td class="champ">Product :</td><td class="text">${product.name}</td></tr>  
                                                            <tr><td class="champ">Description :</td><td class="text">${product.description} </td></tr>
                                                            <tr><td class="champ">Price :</td><td class="text">${product.price}</td></tr>
                                                            <tr><td class="champ">Quantity :</td> <td class="text">${product.quantity}</td></tr>
                                                            <tr><td class="champ">Supplier :</td> <td class="text">${product.supplier}</td></tr>
                                                         </table>
                                                         <table >
                                                             <tr>
                                                                 <td align="center"><div class="button" onmouseover="showForm('${product.id}');" id="updateButton">Update</div></td>
                                                                 <td align="center"><a href="productdelete.htm?id=${product.id}" > <div class="button" id="deleteButton">Delete</div> </a></td></tr>
                                                        </table>
                                                    </div>



                                                <div id="${product.id}" class="ui-widget-content ui-corner-all" hidden="hidden">
                                                    <table><tr><td class="noDeco"> <p class="title">Please enter new product informations.</p> </td><td class="noDeco"><div align="right" id="hideButton1" onclick="runHideEffect('${product.id}')"></div></td></tr></table>

                                                    <form:form method="post" action="productupdate.htm" commandName="productupdate" acceptCharset="UTF-8" >
                                                        <table>
                                                            <tr><input type="text" hidden="true" name="id" value="${product.id}" /></tr>
                                                            <tr><td>Name:</td>
                                                                <td><form:input path="name" class="textareas" id="pName"  value="${product.name}" onclick="viderChamps(this)" /> <span class="error"> ${errors.nameupdate} </span></td></tr>
                                                            <tr><td>Description:</td>
                                                                <td><form:input path="description"  class="textareas" id="pDescription" value="${product.description}" onclick="viderChamps(this)" /> <span class="error"> ${errors.descriptionupdate} </span> </td></tr>
                                                            <tr><td>Quantity:</td>
                                                                <td><form:input path="quantity" class="textareas" id="pQuantity" value="${product.quantity}" onclick="viderChamps(this)" /> <span class="error"> ${errors.quantityupdate} </span></td></tr>
                                                            <tr><td>Price:</td>
                                                                <td><form:input path="price" class="textareas" id="pPrice" value="${product.price}" onclick="viderChamps(this)" /> <span class="error"> ${errors.priceupdate} </span></td></tr>
                                                            <tr><td>Supplier:</td>
                                                                <td><form:input path="supplier" class="textareas" id="pSupplier" value="${product.supplier}" onclick="viderChamps(this)" /> <span class="error"> ${errors.supplierupdate} </span></td></tr>
                                                            <tr><td><input type="submit" value="Validate" class="button"/></td></tr>
                                                        </table>


                                                    </form:form>

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
            <!--************************************ADD PRODUCT SPACE*************************************************-->
            <tr><td align="center">
                    
                    <div class="button" id="addButton" onclick="showForm('addForm')">Add product</div> </br></br>
            
                    <div id="addForm" class="ui-widget-content ui-corner-all" hidden="true" >
                        <table><tr><td class="noDeco"> <p class="title">Please enter product informations.</p> </td><td class="noDeco"><div align="right" id="hideButton2" onclick="runHideEffect('addForm')"></div></td></tr></table>
                        <form:form method="post" action="productadd.htm" commandName="productadd" >
                        <table>                            
                            <tr><td class="champ">Name:   </td>
                                <td><form:input path="name" class="textareas"  name="name" /> <span class="error">${errors.name} </span></td></tr>
                            <tr><td class="champ">Description:</td>
                                <td><form:input path="description" class="textareas" name="productDescription" /> <span class="error">${errors.description} </span></td></tr>
                            <tr><td class="champ">Quantity:</td>
                                <td><form:input path="quantity" class="textareas" name="productQuantity" /> <span class="error">${errors.quantity} </span></td></tr>
                            <tr><td class="champ">Price:</td>
                                <td><form:input path="price" class="textareas" name="productPrice" /> <span class="error">${errors.price} </span></td></tr>
                            <tr><td class="champ">Supplier:</td>
                                <td><form:input path="supplier" class="textareas" name="productSupplier" /> <span class="error">${errors.supplier} </span></td></tr>
                            <tr><td><input type="submit" value="Validate" class="button"/></td></tr>
                        </table>
                        </form:form>
                       

                    </div>
                </td></tr>
        </table>
            <!--***********************************************************************************************************-->
        <script src="<c:url value="/inc/jquery.js"/>"></script>
        <script src="<c:url value="/inc/jquery-ui.js"/>"></script>
        <script src="<c:url value="/inc/product-script.js"/>"></script>

    </body>
</html>
