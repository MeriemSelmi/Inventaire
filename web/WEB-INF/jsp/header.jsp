<header>
    <div>${loggedUser.role} Area   <a href="?language=en">En</a>|<a href="?language=fr">Fr</a></div>
    <div><fmt:message key="title.welcome"/> ${loggedUser.firstName} ${loggedUser.lastName}  <a href="<c:url value='/profile/logout.htm' />" ><fmt:message key="logout"/></a></div>
</header>

