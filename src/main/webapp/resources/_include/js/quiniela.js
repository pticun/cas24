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
