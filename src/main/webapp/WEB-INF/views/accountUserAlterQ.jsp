<!-- My Account -->
<div id="myaccountDiv" class="page">
<div class="container">
    	<div class="row">
            <div  align="center" >
    	<table class="menuop">
    	<tr>
    	<td>
        	<img src="<c:url value='/static/resources/_include/img/options/vacioS.png'/>">
	    </td>
        
        <td>
                <img src="<c:url value='/static/resources/_include/img/options/vacioS.png'/>">
		</td>
		</tr>
    	<tr>
    	<td>
    		<div id="menuOpcion">
    		<a title="Mis Datos">
                <img id="menuMainOp31" src="<c:url value='/static/resources/_include/img/options/option-31.png'/>" alt="Mis Datos">
            </a>
            </div>
	    </td>
        
        <td>
    		<div id="menuOpcion">
    		<a title="Mi Saldo">
                <img id="menuMainOp32" src="<c:url value='/static/resources/_include/img/options/option-32.png'/>" alt="Mi Saldo">
            </a>
            </div>
		</td>
		</tr>
    	<tr align="center">
    	<td>
                <label class="Option">DATOS</label>
	    </td>
        
        <td>
                <label class="Option">SALDO</label>
		</td>
		</tr>
    	<tr>
    	<td>
                <img src="<c:url value='/static/resources/_include/img/options/vacioS.png'/>">
	    </td>
        
        <td>
                <img src="<c:url value='/static/resources/_include/img/options/vacioS.png'/>">
		</td>
		</tr>
		<tr>
		<td>
    		<div id="menuOpcion">
    		<a title="Mi Password">
                <img id="menuMainOp33" src="<c:url value='/static/resources/_include/img/options/option-33.png'/>" alt="Mi Password">
            </a>
            </div>
		</td>
		<td>
    		<div id="menuOpcion">
    		<a title="Mis Apuestas LAE">
                <img id="menuMainOp34" src="<c:url value='/static/resources/_include/img/options/option-34.png'/>" alt="Mis Apuestas LAE">
            </a>
            </div>
        </td>
        </tr>
    	<tr align="center">
    	<td>
                <label class="Option">PASSWD</label>
	    </td>
        
        <td>
                <label class="Option">APUESTAS</label>
		</td>
		</tr>
    	<tr>
    	<td>
                <img src="<c:url value='/static/resources/_include/img/options/vacioS.png'/>">
	    </td>
        
        <td>
                <img src="<c:url value='/static/resources/_include/img/options/vacioS.png'/>">
		</td>
		</tr>
        </table>
			</div>
            </div>
        </div>
</div>
</div>
<!-- End My Account -->

<!-- New Password Section -->
<div id="newPasswordDiv" class="page">
<div class="container">
    <!-- Forgot Form -->
    <div class="row">
		<div align="center">
		   <form id="newPwdForm">
		   		<table class="tablaQuiniGold">
		   			<tr  align="center">
						<td colspan="2">CAMBIAR PASSWORD</td>
					</tr>
			   		<tr>
			   			<td>Password Antigua:</td>
			   			<td><input id="pwdOldNewPwd" type="password" size="20" name="pwdOld"/></td>
			        </tr>
			   		<tr>
			   			<td>Password Nueva:</td>
			   			<td><input id="pwdNewNewPwd" type="password" size="20" name="pwdNew"/></td>
			        </tr>
			   		<tr align="center">
			   			<td align="center" colspan="2"><button id="new_pwd_btn" class="btn btn-danger" name="newpwd" value="send">Cambiar</button></td>
			        </tr>
		   		</table>
		   		<br>
		   		<br>
		   		<div id="newPwdFormResponse" class="linkQuiniGold">respuesta </div>
	        </form>
		</div>
    </div>
    <!-- End New Password Form -->
</div>
</div>
<!-- End New Password Section -->

<!-- MyData Section -->
<div id="mydataDiv" class="page">
<div class="container">
    <!-- MyData Form -->
    <div class="row">
		<div align="center">
		   <form id="myDataForm">
		   		<table class="tablaQuiniGold">
		   			<tr align="center">
						<td colspan="2">MI CUENTA<BR></td>
					</tr>
			   		<tr>
			   			<td>Usuario:</td>
			   			<td><input  class="textbox" id="idData" name="id" type="text" readonly="readonly"/></td>
			        </tr>
			   		<tr>
			   			<td>Nick:</td>
			   			<td><input  class="textbox" id="nickData" name="nick" type="text"/></td>
			        </tr>
			   		<tr>
			   			<td>Nombre:</td>
			   			<td><input class="textbox" name="name" id="nameData" type="text"/></td>
			        </tr>
			   		<tr>
			   			<td>Apellidos:</td>
			   			<td><input  class="textbox" id="idSurnamesData" name="surnames" type="text"/></td>
			        </tr>
			   		<tr class="quiniela">
			   			<td>
			   			<select name="typeID" id="typeIDSign">
   							<option value="1">NIF</option>
   							<option value="2">NIE</option>
   							<option value="3">Pasaporte</option>
						</select> 
			   			</td>
			   			<td><input class="textbox" name="idCard" id="idCardData" type="text"/></td>
			        </tr>
			   		<tr>
			   			<td>Telefono:</td>
			   			<td><input class="textbox" name="phoneNumber" id="phoneNumberData" type="text"/></td>
			        </tr>
			   		<tr>
			   			<td>Nacimiento:</td>
			   			<td><input class="textbox" name="birthday" id="birthDateData" type="text" placeholder="dd/mm/aaaa"/></td>
			        </tr>
			   		<tr>
			   			<td>Provincia:</td>
			   			<td>
				   			<select name="city" id="citySign">
								<option value="0">Tu Provincia</option>
		                        <option value="1">�lava</option>
		                        <option value="2">Albacete</option>
		                        <option value="3">Alicante</option>
		                        <option value="4">Almer�a</option>
		                        <option value="5">Asturias</option>
		                        <option value="6">�vila</option>
		                        <option value="7">Badajoz</option>
		                        <option value="8">Barcelona</option>
		                        <option value="9">Burgos</option>
		                        <option value="10">C�ceres</option>
		                        <option value="11">C�diz</option>
		                        <option value="12">Cantabria</option>
		                        <option value="13">Castell�n</option>
		                        <option value="14">Ceuta</option>
		                        <option value="15">Ciudad Real</option>
		                        <option value="16">C�rdoba</option>
		                        <option value="17">Cuenca</option>
		                        <option value="17">Girona</option>
		                        <option value="18">Granada</option>
		                        <option value="19">Guadalajara</option>
		                        <option value="20">Guip�zcoa</option>
		                        <option value="21">Huelva</option>
		                        <option value="22">Huesca</option>
		                        <option value="23">Illes Balears</option>
		                        <option value="24">Ja�n</option>
		                        <option value="25">A Coru�a</option>
		                        <option value="26">La Rioja</option>
		                        <option value="27">Las Palmas</option>
		                        <option value="28">Le�n</option>
		                        <option value="29">Lleida</option>
		                        <option value="30">Lugo</option>
		                        <option value="31">Madrid</option>
		                        <option value="32">M�laga</option>
		                        <option value="33">Melilla</option>
		                        <option value="34">Murcia</option>
		                        <option value="35">Navarra</option>
		                        <option value="36">Ourense</option>
		                        <option value="37">Palencia</option>
		                        <option value="38">Pontevedra</option>
		                        <option value="39">Tenerife</option>
		                        <option value="40">Salamanca</option>
		                        <option value="41">Segovia</option>
		                        <option value="42">Sevilla</option>
		                        <option value="43">Soria</option>
		                        <option value="44">Tarragona</option>
		                        <option value="45">Teruel</option>
		                        <option value="46">Toledo</option>
		                        <option value="47">Valencia</option>
		                        <option value="48">Valladolid</option>
		                        <option value="49">Vizcaya</option>
		                        <option value="50">Zamora</option>
		                        <option value="51">Zaragoza</option>
		                        <option value="99" >No residente</option>			   			
							</select> 
			   			</td>
			        </tr>
			   		<tr>
			   			<td colspan="2"><input class="textbox" name="accept" id="acceptData" type="checkbox"/>Aceptar terminos y condiciones de uso.</td>
			        </tr>
			   		<tr align="center">
			   			<td align="center" colspan="2"><button id="myDataFormSubmit_btn" class="btn btn-danger" name="submitBtn" value="submitBtn">Enviar</button></td>
			        </tr>
		        </table>
		        <br>
		        <br>
		        <div id="userAlterQFormResponse" class="linkQuiniGold">Actualiza tus datos y pulsa Enviar.</div>
	        </form>
		</div>
    </div>
    <!-- End MyData Form -->
</div>
</div>
<!-- End MyData Section -->

<!-- MyBets Section -->
<div id="mybetsDiv" class="page">
<div class="container">
    <!-- Quiniela Form -->
		<div align="center">
			<form id="betsForm">
				    <table class="tablaQuiniGold" id="apuestasTable">
				    </table>
			</form>
	        <table class="tablaQuiniGold">
	        	<tr align="center"><td colspan="2">GESTION</td></tr>
	        	<tr>
			   		<td>Autom�ticas:</td>
			   		<td><input class="textbox" name="auto" id="companyAutoBets" type="text"/></td>
			    </tr>
	        	
		   		<tr align="center">
		   			<td colspan="2"><button id="myBetsAutomBtn" class="btn btn-danger" name="submitBtn" value="submitBtn">Modificar</button></td>
		        </tr>
		        
	        </table>
	        <br>	        
	        <div id="betsAutomResponse" class="linkQuiniGold">Introduce las apuestas autom�ticas y pulsa Modificar.</div>
		</div>
    
    <!-- End Sign Up Form -->
</div>
</div>
<!-- End MyBets Section -->

<!-- MyRanking Section -->
<div id="myrankingDiv" class="page">
<div class="container">
    <!-- Quiniela Form -->
		<div align="center">
			<form id="rankikngForm">
				    <table class="tablaQuiniGold" id="rankingSelectTable">
				    </table>
				    <table class="tablaQuiniGold" id="rankingTable">
				    </table>
			</form>
		</div>
    
    <!-- End Sign Up Form -->
</div>
</div>
<!-- End MyBets Section -->

<!-- Sing Up Section -->
<div id="signDiv" class="page">
<div class="container">
    <!-- Sign Up Form -->
    <div class="row">
		<div align="center">
		   <form id="signupForm">
		   		<table class="tablaQuiniGold">
		   			<tr align="center">
						<td colspan="2">NUEVO USUARIO</td>
					</tr>
			   		<tr>
			   			<td colspan="2"><input type="text" name="id" id="idSign" placeholder="e-Mail"/></td>
			        </tr>
			   		<tr>
			   			<td colspan="2"><input type="text" name="nick" id="nickSign" placeholder="Nick"/></td>
			        </tr>
			   		<tr>
			   			<td colspan="2"><input type="text" name="name" id="nameSign" placeholder="Nombre"/></td>
			        </tr>
			   		<tr>
			   			<td colspan="2"><input type="text" name="surnames" id="surnamesSign" placeholder="Apellidos"/></td>
			        </tr>
			   		<tr>
			   			<td colspan="2">
			   			<select name="typeID" id="typeIDSign">
   							<option value="1">NIF</option>
   							<option value="2">NIE</option>
   							<option value="3">Pasaporte</option>
							</select> 
			   			</td>
			        </tr>
			   		<tr>
			   			<td colspan="2"><input type="text" name="idCard" id="nifSign" placeholder="NIF-NIE-Pasaporte"/></td>
			        </tr>
			   		<tr>
			   			<td colspan="2"><input type="password" name="pwd" id="pwdSign" placeholder="Password"/></td>
			        </tr>
			   		<tr>
			   			<td colspan="2"><input type="text" name="phoneNumber" id="phoneNumberSign" placeholder="Telefono"/></td>
			        </tr>
			   		<tr>
			   			<td colspan="2"><input type="text" name="birthday" id="birthDateSign" placeholder="Fecha de Nacimiento"/></td>
			        </tr>
			   		<tr>
			   			<td colspan="2">
				   			<select name="city" id="citySign">
								<option value="0">tu provincia</option>
		                        <option value="1">�lava</option>
		                        <option value="2">Albacete</option>
		                        <option value="3">Alicante</option>
		                        <option value="4">Almer�a</option>
		                        <option value="5">Asturias</option>
		                        <option value="6">�vila</option>
		                        <option value="7">Badajoz</option>
		                        <option value="8">Barcelona</option>
		                        <option value="9">Burgos</option>
		                        <option value="10">C�ceres</option>
		                        <option value="11">C�diz</option>
		                        <option value="12">Cantabria</option>
		                        <option value="13">Castell�n</option>
		                        <option value="14">Ceuta</option>
		                        <option value="15">Ciudad Real</option>
		                        <option value="16">C�rdoba</option>
		                        <option value="17">Cuenca</option>
		                        <option value="17">Girona</option>
		                        <option value="18">Granada</option>
		                        <option value="19">Guadalajara</option>
		                        <option value="20">Guip�zcoa</option>
		                        <option value="21">Huelva</option>
		                        <option value="22">Huesca</option>
		                        <option value="23">Illes Balears</option>
		                        <option value="24">Ja�n</option>
		                        <option value="25">A Coru�a</option>
		                        <option value="26">La Rioja</option>
		                        <option value="27">Las Palmas</option>
		                        <option value="28">Le�n</option>
		                        <option value="29">Lleida</option>
		                        <option value="30">Lugo</option>
		                        <option value="31">Madrid</option>
		                        <option value="32">M�laga</option>
		                        <option value="33">Melilla</option>
		                        <option value="34">Murcia</option>
		                        <option value="35">Navarra</option>
		                        <option value="36">Ourense</option>
		                        <option value="37">Palencia</option>
		                        <option value="38">Pontevedra</option>
		                        <option value="39">Tenerife</option>
		                        <option value="40">Salamanca</option>
		                        <option value="41">Segovia</option>
		                        <option value="42">Sevilla</option>
		                        <option value="43">Soria</option>
		                        <option value="44">Tarragona</option>
		                        <option value="45">Teruel</option>
		                        <option value="46">Toledo</option>
		                        <option value="47">Valencia</option>
		                        <option value="48">Valladolid</option>
		                        <option value="49">Vizcaya</option>
		                        <option value="50">Zamora</option>
		                        <option value="51">Zaragoza</option>
		                        <option value="99" >No residente</option>			   			
							</select> 
			   			</td>
			        </tr>
			        
			   		<tr>
			   			<td><input type="checkbox" name="accept" id="acceptData"/></td>
			   			<td><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">Acepto los terminos y condiciones.</button></td>
			        </tr>
			   		<tr>
			   			<td colspan="2"><br></td>
			        </tr>
			   		<tr align="center">
			   			<td align="center" colspan="2"><button id="signup_btn" class="btn btn-danger" name="signup" value="signup">Crear Usuario</button></td>
			        </tr>
			   		<tr>
			   			<td colspan="2"><br></td>
			        </tr>
        
			   		<tr align="center">
			   			<td colspan="2">
				   			<div class="alert fade in" id="signupFormResponse"></div>
						</td>
			        </tr>
		   		</table>
	        </form>
		</div>
    </div>
    <!-- End Sign Up Form -->
</div>
</div>
<!-- End Sign Up Section -->

