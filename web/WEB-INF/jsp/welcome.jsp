<%@ include file="/WEB-INF/jsp/include.jsp" %>
<!DOCTYPE html>

<html>
    <head>
        <link type="text/css" rel="stylesheet" href="inc/style.css" />
        <title><fmt:message key="title"/> | <fmt:message key="title.welcome"/></title>
    </head>
    <body>
        <%@ include file="/WEB-INF/jsp/header.jsp" %>
         <legend id="auth-title"><fmt:message key="title.welcome"/></legend>
        
        <div id="welcom-box">
           
            <a href="<c:url value="/product/productmanagement.htm"/>"><fmt:message key="product.page.title"/></a><br>
            <a href="<c:url value="/profile/profilemanagement.htm"/>"><fmt:message key="profile.page.title"/></a><br>
            
            <c:if test="${loggedUser.role=='Administrator'}">
                <a href="<c:url value="/user/usermanagement.htm"/>"><fmt:message key="user.page.title"/></a>
            </c:if>
        </div>
    </body>
</html>
