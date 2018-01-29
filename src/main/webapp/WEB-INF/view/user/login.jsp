<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/navbar.jsp"%>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="i18n.repairService" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="user.login.title" /></title>
</head>
<body>
	<h2><fmt:message key="user.login.top" /></h2>
	<form class="form-horizontal" method="POST" action="${pageContext.request.contextPath}/pages/login">
   		<div class="form-group">
     		 <label class="control-label col-sm-2" for=username><fmt:message key="user.login.username" />:</label>
     		  <div class="col-sm-6">
     		 	<input type="text" class="form-control" id="username" name="username">
     		 </div>
   		</div>
   		<div class="form-group">
     		 <label class="control-label col-sm-2" for=password><fmt:message key="user.login.password" />:</label>
     		  <div class="col-sm-6">
     		 	<input type="password" class="form-control" id="password" name="password">
     		 </div>
   		</div>   		
		 <div class="form-group">        
      		<div class="col-sm-offset-2 col-sm-10">
       			 <button type="submit" class="btn btn-default"><fmt:message key="user.login.btnOk" /></button>
     		</div>
   		</div>
	</form>
</body>
</html>