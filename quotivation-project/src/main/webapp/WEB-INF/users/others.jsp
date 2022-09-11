<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
	    <title>Other Quotivators!</title>
	    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
		<link rel="stylesheet" href="/css/style.css">
	    <script src="/webjars/jquery/jquery.min.js"></script>
	    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
	</head>
	<body>
		<div class="container">
			<nav class="navbar navbar-expand-xl navbar-light">
				<div class="container-fluid">
					<a href="/home" class="navbar-brand">Quotivation</a>
					<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarBasic" aria-controls="navbarBasic" aria-expanded="false" aria-label="Toggle navigation">
      					<span class="navbar-toggler-icon"></span>
    				</button>
   					<div class="collapse navbar-collapse" id="navbarBasic">
      					<ul class="navbar-nav me-auto mb-2 mb-xl-0 offset-8">
        					<li class="nav-item">
          						<a class="nav-link" aria-current="page" href="/home">Home</a>
        					</li>
        					<li class="nav-item dropdown">
          						<a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            						Inspiration
          						</a>
          						<ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
            						<li><a class="dropdown-item" href="/inspiration">My Inspiration</a></li>
            						<li><a class="dropdown-item" href="/inspiration/new">Create a Quote!</a></li>
          						</ul>
        					</li>
        					<li class="nav-item">
        						<a class="nav-link active" href="/others">Others</a>
        					</li>
        					<li class="nav-item">
        						<a class="nav-link" href="/logout">Logout</a>
        					</li>
        				</ul>
        			</div>
				</div>
			</nav>
			<div class="row my-4">
				<h1>Other Quotivators!</h1>
			</div>
			
			<div class="row row-cols-1 row-cols-sm-3 g-3">
				<c:forEach var="user" items="${otherUsers}">
	  				<div class="col">
	    				<div class="card shadow" style="background-color: #EFB9CB;">
					      	<div class="card-body">
					        	<h5 class="card-title">${user.username}</h5>
					        	<p class="card-text">
					        		<a href="/inspiration/others/${user.id}">
					        			<button class="btn btn-secondary">
							        		See ${user.username}'s Inspiration!
					        			</button>
					        		</a>
					        	</p>
							</div>
					    </div>
					</div>
				</c:forEach>
			</div>
			
			<div class="fixed-bottom" id="polygon"></div>
		</div>
	</body>
</html>