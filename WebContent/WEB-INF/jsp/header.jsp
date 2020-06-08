<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
  	<meta name="viewport" content="width=device-width, initial-scale=1">
  	<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous"> -->
 
  	<link rel="stylesheet" href="css/bootstrap.min.css" >
 
  	<script src="https://kit.fontawesome.com/b33edbd631.js"></script>
  	<style>
	    /* Remove the navbar's default rounded borders and increase the bottom margin */ 
	   	.navbar {
	    	margin-bottom: 50px;
	     	border-radius: 0;
	    } 
	    
	    /* Remove the jumbotron's default bottom margin */ 
	    .jumbotron {
	    	margin-bottom: 0;
	    	padding: 1rem 2rem;
	    }
	    footer
	    {
	      	padding: 25px;
	    }
	    body{		
	    	background-color:#f2f2f2
	    }
	    
	    .page-link{
	 		color: #dc3545;
	    }
	    
	    .page-item.active .page-link {
		    background-color: #dc3545;
		    border-color: #dc3545;
	    }
	    .page-link:hover {
    		color: #dc3545;
	    }
	    
  	</style>
	<title>FG - Online Music Store -${param.pageTitle}</title>
</head>
<body>
	<div class="jumbotron bg-dark ">
		<div class="container text-center">
	    	<h1 class="text-hide"><img alt="" src="img/logo3.png">FG</h1>      
	    	<p class="text-white">Online Music Store</p>
	  	</div>
	</div>
	
	<nav class="navbar navbar-expand-lg navbar-dark bg-danger">
		<a class="navbar-brand" href="."><img alt="" src="img/logobar.png" width="40" height="30"></a>
	  	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	    	<span class="navbar-toggler-icon"></span>
	  	</button>
	
	  	<div class="collapse navbar-collapse" id="navbarSupportedContent">
	  		<ul class="navbar-nav mr-auto">
	  			<li class="nav-item <c:if test="${param.pageTitle =='Home'}">active</c:if>">
	        		<a class="nav-link" href=".">Home</a>
	      		</li>
	      		<li class="nav-item <c:if test="${param.pageTitle =='Categorie'}">active</c:if>">
	        		<a class="nav-link" href="CategorieServlet">Categorie</a>
	      		</li>
		  		<li class="nav-item dropdown">
	    	 		<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	            	<i class="fas fa-user"></i>
	      	 		</a>
	      	 		<c:choose>
	        			<c:when test="${utente == null}">
	        	  			<div class="dropdown-menu" aria-labelledby="navbarDropdown">
	          					<form class="px-4 py-3" action="LoginServlet" method="post">
		            	   			<div class="form-group">
		               					<label for="username" >Username</label>
		                				<input type="text" class="form-control" id="username" name="username" aria-describedby="userHelp" placeholder="Enter username">
		               				</div>
		               				<div class="form-group">
		               					<label for="password" >Password</label>
		                				<input type="password" class="form-control" id="password" name="password" placeholder="Password">
		             				</div>
		                			<button type="submit" class="btn btn-danger">Login</button>
	            				</form>
								<div class="px-4 py-3">
									<a href="RegistrazioneFormServlet">Registrati</a>
	        					</div>
	          	  			</div>
	          	  		</c:when>
	           		   	<c:otherwise>
	          				<div class="dropdown-menu" aria-labelledby="navbarDropdown">
					        	<a  class="dropdown-item">${utente.admin ? 'Admin' : 'Utente'}<br>Ciao, ${utente.nome}</a>
					          	<div class="dropdown-divider"></div>
					          		<c:if test="${utente.admin}">       		
					          			<a class="dropdown-item" href="AggiungiCategoria">Aggiungi Categoria</a>
					          			<a class="dropdown-item" href="AggiungiRimuoviProdotto">Aggiungi Prodotto</a>
					          			<a class="dropdown-item" href="AdminUserBeanServlet">Visualizza Utenti</a>
					          			<div class="dropdown-divider"></div>
									</c:if>
					          		<a class="dropdown-item" href="ProfiloServlet">Profilo</a>
					          		<a class="dropdown-item" href="OrdiniServlet">I miei Ordini</a>
	          	        			<form class="px-4 py-3" action="LogoutServlet">
	          							<button type="submit" class="btn btn-danger">Logout</button>
	          						</form>
	          				</div>
						</c:otherwise>
	      			</c:choose>
	     		</li>
	     		<li class="nav-item <c:if test="${param.pageTitle =='Carrello'}">active</c:if>">
		        	<a class="nav-link nav-item" href="CarrelloServlet"><i class="fas fa-shopping-cart"></i></a>
		    	</li>
			</ul>
	    	<form action="Ricerca" class="form-inline my-2 my-lg-0"  method="get">
	    		<input type="text" class="form-control mr-sm-2" list="ricerca-datalist" placeholder="Search" aria-label="Search" name="q" onkeyup="loadSuggerimenti(this.value)" value="<c:out value="${param.q}" />">
	    	  	<datalist id="ricerca-datalist">
	           	</datalist>
	    	  	<button class="btn btn-dark my-2 my-sm-0" type="submit">Search</button> 
	    	</form>
	  	</div>
	</nav>