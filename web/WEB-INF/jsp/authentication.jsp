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

       <div id="content" align="center">
           <form:form method="post" commandName="authentication">
               <table align="center">
                   <th><div id="auth-title"><fmt:message key="title.authenticate"/> </div></th>
                    <tr> <td><form:errors path="error" cssClass="error" /> </td></tr>
               <tr> <td class="noDeco"><div>
                    <label id="auth-label"><fmt:message key="user.label.login"/>: </label>
                    <form:input path="login" placeholder="Username" id="username" />
                    <form:errors path="login" cssClass="error"/><br>                                   
                       </div></td></tr>
              <tr> <td class="noDeco"> <div>
                   <label id="auth-label"><fmt:message key="user.label.password"/>: </label>
                    <form:password path="password" placeholder="Password" id="password" />
                    <form:errors path="password" cssClass="error"/><br>
                      </div></td></tr>
              <tr>
                  <td align="center" class="noDeco"><input type="submit" value="<fmt:message key="button.authenticate"/>">
                      <input type="reset" value="<fmt:message key="button.reset" />" ></td>

               </tr>
               </table>
           </form:form>

       </div>

        
    </body>
</html>