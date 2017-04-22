<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin console</title>
    </head>
    <body>
        <%@include file="menu.jsp" %>
        <br/>Order with id:<b>${order.id}</b> is successfully delivered to <b>${order.user.firstName} ${order.user.lastName}</b>.
    </body>
</html>
