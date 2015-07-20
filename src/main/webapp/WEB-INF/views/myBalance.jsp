<!-- MyBalance Section -->
<div id="mybalanceDiv" class="page">
<div class="container">
    <!-- MyBalance Form -->
    <div class="row">
		<div align="center">
		   <form id="balanceAlterQForm">
		   		<table class="tablaQuiniGold">
		   			<tr align="center">
						<td colspan="2">SALDO</td>
					</tr>
			   		<tr>
			   			<td>Usuario:</td>
			   			<td><input class="textbox" id="idSaldo" name="id" type="text" readonly="readonly"/></td>
			        </tr>
			   		<tr>
			   			<td>Saldo:</td>
			   			<td><input class="textbox" name="balance" id="balanceSaldo" type="text" readonly="true"/></td>
			        </tr>
			   		<tr align="right" style="display:none" >
			   			<td></td>
			   			<td><button id="balanceAlterQFormSubmit_btn" class="btn btn-danger" name="submitBtn" value="submitBtn">Enviar</button></td>
			        </tr>
		   		</table>
		         <div id="balanceAlterQFormResponse" class="linkQuiniGold" style="display:none">Actualiza tu saldo y pulsa Enviar.</div>
	        </form>
		</div>
    </div>
    <!-- End MyBalance Form -->
</div>
</div>
<!-- End MyBalance Section -->
