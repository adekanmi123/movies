<%@include file="top.jsp" %>
<b>Confirm your order:</b> 
<form method="post" action="confirm">
    First name: <input type="text" name="firstName"/><br/>
    Last name: <input type="text" name="lastName"/><br/>
    Address: <textarea name="address"></textarea><br/>
    City: <input type="text" name="city"/><br/>
    Postal code: <input type="text" name="postalCode"/><br/>
    Email: <input type="email" name="email"/> <br/>
    <input type="submit" value="Confirm"/>
</form>
<%@include file="bottom.jsp" %>
      
