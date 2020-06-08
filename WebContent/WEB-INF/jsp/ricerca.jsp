<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="header.jsp">
	<jsp:param name="pageTitle" value="Ricerca"/>
</jsp:include>

<div class="container">
 	<div class="row">
		<c:choose>
   			<c:when test="<%=request.getParameter(\"q\") == \"\" %>">
				<div class="col-xs-12 col-sm-12 col-md-12">
					<h3>Inserire un nome di un prodotto da cercare.</h3>
				</div>
			</c:when>
			<c:otherwise>
   				<br>
   				<div class="col-xs-12 col-sm-12 col-md-12">
		  	 		<h3>Hai cercato: <b> <em> <%=request.getParameter("q")%> </em> </b> </h3>
				</div>
				<br>
				<br>
 				<c:forEach items="${prodotti}" var="prodotto">
 	      			<div class="col-xs-4 col-sm-4 col-md-4">
	       				<div class="card" >
	        				<img src="http://localhost:8080/images/prodotti/<c:out value="${prodotto.nome}"/>.jpg" class="card-img-top" alt="${prodotto.nome}" >
	         			 	<div class="card-body">
	           					<h5 class="card-title">${prodotto.nome}</h5>
	            				<p class="card-text text-truncate">${prodotto.descrizione}</p>
	          					<a href="ProdottoServlet?nome=<c:out value="${prodotto.nome}"/>" class="btn btn-danger">Vai al Prodotto</a>
	          				</div>
	        			</div>
	        			<br>
	         		</div>			
 				</c:forEach>	
 				<c:if test="${empty prodotti}">
					<div class="text-center p-5">
						<h3>Siamo spiacenti. Non abbiamo prodotti con questo nome.</h3>
					</div>
  	 			</c:if>
   			</c:otherwise>
 		</c:choose>
 	</div>
</div>
<br>
<%@include file="footer.html"%>