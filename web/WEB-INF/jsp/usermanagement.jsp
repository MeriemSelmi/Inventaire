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
            <legend>Actions</legend>
            <a id="<c:out value='useradd'/>">Add user</a>
            <form:form method="post" action="userfind.htm" commandName="userfind">    
                <form:input path="keyword" title="find user"/><input type="submit" value="Go"/>
            </form:form>

            <div>
                <c:forEach var="usersFound" items="${requestScope.usersFound}">
                    <a href="">${usersFound.firstName} ${usersFound.lastName}</a><br>
                </c:forEach>
            </div>
        </fieldset>

        <fieldset>
            <legend>Users List</legend>

            <div id="users">
                <c:forEach var="user" items="${requestScope.users}">

                    <h3 id="${user.id}"> ${user.id} ${user.firstName} ${user.lastName} <a id="<c:out value='userupdate${user.id}'/>">Update</a>   <a href="<c:out value='userdelete.htm?id=${user.id}'/>" id="userdelete">Delete</a></h3>  
                    <div>
                        <p>
                            Email : ${user.email}<br>
                            Telephone : ${user.telephone}<br>
                            Address : ${user.address}<br>
                            Login : ${user.login}<br>
                            Role : ${user.role}<br>
                        </p>
                    </div>

                </c:forEach>
            </div>
        </fieldset>



        <div id="useradd">
            <form:form method="post" action="useradd.htm" commandName="useradd">    
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
                <input type="submit" value="add"/>
            </form:form>
        </div>

        <div id="userupdate">
            <form:form method="post" action="userupdate.htm" commandName="userupdate" id="updateform">    
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

        <script src="<c:url value="/inc/jquery.js"/>"></script>
        <script src="<c:url value="/inc/jquery-ui.js"/>"></script>
        <script src="<c:url value="/inc/user-script.js"/>"></script>
    </body>
</html>
