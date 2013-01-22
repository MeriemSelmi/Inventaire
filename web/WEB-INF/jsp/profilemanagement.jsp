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
            <form:form method="post" action="profileupdate.htm" commandName="profileupdate" id="updateform">
                <form:errors path="error"/><br>
                
                <form:label path="lastName">Last Name : </form:label>
                <form:input path="lastName"/>
                <form:errors path="lastName"/><br>

                <form:label path="firstName">First Name : </form:label>
                <form:input path="firstName"/>
                <form:errors path="firstName"/><br>

                <form:label path="email">Email : </form:label>
                <form:input path="email"/>
                <form:errors path="email"/><br>

                <form:label path="telephone">Telephone : </form:label>
                <form:input path="telephone"/>
                <form:errors path="telephone"/><br>

                <form:label path="address">Address : </form:label>
                <form:input path="address"/>
                <form:errors path="address"/><br>

                <form:label path="login">Login : </form:label>
                <form:input path="login"/>
                <form:errors path="login"/><br>

                <form:label path="password">Password : </form:label>
                <form:password path="password" />
                <form:errors path="password"/><br>

                <input type="submit" value="update"/>
            </form:form>
        </div>

        <%@ include file="/WEB-INF/jsp/footer.jsp" %>
        
        <script src="<c:url value="/inc/jquery.js"/>"></script>
        <script src="<c:url value="/inc/jquery-ui.js"/>"></script>
        <script src="<c:url value="/inc/profile-script.js"/>"></script>
    </body>
</html>