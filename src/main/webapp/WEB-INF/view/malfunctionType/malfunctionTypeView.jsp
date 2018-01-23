<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/WEB-INF/view/navbar.jsp"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="subscriptionView.label.header" /></title>
</head>
<body>
<div class="container">
		<h2>Malfunction View</h2>
		<table class="table">
			<thead>
				<tr>
					<th>Type</th>
					<th>Repair day</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${sessionScope.malfunctionTypeList}" var="malfunctionType">
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