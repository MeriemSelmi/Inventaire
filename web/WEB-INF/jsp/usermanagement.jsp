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
                        <td><c:out value="${user.name}"/></td>
                    </tr>
                </c:forEach>

            </table>
        </fieldset>
    </body>
</html>
