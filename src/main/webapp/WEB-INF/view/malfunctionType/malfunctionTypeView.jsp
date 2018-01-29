<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/WEB-INF/view/navbar.jsp"%>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="i18n.repairService" />
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="malfunctionType.malfunctionTypeView.title" /></title>
</head>
<body>
<div class="container">
		<h2><fmt:message key="malfunctionType.malfunctionTypeView.top" /></h2>
		<table class="table">
			<thead>
				<tr>
					<th><fmt:message key="malfunctionType.malfunctionTypeView.type" /></th>
					<th><fmt:message key="malfunctionType.malfunctionTypeView.repairDay" /></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requestScope.malfunctionTypeList}" var="malfunctionType">
					<tr>
						<td>${malfunctionType.getType()}</td>
						<td>${malfunctionType.getRepairDay()}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>