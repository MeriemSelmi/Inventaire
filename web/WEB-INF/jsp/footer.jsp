<footer>
    
    <a href="<c:url value="/product/productmanagement.htm"/>"><fmt:message key="product.page.title" /></a>  
    <a href="<c:url value="/profile/profilemanagement.htm"/>"><fmt:message key="profile.page.title" /></a>
    <c:if test="${loggedUser.role=='Administrator'}">
        <a href="<c:url value="/user/usermanagement.htm"/>"><fmt:message key="user.page.title" /></a>
    </c:if>
</footer>
