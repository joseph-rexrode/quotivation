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
	    <title>Quotivation - Home</title>
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
          						<a class="nav-link active" aria-current="page" href="/home">Home</a>
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
				<h1>Welcome, ${loggedUser.username}!</h1>
			</div>
						
			<div class="d-flex flex-row mb-4 p-3 justify-content-center align-items-center rounded-pill" style="background-color: #CFD4C5;">
				<div class="col-3">
					<h3>~ Daily Quote ~</h3>
				</div>
				<div class="col-6 col-offset-3">
					<figure>
						<blockquote class="blockquote">
							<p><i>
								${dailyQuote.text}
							</i></p>
						</blockquote>
						<figcaption class="blockquote-footer">
							${dailyQuote.author}
						</figcaption>
					</figure>
				</div>
			</div>
			
			
			<!-- still need to add functionality to this -->
			<c:if test="${dailyAddable.equals('yes')}">
				<div class="d-flex flex-row mb-4 justify-content-center">
					<form action="/inspiration/add" method="POST">
						<input type="hidden" name="_method" value="put">
						<input type="hidden" name="id" value="${dailyQuote.id}">
						<button type="submit" class="btn btn-success">Add this quote to your collection?</button>
					</form>
				</div>
			</c:if>
			
			<div class="d-flex flex-row p-4">		
				<div class="speech-bubble d-flex shadow">
					<div class="align-self-center h-75 my-3 mx-3">
						<figure>
							<blockquote class="blockquote">
								<p><i>
									${randomQuote.text}
								</i></p>
							</blockquote>
							<figcaption class="blockquote-footer">
								${randomQuote.author}
							</figcaption>
						</figure>
					</div>
					<c:if test="${addable.equals('yes')}">
						<div class="align-self-start me-3 mt-2">
							<form action="/inspiration/addRandom" method="POST">
								<input type="hidden" name="_method" value="put">
								<button type="submit" class="btn btn-close" style="transform: rotate(45deg);"></button>
							</form>
						</div>
					</c:if>
				</div>
				<div class="col-3 offset-1 d-flex align-items-center">
					<a href="/inspiration/random">
						<button class="btn btn-secondary">Generate a new random quote!</button>
					</a>
				</div>
			</div>
			
			<div class="fixed-bottom d-flex flex-column justify-content-center align-items-center">
				<p>Project created by Joseph Rexrode</p>
				<p>Inspirational quotes provided by <a href="https://zenquotes.io/" target="_blank">ZenQuotes API</a></p>
			</div>
		</div>
	</body>
</html>