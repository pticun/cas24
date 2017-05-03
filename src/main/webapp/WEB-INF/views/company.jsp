
<!-- MyCompany Section -->
<div id="myCompanyDiv" class="page">
<div class="container">
    <!-- MyCompany Form -->
    <div class="row">
		<div align="center">
	    	<table class="menuop">
	    	<tr>
	    	<td colspan="2">
	    		<div id="menuOpcion">
	    		<a title="MIS GRUPOS">
			   <form id="myCompanyForm">
	                <div id="MisGrBtns" align="center" style="overflow: auto;overflow-x: hidden;">
		       </form>
	            </a>
	            </div>
		    </td>
			</tr>
	    	<tr align="center">
	    	<td colspan="2">
	                <label class="Option">MIS GRUPOS</label>
		    </td>
			</tr>
			<tr>
			<td colspan="2">
	    		<div id="menuOpcion">
	    		<a title="Otros Grupos">
					<form id="joinCompanyForm">
						<div id="PublicGrBtns" align="center" style="overflow: auto; overflow-x: hidden;"></div>
					</form>
	            </a>
	            </div>
			</td>
			</tr>
			<tr align="center">
	    	<td colspan="2">
	                <label class="Option">OTROS GRUPOS</label>
		    </td>
			</tr>
			
	    	<tr align="center">
	    	<td colspan="2">
	    		<div id="menuOpcion">
	    		<a title="Crear Grupo">
	    			<table class="tableTrans">
	    			<tr>
	    			<td>
	                <img id="menuMainOp23" src="<c:url value='/static/resources/_include/img/options/option-23.png'/>" alt="Crear Grupo">
	                </td>
	                <td>
	                	<label class="OptionElem">Crear Grupo</label>
					</td>
	                </tr>
	                </table>
	            </a>
	            </div>
		    </td>
			</tr>

			<tr>
			<td>
	    		<div id="menuOpcion">
	    		<a title="">
	                <img src="<c:url value='/static/resources/_include/img/options/vacio2.png'/>">
	            </a>
	            </div>
	        </td>
			<td>
	    		<div id="menuOpcion">
	    		<a title="">
	                <img src="<c:url value='/static/resources/_include/img/options/vacio2.png'/>">
	            </a>
	            </div>
	        </td>
	        </tr>
	        </table>
		   
		   
		   
		   
		   
		   
		   
		   
		   
<!-- 		   		<table class="tablaQuiniGold">
		   			<tr align="center">
						<td colspan="2">MIS GRUPOS</td>
					</tr>
			   		<tr>

 						<td align="center" colspan="2">
	 						<div id="MisGrBtns" align="center" style="overflow: auto;overflow-x: hidden;">
						</div>
			   			</td>
			        </tr>
		        </table>
 -->		        <br>
<!-- 		   <form id="joinCompanyForm">
		   		<table class="tablaQuiniGold">
		   			<tr align="center">
						<td colspan="2">OTROS GRUPOS</td>
					</tr>
					<tr align="center">
 						<td colspan="2">						
						<div id="PublicGrBtns" align="center" style="overflow: auto; overflow-x: hidden;"></div>
			   			</td>
			        </tr>
		        <br>
	        </form> -->
<!-- 		        <br>
		        <div id="joinCompanyResponse" class="linkQuiniGold">Elige el grupo y pulsa Seleccionar.</div>
		        <br>
		        <table class="tablaQuiniGold">
		        	<tr><td>CREAR GRUPO</td></tr>
			   		<tr align="center">
			   			<td><button id="myCompanyMgrBtn" class="btn btn-danger" name="submitBtn" value="submitBtn">+</button></td>
			        </tr>
		        </table>	 -->        
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

<!-- Modal -->
<div class="modal fade" id="myCompanyOptions" tabindex="-1" role="dialog" aria-labelledby="myModalLabel1" aria-hidden="true">
 <div class="modal-dialog modal-sm">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <p class="modal-title" id="myModalLabel1">SELECCIONA UNA OPCION</p>
      </div>
      <div class="modal-body" align="center">
<!--       	<table class="tablaQuiniGold">
      		<tr align="center">
      			<td align="center">
					<br>
		  			<button id="myBetsBtn" type="button" class="btn btn-danger btn-block">Mis Apuestas</button>
					<br>
		  			<button id="myRankingBtn" type="button" class="btn btn-danger btn-block">Mi Ranking</button>
					<br>
		  			<button id="myQuiniela" type="button" class="btn btn-danger btn-block">Mi Quiniela</button>
					<br>
		  			<button id="myAdminGr" type="button" class="btn btn-danger btn-block">Admin Grupo</button>
					<br>
					<form id="leaveCompanyForm">
		  				<button id="myDejarGr" type="button" class="btn btn-danger btn-block">Dejar Grupo</button>
		  			</form>
					<br>
      			</td>
      		</tr>
      	</table>  -->
    	<table class="menuopH">
    	<tr>
    	<td>
    		<div id="menuOpcion">
    		<a title="Mis Apuestas">
                <img id="myBetsBtn" src="<c:url value='/static/resources/_include/img/options/option-211.png'/>" alt="Mis Apuestas">
            </a>
            </div>
	    </td>
        
        <td>
    		<div id="menuOpcion">
    		<a title="Mi Ranking">
                <img id="myRankingBtn" src="<c:url value='/static/resources/_include/img/options/option-212.png'/>" alt="Mi Ranking">
            </a>
            </div>
		</td>
		<td>
    		<div id="menuOpcion">
    		<a title="Mi Quiniela">
                <img id="myQuiniela" src="<c:url value='/static/resources/_include/img/options/option-213.png'/>" alt="Mi Quiniela">
            </a>
            </div>
		</td>
		</tr>
    	<tr align="center">
    	<td>
                <label class="Option">MIS APUESTAS</label>
	    </td>
        <td>
                <label class="Option">MI RANKING</label>
		</td>
    	<td>
                <label class="Option">MI QUINIELA</label>
	    </td>
		</tr>
		
<%--     	<tr>
    	<td>
                <img src="<c:url value='/static/resources/_include/img/options/vacioS.png'/>">
	    </td>
        
        <td>
                <img src="<c:url value='/static/resources/_include/img/options/vacioS.png'/>">
		</td>
		</tr>
 --%>
 		<tr align="center">
	    	<td>
	                <img src="<c:url value='/static/resources/_include/img/options/vacio.png'/>">
		    </td>
			<td>
				<form id="leaveCompanyForm">
	    		<div id="menuOpcion">
	    		<a title="Dejar Grupo">
	                <img id="myDejarGr" src="<c:url value='/static/resources/_include/img/options/option-215.png'/>" alt="Dejar Grupo">
	            </a>
	            </div>
	            </form>
	        </td>
			<td colspan="3">
	    		<div id="menuOpcion">
	    		<a title="Admin Grupo">
	                <img id="myAdminGr" src="<c:url value='/static/resources/_include/img/options/option-214.png'/>" alt="Admin Grupo">
	            </a>
	            </div>
	        </td>
        </tr>
        <tr align="center">
	        <td>
	                <label class="Option"></label>
			</td>
	        <td>
	                <label id="myDejarGrTitle" class="Option">DEJAR GRUPO</label>
			</td>
	        <td>
	                <label id="myAdminGrTitle" class="Option">ADMIN GRUPO</label>
			</td>
		</tr>
        </table>
      	
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Salir</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div>

<!-- Modal Public Companies-->
<div class="modal fade" id="publicCompanyOptions" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2" aria-hidden="true">
 <div class="modal-dialog modal-sm">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <p class="modal-title" id="myModalLabel2">SELECCIONA UNA OPCION</p>
      </div>
      <div class="modal-body" align="center">
      	<table class="tablaQuiniGold">
      		<tr align="center">
      			<td align="center">
					<br>
					<form id="joinCompanyForm">
		  				<button id="myUnirGr" type="button" class="btn btn-danger btn-block">Unirse al Grupo</button>
		  			</form>
					<br>
      			</td>
      		</tr>
      	</table> 
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Salir</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div>
