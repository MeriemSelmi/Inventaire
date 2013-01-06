<%@ include file="/WEB-INF/jsp/include.jsp" %>

<html>
    <head>
        <link type="text/css" rel="stylesheet" href="inc/style.css" />
        <title><fmt:message key="title"/></title>
    </head>
    <body>
        <%@ include file="/WEB-INF/jsp/header.jsp" %>
        <fieldset>
            <legend>Welcome</legend>
            <a href="<c:url value="/product/productmanagement.htm"/>">Product manager</a><br>
            <a href="<c:url value="/profile/profilemanagement.htm"/>">Profile manager</a><br>
            
            <c:if test="${loggedUser.role=='Administrator'}">
                <a href="<c:url value="/user/usermanagement.htm"/>">User manager</a>
            </c:if>
        </fieldset>
    </body>
</html>
