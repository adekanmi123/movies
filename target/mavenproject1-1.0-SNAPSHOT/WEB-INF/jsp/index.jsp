<%@include file="top.jsp" %>

<c:forEach items="${movies}" var="movie" varStatus="counter">
    <div class="${counter.count%2==0?"rightbox":"leftbox"}">
        <h3>${movie.title}</h3>
        <img src="resources/${movie.image}" width="95" height="95" alt="photo 1" class="left" />
        <p><b>Price:</b> <b>$${movie.price}</b> </p>
        <p class="readmore"><a href="tocart/${movie.id}">BUY <b>NOW</b></a></p>
        <div class="clear"></div>
    </div>
</c:forEach>
        
<%@include file="bottom.jsp" %>
      
