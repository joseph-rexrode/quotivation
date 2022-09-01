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
	    <title>Quotivation</title>
	    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
		<link rel="stylesheet" href="/css/style.css">
	    <script src="/webjars/jquery/jquery.min.js"></script>
	    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
	</head>
	<body>
	   	<h1>Welcome to Quotivation.</h1>
	
		<h3>Register</h3>
		<form:form action="/register" method="POST" modelAttribute="newUser">
			<div class="row mb-3">
				<form:errors path="username" class="text-danger"/>
				<form:errors path="email" class="text-danger"/>
				<form:errors path="password" class="text-danger"/>
				<form:errors path="confirm" class="text-danger"/>
			</div>
			
			<div class="row mb-3">
				<form:label class="col-sm-2 col-form-label" path="username">Username:</form:label>
				<div class="col-sm-10">
					<form:input type="text" path="username" class="form-control"/>
				</div>
			</div>
			
			<div class="row mb-3">
				<form:label class="col-sm-2 col-form-label" path="email">Email:</form:label>
				<div class="col-sm-10">
					<form:input type="email" path="email" class="form-control"/>
				</div>
			</div>
			
			<div class="row mb-3">
				<form:label class="col-sm-2 col-form-label" path="password">Password:</form:label>
				<div class="col-sm-10">
					<form:input type="password" path="password" class="form-control"/>
				</div>
			</div>
			
			<div class="row mb-3">
				<form:label class="col-sm-2 col-form-label" path="confirm">Confirm Password:</form:label>
				<div class="col-sm-10">
					<form:input type="password" path="confirm" class="form-control"/>
				</div>
			</div>
			
			<div class="row mb-3">
				<button type="submit" class="btn btn-primary">Register</button>
			</div>
		</form:form>
		
		<h3>Login</h3>
		<form:form action="/login" method="POST" modelAttribute="newLogin">
			<form:errors path="email" class="text-danger"/>
			<form:errors path="password" class="text-danger"/>
			
			<div class="row mb-3">
				<form:label class="col-sm-2 col-form-label" path="email">Email:</form:label>
				<div class="col-sm-10">
					<form:input type="email" path="email" class="form-control"/>
				</div>
			</div>
			
			<div class="row mb-3">
				<form:label class="col-sm-2 col-form-label" path="password">Password:</form:label>
				<div class="col-sm-10">
					<form:input type="password" path="password" class="form-control"/>
				</div>
			</div>
			
			<div class="row mb-3">
				<button type="submit" class="btn btn-primary">Login</button>
			</div>
		</form:form>
	</body>
</html>
