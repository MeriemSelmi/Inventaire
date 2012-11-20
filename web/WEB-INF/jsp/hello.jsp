<%-- 
    Document   : hello
    Created on : 15 nov. 2012, 12:02:27
    Author     : Meriem
--%>

<%@ include file="/WEB-INF/jsp/include.jsp" %>
<html>
    <head>
        <link type="text/css" rel="stylesheet" href="inc/style.css" />
        <title><fmt:message key="title"/></title>
    </head>
    
    <body>
        <h1><fmt:message key="heading"/></h1>
        <p><fmt:message key="greeting"/> <c:out value="${model.now}"/></p>
        <h3>Products</h3>
        
        <c:forEach items="${model.products}" var="product">
            <c:out value="${product.description}"/> <i>$<c:out
                    value="${product.price}"/></i><br><br>
        </c:forEach>
    </body>
</html>