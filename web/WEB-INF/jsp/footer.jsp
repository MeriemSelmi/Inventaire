<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<footer>
    
    <a href="<c:url value="/product/productmanagement.htm"/>"><spring:message code="product.page.title" /></a>  
    <a href="<c:url value="/profile/profilemanagement.htm"/>"><spring:message code="profile.page.title" /></a>
    <c:if test="${loggedUser.role=='Administrator'}">
        <a href="<c:url value="/user/usermanagement.htm"/>"><spring:message code="user.page.title" /></a>
    </c:if>
</footer>
