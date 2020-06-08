<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="header.jsp">
	<jsp:param name="pageTitle" value="Miei Ordini"/>
</jsp:include>

<div class="container">
	<br>
	<c:choose>
		<c:when test="${notifica}">
			<h2>Ordini di ${cliente.nome}</h2>
		</c:when>
		<c:otherwise>
			<h2>Miei Ordini:</h2>
		</c:otherwise>
	</c:choose>
	<br>
		
	<c:if test="${empty lista}"><h4>Nessun Acquisto Effettuato!</h4></c:if>
	<c:forEach items="${lista}" var="ord" >
		<div class="row">
			<div class="col-xs-2 col-sm-3 col-md-3">
				<img src="http://localhost:8080/images/prodotti/<c:out value="${nomi.get(ord.getIdProdotto())}"/>.jpg" class="img-thumbnail" alt=""/>
			</div>
			<div class="col-xs-4 col-sm-4 col-md-4">
				<h5>${nomi.get(ord.getIdProdotto())}</h5>
			</div>
			<div class="col-xs-5 col-sm-5 col-md-5">
				<h5>Num d'ordine : ${ord.getNumOrd()}</h5>
				<h5>Quantita': ${ord.getQuantita()}</h5>
				<h6>Prezzo tot.: ${ord.getTotaleP()/100.}  &euro; </h6>
				<h6>Data : ${ord.getData()}</h6>
			</div>							
		</div>
		<hr>
	</c:forEach>
</div>

<br> 
<%@ include file="footer.html" %>