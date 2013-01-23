<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

    <head>
        <link type="text/css" rel="stylesheet" href="<c:url value="/inc/style.css"/>" />
        <link type="text/css" rel="stylesheet" href="<c:url value="/inc/jquery-ui.css"/>" />
        <title><fmt:message key="title"/></title>
    </head>

    <body>
        <%@ include file="/WEB-INF/jsp/header.jsp" %>

        <table id="global">
            <caption><div class="welcome">Profile Manager</div></caption>

            <tr>
                <td>
                    <div>                                              
                        <table width="70%">
                            <tr>
                                <td>
                                    <div>
                                        <div class="productDiv">
                                            <div >
                                                <table class="productInfoTable">
                                                    <tr><td class="champ">ID :</td><td class="text">${loggedUser.id}</td></tr>
                                                    <tr><td class="champ">First Name :</td><td class="text">${loggedUser.firstName} </td></tr>
                                                    <tr><td class="champ">Last Name :</td><td class="text">${loggedUser.lastName} </td></tr>
                                                    <tr><td class="champ">Email :</td><td class="text">${loggedUser.email} </td></tr>
                                                    <tr><td class="champ">Telephone :</td><td class="text">${loggedUser.telephone}</td></tr>
                                                    <tr><td class="champ">Address :</td> <td class="text">${loggedUser.address}</td></tr>
                                                    <tr><td class="champ">Login :</td> <td class="text">${loggedUser.login}</td></tr>
                                                    <tr><td class="champ">Role :</td> <td class="text">${loggedUser.role}</td></tr>
                                                </table>
                                                <table >
                                                    <tr>
                                                        <td align="center"><div class="button" onclick="showForm('${loggedUser.id}');" id="updateButton">Update</div></td>
                                                    </tr>
                                                </table>
                                            </div>

                                            <div id="${loggedUser.id}" class="ui-widget-content ui-corner-all" hidden="hidden">
                                                <table><tr><td class="noDeco"> <p class="title">Please enter new user informations.</p> </td><td class="noDeco"><div align="right" id="hideButton1" onclick="runHideEffect('${loggedUser.id}')"></div></td></tr></table>

                                                <form:form method="post" action="profileupdate.htm" commandName="profileupdate" acceptCharset="UTF-8" >
                                                    <form:errors path="error"/>
                                                    <table>
                                                        <tr><input type="text" hidden="true" name="id" value="${loggedUser.id}" /></tr>
                                                        <tr><td><form:label path="firstName">First Name : </form:label></td>
                                                            <td><form:input path="firstName" class="textareas"  value="${loggedUser.firstName}" onclick="viderChamps(this)" /> 
                                                                <form:errors path="firstName"/></td></tr>
                                                        <tr><td><form:label path="lastName">Last Name : </form:label></td>
                                                            <td><form:input path="lastName" class="textareas"  value="${loggedUser.lastName}" onclick="viderChamps(this)" /> 
                                                                <form:errors path="lastName"/></td></tr>
                                                        <tr><td><form:label path="email">Email : </form:label></td>
                                                            <td><form:input path="email" class="textareas"  value="${loggedUser.email}" onclick="viderChamps(this)" /> 
                                                                <form:errors path="email"/></td></tr>
                                                        <tr><td><form:label path="telephone">Telephone : </form:label></td>
                                                            <td><form:input path="telephone" class="textareas"  value="${loggedUser.telephone}" onclick="viderChamps(this)" /> 
                                                                <form:errors path="telephone"/></td></tr>
                                                        <tr><td><form:label path="address">Address : </form:label></td>
                                                            <td><form:input path="address" class="textareas"  value="${loggedUser.address}" onclick="viderChamps(this)" /> 
                                                                <form:errors path="address"/></td></tr>
                                                        <tr><td><form:label path="login">Login : </form:label></td>
                                                            <td><form:input path="login" class="textareas"  value="${loggedUser.login}" onclick="viderChamps(this)" /> 
                                                                <form:errors path="login"/></td></tr>
                                                        <tr><td><form:label path="password">Password : </form:label></td>
                                                            <td><form:password path="password" class="textareas"  value="${loggedUser.password}" onclick="viderChamps(this)" /> 
                                                                <form:errors path="password"/></td></tr>
                                                        <tr><td><input type="submit" value="Validate" class="button"/></td></tr>
                                                    </table>
                                                </form:form>
                                            </div>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </div>
                </td>
            </tr>
        </table>

        <%@ include file="/WEB-INF/jsp/footer.jsp" %>

        <script src="<c:url value="/inc/jquery.js"/>"></script>
        <script src="<c:url value="/inc/jquery-ui.js"/>"></script>
        <script src="<c:url value="/inc/script.js"/>"></script>
    </body>
</html>
