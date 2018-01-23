<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/navbar.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add User</title>
</head>
<body>
	<h2>Add User</h2>
	<form class="form-horizontal" method="POST" action="${pageContext.request.contextPath}/pages/addUser">
		<div class="form-group">
     		 <label class="control-label col-sm-2" for=surname>Surname:</label>
     		  <div class="col-sm-6">
     		 	<input type="text" class="form-control" id="surname" name="surname">
     		 </div>
   		</div>
   		<div class="form-group">
     		 <label class="control-label col-sm-2" for=name>Name:</label>
     		  <div class="col-sm-6">
     		 	<input type="text" class="form-control" id="name" name="name">
     		 </div>
   		</div>
   		<div class="form-group">
     		 <label class="control-label col-sm-2" for=secondName>Second name:</label>
     		  <div class="col-sm-6">
     		 	<input type="text" class="form-control" id="secondName" name="secondName">
     		 </div>
   		</div>
   		<div class="form-group">
     		 <label class="control-label col-sm-2" for=email>Email:</label>
     		  <div class="col-sm-6">
     		 	<input type="text" class="form-control" id="email" name="email">
     		 </div>
   		</div>
   		<div class="form-group">
     		 <label class="control-label col-sm-2" for=phone>Phone:</label>
     		  <div class="col-sm-6">
     		 	<input type="text" class="form-control" id="phone" name="phone">
     		 </div>
   		</div>
   		<div class="form-group">
     		 <label class="control-label col-sm-2" for=username>Username:</label>
     		  <div class="col-sm-6">
     		 	<input type="text" class="form-control" id="username" name="username">
     		 </div>
   		</div>
   		<div class="form-group">
     		 <label class="control-label col-sm-2" for=password>Password:</label>
     		  <div class="col-sm-6">
     		 	<input type="password" class="form-control" id="password" name="password">
     		 </div>
   		</div>
   		<div class="form-group">
     		 <label class="control-label col-sm-2" for="passwordConfirm">Password confirm:</label>
     		  <div class="col-sm-6">
     		 	<input type="password" class="form-control" id="passwordConfirm" name="passwordConfirm">
     		 </div>
   		</div>
   		
		 <div class="form-group">        
      		<div class="col-sm-offset-2 col-sm-10">
       			 <button type="submit" class="btn btn-default">Add User</button>
     		</div>
   		</div>
	</form>
</body>
</html>