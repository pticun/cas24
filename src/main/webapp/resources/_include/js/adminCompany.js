
//alert("context:"+ctx);
$(document).ready(function() {
//alert("$(document).ready: INICIO");	
	consoleAlterQ("AdminCompany - ready");
	$.fn.serializeObject = function()
	{
	    var o = {};
	    var a = this.serializeArray();
	    $.each(a, function() {
	        if (o[this.name] !== undefined) {
	            if (!o[this.name].push) {
	                o[this.name] = [o[this.name]];
	            }
	            o[this.name].push(this.value || '');
	        } else {
	            o[this.name] = this.value || '';
	        }
	    });
	    return o;
	};
	
	initDiv();
    
	
	$('form#closeACForm').submit(function(event) {
		 var dataJson=JSON.stringify($('form#closeACForm').serializeObject());
		 consoleAlterQ(dataJson);
		 jQuery.ajax ({
			 url: ctx+'/adminCompany'+ '/company/' + company + '/season/'+ $("input[id=seasonCloseAC]").val() + '/round/' + $("input[id=roundCloseAC]").val() + '/closeAC',
//			 url: ctx+'/adminCompany/closeAC',
			 	type: "POST",
			    data: dataJson,
			    contentType: "application/json; charset=utf-8",
			    async: false,    //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
	            cache: false,    //This will force requested pages not to be cached by the browser  
	            processData:false, //To avoid making query String instead of JSON
			    success: function(response){
			    	if(response.errorDto!=0){
		   		    	$(response.errorDto).each(function(index, objeto){  
		   		    		consoleAlterQ("closeAC: response="+objeto.stringError);
		   		    		$('#closeACFormResponse').append(objeto.stringError+" - ");
					    });
		   		    	
		   		    }
		   		    else{
		   		    	//consoleAlterQ("open: response= OK"+response.userAlterQ.name);
						//$('#loginFormResponse').text(response.userAlterQ.name);
						consoleAlterQ("closeAC: response= OK");
						$('#closeACFormResponse').text("AdminCompany - Close - OK");
						//userLoged=true;
						//idUserAlterQ=response.userAlterQ.id;
						//$('#idData').val(response.userAlterQ.id);
						//$('#nameData').val(response.userAlterQ.name);
						//$('#phoneNumberData').val(response.userAlterQ.phoneNumber);
						//$('#idSaldo').val(response.userAlterQ.id);
						//$('#balanceSaldo').val(response.userAlterQ.balance);
						//getMainMenuItems(userLoged, userLoged?response.userAlterQ.name:null);
						//showDiv(bHome);
		   		    }
				    //round=response.generalData.round;
				    //season=response.generalData.season;
			    }
			});
		 	event.preventDefault(); // prevent actual form submit and page reload
	});	

	$('form#finalQuinielaForm').submit(function(event) {
		 var dataJson=JSON.stringify($('form#quinielaForm').serializeObject());
		 consoleAlterQ(dataJson);
		 jQuery.ajax ({
			 url: ctx+'/adminCompany'+ '/company/' + company + '/season/'+ $("input[id=seasonQuiniela]").val() + '/round/' + $("input[id=roundQuiniela]").val() + '/finalBet/' + $("input[id=tipoQuiniela]").val(),
			    type: "POST",
			    data: dataJson,
			    //contentType: "application/json; charset=utf-8",
			    async: false,    //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
	            cache: false,    //This will force requested pages not to be cached by the browser  
	            processData:false, //To avoid making query String instead of JSON
			    success: function(response){
			    	if(response.errorDto!=0){
		   		    	$(response.errorDto).each(function(index, objeto){  
		   		    		consoleAlterQ("result: response="+objeto.errorDto);
		   		    		$('#quinielaFormResponse').append(objeto.stringError+" - ");
					    });
		   		    	
		   		    }
		   		    else{
		   		    	//consoleAlterQ("open: response= OK"+response.userAlterQ.name);
						//$('#loginFormResponse').text(response.userAlterQ.name);
						consoleAlterQ("result: response= OK");
						$('#quinielaFormResponse').text("Admin - Quiniela - OK");
						//userLoged=true;
						//idUserAlterQ=response.userAlterQ.id;
						//$('#idData').val(response.userAlterQ.id);
						//$('#nameData').val(response.userAlterQ.name);
						//$('#phoneNumberData').val(response.userAlterQ.phoneNumber);
						//$('#idSaldo').val(response.userAlterQ.id);
						//$('#balanceSaldo').val(response.userAlterQ.balance);
						//getMainMenuItems(userLoged, userLoged?response.userAlterQ.name:null);
						//showDiv(bHome);
		   		    }
				    //round=response.generalData.round;
				    //season=response.generalData.season;
			    }
			});
		 	event.preventDefault(); // prevent actual form submit and page reload
	});
	
	 $('form#confirmBetForm').submit(function( event ) {

			var dataJson=JSON.stringify($('form#confirmBetForm').serializeObject());
			consoleAlterQ('confirmBetForm:'+dataJson);
			if (buttonpressed == 'Confirmar')
			{
				consoleAlterQ(ctx+'/myaccount/'+ idUserAlterQ+'/'+company+'/'+ season+'/'+round+'/bet/confirm');
				// will pass the form date using the jQuery serialize function
				jQuery.ajax ({
					url: ctx+'/myaccount/'+ idUserAlterQ+'/'+company+'/'+ season+'/'+round+'/bet/confirm',
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
							confirmedBet(response.bet.bet, response.round.games, response.bet.numBets, response.bet.price, season, round);
							
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


});

$("#homeBtn").on('click', function(event){
	menuEvent($(this).text(),  "home");
	event.preventDefault(); // prevent actual form submit and page reload
});
$("#quinielaFinalBtn").on('click', function(event){
	getQuiniela();
	menuEvent($(this).text(),  "#quinielaDiv");
	event.preventDefault(); // prevent actual form submit and page reload
});
$("#closeACBtn").on('click', function(event){
//alert("closeACBtn CLICK");	
	menuEvent($(this).text(),  "#closeACDiv");
	event.preventDefault(); // prevent actual form submit and page reload
});

$("#homeBtn9").on('click', function(event){
	menuEvent($(this).text(),  "#homeDiv");
	event.preventDefault(); // prevent actual form submit and page reload
});
$("#homeBtn10").on('click', function(event){
	menuEvent($(this).text(),  "#homeDiv");
	event.preventDefault(); // prevent actual form submit and page reload
});

function consoleAlterQ(text){
	if( (window['console'] !== undefined) ){
		console.log(text);
	}
}

function getTableMatches(bet, loadGames){
	tableBet='<table style="font-size:14px">';
	$(loadGames).each(function(index, element){  
		var temp=padding_right(element.player1+'-'+element.player2,".",27);
		var num = (index+1)<10?(' '+(index+1)):(index+1);
		tableBet+='<tr><td nowrap>' + num + ' - </td><td  nowrap>' + temp + '</td><td  nowrap align="left">'+ getSign(bet.charAt(index)) + '</td>';
		tableBet+='</tr>';
	});
	tableBet+='</table>';		
    return tableBet;
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


