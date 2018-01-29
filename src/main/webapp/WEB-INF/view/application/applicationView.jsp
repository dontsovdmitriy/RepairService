<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
<%@include file="/WEB-INF/view/navbar.jsp"%>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="i18n.repairService" />
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="application.applicationView.title" /></title>
</head>
<body>
<div class="container"> 
	<div class="container">
		<h2><fmt:message key="application.applicationView.top" /></h2>
		<table class="table">
			<thead>
				<tr>
					<th><fmt:message key="application.applicationView.creationDate" /></th>
					<th><fmt:message key="application.applicationView.malfunctionType" /></th>
					<th><fmt:message key="application.applicationView.description" /></th>
					<th><fmt:message key="application.applicationView.client"/></th>
					<th><fmt:message key="application.applicationView.manager" /></th>
					<th><fmt:message key="application.applicationView.master" /></th>
					<th><fmt:message key="application.applicationView.price" /></th>
					<th><fmt:message key="application.applicationView.serviceComment" /></th>
					<th><fmt:message key="application.applicationView.status" /></th>
					<th><fmt:message key="application.applicationView.completionDate" /></th>					
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requestScope.applicationList}" var="application">
					<tr>
						<td>${application.getCreationDate()}</td>
						<td>${application.getMalfunctionType().getType()}</td>
						<td>${application.getDescription()}</td>
						<td>${application.getClient().getSurname()} ${application.getClient().getName()}</td>
						<td>${application.getManager().getSurname()} ${application.getManager().getName()}</td>
						<td>${application.getMaster().getSurname()} ${application.getMaster().getName()}</td>
						<td><ctg:cost-format cost="${application.getPrice()}"/></td>
						<td>${application.getServiceComment()}</td>
						<td>${application.getStatus()}</td>
						<td>${application.getCompletionDate()}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	        <c:choose>
            <c:when test="${empty requestScope.offset}">
                <c:set var="offset" value="0"/>
            </c:when>
            <c:otherwise>
                <c:set var="offset" value="${requestScope.offset}"/>
            </c:otherwise>
        </c:choose>
        <c:choose>
        	<c:when test="${empty requestScope.fromMyApp}">       	
                <form class="pagination" action="${pageContext.request.contextPath}/pages/allApplications" method="POST">
                    <input type="hidden" name="offset" value="${offset}">                                       
                   		 <input type="submit" name="submit" value="<fmt:message key="application.applicationView.btnPrevious" />">
                    <input type="submit" name="submit" value="<fmt:message key="application.applicationView.btnNext" />">
                </form>
            </c:when>
            <c:otherwise>
            	<form class="pagination" action="${pageContext.request.contextPath}/pages/myApplications" method="POST">
                    <input type="hidden" name="offset" value="${offset}">                                       
                   		 <input type="submit" name="submit" value="<fmt:message key="application.applicationView.btnPrevious" />">
                    <input type="submit" name="submit" value="<fmt:message key="application.applicationView.btnNext" />">
                </form>
            </c:otherwise>
         </c:choose>
        
          
</div>
</body>
</html>