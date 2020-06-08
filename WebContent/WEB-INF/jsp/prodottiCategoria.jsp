<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="header.jsp">
	<jsp:param name="pageTitle" value="${categoria.nome}"/>
</jsp:include>


<div class="container">  
	<h1>${categoria.nome}</h1>
	<div class="row">
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
	</div>
</div>
<div class="container">
	<nav aria-label="Navigation for products-category">
		<ul class="pagination">
	    	<c:if test="${currentPage != 1}">
	        	<li class="page-item">
	        		<a class="page-link btn-danger" href="ProdottiCategoriaServlet?recordsPerPage=${recordsPerPage}&currentPage=${currentPage-1}&id=<c:out value="${categoria.id}"/>">Previous</a>
	            </li>
	        </c:if>
	
	        <c:forEach begin="1" end="${noOfPages}" var="i">
	        	<c:choose>
	           		<c:when test="${currentPage eq i}">
	                	<li class="page-item active"><a class="page-link btn-danger">
	                    	${i} <span class="sr-only">(current)</span></a>
	                    </li>
	                </c:when>
	                <c:otherwise>
	                	<li class="page-item">
	                		<a class="page-link btn-danger" href="ProdottiCategoriaServlet?recordsPerPage=${recordsPerPage}&currentPage=${i}&id=<c:out value="${categoria.id}"/>">${i}</a>
	                    </li>
	                </c:otherwise>
	            </c:choose>
	        </c:forEach>
	
	        <c:if test="${currentPage lt noOfPages}">
	            <li class="page-item">
	            	<a class="page-link btn-danger" href="ProdottiCategoriaServlet?recordsPerPage=${recordsPerPage}&currentPage=${currentPage+1}&id=<c:out value="${categoria.id}"/>">Next</a>
	            </li>
	        </c:if>              
	    </ul>
	</nav>
</div>
<br> 
<%@ include file="footer.html" %>