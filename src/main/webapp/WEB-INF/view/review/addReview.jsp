<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/navbar.jsp"%>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="i18n.repairService" />
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="review.addReview.title" /></title>
</head>
<body>
	<h2><fmt:message key="review.addReview.top" /></h2>
	<form class="form-horizontal" method="POST" action="${pageContext.request.contextPath}/pages/addReview">
		<div class="form-group">
     		 <label class="control-label col-sm-2" for=description><fmt:message key="review.addReview.description" />:</label>
     		  <div class="col-sm-6">
     		   	<textarea name="description" rows="5" cols="70"></textarea>
     		 </div>
   		</div>
		 <div class="form-group">        
      		<div class="col-sm-offset-2 col-sm-10">
       			 <button type="submit" class="btn btn-default" > <fmt:message key="review.addReview.btnAdd" /></button>
     		</div>
   		</div>
	</form>
</body>
</html>