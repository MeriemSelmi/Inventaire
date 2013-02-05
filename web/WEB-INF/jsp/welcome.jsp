<%@ include file="/WEB-INF/jsp/include.jsp" %>
<!DOCTYPE html>

<html>
    <head>
        <link type="text/css" rel="stylesheet" href="inc/style.css" />
        <title><fmt:message key="title"/> | <fmt:message key="title.welcome"/></title>
    </head>
    <body>
        <%@ include file="/WEB-INF/jsp/header.jsp" %>
        <fieldset>
            <legend>Welcome</legend>
            <a href="<c:url value="/product/productmanagement.htm"/>"><fmt:message key="productManager"/></a><br>
            <a href="<c:url value="/profile/profilemanagement.htm"/>"><fmt:message key="profileManager"/></a><br>
            
            <c:if test="${loggedUser.role=='Administrator'}">
                <a href="<c:url value="/user/usermanagement.htm"/>"><fmt:message key="userManager"/></a>
            </c:if>
        </fieldset>
    </body>
</html>
