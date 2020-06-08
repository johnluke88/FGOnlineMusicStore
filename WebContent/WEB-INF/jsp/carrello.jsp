<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="header.jsp">
  <jsp:param name="pageTitle" value="Carrello"/>
</jsp:include>

<div class="container">
	<h1>Carrello</h1>
	<c:forEach items="${carrello.lista}" var="ci">
		<div class="row">
			<div class="col-xs-2 col-sm-2 col-md-2">
				<img src="http://localhost:8080/images/prodotti/<c:out value="${ci.prodotto.nome}"/>.jpg" class="img-thumbnail" alt=""/>
			</div>
			<div class="col-xs-3 col-sm-4 col-md-3">
				<h5>${ci.prodotto.nome}</h5>
				<p class="card-text text-truncate">${ci.prodotto.descrizione}</p>
			</div>
			<div class="col-xs-2 col-sm-2 col-md-2">
				<h5>Quantita'</h5>
				<p>${ci.quantita}</p>
			</div>
			<div class="col-xs-2 col-sm-2 col-md-2">
				<h6>Prezzo unit.: ${ci.prodotto.getPrezzoEuro()} &euro;  Prezzo tot.:  ${carrello.get(ci.prodotto.id).getPrezzoTotEuro()}  &euro; </h6>
			</div>
			<div class="col-xs-2 col-sm-1 col-md-2">
				<form action="CarrelloServlet" method="post">
					<input type="hidden" name="prodId" value="${ci.prodotto.id}">
					<input type="hidden" name="setNum" value="0">
					<input type="submit" value="Rimuovi">
				</form>	
			</div>
		</div>
	</c:forEach>
	<c:if test="${empty carrello.lista}">
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12">Nessun prodotto nel carrello</div>
		</div>
	</c:if>
</div>
<c:if test="${not empty carrello.lista}">
	<div class="container">    
		<div class="row">
			<div class="col-xs-7 col-sm-8 col-md-7">
			</div>
			<div class="col-xs-2 col-sm-2 col-md-2">
				<h4>Tot.</h4>
				<p>${carrello.getPrezzoTotEuroItems()} &euro;</p>
			</div>
			<div class="col-xs-3 col-sm-2 col-md-3">
				<form action="Riepilogo" method="post">
			  	<input type="hidden" name="carrello" value="${carrello}">
			  	<input type="hidden" name="utente" value="${utente}">
			  	<button type="submit" class="btn  btn-danger float-left">Acquista</button>
				</form>
			</div>
		</div>
	</div> 
</c:if>

<br> 
<%@ include file="footer.html" %>