<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
                <form:errors path="keyword"/>
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

                <form:label path="role">Role : </form:label>
                <form:select path="role" items="${userRoles}"/><br>
                <input type="submit" value="add"/>
            </form:form>
        </div>

        <div id="userupdate">
            <form:form method="post" action="userupdate.htm" commandName="userupdate" id="updateform">
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

                <form:label path="role">Role : </form:label>
                <form:select path="role" items="${userRoles}"/><br>

                <input type="submit" value="update"/>
            </form:form>
        </div>

        <%@ include file="/WEB-INF/jsp/footer.jsp" %>

        <script src="<c:url value="/inc/jquery.js"/>"></script>
        <script src="<c:url value="/inc/jquery-ui.js"/>"></script>
        <script src="<c:url value="/inc/user-script.js"/>"></script>
    </body>
</html>
