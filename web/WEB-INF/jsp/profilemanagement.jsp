<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

    <head>
        <link type="text/css" rel="stylesheet" href="<c:url value="/inc/style.css"/>" />
        <link type="text/css" rel="stylesheet" href="<c:url value="/inc/jquery-ui.css"/>" />
        <title><fmt:message key="title"/></title>
    </head>

    <body>
        <%@ include file="/WEB-INF/jsp/header.jsp" %>

        <fieldset>
            <legend>Your infos</legend>
                    <div>
                        <p>
                            ID : ${loggedUser.id}<br>
                            First Name : ${loggedUser.firstName}<br>
                            Last Name : ${loggedUser.lastName}<br>
                            Email : ${loggedUser.email}<br>
                            Telephone : ${loggedUser.telephone}<br>
                            Address : ${loggedUser.address}<br>
                            Login : ${loggedUser.login}<br>
                            Role : ${loggedUser.role}<br>
                        </p>
                    </div>
                        <a id="<c:out value='userupdate${loggedUser.id}'/>">Update</a>
        </fieldset>

        <div id="userupdate">
            <form:form method="post" action="profileupdate.htm" commandName="userupdate" id="updateform">    
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

                <input type="submit" value="update"/>
            </form:form>
        </div>

        <%@ include file="/WEB-INF/jsp/footer.jsp" %>
        
        <script src="<c:url value="/inc/jquery.js"/>"></script>
        <script src="<c:url value="/inc/jquery-ui.js"/>"></script>
        <script src="<c:url value="/inc/inventaire-script.js"/>"></script>
    </body>
</html>
