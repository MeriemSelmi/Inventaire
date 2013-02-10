<header>
    <div  id="head">
        <table>
            <caption> <span style="float:left;"><div>${loggedUser.role} Area  &nbsp;&nbsp; <a href="?language=en">En</a>|<a href="?language=fr">Fr</a></div></span></caption>
            <caption><span style="float:left;"><div><fmt:message key="title.welcome"/> ${loggedUser.firstName} ${loggedUser.lastName}</div></span>
                <span style="float:right;"><div style="display: inline-block;"><a href="<c:url value='/welcome.htm' />" ><div id="home"></div></a></div><div style="display: inline-block;"><a href="<c:url value='/profile/logout.htm' />" ><div id="logout" ></div></a></div></span></caption>
        </table> 
    </div>
</header>

