<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/navbar.jsp"%>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="i18n.repairService" />
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="review.deleteReview.title" /></title>
</head>
<body>
	<h2><fmt:message key="review.deleteReview.top" /></h2>
	<form class="form-horizontal" method="POST" action="${pageContext.request.contextPath}/pages/deleteReview">
		<div class="col-sm-6">		  
     			 <select class = "form-control" id="review" name="review">
     			 	 <c:forEach items="${requestScope.reviewList}" var="review">
     			 	 	<option value="${review.id}">${review.description}</option>
     			 	 </c:forEach>
				</select>
     		 </div>
		 <div class="form-group">        
      		<div class="col-sm-offset-2 col-sm-10">
       			 <button type="submit" class="btn btn-default"><fmt:message key="review.deleteReview.btnDelete" /></button>
     		</div>
   		</div>
	</form>
</body>
</html>