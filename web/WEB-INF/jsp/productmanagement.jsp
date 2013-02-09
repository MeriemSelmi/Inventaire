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

        <link type="text/css" rel="stylesheet" href="<c:url value="/inc/jquery-ui.css"/>" />
        <title><fmt:message key="title"/> | <fmt:message key="title.products"/></title>
    </head>
    
    
    <style>


        .title{
    color:#4927e7;
    font-size:20px;
    
}

        


        
    </style>
    
    
    <body>
        <%@ include file="/WEB-INF/jsp/header.jsp" %>
        <table id="global">
            <caption><a href="/Inventory/product/productmanagement.htm" style="text-decoration: none;"><div class="welcome"><fmt:message key="product.page.title"/></div></a></caption>
            <!--**********************************SEARCH TOOL*************************************************-->
            <caption align="center">
                <form:form method="post" action="productfind.htm" commandName="productfind">
                    <table>
                        <tr>
                            <td class="champ" align="center"><fmt:message key="title.find" /> &nbsp;&nbsp;&nbsp;&nbsp; <form:input path="key" class="textareas" /></td>
                            <td align="center"><input type="submit" value="<fmt:message key="button.find" />" class="button" />
                            <form:errors path="key" cssClass="error" /></td>
                        </tr>              
                    </table>                   
                </form:form>
            </caption>
            <!--**********************************ERRORS SPACE************************************************-->
            <caption class="error-caption"><div class="error">${exception}</div></caption>
            <!--**********************************PRODUCT LIST***************************************************-->
            <tr>
                <td>
                    <div>                                              
                        <table width="70%">
                            <tr>
                                <td>
                                    <div id="accordion">
                                        <c:forEach var="product" items='${products}' >
                                            
                                           <p class="nomProduit" ><c:out value="${product.name}"></c:out></p> 
                                            
                                            <div class="productDiv" id="${product.name}"   >
                                                <input type="hidden" value="${nameErrorProduct}" id="errorP" />
                                                <input type="hidden" value="${errorProduct}" id="errorId" />
                                                
                                                    <div  >
                                                        
                                                        
                                                        <table class="productInfoTable">
                                                            <tr><td class="champ"><fmt:message key="product.label.name" /></td><td class="text">${product.name}</td></tr>  
                                                            <tr><td class="champ"><fmt:message key="product.label.description" /></td><td class="text">${product.description} </td></tr>
                                                            <tr><td class="champ"><fmt:message key="product.label.price" /></td><td class="text">${product.price}</td></tr>
                                                            <tr><td class="champ"><fmt:message key="product.label.quantity" /></td> <td class="text">${product.quantity}</td></tr>
                                                            <tr><td class="champ"><fmt:message key="product.label.supplier" /></td> <td class="text">${product.supplier}</td></tr>
                                                         </table>
                                                            
                                                         
                                                         <table >
                                                             <tr>
                                                                 <td align="center"><div class="button" onclick="showForm('${product.id}');" id="updateButton"><fmt:message key="button.update" /></div></td>
                                                                 <td align="center"><a href="productdelete.htm?id=${product.id}" > <div class="button" id="deleteButton"><fmt:message key="button.delete" /></div> </a></td></tr>
                                                        </table>
                                                    </div>



                                                <div id="${product.id}" class="ui-widget-content ui-corner-all" hidden="hidden" >
                                                    <table><tr><td class="noDeco"> <p class="title"><fmt:message key="title.update" /></p> </td><td class="noDeco"><div align="right" id="hideButton1" onclick="runHideEffect('${product.id}')"></div></td></tr></table>
                                                    <form:form method="post" action="productupdate.htm" commandName="productupdate" acceptCharset="UTF-8" >
                                                        <input type="hidden" name="updateName" value="${product.name}" />  
                                                        <table>
                                                            <tr><input type="text" hidden="true" name="id" value="${product.id}" /></tr>
                                                            <tr><td><fmt:message key="product.label.name" /></td>
                                                                <td><form:input path="name"  Class="updateTextareas"  value="${product.name}" onclick="viderChamps(this)" /> </td><td class="noDeco"><c:if test="${product.id==errorProduct}" > <form:errors path="name" cssClass="error"/> </c:if></td></tr>
                                                            <tr><td><fmt:message key="product.label.description" /></td>
                                                                <td><form:input path="description"  class="updateTextareas"  value="${product.description}" onclick="viderChamps(this)" /></td><td class="noDeco"><c:if test="${product.id==errorProduct}" > <form:errors path="description" cssClass="error" /> </c:if> </td></tr>
                                                            <tr><td><fmt:message key="product.label.quantity" /></td>
                                                                <td><form:input path="quantity" class="updateTextareas"  value="${product.quantity}" onclick="viderChamps(this)" /> </td><td class="noDeco"><c:if test="${product.id==errorProduct}" > <form:errors path="quantity" cssClass="error"/></c:if> </td></tr>
                                                            <tr><td><fmt:message key="product.label.price" /></td>
                                                                <td><form:input path="price" class="updateTextareas" value="${product.price}" onclick="viderChamps(this)" /></td><td class="noDeco"><c:if test="${product.id==errorProduct}" > <form:errors path="price" cssClass="error"/> </c:if></td></tr>
                                                            <tr><td><fmt:message key="product.label.supplier" /></td>
                                                                <td><form:input path="supplier" class="updateTextareas"  value="${product.supplier}" onclick="viderChamps(this)" /></td><td class="noDeco"><c:if test="${product.id==errorProduct}" > <form:errors path="supplier" cssClass="error" /></c:if></td></tr>
                                                            <tr><td><input type="submit" value="<fmt:message key="button.validate" />" class="button"/></td></tr>
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
                    
                    <div class="button" id="addButton" onclick="showForm('addForm')"><fmt:message key="button.add" /></div> </br></br>
            
                    <div id="addForm" class="ui-widget-content ui-corner-all" hidden="true" >
                        <input type="hidden" value="${openAddForm}" id="addError" />
                        <table><tr><td class="noDeco"> <p class="title"><fmt:message key="title.add"/></p> </td><td class="noDeco"><div align="right" id="hideButton2" onclick="runHideEffect('addForm')"></div></td></tr></table>
                        <form:form method="post" action="productadd.htm" commandName="productadd" >
                        <table>                            
                            <tr><td class="champ"><fmt:message key="product.label.name" />   </td>
                                <td><form:input path="name" class="textareas"  name="name" /></td><td class="noDeco"><form:errors path="name" cssClass="error" /> </td></tr>
                            <tr><td class="champ"><fmt:message key="product.label.description" /></td>
                                <td><form:input path="description" class="textareas" name="productDescription" /></td><td class="noDeco"> <form:errors path="description" cssClass="error" /> </td></tr>
                            <tr><td class="champ"><fmt:message key="product.label.quantity" /></td>
                                <td><form:input path="quantity" class="textareas" name="productQuantity" /></td><td class="noDeco"><form:errors path="quantity" cssClass="error" /></td></tr>
                            <tr><td class="champ"><fmt:message key="product.label.price" /></td>
                                <td><form:input path="price" class="textareas" name="productPrice" /></td><td class="noDeco"><form:errors path="price" cssClass="error" /></td></tr>
                            <tr><td class="champ"><fmt:message key="product.label.supplier" /></td>
                                <td><form:input path="supplier" class="textareas" name="productSupplier" /></td><td class="noDeco"><form:errors path="supplier" cssClass="error" /></td></tr>
                            <tr><td><input type="submit" value="<fmt:message key="button.validate" />" class="button"/></td></tr>
                        </table>
                        </form:form>
                       

                    </div>
                </td></tr>
        </table>
                    
                    
        <%@ include file="/WEB-INF/jsp/footer.jsp" %>
        <!--***********************************************************************************************************-->
        <script src="<c:url value="/inc/jquery.js"/>"></script>
        <script src="<c:url value="/inc/jquery-ui.js"/>"></script>
        <script src="<c:url value="/inc/script.js"/>"></script>

    </body>
</html>
