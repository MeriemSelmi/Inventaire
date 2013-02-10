<footer>
    
    <span style="display: inline-table;"><a id="footer-page" href="<c:url value='/product/productmanagement.htm'/>"><div id="product"></div></a> </span> 
        <span style="display: inline-table;"><a id="footer-page" href="<c:url value='/profile/profilemanagement.htm'/>"><div id="profile"></div></a></span>
            <span style="display: inline-table;"><c:if test="${loggedUser.role=='Administrator'}">
        <a id="footer-page" href="<c:url value='/user/usermanagement.htm'/>"><div id="user"></div></a>
    </c:if></span>
</footer>
