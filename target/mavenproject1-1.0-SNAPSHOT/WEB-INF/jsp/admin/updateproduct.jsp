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
        <form method="post" action="updatedproduct" enctype="multipart/form-data">
            <input type="hidden" name="id" value="${selectedMovie.id}"/>
            Title: <input type="text" name="title" value="${selectedMovie.title}"/> <br/>
            Genre: <select name="genre"> 
                <c:forEach items="${genres}" var="genre">
                    <option <c:if test="${selectedMovie.genre.id==genre.id}">selected</c:if> value="${genre.id}" >${genre.name}</option>
                </c:forEach>
            </select> <br/>
            Price: <input type="text" name="price" value="${selectedMovie.price}"/> <br/>
            Image: <br/>
            <img src="../resources/${selectedMovie.image}" alt="photo" width="180" height="200"/> <br/>
            Change image: <input type="file" name="image" accept="images/*" /> <br/>
            <input type="checkbox" name="check" value="1" <c:if test="${selectedMovie.onstock==1}">checked="checked"</c:if>/> Don't have this movie <br/> <br/>
            <input type="submit" value="Update"/> 
        </form>
    </body>
</html>
