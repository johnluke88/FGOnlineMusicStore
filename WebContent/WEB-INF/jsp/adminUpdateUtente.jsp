<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Admin Utente"/>
</jsp:include>
 
<div class="container">    
	<div class="row">
		<div class="col-xs-12 col-sm-12 col-md-12"> 
        	<h4>Modifica</h4>
            <c:if test="${notifica}"><h5>Utente Modificato</h5></c:if>
            <form action = "AdminUpdateUserBeanServlet" method ="post" name = "modifica">
	        	<input type="hidden" name="id" value="${user.id}">
	            <div class="form-row">
	            	<div class="form-group col-md-6">
	                	<label for="inputUsername">Username (almeno 6 caratteri, solo lettere e numeri)</label>
	                  	<input type="text" class="form-control" id="inputUsername" placeholder="Username" name="username" value="${user.usr}" oninput="validaUsername()">
	                </div>
	                <div class="form-group col-md-6">
	                  	<label for="inputPassword">Password (almeno 8 caratteri, almeno un numero)</label>
	                  	<input type="password" class="form-control" id="inputPassword" placeholder="Password" name="password" <%-- value = "${user.psw}" --%> oninput="validaPassword()">
	                </div>
	            </div>
	            <div class="form-row">
	            	<div class="form-group col-md-6">
	                	<label for="inputNome">Nome (solo lettere e spazi)</label>
	                  	<input type="text" class="form-control" id="inputNome" placeholder="Nome" name="nome" value="${user.nome}" oninput="validaNome()">
	                </div>
	                <div class="form-group col-md-6">
	                  	<label for="inputCognome">Cognome (solo lettere e spazi)</label>
	                  	<input type="text" class="form-control" id="inputCognome" placeholder="Cognome" name="cognome" value="${user.cognome}" oninput="validaCognome()">
	                </div>
				</div>
	            <div class="form-row">
	                <div class="form-group col-md-6">
	                  	<label for="inputPhone">Telefono</label>
	                  	<input type="text" class="form-control" id="inputPhone" placeholder="Telefono" name="telefono" value="${user.telefono}" oninput="validaTelefono()">
	                </div>
	                <div class="form-group col-md-6">
	                  	<label for="inputEmail">Mail (diversa da quella di utenti esistenti)</label>
	                  	<input type="email" class="form-control" id="inputEmail" placeholder="Mail" name="mail" value="${user.mail}" oninput="validaEmail()">
	                </div>
	            </div>
            	<div class="form-row">
	            	<div class="form-group col-md-12 ">
	                	<label for="inputAddress">Via</label>
	                	<input type="text" class="form-control" id="inputAddress" placeholder="Via" name="via" value="${user.via}" oninput="validaVia()">
	             	</div>
              	</div>
             	<div class="form-row">
                	<div class="form-group col-md-6">
                  		<label for="inputCity">Città</label>
                  		<input type="text" class="form-control" id="inputCity" placeholder="Citta" name="citta" value="${user.citta}" oninput="validaCitta()">
                	</div>
                	<div class="form-group col-md-6">
                  		<label for="inputCap">Cap</label>
                  		<input type="text" class="form-control" id="inputCap" placeholder="Cap" name="cap" value="${user.cap}" oninput="validaCap()">
                	</div>
             	</div>
             	<div class="form-row">
                	<div class="form-group">
                  		<div class="form-check " align="center">		    
		               		<input type="checkbox" class="form-check-input" id="defaultCheck" name="admin" value="true" onclick="cambiaStatoModifica()"
		               		<c:if test="${user.admin == true}">checked</c:if>>
		                	<label for="defaultCheck" class="form-check-label">Admin</label>
	             		</div>
                	</div>
             	</div>
             	<button type="submit" class="btn btn-danger" id="update" disabled >Modifica</button><br><span id="modificamimessaggio"></span>
            </form>                
        </div>
    </div>
</div>
<script>
		var borderOk = '2px solid #080';
		var borderNo = '2px solid #f00';
		var usernameOk = true;
		var passwordOk = true;
		var nomeOk = true;
		var cognomeOk = true;
		var telefonoOk = true;
		var emailOk = true;
		var viaOk = true;
		var cittaOk = true;
		var capOk = true;
		var inputUsr = document.forms['modifica']['username'].value;
		var inputMail = document.forms['modifica']['mail'].value;
	
		function validaUsername() {
			var input = document.forms['modifica']['username'];
			if (input.value.length >= 6 && input.value.match(/^[0-9a-zA-Z]+$/)) {
				// verifica se esiste un utente con lo stresso username
				var xmlHttpReq = new XMLHttpRequest();
				xmlHttpReq.onreadystatechange = function() {
					if (this.readyState == 4 && this.status == 200
							&& this.responseText == '<ok/>' ) {
						usernameOk = true;
						input.style.border = borderOk;
					} else {
						input.style.border = borderNo;
						usernameOk = false;
					}
					cambiaStatoModifica();
				}
				xmlHttpReq.open("GET", "VerificaUsername?username="
						+ encodeURIComponent(input.value)+"&usernameOld="
						+ encodeURIComponent(inputUsr), true);
				xmlHttpReq.send();
			}
			else{
				input.style.border = borderNo;
				usernameOk = false;
				cambiaStatoModifica();
			}
		}
	
		function validaPassword() {
		var password = document.forms['modifica']['password'];
			if (password.value.length >= 8 && /[0-9]/.test(password.value)) {
				password.style.border = borderOk;
				passwordOk = true;
			} else {
				password.style.border = borderNo;
				passwordOk = false;
			}
			cambiaStatoModifica();
		}
	
		function validaNome() {
			var input = document.forms['modifica']['nome'];
			if (input.value.trim().length > 0
					&& input.value.match(/^[ a-zA-Z\u00C0-\u00ff]+$/)) {
				input.style.border = borderOk;
				nomeOk = true;
			} else {
				input.style.border = borderNo;
				nomeOk = false;
			}
			cambiaStatoModifica();
		}
		function validaCognome() {
			var input = document.forms['modifica']['cognome'];
			if (input.value.trim().length > 0
					&& input.value.match(/^[ a-zA-Z\u00C0-\u00ff]+$/)) {
				input.style.border = borderOk;
				cognomeOk = true;
			} else {
				input.style.border = borderNo;
				cognomeOk = false;
			}
			cambiaStatoModifica();
		}
		
		function validaTelefono(){
			
			var input = document.forms['modifica']['telefono'];
			if(input.value.match(/^[0-9]{10}$/)){
				input.style.border = borderOk;
				telefonoOk = true;
			} else {
				input.style.border = borderNo;
				telefonoOk = false;
			}
			cambiaStatoModifica();
		}
	
		function validaEmail() {
			var input = document.forms['modifica']['mail'];
			if (input.value.match(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w+)+$/)) {
				var xmlHttpReq = new XMLHttpRequest();
				xmlHttpReq.onreadystatechange = function() {
					if (this.readyState == 4 && this.status == 200 && this.responseText == '<ok/>')
					{
						emailOk = true;
						input.style.border = borderOk;
					} else {
						input.style.border = borderNo;
						emailOk = false;
					}
					cambiaStatoModifica();
				}
				xmlHttpReq.open("GET", "VerificaEmail?mail="
						+ encodeURIComponent(input.value)+"&mailOld="
						+ encodeURIComponent(inputMail), true);
				xmlHttpReq.send();
			} else {
				input.style.border = borderNo;
				emailOk = false;
			}
			cambiaStatoModifica();
		}
		
		function validaVia() {
			var input = document.forms['modifica']['via'];
			if (input.value.trim().length > 0
					&& input.value.match(/^[ 0-9a-zA-Z\u00C0-\u00ff]+$/)) {
				input.style.border = borderOk;
				viaOk = true;
			} else {
				input.style.border = borderNo;
				viaOk = false;
			}
			cambiaStatoModifica();
		}
		
		function validaCitta() {
			var input = document.forms['modifica']['citta'];
			if (input.value.trim().length > 0
					&& input.value.match(/^[ a-zA-Z\u00C0-\u00ff]+$/)) {
				input.style.border = borderOk;
				cittaOk = true;
			} else {
				input.style.border = borderNo;
				cittaOk = false;
			}
			cambiaStatoModifica();
		}
		function validaCap() {
			var input = document.forms['modifica']['cap'];
			if(input.value.match(/^[0-9]{5}$/)){
				input.style.border = borderOk;
				capOk = true;
			} else {
				input.style.border = borderNo;
				capOk = false;
			}
			cambiaStatoModifica();
		}
	
		function cambiaStatoModifica() {
			if (usernameOk && passwordOk && nomeOk && cognomeOk && telefonoOk && emailOk && viaOk && cittaOk && capOk) {
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
 
<%@include file="footer.html"%>