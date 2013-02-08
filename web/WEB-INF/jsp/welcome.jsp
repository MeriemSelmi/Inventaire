<%@ include file="/WEB-INF/jsp/include.jsp" %>
<!DOCTYPE html>

<html>
    <head>
        <link type="text/css" rel="stylesheet" href="inc/style.css" />
        <title><fmt:message key="title"/> | <fmt:message key="title.welcome"/></title>
    </head>
    <body>
        <%@ include file="/WEB-INF/jsp/header.jsp" %>
         
        
        <div id="welcom-box">
           <legend id="welcome-title"><fmt:message key="title.welcome"/></legend>
           <div class="links">
               <ul type="none">
                   <li> <a href="<c:url value="/product/productmanagement.htm"/>"><fmt:message key="product.page.title"/></a></li>
                   <li> <a href="<c:url value="/profile/profilemanagement.htm"/>"><fmt:message key="profile.page.title"/></a><br></li>
            
                   <li><c:if test="${loggedUser.role=='Administrator'}">
                <a href="<c:url value="/user/usermanagement.htm"/>"><fmt:message key="user.page.title"/></a>
                       </c:if></li>
               </ul>
           </div>
        </div>
    </body>
</html>
