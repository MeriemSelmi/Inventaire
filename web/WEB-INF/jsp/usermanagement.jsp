<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
    
    <head>
        <link type="text/css" rel="stylesheet" href="<c:url value="/inc/style.css"/>" />
        <title><fmt:message key="title"/></title>
    </head>
    
    <body>
        <fieldset>
            <legend>Users List</legend>
            <table>
                <c:forEach var="user" items="${requestScope.users}">
                    <tr>
                        <td width="5%">
                            ${user.id}
                        </td>
                        <td>
                            <a id="<c:out value='userinfo${user.id}'/>">
                                <c:out value="${user.firstName} ${user.name}"/>
                            </a>
                        </td>
                        <td>
                            <a id="<c:out value='userupdate${user.id}'/>">Update</a>   <a href="">Delete</a>
                        </td>
                    </tr>

                    <tr>
                        <td></td>
                        <td colspan="2">
                            <div id="<c:out value='userinfo${user.id}'/>">
                                <p>
                                    Email : ${user.mail}<br>
                                    Telephone : ${user.telephone}<br>
                                    Address : ${user.address}<br>
                                    Login : ${user.login}<br>
                                    Type : ${user.type}<br>
                                </p>
                            </div>

                            <div id="<c:out value='userupdate${user.id}'/>">
                                <form:form method="post" action="userupdate.htm" commandName="userupdate">    
                                    <label>Name : </label>
                                    <form:input path="name"/><br>
                                    
                                    <label>First Name : </label>
                                    <form:input path="firstName"/><br>
                                    
                                    <label>Mail : </label>
                                    <form:input path="mail"/><br>
                                    
                                    <label>Telephone : </label>
                                    <form:input path="telephone"/><br>
                                    
                                    <label>Address : </label>
                                    <form:input path="address"/><br>
                                    
                                    <label>Login : </label>
                                    <form:input path="login"/><br>
                                    
                                    <label>Password : </label>
                                    <form:input path="pass" /><br>
                                    
                                    <label>Type : </label>
                                    <form:select path="type" items="${userTypes}"/><br>
                                    
                                    <input type="number" name="id" value="${user.id}" hidden="">
                                    <input type="submit" value="update"/>
                                </form:form>
                            </div>
                        </td>
                    </tr>
                </c:forEach>

            </table>
        </fieldset>

        <script src="<c:url value="/inc/jquery.js"/>"></script>
        <script src="<c:url value="/inc/inventaire-script.js"/>"></script>
        <p id='test'></p>
    </body>
</html>
