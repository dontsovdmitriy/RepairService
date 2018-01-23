<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html >
<head>
  <title>Title</title>
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
      <a class="navbar-brand">Web site name</a>
    </div>
    <div class="collapse navbar-collapse myNavbar">
      <ul class="nav navbar-nav">
        <li class="active"><a href="${pageContext.request.contextPath}/user/welcome">Home</a></li>
        <li class="dropdown">
          <a class="dropdown-toggle" data-toggle="dropdown" href="#">Malfunction type<span class="caret"></span></a>
          <ul class="dropdown-menu">
       		<li><a href="${pageContext.request.contextPath}/pages/showAddMalfunctionType">Add</a></li>
    		<li><a href="${pageContext.request.contextPath}/pages/allMalfunctionTypes">all malfunction types</a></li>
    		<li><a href="${pageContext.request.contextPath}/pages/showDeleteMalfunctionType">Delete malfunction type</a></li>
    		
        	     	
          </ul>
        </li> 
        <li class="dropdown">
          <a class="dropdown-toggle" data-toggle="dropdown" href="#">User<span class="caret"></span></a>
          <ul class="dropdown-menu">
       		<li><a href="${pageContext.request.contextPath}/pages/showDeleteUser">Delete</a></li>	
       		<li><a href="${pageContext.request.contextPath}/pages/allUsers">all users</a></li>
       		     	
       		    	
          </ul>
        </li> 
         <li class="dropdown">
          <a class="dropdown-toggle" data-toggle="dropdown" href="#">Review<span class="caret"></span></a>
          <ul class="dropdown-menu">
       		<li><a href="${pageContext.request.contextPath}/pages/showAddReview">Add</a></li>
    		<li><a href="${pageContext.request.contextPath}/pages/allReviews">all reviews</a></li>
    		<li><a href="${pageContext.request.contextPath}/pages/showDeleteReview">Delete review</a></li>
    		
        	     	
          </ul>
        </li> 
        
         <li class="dropdown">
          <a class="dropdown-toggle" data-toggle="dropdown" href="#">Application<span class="caret"></span></a>
          <ul class="dropdown-menu">
       		<li><a href="${pageContext.request.contextPath}/pages/showAddApplication">Add</a></li>
    		<li><a href="${pageContext.request.contextPath}/pages/allApplications">all applications</a></li>
    		<li><a href="${pageContext.request.contextPath}/pages/showDeleteApplication">Delete application</a></li>
    		
        	     	
          </ul>
        </li> 
        </ul>
        <ul class="nav navbar-nav navbar-right">
      	<c:choose>
                <c:when test="${not empty sessionScope.user}">
                   <li><a href="${pageContext.request.contextPath}/pages/logout"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
               	</c:when>
               	<c:otherwise>      
        <li><a href="${pageContext.request.contextPath}/pages/showAddUser"><span class="glyphicon glyphicon-user"></span>Sign up</a></li>
        <li><a href="${pageContext.request.contextPath}/pages/showLogin"><span class="glyphicon glyphicon-log-in"></span>Login</a></li>
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
        </ul>
    </div>
  </div>
</nav>
</body>
</html>