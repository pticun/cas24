
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
		        <br>
		        <br>
		        <div id="userAlterQFormResponse" class="linkQuiniGold">Elige el grupo y pulsa Seleccionar.</div>
		        <br>
		        <table class="tablaQuiniGold">
		        	<tr><td>GESTIONAR</td></tr>
			   		<tr align="center">
			   			<td><button id="myCompanyMgrBtn" class="btn btn-danger" name="submitBtn" value="submitBtn">+</button></td>
			        </tr>
		        </table>	        
	        </form>
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
			   		<tr align="right">
			   			<td></td>
			   			<td><button id="balanceAlterQFormSubmit_btn" class="btn btn-danger" name="submitBtn" value="submitBtn">Añadir</button></td>
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
<!-- End MyCompany Section -->

