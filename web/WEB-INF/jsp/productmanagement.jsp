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
            <caption><div class="welcome"><spring:message code="product.page.title"/></div></caption>
            <!--**********************************SEARCH TOOL*************************************************-->
            <caption align="center">
                <form:form method="post" action="productfind.htm" commandName="productfind">
                    <table>
                        <tr>
                            <td class="champ" align="center"><spring:message code="product.label.find" /> &nbsp;&nbsp;&nbsp;&nbsp; <form:input path="key" class="textareas" /></td>
                            <td align="center"><input type="submit" value="<spring:message code="product.button.find" />" class="button" /></td>
                            <td class="noDeco"><form:errors path="key" cssClass="error" /></td>
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
                                                            <tr><td class="champ"><spring:message code="product.label.name" /></td><td class="text">${product.name}</td></tr>  
                                                            <tr><td class="champ"><spring:message code="product.label.description" /></td><td class="text">${product.description} </td></tr>
                                                            <tr><td class="champ"><spring:message code="product.label.price" /></td><td class="text">${product.price}</td></tr>
                                                            <tr><td class="champ"><spring:message code="product.label.quantity" /></td> <td class="text">${product.quantity}</td></tr>
                                                            <tr><td class="champ"><spring:message code="product.label.supplier" /></td> <td class="text">${product.supplier}</td></tr>
                                                         </table>
                                                         <table >
                                                             <tr>
                                                                 <td align="center"><div class="button" onclick="showForm('${product.id}');" id="updateButton"><spring:message code="product.button.update" /></div></td>
                                                                 <td align="center"><a href="productdelete.htm?id=${product.id}" > <div class="button" id="deleteButton"><spring:message code="product.button.delete" /></div> </a></td></tr>
                                                        </table>
                                                    </div>



                                                <div id="${product.id}" class="ui-widget-content ui-corner-all" hidden="hidden">
                                                    <table><tr><td class="noDeco"> <p class="title"><spring:message code="product.update.title" /></p> </td><td class="noDeco"><div align="right" id="hideButton1" onclick="runHideEffect('${product.id}')"></div></td></tr></table>

                                                    <form:form method="post" action="productupdate.htm" commandName="productupdate" acceptCharset="UTF-8" >
                                                        <table>
                                                            <tr><input type="text" hidden="true" name="id" value="${product.id}" /></tr>
                                                            <tr><td><spring:message code="product.label.name" /></td>
                                                                <td><form:input path="name" class="textareas" id="pName"  value="${product.name}" onclick="viderChamps(this)" /> </td><td class="noDeco"><form:errors path="name" cssClass="error"/></td></tr>
                                                            <tr><td><spring:message code="product.label.description" /></td>
                                                                <td><form:input path="description"  class="textareas" id="pDescription" value="${product.description}" onclick="viderChamps(this)" /></td><td class="noDeco"> <form:errors path="description" cssClass="error" />  </td></tr>
                                                            <tr><td><spring:message code="product.label.quantity" /></td>
                                                                <td><form:input path="quantity" class="textareas" id="pQuantity" value="${product.quantity}" onclick="viderChamps(this)" /> </td><td class="noDeco"> <form:errors path="quantity" cssClass="error"/> </td></tr>
                                                            <tr><td><spring:message code="product.label.price" /></td>
                                                                <td><form:input path="price" class="textareas" id="pPrice" value="${product.price}" onclick="viderChamps(this)" /></td><td class="noDeco"> <form:errors path="price" cssClass="error"/> </td></tr>
                                                            <tr><td><spring:message code="product.label.supplier" /></td>
                                                                <td><form:input path="supplier" class="textareas" id="pSupplier" value="${product.supplier}" onclick="viderChamps(this)" /></td><td class="noDeco"> <form:errors path="supplier" cssClass="error" /></td></tr>
                                                            <tr><td><input type="submit" value="<spring:message code="product.button.validate" />" class="button"/></td></tr>
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
                    
                    <div class="button" id="addButton" onclick="showForm('addForm')"><spring:message code="product.button.add" /></div> </br></br>
            
                    <div id="addForm" class="ui-widget-content ui-corner-all" hidden="true" >
                        <table><tr><td class="noDeco"> <p class="title">Please enter product informations.</p> </td><td class="noDeco"><div align="right" id="hideButton2" onclick="runHideEffect('addForm')"></div></td></tr></table>
                        <form:form method="post" action="productadd.htm" commandName="productadd" >
                        <table>                            
                            <tr><td class="champ"><spring:message code="product.label.name" />   </td>
                                <td><form:input path="name" class="textareas"  name="name" /></td><td class="noDeco"><form:errors path="name" cssClass="error" /> </td></tr>
                            <tr><td class="champ"><spring:message code="product.label.description" /></td>
                                <td><form:input path="description" class="textareas" name="productDescription" /></td><td class="noDeco"> <form:errors path="description" cssClass="error" /> </td></tr>
                            <tr><td class="champ"><spring:message code="product.label.quantity" /></td>
                                <td><form:input path="quantity" class="textareas" name="productQuantity" /></td><td class="noDeco"><form:errors path="quantity" cssClass="error" /></td></tr>
                            <tr><td class="champ"><spring:message code="product.label.price" /></td>
                                <td><form:input path="price" class="textareas" name="productPrice" /></td><td class="noDeco"><form:errors path="price" cssClass="error" /></td></tr>
                            <tr><td class="champ"><spring:message code="product.label.supplier" /></td>
                                <td><form:input path="supplier" class="textareas" name="productSupplier" /></td><td class="noDeco"><form:errors path="supplier" cssClass="error" /></td></tr>
                            <tr><td><input type="submit" value="<spring:message code="product.button.validate" />" class="button"/></td></tr>
                        </table>
                        </form:form>
                       

                    </div>
                </td></tr>
        </table>
                    <%@ include file="/WEB-INF/jsp/footer.jsp" %>
            <!--***********************************************************************************************************-->
        <script src="<c:url value="/inc/jquery.js"/>"></script>
        <script src="<c:url value="/inc/jquery-ui.js"/>"></script>
        <script src="<c:url value="/inc/product-script.js"/>"></script>

    </body>
</html>
