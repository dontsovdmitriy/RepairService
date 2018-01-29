<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/navbar.jsp"%>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="i18n.repairService" />
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="application.deleteApplication.title" /></title>
</head>
<body>
	<h2><fmt:message key="application.deleteApplication.top" /></h2>
	<form class="form-horizontal" method="POST" action="${pageContext.request.contextPath}/pages/deleteApplication">
		<div class="col-sm-6">		  
     			 <select class = "form-control" id="application" name="application">
     			 	 <c:forEach items="${requestScope.applicationList}" var="application">
     			 	 	<option value="${application.id}">${application.malfunctionType.type} ${application.description} ${application.getClient().getSurname()}${application.getClient().getName()} ${application.getCreationDate()}</option>
     			 	 </c:forEach>
				</select>
     		 </div>
		 <div class="form-group">        
      		<div class="col-sm-offset-2 col-sm-10">
       			 <button type="submit" class="btn btn-default"><fmt:message key="application.deleteApplication.btnDelete" /></button>
     		</div>
   		</div>
	</form>
</body>
</html>