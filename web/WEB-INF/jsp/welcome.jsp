<%@ include file="/WEB-INF/jsp/include.jsp" %>

<html>
    <head>
        <link type="text/css" rel="stylesheet" href="inc/style.css" />
        <title><fmt:message key="title"/></title>
    </head>
    <body>
        <fieldset>
            <legend>Welcome</legend>
            <a href="<c:url value="productmanagement.htm"/>">Product manager</a><br>
            <a href="<c:url value="/user/usermanagement.htm"/>">User manager</a>
        </fieldset>
    </body>
</html>
