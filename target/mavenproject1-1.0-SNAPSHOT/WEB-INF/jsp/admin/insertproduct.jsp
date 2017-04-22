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
        <form method="post" action="addedproduct" enctype="multipart/form-data">
            Title: <input type="text" name="title" /> <br/>
            Genre: <select name="genre"> 
                <c:forEach items="${genres}" var="genre">
                    <option value="${genre.id}">${genre.name}</option>
                </c:forEach>
            </select> <br/>
            Price: <input type="text" name="price"/> <br/>
            Image: <input type="file" name="image" /> <br/>
            <input type="submit" value="Insert"/> 
        </form>
    </body>
</html>
