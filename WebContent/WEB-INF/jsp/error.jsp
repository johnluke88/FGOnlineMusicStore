<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
 
   
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Errore ${requestScope['javax.servlet.error.status_code']}"/>
</jsp:include>
 
<div class="container">    
    <div class="row">
        <div class="col-xs-12 col-sm-12 col-md-12">
            <h1>Errore ${requestScope['javax.servlet.error.status_code']}</h1>
            <pre>
            <%
                if (exception != null)
                {
                    out.flush();
                    exception.printStackTrace(response.getWriter());
                }
            %>
            </pre>
        </div>
    </div>
</div>
 
 
<br>
<%@include file="footer.html"%>