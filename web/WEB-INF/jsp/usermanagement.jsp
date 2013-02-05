<%-- 
    Document   : usermanagement
    Created on : 24 oct. 2012, 16:02:43
    Author     : Meriem
--%>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <link type="text/css" rel="stylesheet" href="<c:url value="/inc/style.css"/>" />
        <link type="text/css" rel="stylesheet" href="<c:url value="/inc/jquery-ui.css"/>" />
        <title><fmt:message key="title"/> | <fmt:message key="title.users"/></title>
    </head>

    <body>
        <%@ include file="/WEB-INF/jsp/header.jsp" %>
        <table id="global">
            <caption><div class="welcome"><fmt:message key="userManager"/></div></caption>
            <!--**********************************SEARCH TOOL*************************************************-->
            <caption align="center">
                <form:form method="post" action="userfind.htm" commandName="userfind">
                    <table>
                        <tr>
                            <td class="champ" align="center"><fmt:message key="enterCriterion"/>: &nbsp;&nbsp;&nbsp;&nbsp; <form:input path="keyword" class="textareas" /> <form:errors path="keyword"/></td>
                            <td align="center"><input type="submit" value="<fmt:message key="button.find"/>" class="button" /></td>
                        </tr>              
                    </table>                   
                </form:form>
            </caption>

            <!--**********************************USERS LIST***************************************************-->
            <tr>
                <td>
                    <div>                                              
                        <table width="70%">
                            <tr>
                                <td>
                                    <div id="accordion">
                                        <c:forEach var="user" items='${users}' >

                                            <p><c:out value="${user.firstName} ${user.lastName}"></c:out></p>

                                                <div class="productDiv">
                                                    <div>
                                                        <table class="productInfoTable">
                                                        <tr><td class="champ"><fmt:message key="label.id"/> :</td><td class="text">${user.id}</td></tr>  
                                                        <tr><td class="champ"><fmt:message key="label.email"/> :</td><td class="text">${user.email} </td></tr>
                                                        <tr><td class="champ"><fmt:message key="label.telephone"/> :</td><td class="text">${user.telephone}</td></tr>
                                                        <tr><td class="champ"><fmt:message key="label.address"/> :</td> <td class="text">${user.address}</td></tr>
                                                        <tr><td class="champ"><fmt:message key="label.login"/> :</td> <td class="text">${user.login}</td></tr>
                                                        <tr><td class="champ"><fmt:message key="label.role"/> :</td> <td class="text">${user.role}</td></tr>
                                                    </table>
                                                    <table >
                                                        <tr>
                                                            <td align="center"><div class="button" onclick="showForm('${user.id}');" id="updateButton"><fmt:message key="button.update"/></div></td>
                                                            <td align="center"><a href="userdelete.htm?id=${user.id}" > <div class="button" id="deleteButton"><fmt:message key="button.delete"/></div> </a></td></tr>
                                                    </table>
                                                </div>

                                                <div id="${user.id}" class="ui-widget-content ui-corner-all" hidden="hidden">
                                                    <table><tr><td class="noDeco"> <p class="title"><fmt:message key="enterNewUserInfos"/></p> </td><td class="noDeco"><div align="right" id="hideButton1" onclick="runHideEffect('${user.id}')"></div></td></tr></table>

                                                    <form:form method="post" action="userupdate.htm" commandName="userupdate" acceptCharset="UTF-8" >
                                                        <form:errors path="error"/>
                                                        <table>
                                                            <tr><input type="text" hidden="true" name="id" value="${user.id}" /></tr>
                                                            <tr><td><form:label path="firstName"><fmt:message key="label.firstName"/> : </form:label></td>
                                                                <td><form:input path="firstName" class="textareas"  value="${user.firstName}" onclick="viderChamps(this)" /> 
                                                                    <form:errors path="firstName"/></td></tr>
                                                            <tr><td><form:label path="lastName"><fmt:message key="label.lastName"/> : </form:label></td>
                                                                <td><form:input path="lastName" class="textareas"  value="${user.lastName}" onclick="viderChamps(this)" /> 
                                                                    <form:errors path="lastName"/></td></tr>
                                                            <tr><td><form:label path="email"><fmt:message key="label.email"/> : </form:label></td>
                                                                <td><form:input path="email" class="textareas"  value="${user.email}" onclick="viderChamps(this)" /> 
                                                                    <form:errors path="email"/></td></tr>
                                                            <tr><td><form:label path="telephone"><fmt:message key="label.telephone"/> : </form:label></td>
                                                                <td><form:input path="telephone" class="textareas"  value="${user.telephone}" onclick="viderChamps(this)" /> 
                                                                    <form:errors path="telephone"/></td></tr>
                                                            <tr><td><form:label path="address"><fmt:message key="label.address"/> : </form:label></td>
                                                                <td><form:input path="address" class="textareas"  value="${user.address}" onclick="viderChamps(this)" /> 
                                                                    <form:errors path="address"/></td></tr>
                                                            <tr><td><form:label path="login"><fmt:message key="label.login"/> : </form:label></td>
                                                                <td><form:input path="login" class="textareas"  value="${user.login}" onclick="viderChamps(this)" /> 
                                                                    <form:errors path="login"/></td></tr>
                                                            <tr><td><form:label path="password"><fmt:message key="label.password"/> : </form:label></td>
                                                                <td><form:password path="password" class="textareas"  value="${user.password}" onclick="viderChamps(this)" /> 
                                                                    <form:errors path="password"/></td></tr>
                                                            <tr><td><form:label path="role"><fmt:message key="label.role"/> : </form:label></td>
                                                                    <td> 
                                                                    <form:select path="role">
                                                                        <form:option value="${user.role}"/>
                                                                        <c:if test="${user.role ne 'Administrator'}">
                                                                            <form:option value="Administrator"/>
                                                                        </c:if>
                                                                        <c:if test="${user.role ne 'Storekeeper'}">
                                                                            <form:option value="Storekeeper"/>
                                                                        </c:if>
                                                                    </form:select>
                                                                    <form:errors path="role"/></td></tr>
                                                            <tr><td><input type="submit" value="<fmt:message key="button.validate"/>" class="button"/></td></tr>
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

            <!--************************************ADD USER SPACE*************************************************-->
            <tr>
                <td align="center">
                    <div class="button" id="addButton" onclick="showForm('addForm')"><fmt:message key="button.addUser"/></div> </br></br>

                    <div id="addForm" class="ui-widget-content ui-corner-all" hidden="true" >
                        <table><tr><td class="noDeco"> <p class="title"><fmt:message key="enterUserInfos"/></p> </td><td class="noDeco"><div align="right" id="hideButton2" onclick="runHideEffect('addForm')"></div></td></tr></table>
                        <form:form method="post" action="useradd.htm" commandName="useradd" >
                            <table>                            
                                <tr><td><form:label path="firstName"><fmt:message key="label.firstName"/> : </form:label></td>
                                    <td><form:input path="firstName" class="textareas"/> 
                                        <form:errors path="firstName"/></td></tr>
                                <tr><td><form:label path="lastName"><fmt:message key="label.lastName"/> : </form:label></td>
                                    <td><form:input path="lastName" class="textareas"/> 
                                        <form:errors path="lastName"/></td></tr>
                                <tr><td><form:label path="email"><fmt:message key="label.email"/> : </form:label></td>
                                    <td><form:input path="email" class="textareas"/> 
                                        <form:errors path="email"/></td></tr>
                                <tr><td><form:label path="telephone"><fmt:message key="label.telephone"/> : </form:label></td>
                                    <td><form:input path="telephone" class="textareas"/> 
                                        <form:errors path="telephone"/></td></tr>
                                <tr><td><form:label path="address"><fmt:message key="label.address"/> : </form:label></td>
                                    <td><form:input path="address" class="textareas"/> 
                                        <form:errors path="address"/></td></tr>
                                <tr><td><form:label path="login"><fmt:message key="label.login"/> : </form:label></td>
                                    <td><form:input path="login" class="textareas"/> 
                                        <form:errors path="login"/></td></tr>
                                <tr><td><form:label path="password"><fmt:message key="label.password"/> : </form:label></td>
                                    <td><form:password path="password" class="textareas"/> 
                                        <form:errors path="password"/></td></tr>
                                <tr><td><form:label path="role"><fmt:message key="label.role"/> : </form:label></td>
                                    <td><form:select path="role" items="${userRoles}" class="textareas"/> 
                                        <form:errors path="role"/></td></tr>
                                <tr><td><input type="submit" value="<fmt:message key="button.validate"/>" class="button"/></td></tr>
                            </table>
                        </form:form>


                    </div>
                </td>
            </tr>
        </table>

        <%@ include file="/WEB-INF/jsp/footer.jsp" %>

        <script src="<c:url value="/inc/jquery.js"/>"></script>
        <script src="<c:url value="/inc/jquery-ui.js"/>"></script>
        <script src="<c:url value="/inc/script.js"/>"></script>
    </body>
</html>
