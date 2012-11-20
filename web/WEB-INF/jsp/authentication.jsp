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
                <table width="95%" border="0" cellspacing="0" cellpadding="5">
                    <tr>
                        <td align="right" width="20%">Login : </td>
                        <td width="80%">
                            <form:input path="login"/>
                            <form:errors path="login" cssClass="error"/>
                        </td>
                    </tr>
                    
                    <tr>
                        <td align="right" width="20%">Password : </td>
                        <td width="80%">
                            <form:input path="pass"/>
                            <form:errors path="pass" cssClass="error"/>
                        </td>
                    </tr>
                    
                    <tr>
                        <td></td>
                        <td></td>
                    </tr>
                    
                    <tr>
                        <td></td>
                        <td><input type="submit" value="Authenticate"><input type="reset" value="Reset"></td>
                    </tr>
                    
                </table>

            </form:form>
        </fieldset>
    </body>
</html>