<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/navbar.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add review</title>
</head>
<body>
	<h2>Add review</h2>
	<form class="form-horizontal" method="POST" action="${pageContext.request.contextPath}/pages/addReview">
		<div class="form-group">
     		 <label class="control-label col-sm-2" for=description>Description:</label>
     		  <div class="col-sm-6">
     		 	<input type="text" class="form-control" id="description" name="description">
     		 </div>
   		</div>
		 <div class="form-group">        
      		<div class="col-sm-offset-2 col-sm-10">
       			 <button type="submit" class="btn btn-default">Добавить</button>
     		</div>
   		</div>
	</form>
</body>
</html>