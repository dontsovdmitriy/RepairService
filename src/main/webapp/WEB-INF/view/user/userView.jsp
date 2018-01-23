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
		<h2>User View</h2>
		<table class="table">
			<thead>
				<tr>
					<th>Surname</th>
					<th>Name</th>
					<th>Second name</th>
					<th>Email</th>
					<th>Phone</th>
					<th>Username</th>
					<th>Role</th>
			
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${sessionScope.userList}" var="user">
					<tr>
						<td>${user.getSurname()}</td>
						<td>${user.getName()}</td>
						<td>${user.getSecondName()}</td>
						<td>${user.getEmail()}</td>
						<td>${user.getPhone()}</td>
						<td>${user.getUsername()}</td>
						<td>${user.getRole()}</td>					
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>