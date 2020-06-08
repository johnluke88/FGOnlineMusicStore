<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="header.jsp">
  <jsp:param name="pageTitle" value="${prodotto.nome}"/>
</jsp:include>

<div class="container">    
<c:if test="${notifica}"><h4>Prodotto Aggiunto</h4></c:if>
	<div class="row">
		<div class="col-xs-4 col-sm-4 col-md-4"> 
			<img src="http://localhost:8080/images/prodotti/<c:out value="${prodotto.nome}"/>.jpg" class="img-thumbnail" alt=""/>
		</div>
		<div class="col-xs-4 col-sm-4 col-md-4">
			<h4>${prodotto.nome}</h4>
			<p>${prodotto.descrizione}</p>
		</div>
		<div class="col-xs-4 col-sm-4 col-md-4">
			<h4>Prezzo: ${prodotto.getPrezzoEuro()} &euro;</h4>
			<form action="CarrelloServlet" method="post">
				<div class="form-group">
					<input type ="hidden" name ="prodId" value ="${prodotto.id}">			<!-- ID -->
					<label for="exampleFormControlSelect1">Quantita'</label>
					<select class="form-control" id="exampleFormControlSelect1" name="addNum">
						<c:forEach begin="1" end="10" varStatus="loop">
	           				<option value="${loop.index}">${loop.index}</option>
	       				</c:forEach>
					</select>
					<br>
					<input class="btn btn-danger" type="submit" value="Aggiungi al carrello">
				</div>
			</form>
			<c:if test="${utente.admin}">
 				<br>
				<form action="ModificaProdotto" method="post">
					<input type ="hidden" name ="id" value ="${prodotto.id}">
					<button class = "btn btn-danger" name="modifica" type="submit" >Modifica</button>
				</form>
				<br>
				<form action="AggiungiRimuoviProdotto" method="post">
					<input type ="hidden" name ="id" value ="${prodotto.id}">
					<button class = "btn btn-danger" name="rimuovi" type="submit">Rimuovi</button>
				</form>
			</c:if>
		</div>
	</div>
</div> 
<br>
<br> 
<%@ include file="footer.html" %>