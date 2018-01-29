<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/navbar.jsp"%>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="i18n.repairService" />
<!DOCTYPE html>
<html>
<head>
<title><fmt:message key="user.addUser.title" /></title>
</head>
<body>
	<h2><fmt:message key="user.addUser.top" /></h2>
	<form class="form-horizontal" method="POST" action="${pageContext.request.contextPath}/pages/addUser">
		<div class="form-group">
     		 <label class="control-label col-sm-2" for=surname><fmt:message key="user.addUser.surname" />:</label>
     		  <div class="col-sm-6">
     		 	<input type="text" class="form-control" id="surname" name="surname">
     		 </div>
   		</div>
   		<div class="form-group">
     		 <label class="control-label col-sm-2" for=name><fmt:message key="user.addUser.name" />:</label>
     		  <div class="col-sm-6">
     		 	<input type="text" class="form-control" id="name" name="name">
     		 </div>
   		</div>
   		<div class="form-group">
     		 <label class="control-label col-sm-2" for=secondName><fmt:message key="user.addUser.secondName" />:</label>
     		  <div class="col-sm-6">
     		 	<input type="text" class="form-control" id="secondName" name="secondName">
     		 </div>
   		</div>
   		<div class="form-group">
     		 <label class="control-label col-sm-2" for=email><fmt:message key="user.addUser.email" />:</label>
     		  <div class="col-sm-6">
     		 	<input type="email" class="form-control" id="email" name="email">
     		 </div>
   		</div>
   		<div class="form-group">
     		 <label class="control-label col-sm-2" for=phone><fmt:message key="user.addUser.phone" />:</label>
     		  <div class="col-sm-6">
     		 	<input type="text" class="form-control" id="phone" name="phone">
     		 </div>
   		</div>
   		<div class="form-group">
     		 <label class="control-label col-sm-2" for=username><fmt:message key="user.addUser.username" />:</label>
     		  <div class="col-sm-6">
     		 	<input type="text" class="form-control" id="username" name="username">
     		 </div>
   		</div>
   		<div class="form-group">
     		 <label class="control-label col-sm-2" for=password><fmt:message key="user.addUser.password" />:</label>
     		  <div class="col-sm-6">
     		 	<input type="password" class="form-control" id="password" name="password">
     		 </div>
   		</div>
   		<div class="form-group">
     		 <label class="control-label col-sm-2" for="passwordConfirm"><fmt:message key="user.addUser.passwordConfirm" />:</label>
     		  <div class="col-sm-6">
     		 	<input type="password" class="form-control" id="passwordConfirm" name="passwordConfirm">
     		 </div>
   		</div>
   		
		 <div class="form-group">        
      		<div class="col-sm-offset-2 col-sm-10">
       			 <button type="submit" class="btn btn-default"><fmt:message key="user.addUser.btnAdd" /></button>
     		</div>
   		</div>
	</form>
</body>
</html>