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
<title><fmt:message key="user.userView.title" /></title>
</head>
<body>
<div class="container"> 
	<div class="container">
		<h2><fmt:message key="user.userView.top" /></h2>
		<table class="table">
			<thead>
				<tr>
					<th><fmt:message key="user.userView.surname" /></th>
					<th><fmt:message key="user.userView.name" /></th>
					<th><fmt:message key="user.userView.secondName" /></th>
					<th><fmt:message key="user.userView.email" /></th>
					<th><fmt:message key="user.userView.phone" /></th>
					<th><fmt:message key="user.userView.username" /></th>
					<th><fmt:message key="user.userView.role" /></th>
			
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requestScope.userList}" var="user">
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
	 <c:choose>
            <c:when test="${empty requestScope.offset}">
                <c:set var="offset" value="0"/>
            </c:when>
            <c:otherwise>
                <c:set var="offset" value="${requestScope.offset}"/>
            </c:otherwise>
        </c:choose>            
                <form class="pagination" action="${pageContext.request.contextPath}/pages/allUsers" method="POST">
                    <input type="hidden" name="offset" value="${offset}">          
                   	<input type="submit" name="submit" value="<fmt:message key="user.userView.btnPrevious" />">
                    <input type="submit" name="submit" value="<fmt:message key="user.userView.btnNext" />">
                    
                </form>
	</div>
</body>
</html>