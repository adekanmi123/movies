<%@include file="top_back.jsp" %>
<div class="leftbox">
    <h3>${movie.title}</h3>
    <img src="../resources/${movie.image}" width="93" height="95" alt="photo 1" class="left" />
    <p><b>Price:</b> <b>$${movie.price}</b> </p>
    <p><b>Availability:</b> Usually ships within 24 hours</p>          
    <div class="clear"></div>
    <form method="post" action="../addtocart">        
        <input type="number" name="quantity" value="1" min="1"/> <br/>       
        <input type="submit" value="Add to cart"/>
        <input type="hidden" name="id" value="${movie.id}"/>
    </form>
</div>    
<div class="clear"></div>    
        
<%@include file="bottom.jsp" %>
      
