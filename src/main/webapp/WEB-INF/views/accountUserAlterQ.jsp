<!-- My Account -->
<div id="myaccountDiv" class="page">
<div class="container">
    	<div class="row">
            <div class="span12">
            	<div class="well" id="myDataBtn">Mis Datos</div>
            	<div class="well" id="myBalanceBtn">Mi Saldo</div>
            	<div class="well" id="myBetsBtn">Mis Apuestas</div>
<!--            	<div class="well" id="myRankBtn">Ranking</div>	 -->
<!--            	<div class="well" id="myResumBtn">Resumen</div>	 -->
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
		   		<table class="quiniela">
		   			<tr class="quinielatitulo">
						<td colspan="2">My Account</td>
					</tr>
			   		<tr class="quiniela">
			   			<td class="partidoLast">Username:</td>
			   			<td class="partidoLast"><input  class="textbox" id="idData" name="id" type="text" readonly="readonly"/></td>
			        </tr>
			   		<tr class="quiniela">
			   			<td class="partidoLast">Nick:</td>
			   			<td class="partidoLast"><input  class="textbox" id="nickData" name="nick" type="text"/></td>
			        </tr>
			   		<tr class="quiniela">
			   			<td class="partidoLast">Name:</td>
			   			<td class="partidoLast"><input class="textbox" name="name" id="nameData" type="text"/></td>
			        </tr>
			   		<tr class="quiniela">
			   			<td class="partidoLast">Surnames:</td>
			   			<td class="partidoLast"><input  class="textbox" id="idSurnamesData" name="surnames" type="text"/></td>
			        </tr>
			   		<tr class="quiniela">
			   			<td class="partidoLast">
			   			<select name="typeID" id="typeIDSign">
   							<option value="1">NIF</option>
   							<option value="2">NIE</option>
   							<option value="3">Pasaporte</option>
						</select> 
			   			</td>
			   			<td class="partidoLast"><input class="textbox" name="idCard" id="idCardData" type="text"/></td>
			        </tr>
			   		<tr class="quiniela">
			   			<td class="partidoLast">PhoneNumber:</td>
			   			<td class="partidoLast"><input class="textbox" name="phoneNumber" id="phoneNumberData" type="text"/></td>
			        </tr>
			   		<tr>
			   			<td class="partidoLast">BirthDate:</td>
			   			<td class="partidoLast"><input class="textbox" name="birthday" id="birthDateData" type="text"/></td>
			        </tr>
			   		<tr>
			   			<td class="partidoLast">City:</td>
			   			<td class="partidoLast">
			   			<select name="city" id="citySign">
   							<option value="1">Alicante</option>
   							<option value="2">Barcelona</option>
   							<option value="3">Castellon</option>
   							<option value="4">Girona</option>
   							<option value="5">Lleida</option>
   							<option value="6">Tarragona</option>
   							<option value="6">Valencia</option>
   							<option value="0">No Residente</option>
						</select> 
			   			</td>
			        </tr>
			   		<tr class="quiniela">
			   			<td class="partidoLast" colspan="2"><input class="textbox" name="accept" id="acceptData" type="checkbox"/>Aceptar terminos y condiciones de uso.</td>
			        </tr>
			   		<tr class="quiniela" align="center">
			   			<td align="center" colspan="2" class="partidoLast"><button id="myDataFormSubmit_btn" class="button" name="submitBtn" value="submitBtn">Enviar</button></td>
			        </tr>
		        </table>
		        	<div id="userAlterQFormResponse">Actualiza tus datos y pulsa Enviar.</div>
	        </form>
		</div>
    </div>
    <!-- End MyData Form -->
</div>
</div>
<!-- End MyData Section -->


<!-- Sing Up Section -->
<div id="signDiv" class="page">
<div class="container">
    <!-- Sign Up Form -->
    <div class="row">
		<div align="center">
		   <form id="signupForm">
		   		<table class="quiniela">
		   			<tr class="quinielatitulo">
						<td colspan="2">Sign up</td>
					</tr>
			   		<tr>
			   			<td colspan="2" class="partidoLast"><input type="text" name="nick" id="nickSign" placeholder="Nick"/></td>
			        </tr>
			   		<tr>
			   			<td colspan="2" class="partidoLast"><input type="text" name="name" id="nameSign" placeholder="Name"/></td>
			        </tr>
			   		<tr>
			   			<td colspan="2" class="partidoLast"><input type="text" name="surnames" id="surnamesSign" placeholder="Surnames"/></td>
			        </tr>
			   		<tr>
			   			<td colspan="2" class="partidoLast">
			   			<select name="typeID" id="typeIDSign">
   							<option value="1">NIF</option>
   							<option value="2">NIE</option>
   							<option value="3">Pasaporte</option>
							</select> 
			   			</td>
			        </tr>
			   		<tr>
			   			<td colspan="2" class="partidoLast"><input type="text" name="idCard" id="nifSign" placeholder="ID"/></td>
			        </tr>
			   		<tr>
			   			<td colspan="2" class="partidoLast"><input type="text" name="id" id="idSign" placeholder="E-Mail"/></td>
			        </tr>
			   		<tr>
			   			<td colspan="2" class="partidoLast"><input type="password" name="pwd" id="pwdSign" placeholder="Password"/></td>
			        </tr>
			   		<tr>
			   			<td colspan="2" class="partidoLast"><input type="text" name="phoneNumber" id="phoneNumberSign" placeholder="Phone Number"/></td>
			        </tr>
			   		<tr>
			   			<td colspan="2" class="partidoLast"><input type="text" name="birthday" id="birthDateSign" placeholder="Birth Date"/></td>
			        </tr>
			   		<tr>
			   			<td colspan="2" class="partidoLast">
			   			<select name="city" id="citySign">
   							<option value="1">Alicante</option>
   							<option value="2">Barcelona</option>
   							<option value="3">Castellon</option>
   							<option value="4">Girona</option>
   							<option value="5">Lleida</option>
   							<option value="6">Tarragona</option>
   							<option value="6">Valencia</option>
   							<option value="0">No Residente</option>
						</select> 
			   			</td>
			        </tr>
			        
			   		<tr>
			   			<td class="partidoLast"><input type="checkbox" name="accept" id="acceptData"/></td>
			   			<td class="partidoLast"><button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">Acepto los terminos y condiciones de uso.</button></td>
			        </tr>
			   		<tr class="quiniela" align="center">
			   			<td align="center" colspan="2" class="partidoLast"><button id="signup_btn" class="button" name="signup" value="signup">signup</button></td>
			        </tr>
			        
			   		<tr class="quiniela" align="center">
			   			<td colspan="2" class="partido">
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

