<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/navbar.jsp"%>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="i18n.repairService" />
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="application.selectApplicationManager.title" /></title>
</head>
<body>
	<h2><fmt:message key="application.selectApplicationManager.top" /></h2>
	<form class="form-horizontal" method="POST" action="${pageContext.request.contextPath}/pages/showUpdateManagerApplication">
		<div class="col-sm-6">		  
     			 <select class = "form-control" id="application" name="application">
     			 	 <c:forEach items="${requestScope.applicationManagerList}" var="application">
     			 	 	<option value="${application.id}"><fmt:message key="application.selectApplicationManager.malfunctionType" />: ${application.malfunctionType.type}, <fmt:message key="application.selectApplicationManager.description" />: ${application.description}, <fmt:message key="application.selectApplicationManager.client" />: ${application.getClient().getSurname()}${application.getClient().getName()}, <fmt:message key="application.selectApplicationManager.creationDate" />: ${application.getCreationDate()}</option>
     			 	 </c:forEach>
				</select>
     		 </div>
		 <div class="form-group">        
      		<div class="col-sm-offset-2 col-sm-10">
       			 <button type="submit" class="btn btn-default"><fmt:message key="application.selectApplicationManager.btnOk" /></button>
     		</div>
   		</div>
	</form>
</body>
</html>