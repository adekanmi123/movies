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
            <tr><td>id</td><td>name</td><td>movies</td><td>price</td><td>address</td><td>city</td><td>postal code</td><td>order time</td><td>delivered</td></tr>
            <c:forEach items="${orders}" var="order">
                <tr><td>${order.id}</td><td>${order.user.firstName} ${order.user.lastName}</td>
                    <td>
                        <c:forEach items="${order.movieList}" var="movie">
                            ${movie.title} - quantity: ${movie.quantity} <br/>
                        </c:forEach> 
                    </td>
                    <td>$${order.price}</td><td>${order.user.address}</td><td>${order.user.city}</td><td>${order.user.postalCode}</td><td>${order.ordertime}</td>
                        <c:set var="delivered">
                            <c:choose>
                                <c:when test="${order.delivered==0}">NO - <a href="deliver?id=${order.id}">deliver</a></c:when>
                                <c:when test="${order.delivered==1}">YES</c:when>
                            </c:choose>
                        </c:set>
                    <td>${delivered}</td></tr>
            
            </c:forEach>
            <tr>
                <td colspan="9">
                    <c:forEach begin="0" end="${totalpages}" varStatus="counter">
                        <a href="orders?page=${counter.count}">${counter.count}</a>
                    </c:forEach>
                </td>
            </tr>
        </table>
    </body>
</html>
