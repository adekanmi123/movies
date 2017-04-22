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
            <tr><td>id</td><td>title</td><td>genre</td><td>price</td></tr>
            <c:forEach items="${movies}" var="movie">
                <tr>
                    <td>${movie.id}</td>
                    <td><a href="updateproduct?id=${movie.id}">${movie.title}</a></td>
                    <td>${movie.genre.name}</td>
                    <td>${movie.price}</td>
                </tr>
            </c:forEach>
                <tr><td colspan="4">
                        <c:forEach begin="0" end="${totalpages}" varStatus="counter">
                            <a href="allproducts?page=${counter.count}">${counter.count}</a>
                        </c:forEach>
                            
                    </td>
                </tr>
        </table>
    </body>
</html>
