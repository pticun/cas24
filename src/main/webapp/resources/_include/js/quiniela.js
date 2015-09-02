function getSign(sign){
	switch(sign)
	{
		case "4": return '1&nbsp;&nbsp;';break;
		case "2": return '&nbsp;X&nbsp;';break;
		case "1": return '&nbsp;&nbsp;2';break;
		case "3": return '&nbsp;X2';break;
		case "5": return '1&nbsp;2';break;
		case "6": return '1X&nbsp;';break;
		case "7": return '1X2';break;
		default: return '&nbsp;&nbsp;&nbsp;';
	}
}

function getSignPleno15(sign){
	//alert('getSignPleno15 - sign='+sign);
	switch(sign)
	{
		case "1": return '0&nbsp;&nbsp;&nbsp;';break;
		case "2": return '&nbsp;1&nbsp;&nbsp;';break;
		case "3": return '01&nbsp;&nbsp;';break;
		case "4": return '&nbsp;&nbsp;2&nbsp;';break;
		case "5": return '0&nbsp;2&nbsp;';break;
		case "6": return '&nbsp;12&nbsp;';break;
		case "7": return '012&nbsp;';break;
		case "8": return '&nbsp;&nbsp;&nbsp;M';break;
		case "9": return '0&nbsp;&nbsp;M';break;
		case "a": return '&nbsp;1&nbsp;M';break;
		case "b": return '01&nbsp;M';break;
		case "c": return '0&nbsp;2M';break;
		case "e": return '&nbsp;12M';break;
		case "f": return '012M';break;
		default: return '&nbsp;&nbsp;&nbsp;';
	}
}

function getTableMatches(bet, loadGames, color){
	consoleAlterQ("getTableMatches() bet="+bet);	
	tableBet='<table style="font-size:14px; color:'+color+';">';
	$(loadGames).each(function(index, element){ 
		var temp=padding_right(element.player1+'-'+element.player2,".",27);
		var num = (index+1)<10?(' '+(index+1)):(index+1);
		if (index == 14){
			tableBet+='<tr><td colspan ="3" nowrap align="center">PLENO AL 15</td></tr>';
			tableBet+='<tr><td></td><td colspan ="2" nowrap align="left">' + padding_right(element.player1,".",23) + '&nbsp;'+ getSignPleno15(bet.charAt(index)) + '</td></tr>';
			tableBet+='<tr><td></td><td colspan ="2" nowrap align="left">' + padding_right(element.player2,".",23) + '&nbsp;'+ getSignPleno15(bet.charAt(index+1)) + '</td>';
		}else if (index != 15)
			tableBet+='<tr><td nowrap>' + num + ' - </td><td  nowrap>' + temp + '</td><td  nowrap align="left">'+ getSign(bet.charAt(index)) + '</td>';
		tableBet+='</tr>';
	});
	tableBet+='</table>';
    return tableBet;
}

function getPrizeInfo(apuestas, precio, color){
	tablePrize='<table style="font-size:14px; color:'+color+';">';
	tablePrize+='<tr><td>===================================</td></tr>';
	tablePrize+='<tr><td align="center">APUESTAS: ' + apuestas + '</td></tr>';
	tablePrize+='<tr><td align="center">PRECIO: ' + precio + ' EUR</td></tr>';
	tablePrize+='<tr><td>===================================</td></tr>';
	tablePrize+='</table>';
	
    return tablePrize;
}

function getHeadInfo(temporada, jornada, color){
	tableHead='<table style="font-size:14px; color:'+color+';">';
	tableHead+='<tr><td align="center">QUINIGOLD</td></tr>';
	tableHead+='<tr><td align="center">Jornada ' + jornada + ' Temporada ' +temporada+'/'+(temporada+1)+'</td></tr>';
	tableHead+='<tr><td>===================================</td></tr>';
	tableHead+='</table>';
	
    return tableHead;
}

function formatMatches(team1, team2){
	var rdo ="";
	
	if ((team1.length + team2.length)>18){
		if (team1.length > 9){
			team1 = team1.substring(0, 9);
		}
		if (team2.length > 9){
			team2 = team2.substring(0, 9);
		}
	}
		
	rdo = padding_right(team1+'-'+team2,".",21);
	
	return rdo;
}

function getQuiniela(){
		consoleAlterQ('getQuiniela');
		consoleAlterQ("loadBet="+window.loadBet);
		consoleAlterQ('company='+window.company+' season='+window.season+' round='+window.round);
		if(window.loadBet){
			window.loadBet=false;
		 	limpiaQuiniela();
		 	
			jQuery.ajax ({
			    url: ctx+'/myaccount/mail@mail.es/'+window.company+'/'+ window.season+'/'+window.round+'/round',
			    type: "GET",
			    data: null,
			    contentType: "application/json; charset=utf-8",
			    async: false,    //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
		        cache: false,    //This will force requested pages not to be cached by the browser  
		        processData:false, //To avoid making query String instead of JSON
			    success: function(response){
				    if(response.errorDto!=0){
		   		    	$(response.errorDto).each(function(index, objeto){  
		   		    		$('#temporada').append(objeto.stringError+" - ");
					    });
				    }
				    else if (response.round!=null){
						$('#quinielaTitle').text("Jornada "+ response.round.round+ " Temporada "+response.round.season+"/"+(response.round.season+1-2000));
					    $('#quinielaTable').append('<input type="hidden" name="season" id="season" value="'+ response.round.season+'"/>');       
					    $('#quinielaTable').append('<input type="hidden" name="round" id="round" value="'+ response.round.round+'"/>');       
						var temp= "Jorn"+ ((response.round.round<9)?'0'+response.round.round:response.round.round) + "  Temp "+ (response.round.season - 2000)+"/"+(response.round.season + 1 - 2000);
					    $('#quinielaTable').append('<tr align="center id="rowBetTitle"><td>'+ temp +' - APUESTA</tr>');       
					    $('#quinielaTableRec').append('<tr align="center id="rowBetTitleRec"><td>&nbsp</td></tr>');       
						$(response.round.games).each(function(index, element){  
							consoleAlterQ(element);
							var row="";
							var rowRec="";
							var temp=formatMatches(element.player1, element.player2);
							if(index>8){
								temp=temp+(index+1);
							}
							else{
								temp=temp+" "+(index+1);
							}
							
							
//							if(index==0 || index==4 || index==8 || index==11){
//								row+='<tr id="rowBet_'+index+'"><td style="white-space: nowrap" class="partidolinea"><label>'+temp+'</label></td>';
//							}
//							else if (index==13){
//								row+='<tr id="rowBet_'+index+'"><td style="white-space: nowrap" class="partidoLast"><label>'+temp+'</label></td>';
//							}else if (index!=14){
//								row+='<tr id="rowBet_'+index+'"><td style="white-space: nowrap" class="partido"><label>'+temp+'</label></td>';
//							}
							
							
							
							//agrupamos el pleno al 15
							if (index == 14)
							{
								row+='<tr><td colspan="5" style="text-align: center; white-space: nowrap"><label>PLENO AL 15</label></td><tr>';
								row+='<tr id="rowBet_'+index+'"><td style="white-space: nowrap"><label>'+element.player1+'</label></td>';
								
								row+='<td><input class="class0" type="checkbox" id="'+index+'_0" name="'+index+'_0" />';
								row+='<label class="quiniela" for="'+index+'_0"></label>';
								row+='</td>';
								row+='<td><input class="class1" type="checkbox" id="'+index+'_1" name="'+index+'_1" />';
								row+='<label class="quiniela" for="'+index+'_1"></label>';
								row+='</td>';
								row+='<td><input class="class2" type="checkbox" id="'+index+'_2" name="'+index+'_2" />';
								row+='<label class="quiniela" for="'+index+'_2"></label>';
								row+='</td>';
								row+='<td><input class="classM" type="checkbox" id="'+index+'_3" name="'+index+'_3" />';
								row+='<label class="quiniela" for="'+index+'_3"></label>';
								row+='</td>';
								row+='</tr>';

								row+='<tr id="rowBet_'+(index+1)+'"><td style="white-space: nowrap"><label>'+element.player2+'</label></td>';
								row+='<td><input class="class0" type="checkbox" id="'+(index+1)+'_0" name="'+(index+1)+'_0" />';
								row+='<label class="quiniela" for="'+(index+1)+'_0"></label>';
								row+='</td>';
								row+='<td><input class="class1" type="checkbox" id="'+(index+1)+'_1" name="'+(index+1)+'_1" />';
								row+='<label class="quiniela" for="'+(index+1)+'_1"></label>';
								row+='</td>';
								row+='<td><input class="class2" type="checkbox" id="'+(index+1)+'_2" name="'+(index+1)+'_2" />';
								row+='<label class="quiniela" for="'+(index+1)+'_2"></label>';
								row+='</td>';
								row+='<td><input class="classM" type="checkbox" id="'+(index+1)+'_3" name="'+(index+1)+'_3" />';
								row+='<label class="quiniela" for="'+(index+1)+'_3"></label>';
								row+='</td>';
								row+='</tr>';

								$('#quinielaTablePleno15').append(row);
							}
							else{
								if((index==3)||(index==7)||(index==10)){
									row+='<tr id="rowBet_'+index+'"><td style="white-space: nowrap;border-bottom:2pt solid rgb(181, 31, 31);"><label>'+temp+'</label></td>';
								}
								else
									row+='<tr id="rowBet_'+index+'"><td style="white-space: nowrap"><label>'+temp+'</label></td>';
									
									
								row+='<td style="border-bottom:2pt solid rgb(181, 31, 31);"><input class="class1" type="checkbox" id="'+index+'_1" name="'+index+'_1" />';
								row+='<label class="quiniela" for="'+index+'_1"></label>';
								row+='</td>';
								row+='<td style="border-bottom:2pt solid rgb(181, 31, 31);"><input class="classX" type="checkbox" id="'+index+'_X" name="'+index+'_X" />';
								row+='<label class="quiniela" for="'+index+'_X"></label>';
								row+='</td>';
								row+='<td style="border-bottom:2pt solid rgb(181, 31, 31);"><input class="class2" type="checkbox" id="'+index+'_2" name="'+index+'_2" />';
								row+='<label class="quiniela" for="'+index+'_2"></label>';
								row+='</td>';
								row+='</tr>';

								rowRec+='<tr><td style="border-bottom:2pt solid rgb(181, 31, 31);"><input class="classR" type="checkbox" id="'+index+'_R" name="'+index+'_R" />';
								rowRec+='<label class="quiniela" for="'+index+'_R"></label>';
								rowRec+='</td>';
								rowRec+='</tr>';
								
								$('#quinielaTable_in').append(row);
								$('#quinielaTableRec_in').append(rowRec);
/*
								if(index==0 || index==1 || index==2 || index==3)//agrupamos los partidos 1-2-3-4
								{
									$('#quinielaTable_1_to_4').append(row);
									$('#quinielaTableRec_1_to_4').append(rowRec);
								}
								else if(index==4 || index==5 || index==6 || index==7)//agrupamos los partidos 5-6-7-8
								{
									$('#quinielaTable_5_to_8').append(row);
									$('#quinielaTableRec_5_to_8').append(rowRec);
								}
								else if(index==8 || index==9 || index==10)//agrupamos los partidos 9-10-11
								{
									$('#quinielaTable_9_to_11').append(row);
									$('#quinielaTableRec_9_to_11').append(rowRec);
								}
								else if(index==11 || index==12 || index==13)//agrupamos los partidos 12-13-14
								{
									$('#quinielaTable_12_to_14').append(row);
									$('#quinielaTableRec_12_to_14').append(rowRec);
								}	
*/								
								//$('#quinielaTable').append(row);
								//$('#quinielaTableRec').append(rowRec);
								
							}
							
						});
						//añadimos el check de la reducción
						var row="";
						row+='<tr>';
						row+='<td><input class="classR" type="checkbox" id="reducedCHK" name="reducedCHK" />';
						row+='<label id="reducedCHKlabel" class="quiniela" for="reducedCHK"></label>';
						row+='</td>';
						row+='<td style="white-space: nowrap"><label>REDUCIR QUINIELA </label></td>';
						row+='<td style="white-space: nowrap"><button type="button" class="btn btn-danger" data-toggle="modal" data-target="#modalReduced"><strong>Info</strong></button></td>';
						row+='</tr>';

						$('#quinielaTableReduced').append(row);
						
						if (window.company != window.DEFECT_COMPANY)
							$('#quinielaTableReduced').hide();
						if (window.quinielaFinal)
							$('#quinielaTableReduced').show();
						
				    }
				    else{
				    	consoleAlterQ('Error en getQuiniela');
				    	window.loadBet=true;
				    }
			    },
			    error: function(){
			    	consoleAlterQ('Error en getQuiniela');
			    	window.loadBet=true;
			    }
		 });
		}
}

function limpiaQuiniela()
{
 	$('#quinielaTableReduced').empty();
 	$('#quinielaTable').empty();
 	$('#quinielaTablePleno15').empty();
 	$('#quinielaTableRec').empty();
 	$('#quinielaTablePleno15').empty();
	$('#quinielaTable_in').empty();
	$('#quinielaTableRec_in').empty();
	
}

function calculatePrice(){
	consoleAlterQ('calculatePrice');
//	var dataJson=JSON.stringify($("form#betForm").serializeObject());
	consoleAlterQ(ctx+'/bet/price');	
	var dataJson=$("form#betForm").serialize();
	jQuery.ajax ({
		url: ctx+'/myaccount/mail@mail.es/'+window.company+'/'+ window.season+'/'+window.round+'/bet/price',
	    type: "POST",
	    data: dataJson,
//	    contentType: "application/json; charset=utf-8",
	    async: false,    //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
	    cache: false,    //This will force requested pages not to be cached by the browser  
	    processData:false, //To avoid making query String instead of JSON
	    success: function(response){
			if(response.errorDto!=0){
				$('#quinielaFormResponse').empty();
   		    	$(response.errorDto).each(function(index, objeto){  
   		    		$('#quinielaFormResponse').append(objeto.stringError+" - ");
			    });
   		    	$('#labelPrecio').empty();
   		    	$('#labelApuestas').empty();
			}
			else{
				$('#quinielaFormResponse').empty();
				$('#quinielaFormResponse').append("Rellena tu apuesta y pulsa enviar.");
				$('#labelPrecio').empty();
				$('#labelPrecio').append(response.roundBet.bets[0].price);
				$('#labelApuestas').empty();
				$('#labelApuestas').append(response.roundBet.bets[0].numBets);
			}
	    }
	});
	return false;
}

$('div').on("click", "#reducedCHK", function( event ) {
		if ($("#reducedCHK").is(':checked')){
			showReducciones();
		}
		else{
			hideReducciones();
		}
		
});

$('form#betForm button#quinielaButton').click(function() {
	 buttonpressed = $('form#betForm button#quinielaButton').val();
	});

$('form#betForm button#prizeButton').click(function() {
	 buttonpressed = $('form#betForm button#prizeButton').val();
	});

$('form#betForm').submit(function( event ) {
	 //if (window.quinielaFinal)
	 consoleAlterQ("quinielaFinal="+window.quinielaFinal)

	var dataJson=JSON.stringify($('form#betForm').serializeObject());
	consoleAlterQ('betForm:'+dataJson);
	consoleAlterQ('buttonpressed:'+buttonpressed);
	if (buttonpressed == 'Enviar')
	{
		if (window.idUserAlterQ != ''){
			// will pass the form date using the jQuery serialize function
			jQuery.ajax ({
				url: ctx+'/myaccount/'+ window.idUserAlterQ+'/'+window.company+'/'+ window.season+'/'+window.round+'/bet',
			    type: "POST",
			    data: $(this).serialize(),
	//		    contentType: "application/json; charset=utf-8",
			    async: false,    //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
			    cache: false,    //This will force requested pages not to be cached by the browser  
			    processData:false, //To avoid making query String instead of JSON
			    success: function(response){
					if(response.errorDto!=0){
						$('#quinielaFormResponse').text("");
		   		    	$(response.errorDto).each(function(index, objeto){  
		   		    		$('#quinielaFormResponse').append(objeto.stringError+" - ");
					    });
					}
					else{
						$('#quinielaFormResponse').text("Apuesta realizada correctamente");
						//doLogin();
						confirmBet(response.bet.bet, response.round.games, response.bet.numBets, response.bet.price, window.season, window.round);
						//pasamos los parámetros
						$('#param_apuesta').val(response.bet.bet);
						$('#param_reduccion').val(response.bet.reduction);
						$('#param_tiporeduccion').val(response.bet.typeReduction);
						$('#param_numbets').val(response.bet.numBets);
						
						showDiv(bConfirmQuiniela);
					}
			    }
			});
		}
		else{
			$('#quinielaFormResponse').text("No se puede hacer apuestas sin usuario.");				
		}
	}
	if (buttonpressed=='Precio'){
		calculatePrice();
	}
	event.preventDefault(); // prevent actual form submit and page reload
});

$('form#confirmBetForm button#modificarQuinielaButton').click(function() {
	 buttonpressed = $('form#confirmBetForm button#modificarQuinielaButton').val();
	});
$('form#confirmBetForm button#confirmarQuinielaButton').click(function() {
	 buttonpressed = $('form#confirmBetForm button#confirmarQuinielaButton').val();
	});
	
$('form#confirmBetForm').submit(function( event ) {

	var dataJson=JSON.stringify($('form#confirmBetForm').serializeObject());
	consoleAlterQ('confirmBetForm:'+dataJson);
	if (buttonpressed == 'Confirmar')
	{
		consoleAlterQ(ctx+'/myaccount/'+ window.idUserAlterQ+'/'+window.company+'/'+ window.season+'/'+window.round+'/bet/confirm');
		// will pass the form date using the jQuery serialize function
		jQuery.ajax ({
			url: ctx+'/myaccount/'+ window.idUserAlterQ+'/'+window.company+'/'+ window.season+'/'+window.round+'/bet/confirm',
		    type: "POST",
		    data: $(this).serialize(),
//		    contentType: "application/json; charset=utf-8",
		    async: false,    //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
		    cache: false,    //This will force requested pages not to be cached by the browser  
		    processData:false, //To avoid making query String instead of JSON
		    success: function(response){
				if(response.errorDto!=0){
	   		    	$(response.errorDto).each(function(index, objeto){  
	   		    		$('#quinielaFormResponse').append(objeto.stringError+" - ");
				    });
				}
				else{
					$('#0_1').removeAttr('checked');$('#0_X').removeAttr('checked');$('#0_2').removeAttr('checked');$('#0_R').removeAttr('checked');
					$('#1_1').removeAttr('checked');$('#1_X').removeAttr('checked');$('#1_2').removeAttr('checked');$('#1_R').removeAttr('checked');
					$('#2_1').removeAttr('checked');$('#2_X').removeAttr('checked');$('#2_2').removeAttr('checked');$('#2_R').removeAttr('checked');
					$('#3_1').removeAttr('checked');$('#3_X').removeAttr('checked');$('#3_2').removeAttr('checked');$('#3_R').removeAttr('checked');
					$('#4_1').removeAttr('checked');$('#4_X').removeAttr('checked');$('#4_2').removeAttr('checked');$('#4_R').removeAttr('checked');
					$('#5_1').removeAttr('checked');$('#5_X').removeAttr('checked');$('#5_2').removeAttr('checked');$('#5_R').removeAttr('checked');
					$('#6_1').removeAttr('checked');$('#6_X').removeAttr('checked');$('#6_2').removeAttr('checked');$('#6_R').removeAttr('checked');
					$('#7_1').removeAttr('checked');$('#7_X').removeAttr('checked');$('#7_2').removeAttr('checked');$('#7_R').removeAttr('checked');
					$('#8_1').removeAttr('checked');$('#8_X').removeAttr('checked');$('#8_2').removeAttr('checked');$('#8_R').removeAttr('checked');
					$('#9_1').removeAttr('checked');$('#9_X').removeAttr('checked');$('#9_2').removeAttr('checked');$('#9_R').removeAttr('checked');
					$('#10_1').removeAttr('checked');$('#10_X').removeAttr('checked');$('#10_2').removeAttr('checked');$('#10_R').removeAttr('checked');
					$('#11_1').removeAttr('checked');$('#11_X').removeAttr('checked');$('#11_2').removeAttr('checked');$('#11_R').removeAttr('checked');
					$('#12_1').removeAttr('checked');$('#12_X').removeAttr('checked');$('#12_2').removeAttr('checked');$('#12_R').removeAttr('checked');
					$('#13_1').removeAttr('checked');$('#13_X').removeAttr('checked');$('#13_2').removeAttr('checked');$('#13_R').removeAttr('checked');
					$('#14_0').removeAttr('checked');$('#14_1').removeAttr('checked');$('#14_2').removeAttr('checked');$('#14_3').removeAttr('checked');
					$('#15_0').removeAttr('checked');$('#15_1').removeAttr('checked');$('#15_2').removeAttr('checked');$('#15_3').removeAttr('checked');
					$('#reducedCHK').removeAttr('checked');
					hideReducciones();
	   		    	$('#labelPrecio').empty();
	   		    	$('#labelApuestas').empty();

					$('#confirmarQuinielaFormResponse').text("Apuesta realizada correctamente");
					//doLogin();
					fillUserData(response);
					confirmedBet(response.bet.bet, response.round.games, response.bet.numBets, response.bet.price, window.season, window.round);
					
					showDiv(bConfirmedQuiniela);
				}
		    }
		});
	}
	if (buttonpressed=='Modificar'){
		showDiv(bQuiniela);
	}
	event.preventDefault(); // prevent actual form submit and page reload
});	 

$('form#confirmedBetForm').submit(function( event ) {
	showDiv(bHome);
	event.preventDefault(); // prevent actual form submit and page reload
});	 

function confirmBet(bet, mygames, apuestas, precio, temporada, jornada)
{
	var row="";
	
	consoleAlterQ('confirmBet');
	consoleAlterQ('bet=' + bet);
	$('#confirmarQuinielaTable').empty();
    row+='<tr>';
    row+='<td>';
    row+='<article>';
    row+='<header>';
	row+='<div align="center"><h3>'+getHeadInfo(temporada, jornada, "#0000ff")+'</h3></div>';
	row+='<div align="center"><h3>'+getTableMatches(bet.toString(), mygames, "#0000ff")+'</h3></div>';
	row+='</header>';
	row+='<div align="center"><h3>'+getPrizeInfo(apuestas, precio, "#0000ff")+'</h3></div>';
	row+='</article>';
    row+='</td>';
    row+='</tr>';
	$('#confirmarQuinielaTable').append(row);
	//showDiv(bQuinielaDetail);
}

function confirmedBet(bet, mygames, apuestas, precio, temporada, jornada)
{
	var row="";
	
	consoleAlterQ('confirmedBet');
	consoleAlterQ('bet=' + bet);
	
	$('#confirmadaQuinielaTable').empty();
    row+='<tr>';
    row+='<td>';
    row+='<article>';
    row+='<header>';
	row+='<div align="center"><h3>'+getHeadInfo(temporada, jornada, "#ff0000")+'</h3></div>';
	row+='<div align="center"><h3>'+getTableMatches(bet.toString(), mygames, "#ff0000")+'</h3></div>';
	row+='</header>';
	row+='<div align="center"><h3>'+getPrizeInfo(apuestas, precio, "#ff0000")+'</h3></div>';
	row+='</article>';
    row+='</td>';
    row+='</tr>';
	$('#confirmadaQuinielaTable').append(row);
	//showDiv(bQuinielaDetail);
	window.loadBetUser=true;
}

function showReducciones(){
	$('#quinielaTableRec').show();	
	$('#quinielaTableRec_in').show();
}

function hideReducciones(){
	$('#quinielaTableRec').hide();
	$('#quinielaTableRec_in').hide();
}

function showModalReduced(){
//	alert("showModal");
	showDiv(bModalReduced);
}


function getSign(sign){
	switch(sign)
	{
		case "4": return '1&nbsp;&nbsp;';break;
		case "2": return '&nbsp;X&nbsp;';break;
		case "1": return '&nbsp;&nbsp;2';break;
		case "3": return '&nbsp;X2';break;
		case "5": return '1&nbsp;2';break;
		case "6": return '1X&nbsp;';break;
		case "7": return '1X2';break;
		default: return '&nbsp;&nbsp;&nbsp;';
	}
}