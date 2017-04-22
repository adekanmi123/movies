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
        <table border="1">
            <tr><td>id</td><td>name</td><td>address</td><td>email</td></tr>
            <c:forEach items="${members}" var="member">
                <tr>
                    <td>${member.id}</td>
                    <td><a href="updatemember?id=${member.id}">${member.firstName} ${member.lastName}</a></td>
                    <td>${member.address}</td>
                    <td>${member.email}</td>
                </tr>
            </c:forEach>
                <tr><td colspan="4">
                        <c:forEach begin="0" end="${totalpages}" varStatus="counter">
                            <a href="allmembers?page=${counter.count}">${counter.count}</a>
                        </c:forEach>
                            
                    </td>
                </tr>
        </table>
    </body>
</html>
