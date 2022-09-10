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
	    <title>My Inspiration</title>
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
				<h1>My Inspiration</h1>
			</div>
			
			<p>Do we have the daily quote: ${haveDaily}</p>
						
			<div id="carouselWithControls" class="carousel slide" data-bs-ride="carousel">
  				<div class="carousel-inner">
  					<div class="carousel-item active">
  						<div class="w-100 p-5 d-flex justify-content-center" style="background-color: #EFB9CB;">
  							<h3>My Quotes :)</h3>
  						</div>
  					</div>
  					<c:forEach var="quote" items="${quotes}">
  						<div class="carousel-item">
  							<div class="w-100 d-flex p-5 justify-content-evenly align-items-center" style="background-color: #EFB9CB;">
								<figure class="px-5 py-3 w-50 border border-2 border-dark rounded-pill">
								  	<blockquote class="blockquote">
								   	 	<p><c:out value="${quote.text}"/></p>
								  	</blockquote>
								  	<figcaption class="blockquote-footer">
								    	<c:out value="${quote.author}"/>
								  	</figcaption>
								</figure>
								<c:if test="${quote.creator.id == user.id}">
									<p>
										<a href="/inspiration/edit/${quote.id}">
											<button class="btn btn-secondary">
												Edit Quote
											</button>
										</a>
									</p>
								</c:if>
							</div>
  						</div>
  					</c:forEach>
				</div>
				  <a class="carousel-control-prev" href="#carouselWithControls" role="button" data-bs-slide="prev">
				    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
				    <span class="visually-hidden">Previous</span>
				  </a>
				  <a class="carousel-control-next" href="#carouselWithControls" role="button" data-bs-slide="next">
				    <span class="carousel-control-next-icon" aria-hidden="true"></span>
				    <span class="visually-hidden">Next</span>
				  </a>
			</div>
		</div>
	</body>
</html>