<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/navbar.jsp"%>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="i18n.repairService" />
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="application.updateMasterApplication.title" /></title>
</head>
<body>
	<h2><fmt:message key="application.updateMasterApplication.top" /></h2>
	<form class="form-horizontal" method="POST" action="${pageContext.request.contextPath}/pages/updateMasterApplication">
   		<input type="hidden" name="applicationId" value="${applicationId}">                                      	
   		<div class="container">
   			 	<label class="radio-inline"><input type="radio" name="status" value="<fmt:message key="application.updateMasterApplication.todo" />"></label>
				<label class="radio-inline"><input type="radio" name="status" value="<fmt:message key="application.updateMasterApplication.done" />"></label>
		</div>
   		
		 <div class="form-group">        
      		<div class="col-sm-offset-2 col-sm-10">
       			 <button type="submit" class="btn btn-default"><fmt:message key="application.updateMasterApplication.btnAdd" /></button>
     		</div>
   		</div>
	</form>
</body>
</html>