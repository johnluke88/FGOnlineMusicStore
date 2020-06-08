<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="header.jsp">
  <jsp:param name="pageTitle" value="Profilo"/>
</jsp:include>
<div class="container">
	<br>
	<h2>Profilo di ${utente.nome}</h2> 
	<br>
	<c:choose>
		<c:when test="${notifica}">
	    	<form action = "UpdateProfilo" method ="post" name = "modifica">
		    	<input type="hidden" name="id" value="${utente.id}">
		        	<div class="form-row">
		           		<div class="form-group col-xs-6 col-sm-6 col-md-6">
		                	<label for="inputUsername">Username (almeno 6 caratteri, solo lettere e numeri)</label>
		                  	<input type="text" class="form-control" id="inputUsername" placeholder="Username" name="username" value="${utente.usr}" oninput="validaUsername()">
		                </div>
		                <div class="form-group col-xs-6 col-sm-6 col-md-6">
		                  	<label for="inputPassword">Password (almeno 8 caratteri, almeno un numero)</label>
		                  	<input type="password" class="form-control" id="inputPassword" placeholder="Password" name="password" oninput="validaPassword()">
		                </div>
		            </div>
		            <div class="form-row">
		            	<div class="form-group col-xs-6 col-sm-6 col-md-6">
		                  	<label for="inputNome">Nome (solo lettere e spazi)</label>
		                  	<input type="text" class="form-control" id="inputNome" placeholder="Nome" name="nome" value="${utente.nome}" oninput="validaNome()">
		                </div>
		                <div class="form-group col-xs-6 col-sm-6 col-md-6">
		                  	<label for="inputCognome">Cognome (solo lettere e spazi)</label>
		                  	<input type="text" class="form-control" id="inputCognome" placeholder="Cognome" name="cognome" value="${utente.cognome}" oninput="validaCognome()">
		                </div>
		            </div>
		            <div class="form-row">
		            	<div class="form-group col-xs-6 col-sm-6 col-md-6">
		                	<label for="inputPhone">Telefono</label>
		                  	<input type="text" class="form-control" id="inputPhone" placeholder="Telefono" name="telefono" value="${utente.telefono}" oninput="validaTelefono()">
		                </div>
		                <div class="form-group col-xs-6 col-sm-6 col-md-6">
		                  	<label for="inputEmail">Mail </label>
		                  	<input type="email" class="form-control" id="inputEmail" placeholder="Mail" name="mail" value="${utente.mail}" oninput="validaEmail()">
		                </div>
		            </div>
		            <div class="form-row">
			        	<div class="form-group col-xs-6 col-sm-6 col-md-6">
			            	<label for="inputAddress">Via</label>
			                <input type="text" class="form-control" id="inputAddress" placeholder="Via" name="via" value="${utente.via}" oninput="validaVia()">
			            </div>
		            </div>
		            <div class="form-row">
		            	<div class="form-group col-xs-6 col-sm-6 col-md-6">
		                	<label for="inputCity">Citta</label>
		                  	<input type="text" class="form-control" id="inputCity" placeholder="Citta" name="citta" value="${utente.citta}" oninput="validaCitta()">
		                </div>
		                <div class="form-group col-xs-6 col-sm-6 col-md-6">
		                	<label for="inputCap">Cap</label>
		                 	<input type="text" class="form-control" id="inputCap" placeholder="Cap" name="cap" value="${utente.cap}" oninput="validaCap()">
		                </div>
		            </div>
		            <button type="submit" class="btn btn-danger" id="update" disabled >Modifica</button><br><span id="modificamimessaggio"></span>
	        </form>
		</c:when>
		<c:otherwise>
			<div class="form-row">
				<div class="form-group col-xs-6 col-sm-6 col-md-6 ">
					<label for="inputUsername">Username:</label>
		   			<input type="text" class="form-control" id="inputUsername" value="${utente.usr}" disabled>		
				</div>
			</div>
			<div class="form-row">
				<div class="form-group col-xs-6 col-sm-6 col-md-6 ">
		        	<label for="inputNome">Nome:</label>
		         	<input type="text" class="form-control" id="inputNome" value="${utente.nome}" disabled>
		    	</div>
	       		<div class="form-group col-xs-6 col-sm-6 col-md-6 ">
	            	<label for="inputCognome">Cognome:</label>
	             	<input type="text" class="form-control" id="inputCognome" value="${utente.cognome}" disabled>
	        	</div>
			</div>
	    	<div class="form-row">
	       		<div class="form-group col-xs-6 col-sm-6 col-md-6 ">
	         		<label for="inputPhone">Telefono:</label>
	         		<input type="text" class="form-control" id="inputPhone"value="${utente.telefono}" disabled>
	       		</div>
	       		<div class="form-group col-xs-6 col-sm-6 col-md-6">
	         		<label for="inputEmail">Mail:</label>
	         		<input type="email" class="form-control" id="inputEmail" value="${utente.mail}" disabled>
	       		</div>
	    	</div>
	    	<div class="form-row">
	  	  		<div class=" form-group col-xs-6 col-sm-6 col-md-6 ">
	    	   		<label for="inputAddress">Via:</label>
	    	   		<input type="text" class="form-control" id="inputAddress" value="${utente.via}" disabled>
	      		</div>
	    	</div>
	   		<div class="form-row">
	      		<div class="form-group col-xs-6 col-sm-6 col-md-6 ">
	        		<label for="inputCity">Citta:</label>
	        		<input type="text" class="form-control" id="inputCity" value="${utente.citta}" disabled>
	      		</div>
	      		<div class=" form-group col-xs-6 col-sm-6 col-md-6">
	        		<label for="inputCap">Cap:</label>
	        		<input type="text" class="form-control" id="inputCap" value="${utente.cap}" disabled>
	      		</div>
	   		</div>
	   		<div class="form-row">
				<div class="form-group col-xs-12 col-sm-12 col-md-12">
					<form action="ProfiloServlet" method="post">
						<input type="hidden" name="id" value="${utente.id}">
						<input type="hidden" name="utente" value="${utente}">
						<input type="submit" class="btn btn-danger" id="myButton" name="modifica" value="Modifica">
						<input type="submit" class="btn btn-danger" id="myButton" name="rimuovi" value="Elimina account">
					</form>
				</div>
			</div>
		</c:otherwise>
	</c:choose>
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
<%@ include file="footer.html" %>