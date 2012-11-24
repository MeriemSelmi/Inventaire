<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
    
    <head>
        <link type="text/css" rel="stylesheet" href="inc/style.css" />
        <title><fmt:message key="title"/></title>
    </head>
    
    <body>
        <fieldset>
            <legend>Users List</legend>
            <table>
                <c:forEach var="user" items="${requestScope.users}">
                    <tr>
                        <td>
                            ${user.id}
                        </td>
                        <td>
                            <a id="<c:out value='user${user.id}'/>">
                                <c:out value="${user.firstName} ${user.name}"/>
                            </a>
                    </tr>

                    <tr>
                        <td></td>
                        <td>
                            <div id="<c:out value='user${user.id}'/>">
                                <p>
                                    Email : ${user.mail}<br>
                                    Telephone : ${user.telephone}<br>
                                    Address : ${user.address}<br>
                                    Login : ${user.login}<br>
                                    Type : ${user.type}<br>
                                </p>
                            </div>

                            <div id="<c:out value='alteruser${user.id}'/>">
                                
                            </div>
                        </td>
                    </tr>
                </c:forEach>

            </table>
        </fieldset>

        <script src="inc/jquery.js"></script>
        <script src="inc/inventaire-script.js"></script>
        <p id='test'></p>
    </body>
</html>
