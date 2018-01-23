<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/WEB-INF/view/navbar.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Start page</title>
</head>
<body>
	<c:choose>
	
	  <c:when test="${not empty sessionScope.user}">
	  	<h1>Start page</h1>
	  </c:when>
	  <c:otherwise>  
	  	  	<h1>Username or password incorect</h1>    
	  </c:otherwise>        
	 </c:choose>
	
</body>
</html>