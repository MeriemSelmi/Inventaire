<footer>
    <a href="<c:url value="/product/productmanagement.htm"/>"><fmt:message key="productManager"/></a>  
    <a href="<c:url value="/profile/profilemanagement.htm"/>"><fmt:message key="profileManager"/></a>
    <c:if test="${loggedUser.role=='Administrator'}">
        <a href="<c:url value="/user/usermanagement.htm"/>"><fmt:message key="userManager"/></a>
    </c:if>
</footer>
