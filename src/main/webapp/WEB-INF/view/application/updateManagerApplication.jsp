<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/navbar.jsp"%>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="i18n.repairService" />
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="application.updateManagerApplication.title" /></title>
</head>
<body>
	<h2><fmt:message key="application.updateManagerApplication.top" /></h2>
	<form class="form-horizontal" method="POST" action="${pageContext.request.contextPath}/pages/updateManagerApplication">
		<input type="hidden" name="applicationId" value="${applicationId}">                                      	
		<div class="form-group">
		<label class="control-label col-sm-2" for="master"><fmt:message key="application.updateManagerApplication.master" />:</label>
     		  <div class="col-sm-6">		  
     			  <select class = "form-control" id="master" name="master">
     			 	 <c:forEach items="${requestScope.masterList}" var="master">
     			 	 	<option value="${master.id}">${master.surname} ${master.name} ${master.secondName}</option>
     			 	 </c:forEach>
				</select>
			</div>
   		</div>
   		<div class="form-group">
     		 <label class="control-label col-sm-2" for=price><fmt:message key="application.updateManagerApplication.price" />:</label>
     		  <div class="col-sm-6">
     		 	<input type="text" class="form-control" id="price" name="price">
     		 </div>
   		</div>
   		<div class="form-group">
     		 <label class="control-label col-sm-2" for=serviceComment><fmt:message key="application.updateManagerApplication.serviceComment" />:</label>
     		  <div class="col-sm-6">
     		   	<textarea name="serviceComment" rows="5" cols="70"></textarea>
     		 </div>
   		</div>
   		<div class="container">
   			 	<label class="radio-inline"><input type="radio" name="status" value="INPROGRESS"><fmt:message key="application.updateManagerApplication.inprogress" /></label>
				<label class="radio-inline"><input type="radio" name="status" value="CANCELED"><fmt:message key="application.updateManagerApplication.canceled" /></label>
		</div>
   		
		 <div class="form-group">        
      		<div class="col-sm-offset-2 col-sm-10">
       			 <button type="submit" class="btn btn-default"><fmt:message key="application.updateManagerApplication.btnAdd" /></button>
     		</div>
   		</div>
	</form>
</body>
</html>