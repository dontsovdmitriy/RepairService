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
<title><fmt:message key="review.reviewView.title" /></title>
</head>
<body>
<div class="container">
	<div class="container">
		<h2><fmt:message key="review.reviewView.top" /></h2>
		<table class="table">
			<thead>
				<tr>
					<th><fmt:message key="review.reviewView.client" /></th>
					<th><fmt:message key="review.reviewView.description" /></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requestScope.reviewList}" var="review">
					<tr>
						<td>${review.getClient().getUsername()}</td>
						<td>${review.getDescription()}</td>
						
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
                <form class="pagination" action="${pageContext.request.contextPath}/pages/allReviews" method="POST">
                    <input type="hidden" name="offset" value="${offset}">                                       
                   	<input type="submit" name="submit" value="<fmt:message key="review.reviewView.btnPrevious" />">
                    <input type="submit" name="submit" value="<fmt:message key="review.reviewView.btnNext" />">
                </form>
	</div>
</body>
</html>