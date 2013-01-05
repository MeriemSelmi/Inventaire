<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
    <head>
        <link type="text/css" rel="stylesheet" href="<c:url value="/inc/style.css"/>" />
        <title>Update User</title>
    </head>
    <body>
    <form:form method="post" action="userupdate.htm" commandName="userupdate">    
        <label>Name : </label>
        <form:input path="lastName"/><br>

        <label>First Name : </label>
        <form:input path="firstName"/><br>

        <label>Mail : </label>
        <form:input path="email"/><br>

        <label>Telephone : </label>
        <form:input path="telephone"/><br>

        <label>Address : </label>
        <form:input path="address"/><br>

        <label>Login : </label>
        <form:input path="login"/><br>

        <label>Password : </label>
        <form:input path="password" /><br>

        <label>Role : </label>
        <form:select path="role" items="${userRoles}"/><br>

        <input type="number" name="id" value="${loggedUser.id}" hidden="">
        <input type="submit" value="update"/>
    </form:form>
</body>
</html>
