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
        <form method="post" action="updatedcategory">
            <select onchange="if(this.value!==-1) window.location='./updatecategory?id='+ this.value" name="id">
                <option value="-1">Select category</option>
                <c:forEach items="${genres}" var="genre">
                    <option <c:if test="${selectedcategory.id==genre.id}">selected</c:if> value="${genre.id}">${genre.name}</option>
                </c:forEach>
            </select> <br/> <br/>
            Name: <input type="text" name="name" value="${selectedcategory.name}"/> <br/>
            <input type="submit" name="update" value="Update"/>
        </form>
    </body>
</html>
