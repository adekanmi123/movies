<%-- 
    Document   : updatemember
    Created on : Dec 22, 2016, 1:21:08 PM
    Author     : Nebojsa
--%>

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
        <form method="post" action="updatedmember">
            <input type="hidden" name="id" value="${selectedMember.id}"/>        
            First name: <input type="text" name="firstName" value="${selectedMember.firstName}"/><br/>
            Last name: <input type="text" name="lastName" value="${selectedMember.lastName}"/><br/>
            Address: <textarea name="address">${selectedMember.address}</textarea><br/>
            City: <input type="text" name="city" value="${selectedMember.city}"/><br/>
            Postal code: <input type="text" name="postalCode" value="${selectedMember.postalCode}"/><br/>
            Email: <input type="text" name="email" value="${selectedMember.email}"/><br/>
            <input type="submit" value="Update"/>
        </form>
    </body>
</html>
