<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/navbar.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Delete review</title>
</head>
<body>
	<h2>Delete review</h2>
	<form class="form-horizontal" method="POST" action="${pageContext.request.contextPath}/pages/deleteReview">
		<div class="col-sm-6">		  
     			 <select class = "form-control" id="review" name="review">
     			 	 <c:forEach items="${sessionScope.reviewList}" var="review">
     			 	 	<option value="${review.id}">${review.description}</option>
     			 	 </c:forEach>
				</select>
     		 </div>
		 <div class="form-group">        
      		<div class="col-sm-offset-2 col-sm-10">
       			 <button type="submit" class="btn btn-default">Delete</button>
     		</div>
   		</div>
	</form>
</body>
</html>