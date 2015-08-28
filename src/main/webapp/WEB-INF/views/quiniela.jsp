<!-- Quiniela Section -->
<div id="quinielaDiv" class="page">
<div class="container">
    <!-- Quiniela Form -->
		<div align="center">
			<form id="betForm">
					<table class="tablaQuiniGold">
						<tr>
							<td>
 							    <table id="quinielaTable">
								</table>
								<br>
						    	<table class="tablaQuiniGold" id="quinielaTable_in">
						    	</table>
							</td>
							<td>
							    <table id="quinielaTableRec" style="display: none">
							    </table>
							    <br>
							    <table class="tablaRedQuiniGold" id="quinielaTableRec_in" style="display: none">
							    </table>
							</td>
						</tr>
						<tr align="center">
							<td colspan="2">
							    <br>
								<table class="tablaQuiniGold" id="quinielaTablePleno15">
				    			</table>
							</td>
						</tr>
						<tr align="center">
							<td colspan="2">
							    <br>
							    <button id="quinielaButton" class="btn btn-danger" name="quiniela" value="Enviar">Enviar</button>
							    <br>
							</td>
						</tr>
					</table>
				    <p></p>
				    <table class="tablaQuiniGold" id="quinielaTableReduced">
				    </table>
				    <p></p>
				    <table class="tablaQuiniGold" id="quinielaTablePrize">
				    <tr align="center">
				    <td>APUESTAS</td>
				    <td width="70"><label id="labelApuestas">&nbsp;</label></td>
				    <td>PRECIO</td>
				    <td width="70"><label id="labelPrecio">&nbsp;</label></td>
				    <td><button id="prizeButton" class="btn btn-danger" name="prize" value="Precio">Precio</button></td>
				    </tr>
				    </table>
				    <p></p>
				    <!-- <input type="submit" value="Enviar"> -->
				    <div align="center" id="quinielaFormResponse" class="linkQuiniGold">Rellena tu apuesta y pulsa enviar.</div>
			</form>
		</div>
    
    <!-- End Quiniela Form -->
</div>
</div>
<!-- End Quiniela Section -->

<!-- Confirm Quiniela Section -->
<div id="confirmarQuinielaDiv" class="page">
<div class="container">
    <!-- Confirm Bet Form -->
		<div align="center">
			<form id="confirmBetForm">
				<input type="hidden" id="param_apuesta" name="param_apuesta" value="">
				<input type="hidden" id="param_reduccion" name="param_reduccion" value="">
				<input type="hidden" id="param_tiporeduccion" name="param_tiporeduccion" value="">
				<input type="hidden" id="param_numbets" name="param_numbets" value="">
				<table class="tablaQuiniGold">
					<tr>
						<td>
						    <table id="confirmarQuinielaTable">
						    </table>
						</td>
					</tr>
					<tr align="center">
						<td>
						    <button id="modificarQuinielaButton" class="btn btn-primary" name="modificarQuiniela" value="Modificar">Modificar</button>
						    <button id="confirmarQuinielaButton" class="btn btn-primary" name="confirmarQuiniela" value="Confirmar">Confirmar</button>
						</td>
					</tr>
				</table>
			    <p></p>
			    <div align="center" id="confirmarQuinielaFormResponse" class="linkQuiniGold">pulsa Confirmar para validar tu apuesta.</div>
			</form>
		</div>
    
    <!-- End Confirm Bet Form -->
</div>
</div>
<!-- End Confirm Quiniela Section -->

<!-- Confirmed Quiniela Section -->
<div id="confirmadaQuinielaDiv" class="page">
<div class="container">
    <!-- Confirm Bet Form -->
		<div align="center">
			<form id="confirmedBetForm">
				<table class="tablaQuiniGold">
					<tr>
						<td>
						    <table id="confirmadaQuinielaTable">
						    </table>
						</td>
					</tr>
					<tr align="center">
						<td>
						    <button id="confirmadaQuinielaButton" class="btn btn-danger" name="confirmadaQuiniela" value="Finalizar">Finalizar</button>
						</td>
					</tr>
				</table>
			</form>
		</div>
    
    <!-- End Confirm Bet Form -->
</div>
</div>
<!-- End Confirm Quiniela Section -->

