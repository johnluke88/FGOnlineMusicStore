<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="header.jsp">
	<jsp:param name="pageTitle" value="Utenti"/>
</jsp:include>

<div class="container">    
	<div class="row table-responsive">
		<div class="col-xs-12 col-sm-12 col-md-12"> <!-- col-xs-2 col-sm-2 col-md-2 -->
			<table class="table">
				<thead>
					<tr>
						<th scope="col">ID</th>
						<th scope="col">Nome</th>
						<th scope="col">Username</th>
						<th scope="col">Email</th>
						<th scope="col">Ordini</th>
						<th scope="col" colspan="2">Azioni</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${utenti}" var="utenteLoc">
						<c:if test="${utente.getUsr()!= utenteLoc.getUsr()}"> 
							<tr>
								<th scope="row">${utenteLoc.id}</th>
								<td>${utenteLoc.nome}</td>
								<td>${utenteLoc.usr}</td>
								<td>${utenteLoc.mail}</td>
								<td><a href="OrdiniServlet?id=<c:out value="${utenteLoc.id}"/>">Dettagli</a></td>
								<td>
									<form action="AdminUpdateUserBeanServlet" method="post">
										<input type="hidden" name="id" value="${utenteLoc.id}">
										<button type="submit" class="btn btn-danger" name="modifica" >Modifica</button>
									</form>					
								</td>
								<td>
									<form action="AdminUserBeanServlet" method="post">
										<input type="hidden" name="id" value="${utenteLoc.id}">
										<button type="submit" class="btn btn-danger" name="rimuovi" value="rimuovi">Rimuovi</button>
									</form>
								</td>
							</tr>
						</c:if>
					</c:forEach>	
				</tbody>
			</table>
		</div>
	</div>
</div> 
<br>
<br>
<%@ include file="footer.html" %>