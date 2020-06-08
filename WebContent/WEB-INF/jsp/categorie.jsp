<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<jsp:include page="header.jsp">
 <jsp:param name="pageTitle" value="Categorie"/>
</jsp:include>

<div class="container">    
	<c:if test="${utente.admin}">
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12"> 
				<a class="btn btn-danger" href="AggiungiCategoria">Aggiungi Categoria</a>
			</div>
		</div>
		<br>
		<hr>
		<br>
	</c:if>

	<c:forEach items="${categorie}" var="categoria">
		<div class="row">
   			<div class="col-xs-2 col-sm-4 col-md-2">  
   				<h4><a href="ProdottiCategoriaServlet?id=<c:out value="${categoria.id}"/>&nome=<c:out value="${categoria.nome}"/>" style="color:black">${categoria.nome}</a></h4>
 				<c:if test="${utente.admin}">
 					<br>
 					<br>
					<form action="AdminCategoria" method="post">
						<input type ="hidden" name ="id" value ="${categoria.id}">
						<button class = "btn btn-danger" name="modifica" type="submit" >Modifica</button>
					</form>
					<br>
					<form action="AggiungiCategoria" method="post">
						<input type ="hidden" name ="id" value ="${categoria.id}">
						<button class = "btn btn-danger" name="rimuovi" type="submit">Rimuovi</button>
					</form>
				</c:if>
   			</div>
   			<div class="col-xs-2 col-sm-4 col-md-2">
    			<img src="http://localhost:8080/images/categorie/<c:out value="${categoria.nome}"/>.jpg" class="img-thumbnail" alt="" /> 			
   			</div>
   			<div class="col-xs-8 col-sm-4 col-md-8">
   				<p>${categoria.descrizione}</p>
   			</div>		
 		</div>
 		<br>
 		<hr>
 	</c:forEach>
</div>
<br>


<%@ include file="footer.html" %>