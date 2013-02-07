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
            <legend><fmt:message key="title.authenticate"/> </legend>
            
            <form:form method="post" commandName="authentication"> 
                <form:errors path="error" cssClass="error" /> <br>
                
                <label><fmt:message key="user.label.login"/> : </label>
                <form:input path="login"/>
                <form:errors path="login" cssClass="error"/><br>

                <label><fmt:message key="user.label.password"/> : </label>
                <form:input path="password"/>
                <form:errors path="password" cssClass="error"/><br>

                <input type="submit" value="<fmt:message key="button.authenticate"/>" class="sansLabel">
                <input type="reset" value="<fmt:message key="button.reset"/>">

            </form:form>
        </fieldset>
    </body>
</html>