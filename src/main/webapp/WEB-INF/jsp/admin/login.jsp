<%-- 
    Document   : login
    Created on : Jan 25, 2017, 1:42:47 PM
    Author     : Nebojsa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form method="post" action="adminconsole">
            Username: <input type="text" name="username"/> <br/>
            Password: <input type="password" name="password"/> <br/>
            <input type="submit" value="Log in"/>
        </form>
    </body>
</html>
