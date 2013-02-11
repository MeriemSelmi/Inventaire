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
           <legend id="welcome-title" ><fmt:message key="title.welcome"/></legend>
           <div class="links">
               <ul type="none">
                   <li> <a href="<c:url value="/product/productmanagement.htm"/>"><div><img src="inc/product.png" width="25" height="25" class="product"><fmt:message key="product.page.title"/></div></a></li>
                   <li> <a href="<c:url value="/profile/profilemanagement.htm"/>"><div><img src="inc/profile.png" width="25" height="25" class="profile"><fmt:message key="profile.page.title"/></div></a></li>        
                   <li><c:if test="${loggedUser.role=='Administrator'}">
                           <a href="<c:url value="/user/usermanagement.htm"/>"><div><img src="inc/users.png" width="25" height="25" class="user"><fmt:message key="user.page.title"/></div></a>
                       </c:if></li>
               </ul>
           </div>
        </div>
    </body>
</html>
