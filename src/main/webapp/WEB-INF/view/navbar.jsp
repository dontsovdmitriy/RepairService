<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="language"
	value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
	scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="i18n.repairService" />
<!DOCTYPE html>
<html lang="${language}">
<head>
  <title><fmt:message key="navbar.title.name" /></title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
   <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
  <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
   <%-- <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script> --%>
   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand"><fmt:message key="navbar.title.name" /></a>
    </div>
    <div class="collapse navbar-collapse myNavbar">
      <ul class="nav navbar-nav">
        <c:if test="${not empty sessionScope.user}">   
        <li class="active"><a href="${pageContext.request.contextPath}/pages/showHome"><fmt:message key="navbar.title.home" /></a></li>
        <li class="dropdown">
          <a class="dropdown-toggle" data-toggle="dropdown" href="#"><fmt:message key="navbar.malfunctionType.top" /><span class="caret"></span></a>
          <ul class="dropdown-menu">
       		<li><a href="${pageContext.request.contextPath}/pages/allMalfunctionTypes"><fmt:message key="navbar.malfunctionType.allMalfTypes" /></a></li>	
    		<c:if test="${(sessionScope.user.role == 'MANAGER') || (sessionScope.user.role == 'ADMIN')}"> 	
    			<li><a href="${pageContext.request.contextPath}/pages/showAddMalfunctionType"><fmt:message key="navbar.malfunctionType.addMalfType" /></a></li>
    			<li><a href="${pageContext.request.contextPath}/pages/showDeleteMalfunctionType"><fmt:message key="navbar.malfunctionType.deleteMalfType" /></a></li>
    		</c:if>
          </ul>
        </li> 
        <li class="dropdown">
          <a class="dropdown-toggle" data-toggle="dropdown" href="#"><fmt:message key="navbar.user.top" /><span class="caret"></span></a>
          <ul class="dropdown-menu">
          	<c:if test="${(sessionScope.user.role == 'MANAGER') || (sessionScope.user.role == 'ADMIN')}">
          		<li><a href="${pageContext.request.contextPath}/pages/allUsers"><fmt:message key="navbar.user.allUsers" /></a></li>
       		</c:if>
       		<c:if test="${sessionScope.user.role == 'ADMIN'}"> 		
       			<li><a href="${pageContext.request.contextPath}/pages/showDeleteUser"><fmt:message key="navbar.user.deleteUser" /></a></li>	
       			<li><a href="${pageContext.request.contextPath}/pages/showChangeRole"><fmt:message key="navbar.user.changeUserRole" /></a></li>
       		 </c:if>      		    	
          </ul>
        </li> 
       </c:if>
         <li class="dropdown">
          <a class="dropdown-toggle" data-toggle="dropdown" href="#"><fmt:message key="navbar.review.top" /><span class="caret"></span></a>
          <ul class="dropdown-menu">
          <c:if test="${not empty sessionScope.user}">             
       		<li><a href="${pageContext.request.contextPath}/pages/showAddReview"><fmt:message key="navbar.review.addReview" /></a></li>
       		   <c:if test="${(sessionScope.user.role == 'MANAGER') || (sessionScope.user.role == 'ADMIN')}">    		
    				<li><a href="${pageContext.request.contextPath}/pages/showDeleteReview"><fmt:message key="navbar.review.deleteReview" /></a></li>
    			</c:if>
    	  </c:if>
    		<li><a href="${pageContext.request.contextPath}/pages/allReviews"><fmt:message key="navbar.review.allReviews" /></a></li>  		        	     	
          </ul>
        </li> 
         <c:if test="${not empty sessionScope.user}">         
         <li class="dropdown">
          <a class="dropdown-toggle" data-toggle="dropdown" href="#"><fmt:message key="navbar.application.top" /><span class="caret"></span></a>
          <ul class="dropdown-menu">
       		<li><a href="${pageContext.request.contextPath}/pages/showAddApplication"><fmt:message key="navbar.application.addApplication" /></a></li>
    		<c:if test="${(sessionScope.user.role == 'MANAGER') || (sessionScope.user.role == 'ADMIN')}">   		
    			<li><a href="${pageContext.request.contextPath}/pages/allApplications"><fmt:message key="navbar.application.allApplications" /></a></li>
    			<li><a href="${pageContext.request.contextPath}/pages/showDeleteApplication"><fmt:message key="navbar.application.deleteApplication" /></a></li>
    			<li><a href="${pageContext.request.contextPath}/pages/showSelectManagerApplication"><fmt:message key="navbar.application.appProcessingManager" /></a></li>
    		</c:if>
    		<c:if test="${(sessionScope.user.role == 'MASTER') || (sessionScope.user.role == 'MANAGER') || (sessionScope.user.role == 'ADMIN')}">   		
    			<li><a href="${pageContext.request.contextPath}/pages/showSelectMasterApplication"><fmt:message key="navbar.application.appProcessingMaster" /></a></li>
    		</c:if>
    		<li><a href="${pageContext.request.contextPath}/pages/myApplications"><fmt:message key="navbar.application.myApplications" /></a></li>    		
          </ul>
        </li> 
        </c:if>        
        </ul>
        <ul class="nav navbar-nav navbar-right">
      	<c:choose>
                <c:when test="${not empty sessionScope.user}">
                   <li><a href="${pageContext.request.contextPath}/pages/logout"><span class="glyphicon glyphicon-log-out"></span> <fmt:message key="navbar.title.logout" /></a></li>
               	</c:when>
               	<c:otherwise>      
        <li><a href="${pageContext.request.contextPath}/pages/showAddUser"><span class="glyphicon glyphicon-user"></span><fmt:message key="navbar.title.signUp" /></a></li>
        <li><a href="${pageContext.request.contextPath}/pages/showLogin"><span class="glyphicon glyphicon-log-in"></span><fmt:message key="navbar.title.login" /></a></li>
          	</c:otherwise>
        </c:choose>
        <li>
        <form>
        	<select class = "form-control" id="language" name="language" onchange="submit()">
				<option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
				<option value="ru" ${language == 'ru' ? 'selected' : ''}>Русский</option>
			</select>
		</form>
        </li>
      </ul>
    </div>
  </div>
</nav>
</body>
</html>