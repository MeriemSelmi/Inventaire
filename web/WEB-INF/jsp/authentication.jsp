<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

    <head>
        <link type="text/css" rel="stylesheet" href="inc/style.css" />
        <title><fmt:message key="title"/></title>
    </head>

    <style>
        .error{
            color: #900;
        }
    </style>

    <body>
        <fieldset>
            <legend>Authenticate</legend>
            
            <form:form method="post" commandName="authentication">    
                <label>Login : </label>
                <form:input path="login"/>
                <form:errors path="login" cssClass="error"/><br>

                <label>Password : </label>
                <form:input path="password"/>
                <form:errors path="password" cssClass="error"/><br>

                <input type="submit" value="Authenticate" class="sansLabel">
                <input type="reset" value="Reset">

            </form:form>
        </fieldset>
    </body>
</html>