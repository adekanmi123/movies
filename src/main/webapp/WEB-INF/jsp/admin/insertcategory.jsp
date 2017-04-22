<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin console</title>
    </head>
    <body>
        <%@include file="menu.jsp" %>
        <br/>
        <form method="post" action="addedcategory">
            Name: <input type="text" name="name"/><br/>
            <input type="submit" value="Insert"/>
        </form>
    </body>
</html>
