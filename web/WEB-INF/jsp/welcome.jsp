<%@ include file="/WEB-INF/jsp/include.jsp" %>

<html>
    <head>
        <link type="text/css" rel="stylesheet" href="inc/style.css" />
        <title><fmt:message key="title"/></title>
    </head>
    <body>
        <a href="<c:url value="productmanagement.htm"/>">Products manager</a><br>
        <a href="<c:url value="usermanagement.htm"/>">User manager</a>
    </body>
</html>
