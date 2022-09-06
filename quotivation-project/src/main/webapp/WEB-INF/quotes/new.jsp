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
	    <title>Create a Quote</title>
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
          						<a class="nav-link active dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            						Inspiration
          						</a>
          						<ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
            						<li><a class="dropdown-item" href="/inspiration">My Inspiration</a></li>
            						<li><a class="dropdown-item" href="/inspiration/new">Create a Quote!</a></li>
          						</ul>
        					</li>
        					<li class="nav-item">
        						<a class="nav-link" href="/others">Others</a>
        					</li>
        					<li class="nav-item">
        						<a class="nav-link" href="/logout">Logout</a>
        					</li>
        				</ul>
        			</div>
				</div>
			</nav>
			
			<div class="row my-4">
				<h1>Create a Quote</h1>
			</div>
			
			
			<div class="w-50">
				<form:form action="/inspiration/new" method="POST" modelAttribute="quote">
					<div class="row mb-3">
						<form:errors path="text" class="text-danger"/>
						<form:errors path="author" class="text-danger"/>
					</div>
					
					<div class="row mb-3">
						<form:label class="col-sm-2 col-form-label" path="text">Quote:</form:label>
						<div class="col-sm-10">
							<form:textarea path="text" class="form-control"/>
						</div>
					</div>
					
					<div class="row mb-3">
						<form:label class="col-sm-2 col-form-label" path="author">Author:</form:label>
						<div class="col-sm-10">
							<form:input type="text" path="author" class="form-control"/>
						</div>
					</div>
					
					<div class="d-flex flex-row my-4 justify-content-end">
						<div class="col-4">
							<button type="submit" class="btn btn-primary w-100">Create Quote</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</body>
</html>