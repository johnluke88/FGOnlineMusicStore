<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="header.jsp">
  <jsp:param name="pageTitle" value="Checkout"/>
</jsp:include>

<div class="container"> 
	<br>
	<h2>Checkout:</h2>
	<br>
	<div class="row">
		<div class="col-xs-12 col-sm-12 col-md-12">
			<form action="CheckoutServlet" method="post">
				<input type="hidden" value="${utente.id}" name="utenteId">		
				<div class="form-row">
	            	<div class="form-group col-xs-6 col-sm-6 col-md-6"> 
	                	<label for="inputNome">Nome </label>
	                	<input type="text" class="form-control" id="inputNome" value="${utente.nome}" name="nome" disabled>
	                </div>
	                <div class="form-group col-xs-6 col-sm-6 col-md-6">
	                	<label for="inputCognome">Cognome </label>
	                  	<input type="text" class="form-control" id="inputCognome" value="${utente.cognome}" name="cognome" disabled>
	                </div>
	            </div>
		        <div class="form-row">
	            	<div class="form-group col-xs-6 col-sm-6 col-md-6">
	                	<label for="inputPhone">Telefono</label>
	                	<input type="text" class="form-control" id="inputPhone" value="${utente.telefono}" name="telefono" disabled>
	                </div>
	               	<div class="form-group col-xs-6 col-sm-6 col-md-6">
	                  	<label for="inputEmail">Mail </label>
	                  	<input type="email" class="form-control" id="inputEmail" value="${utente.mail}" name="mail" disabled>
	                </div>
		        </div>
		        <div class="form-row">
		        	<div class="form-group">
		            	<label for="inputAddress">Via</label>
		                <input type="text" class="form-control" id="inputAddress" value="${utente.via}" name="via" disabled>
		            </div>
		        </div>
		        <div class="form-row">
		        	<div class="form-group col-xs-6 col-sm-6 col-md-6">
		            	<label for="inputCity">Citta</label>
		                <input type="text" class="form-control" id="inputCity" value="${utente.citta}" name="citta" disabled>
		            </div>
		           	<div class="form-group col-xs-6 col-sm-6 col-md-6">
		            	<label for="inputCap">Cap</label>
		               	<input type="text" class="form-control" id="inputCap" value="${utente.cap}" name="cap" disabled>
		            </div>
		        </div>
		        <div class="form-row">
					<div class="form-group col-xs-6 col-sm-6 col-md-6">
						<label for="prodottiAcquistati">Prodotti:</label>
		            	<c:forEach items="${carrello.lista}" var="ci">
							<input class="form-control" id="prodottiAcquistati" type="text" value="${ci.quantita}x ${ci.prodotto.nome}: ${ci.getPrezzoTotEuro()} &euro;" disabled>
							<br>
						</c:forEach>
		           	</div>          	
		        </div>
		       	<div class="form-row">
			    	<div class="form-group col-xs-6 col-sm-6 col-md-6">
			        	<label for="tot">Totale ordine:</label>
			          	<input type="text" name="prezzoTotale" id="prezzoTotale" class="form-control" value="${carrello.getPrezzoTotEuroItems()} &euro;" readonly>
			        </div>
		        </div>
	            <button type="submit" class="btn btn-danger" id="paga" >Procedi</button><br>
			</form>
		</div>
	</div>
</div>
<br> 
<%@ include file="footer.html" %>