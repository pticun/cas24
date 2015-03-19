<!-- MyBalance Section -->
<div id="mybalanceDiv" class="page">
<div class="container">
    <!-- MyBalance Form -->
    <div class="row">
		<div align="center">
		   <form id="balanceAlterQForm">
		   		<table class="quiniela">
		   			<tr class="quinielatitulo">
						<td colspan="2">Saldo</td>
					</tr>
			   		<tr>
			   			<td class="partidoLast">Username:</td>
			   			<td class="partidoLast"><input class="textbox" id="idSaldo" name="id" type="text" readonly="readonly"/></td>
			        </tr>
			   		<tr>
			   			<td class="partidoLast">Saldo:</td>
			   			<td class="partidoLast"><input class="textbox" name="balance" id="balanceSaldo" type="text" readonly="true"/></td>
			        </tr>
			   		<tr align="right" style="display:none" >
			   			<td class="partidoLast"></td>
			   			<td class="partidoLast"><button id="balanceAlterQFormSubmit_btn" class="button" name="submitBtn" value="submitBtn">Enviar</button></td>
			        </tr>
		   		</table>
		         <div id="balanceAlterQFormResponse" style="display:none">Actualiza tu saldo y pulsa Enviar.</div>
	        </form>
		</div>
    </div>
    <!-- End MyBalance Form -->
</div>
</div>
<!-- End MyBalance Section -->
