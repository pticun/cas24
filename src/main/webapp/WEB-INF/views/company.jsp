
<!-- MyCompany Section -->
<div id="myCompanyDiv" class="page">
<div class="container">
    <!-- MyCompany Form -->
    <div class="row">
		<div align="center">
		   <form id="myCompanyForm">
		   		<table class="tablaQuiniGold">
		   			<tr align="center">
						<td colspan="2">MIS GRUPOS</td>
					</tr>
			   		<tr>
			   			<td>Grupo:</td>
			   			<td>
			   			<select name="companyID" id="companyToChoose">
						</select> 
			   			</td>
			        </tr>
			   		<tr align="center">
			   			<td align="center" colspan="2"><button id="myCompanyFormSubmit_btn" class="btn btn-danger" name="submitBtn" value="submitBtn">Seleccionar</button></td>
			        </tr>
		        </table>
	        </form>
		        <br>
		   <form id="joinCompanyForm">
		   		<table class="tablaQuiniGold">
		   			<tr align="center">
						<td colspan="2">
							<select id="selectActionRolCompany">
								<option value="1">Unirse a --></option>
								<option value="2">Borrarse de --></option>
							</select>
						</td>
					</tr>
			   		<tr>
			   			<td>Grupo:</td>
			   			<td>
			   			<select name="companyID" id="companyToChoosePublic">
						</select> 
			   			</td>
			        </tr>
			   		<tr align="center">
			   			<td align="center" colspan="2"><button id="myCompanyFormSubmit_btn" class="btn btn-danger" name="submitBtn" value="submitBtn">Seleccionar</button></td>
			        </tr>
		        </table>
		        <br>
		        <div id="joinCompanyResponse" class="linkQuiniGold"></div>
	        </form>
		        <br>
		        <div class="linkQuiniGold">Elige el grupo y pulsa Seleccionar.</div>
		        <br>
		        <table class="tablaQuiniGold">
		        	<tr><td>GESTIONAR</td></tr>
			   		<tr align="center">
			   			<td><button id="myCompanyMgrBtn" class="btn btn-danger" name="submitBtn" value="submitBtn">+</button></td>
			        </tr>
		        </table>	        
		</div>
    </div>
    <!-- End MyCompany Form -->
</div>
</div>
<!-- End MyCompany Section -->

<!-- MyCompanyMgr Section -->
<div id="myCompanyMgrDiv" class="page">
<div class="container">
    <!-- AdminCompany Form -->
    <div class="row">
		<div align="center">
		   <form id="adminCompanyForm">
		   		<input type="hidden" name="visibility" value="true"/>
		   		<table class="tablaQuiniGold">
		   			<tr align="center">
						<td colspan="2">Crear Compañia</td>
					</tr>
			   		<tr>
			   			<td>Descripción:</td>
			   			<td><input class="textbox" id="idCompanyDesc" name="description" type="text"/></td>
			        </tr>
			   		<tr>
			   			<td>Nick:</td>
			   			<td><input class="textbox" name="nick" id="companyNick" type="text"/></td>
			        </tr>
			   		<tr>
			   			<td>Tipo:</td>
			   			<td>
							<select name="type" id="typeCompanyCreate">
								<option value="1">Pública</option>
								<option value="3">Privada</option>
							</select> 			   			
						</td>
			        </tr>
			   		<tr align="center">
			   			<td colspan="2"><button id="balanceAlterQFormSubmit_btn" class="btn btn-danger" name="submitBtn" value="submitBtn">Añadir</button></td>
			        </tr>
		        </table>
		        <br>
		        <br>
		        <div id="adminCompanyFormResponse" class="linkQuiniGold">Añade la compañia y pulsa Añadir.</div>
	        </form>
		</div>
    </div>
    <!-- End AdminCompany Form -->
</div>
</div>
<!-- End MyCompany Section -->

