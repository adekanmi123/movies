<%@include file="top.jsp" %>
<br/><a href="confirm">CONFIRM YOUR ORDER</a><br/>
<c:forEach items="${movies}" var="movie">
    <div class="leftbox">
        <h3>${movie.title}</h3>
        <img src="resources/${movie.image}" width="93" height="95" alt="photo 1" class="left" />
        <p><b>Price:</b> <b>$${movie.price}</b></p>
        <p><b>Availability:</b> Usually ships within 24 hours</p>          
        <div class="clear"></div>
        <p><b>Quantity:</b> ${movie.quantity}</p>
        <form method="post" action="remove"> 
            <input type="submit" value="Remove from cart"/>
            <input type="hidden" name="id" value="${movie.id}"/>
        </form>
    </div>    
    <div class="clear"></div>
</c:forEach>
    
  <br/>  <b>Order price:</b> ${price}
<br/><br/><a href="confirm">CONFIRM YOUR ORDER</a><br/>        
<%@include file="bottom.jsp" %>
      
