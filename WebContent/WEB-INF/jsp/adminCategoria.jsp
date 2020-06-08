<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="header.jsp">
  <jsp:param name="pageTitle" value="Admin Categoria"/>
</jsp:include>

<div class="container">    
	<div class="row">
		<div class="col-xs-12 col-sm-12 col-md-12">  
			<c:choose>
				<c:when test="${categoria != null}">
					<c:if test="${notifica}"><h5>Categoria Modificata</h5></c:if>
						<h4>Modifica</h4>
						<form action="AdminCategoria" method="post" enctype = "multipart/form-data" id = "form" name ="modifica">
					  		<div class="form-group">
								<label for="nome">Nome (almeno 2 caratteri)</label>
								<input type="text" class="form-control" id="nome" name="nome" placeholder="nome categoria" value="${categoria.nome}" oninput="validaNome()">
					 		</div>
					  		<div class="form-group">
								<label for="descrizione">Descrizione</label>
								<textarea class="form-control" id="descrizione" name="descrizione" rows="3" oninput="validaDescrizione()">${categoria.descrizione}</textarea>
					  		</div>
					  		<div class="form-group">
								<div class="custom-file">
						 	  		<input type="file" class="custom-file-input" id="customFile" name="file" onchange="cambiaStatoModifica()">
							  		<label class="custom-file-label" for="customFile">Choose file</label>
								</div>
					 		</div>
					  		<input type="hidden" name ="id" value ="${categoria.id}">
					  		<input class="btn btn-danger" type="submit" value="Modifica" id = "update" disabled><span id="modificamimessaggio"></span>
						</form>	
				</c:when>
				<c:otherwise>
					<c:if test="${notifica}"><h5>Categoria Aggiunta</h5></c:if>
						<h4>Aggiungi</h4>
						<form action="AdminCategoria" method="post" enctype = "multipart/form-data" id = "form" name ="aggiungi">
					  		<div class="form-group">
								<label for="nome">Nome (almeno 2 caratteri)</label>
								<input type="text" class="form-control" id="nome" name="nome" placeholder="nome categoria" oninput="validaNome()">
					  		</div>
					  		<div class="form-group">
								<label for="descrizione">Descrizione</label>
								<textarea class="form-control" id="descrizione" name="descrizione" rows="3" oninput="validaDescrizione()">Descrizione</textarea>
					  		</div>
					  		<div class="form-group">
								<div class="custom-file">
						 	  		<input type="file" class="custom-file-input" id="customFile" name="file">
							  		<label class="custom-file-label" for="customFile">Choose file</label>
								</div>
					 		</div>
					  		<input class="btn btn-danger" type="submit" value="Aggiungi" id="update" disabled><span id="modificamimessaggio"></span>
						</form>	
				</c:otherwise>
			</c:choose>			
		</div>
	</div>
</div>  
<script src="https://cdn.jsdelivr.net/npm/bs-custom-file-input/dist/bs-custom-file-input.js"></script>
<script>

	bsCustomFileInput.init()

</script>
<script>
		var borderOk = '2px solid #080';
		var borderNo = '2px solid #f00';
		var nomeOk = false;
		var descrizioneOk = false;
		var form = document.getElementById("form").name;
		if(form == 'modifica')
		{
			nomeOk = true;
			descrizioneOk = true;
		}
		var nomeOld = document.forms[form]['nome'].value;
		
		function validaNome() {
			var input = document.forms[form]['nome'];
			if (input.value.length >= 2 && input.value.match(/^[ 0-9a-zA-Z\u00C0-\u00ff]+$/)) {
				// verifica se esiste una categoria con lo stresso nome
				var xmlHttpReq = new XMLHttpRequest();
				xmlHttpReq.onreadystatechange = function() {
					if (this.readyState == 4 && this.status == 200
							&& this.responseText == '<ok/>' ) {
						nomeOk = true;
						input.style.border = borderOk;
					} else {
						input.style.border = borderNo;
						nomeOk = false;
					}
					cambiaStatoModifica();
				}
				xmlHttpReq.open("GET", "VerificaCategoria?nome="
						+ encodeURIComponent(input.value)+"&nomeOld="
							+ encodeURIComponent(nomeOld), true);
				xmlHttpReq.send();
			}
			else{
				input.style.border = borderNo;
				nomeOk = false;
				cambiaStatoModifica();
			}
		}
		
		function validaDescrizione() {
			var input = document.forms[form]['descrizione'];
			if (input.value.trim().length > 0
					&& input.value.match(/^[ 0-9a-zA-Z\u00c0-\u00ff\!\?\,\.\;\(\)\[\]\r\n]+$/)) {
				input.style.border = borderOk;
				descrizioneOk = true;
			} else {
				input.style.border = borderNo;
				descrizioneOk = false;
			}
			cambiaStatoModifica();
		}
	
		function cambiaStatoModifica() {
			if (nomeOk && descrizioneOk) {
				document.getElementById('update').disabled = false;
				document.getElementById('modificamimessaggio').innerHTML = '';
			} else {
				document.getElementById('update').disabled = true;
				document.getElementById('modificamimessaggio').innerHTML = 'Verifica che tutti i campi siano in verde.';
			}
		}
</script>
<br>
<br> 
<%@ include file="footer.html" %>