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
        <form method="post" action="addedmember">
            First name: <input type="text" name="firstName"/><br/>
            Last name: <input type="text" name="lastName"/><br/>
            Address: <textarea name="address"></textarea><br/>
            City: <input type="text" name="city"/><br/>
            Postal code: <input type="number" name="postalCode"/><br/>
            Email: <input type="email" name="email"/> <br/>
            <input type="submit" value="Insert"/>
        </form>
    </body>
</html>
