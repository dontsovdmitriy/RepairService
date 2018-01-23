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
		<h2>Review View</h2>
		<table class="table">
			<thead>
				<tr>
					<th>Creation date</th>
					<th>Malfunction type</th>
					<th>Description</th>
					<th>Client</th>
					<th>Manager</th>
					<th>Master</th>
					<th>Price</th>
					<th>Service comment</th>
					<th>Status</th>
					<th>Completion date</th>
					
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${sessionScope.applicationList}" var="application">
					<tr>
						<td>${application.getCreationDate()}</td>
						<td>${application.getMalfunctionType().getType()}</td>
						<td>${application.getDescription()}</td>
						<td>${application.getClient().getSurname()} ${application.getClient().getName()}</td>
						<td>${application.getManager().getSurname()} ${application.getManager().getName()}</td>
						<td>${application.getMaster().getSurname()} ${application.getMaster().getName()}</td>
						<td>${application.getPrice()}</td>
						<td>${application.getServiceComment()}</td>
						<td>${application.getStatus()}</td>
						<td>${application.getCompletionDate()}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>