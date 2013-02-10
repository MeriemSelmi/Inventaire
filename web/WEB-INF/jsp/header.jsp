<header>
    <div  id="head">
        
        <div>${loggedUser.role} Area  &nbsp;&nbsp; <a href="?language=en">En</a>|<a href="?language=fr">Fr</a></div>
        <div class="accueil"><a href="<c:url value='/welcome.htm' />" ><fmt:message key="welcome.page"/></a></div>
        <div><fmt:message key="title.welcome"/> ${loggedUser.firstName} ${loggedUser.lastName} &nbsp;&nbsp; <a href="<c:url value='/profile/logout.htm' />" ><fmt:message key="logout"/></a></div>
        
    </div>
</header>

