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
					<th>Client</th>
					<th>Review</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${sessionScope.reviewList}" var="review">
					<tr>
						<td>${review.getDescription()}</td>
						<td>${review.getClient().getUsername()}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>