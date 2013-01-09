<footer>
    <a href="<c:url value="/product/productmanagement.htm"/>">Products Manager</a>  
    <a href="<c:url value="/profile/profilemanagement.htm"/>">Profile Manager</a>
    <c:if test="${loggedUser.role=='Administrator'}">
        <a href="<c:url value="/user/usermanagement.htm"/>">User Manager</a>
    </c:if>
</footer>
