<!-- My Account -->
<div id="myaccountDiv" class="page">
<div class="container">
    	<div class="row">
            <div  align="center" >
            <table class="tablaQuiniGold">
		   			<tr align="center">
		   				
						<td><br>INFORMACION DEL USUARIO</td>
					</tr>
			   		<tr align="center">
			   			<td align="center">
							<br>
							<button id="myDataBtn" type="button" class="btn btn-danger btn-block">Mis Datos</button>
							<br>
		  					<button id="myBalanceBtn" type="button" class="btn btn-danger btn-block">Mi Saldo</button>
		  					<br>
		  					<button id="myBetsBtn" type="button" class="btn btn-danger btn-block">Mis Apuestas</button>
		  					<br>
			   			</td>
			        </tr>
			</table>            
<!--            	<div class="well" id="myDataBtn">Mis Datos</div>	-->
<!--	           	<div class="well" id="myBalanceBtn">Mi Saldo</div>	-->
<!--    	       	<div class="well" id="myBetsBtn">Mis Apuestas</div>	-->

<!--            	<div class="well" id="myRankBtn">Ranking</div>	 -->
<!--            	<div class="well" id="myResumBtn">Resumen</div>	 -->
			</div>
            </div>
        </div>
</div>
</div>
<!-- End My Account -->


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
			   			<td><input class="textbox" name="birthday" id="birthDateData" type="text"/></td>
			        </tr>
			   		<tr>
			   			<td>Provincia:</td>
			   			<td>
				   			<select name="city" id="citySign">
								<option value="0">Tu Provincia</option>
		                        <option value="1">Álava</option>
		                        <option value="2">Albacete</option>
		                        <option value="3">Alicante</option>
		                        <option value="4">Almería</option>
		                        <option value="5">Asturias</option>
		                        <option value="6">Ávila</option>
		                        <option value="7">Badajoz</option>
		                        <option value="8">Barcelona</option>
		                        <option value="9">Burgos</option>
		                        <option value="10">Cáceres</option>
		                        <option value="11">Cádiz</option>
		                        <option value="12">Cantabria</option>
		                        <option value="13">Castellón</option>
		                        <option value="14">Ceuta</option>
		                        <option value="15">Ciudad Real</option>
		                        <option value="16">Córdoba</option>
		                        <option value="17">Cuenca</option>
		                        <option value="17">Girona</option>
		                        <option value="18">Granada</option>
		                        <option value="19">Guadalajara</option>
		                        <option value="20">Guipúzcoa</option>
		                        <option value="21">Huelva</option>
		                        <option value="22">Huesca</option>
		                        <option value="23">Illes Balears</option>
		                        <option value="24">Jaén</option>
		                        <option value="25">A Coruña</option>
		                        <option value="26">La Rioja</option>
		                        <option value="27">Las Palmas</option>
		                        <option value="28">León</option>
		                        <option value="29">Lleida</option>
		                        <option value="30">Lugo</option>
		                        <option value="31">Madrid</option>
		                        <option value="32">Málaga</option>
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
		                        <option value="1">Álava</option>
		                        <option value="2">Albacete</option>
		                        <option value="3">Alicante</option>
		                        <option value="4">Almería</option>
		                        <option value="5">Asturias</option>
		                        <option value="6">Ávila</option>
		                        <option value="7">Badajoz</option>
		                        <option value="8">Barcelona</option>
		                        <option value="9">Burgos</option>
		                        <option value="10">Cáceres</option>
		                        <option value="11">Cádiz</option>
		                        <option value="12">Cantabria</option>
		                        <option value="13">Castellón</option>
		                        <option value="14">Ceuta</option>
		                        <option value="15">Ciudad Real</option>
		                        <option value="16">Córdoba</option>
		                        <option value="17">Cuenca</option>
		                        <option value="17">Girona</option>
		                        <option value="18">Granada</option>
		                        <option value="19">Guadalajara</option>
		                        <option value="20">Guipúzcoa</option>
		                        <option value="21">Huelva</option>
		                        <option value="22">Huesca</option>
		                        <option value="23">Illes Balears</option>
		                        <option value="24">Jaén</option>
		                        <option value="25">A Coruña</option>
		                        <option value="26">La Rioja</option>
		                        <option value="27">Las Palmas</option>
		                        <option value="28">León</option>
		                        <option value="29">Lleida</option>
		                        <option value="30">Lugo</option>
		                        <option value="31">Madrid</option>
		                        <option value="32">Málaga</option>
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

