<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/navbar.jsp"%>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="i18n.repairService" />
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="application.selectApplicationMaster.title" /></title>
</head>
<body>
	<h2><fmt:message key="application.selectApplicationMaster.top" /></h2>
	<form class="form-horizontal" method="POST" action="${pageContext.request.contextPath}/pages/showUpdateMasterApplication">
		<div class="col-sm-6">		  
     			 <select class = "form-control" id="application" name="application">
     			 	 <c:forEach items="${requestScope.applicationMasterList}" var="application">
     			 	 	<option value="${application.id}"><fmt:message key="application.selectApplicationMaster.malfunctionType" />: ${application.malfunctionType.type}, <fmt:message key="application.selectApplicationMaster.description" />: ${application.description}, <fmt:message key="application.selectApplicationMaster.client" />: ${application.getClient().getSurname()}${application.getClient().getName()}, <fmt:message key="application.selectApplicationMaster.creationDate" />: ${application.getCreationDate()}</option>
     			 	 </c:forEach>
				</select>
     		 </div>
		 <div class="form-group">        
      		<div class="col-sm-offset-2 col-sm-10">
       			 <button type="submit" class="btn btn-default"><fmt:message key="application.selectApplicationMaster.btnOk" /></button>
     		</div>
   		</div>
	</form>
</body>
</html>