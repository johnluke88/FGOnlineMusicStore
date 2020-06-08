<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
 
   
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Errore"/>
</jsp:include>
 
<div class="container">    
    <div class="row">
        <div class="col-xs-12 col-sm-12 col-md-12">
            <h1><%= exception.getMessage() %></h1>
        </div>
    </div>
</div>
 
<br>
<br>
<br>
 
<%@include file="footer.html"%>