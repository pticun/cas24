var round=0;
var season=0;
var idUserAlterQ="";
var company=1; // "quiniGoldClassic = 1" depends environment

// left padding s with c to a total of n chars
function padding_left(s, c, n) {
  if (! s || ! c || s.length >= n) {
    return s;
  }
  var max = (n - s.length)/c.length;
  for (var i = 0; i < max; i++) {
    s = c + s;
  }
  return s;
}
 
// right padding s with c to a total of n chars
function padding_right(s, c, n) {
  if (! s || ! c || s.length >= n) {
    return s;
  }
  var max = (n - s.length)/c.length;
  for (var i = 0; i < max; i++) {
    s += c;
  }
  return s;
}

//alert("context:"+ctx);
$(document).ready(function() {
//alert("$(document).ready: INICIO");	
	consoleAlterQ("Admin - ready");
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
    
	
	$('form#openForm').submit(function(event) {
		 var dataJson=JSON.stringify($('form#openForm').serializeObject());
		 consoleAlterQ(dataJson);
		 jQuery.ajax ({
			 url: ctx+'/admin'+ '/company/' + company + '/season/'+ $("input[name=season]").val() + '/round/' + $("input[name=round]").val() + '/open',
			    type: "POST",
			    data: dataJson,
			    contentType: "application/json; charset=utf-8",
			    async: false,    //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
	            cache: false,    //This will force requested pages not to be cached by the browser  
	            processData:false, //To avoid making query String instead of JSON
			    success: function(response){
		   		    if(response.errorDto!=0){
		   		    	$(response.errorDto).each(function(index, objeto){  
		   		    		consoleAlterQ("open: response="+objeto.stringError);
		   		    		$('#openFormResponse').append(objeto.stringError+" - ");
					    });
		   		    	
		   		    }
		   		    else{
		   		    	//consoleAlterQ("open: response= OK"+response.userAlterQ.name);
						//$('#loginFormResponse').text(response.userAlterQ.name);
						consoleAlterQ("open: response= OK");
						$('#openFormResponse').text("Admin - Open - OK");
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
	
	$('form#closeForm').submit(function(event) {
		 var dataJson=JSON.stringify($('form#closeForm').serializeObject());
		 consoleAlterQ(dataJson);
		 jQuery.ajax ({
			 url: ctx+'/admin'+ '/company/' + company + '/season/'+ $("input[id=seasonClose]").val() + '/round/' + $("input[id=roundClose]").val() + '/close',
			    type: "POST",
			    data: dataJson,
			    contentType: "application/json; charset=utf-8",
			    async: false,    //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
	            cache: false,    //This will force requested pages not to be cached by the browser  
	            processData:false, //To avoid making query String instead of JSON
			    success: function(response){
			    	if(response.errorDto!=0){
		   		    	$(response.errorDto).each(function(index, objeto){  
		   		    		consoleAlterQ("open: response="+objeto.stringError);
		   		    		$('#closeFormResponse').append(objeto.stringError+" - ");
					    });
		   		    	
		   		    }
		   		    else{
		   		    	//consoleAlterQ("open: response= OK"+response.userAlterQ.name);
						//$('#loginFormResponse').text(response.userAlterQ.name);
						consoleAlterQ("close: response= OK");
						$('#closeFormResponse').text("Admin - Close - OK");
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
	$('form#matchesForm').submit(function(event) {
		 var dataJson=JSON.stringify($('form#matchesForm').serializeObject());
		 consoleAlterQ(dataJson);
		 jQuery.ajax ({
			 url: ctx+'/admin'+ '/company/' + company + '/season/'+ $("input[id=seasonMatches]").val() + '/round/' + $("input[id=roundMatches]").val() + '/matches',
			    type: "POST",
			    data: $(this).serialize(),
			    //contentType: "application/json; charset=utf-8",
			    async: false,    //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
	            cache: false,    //This will force requested pages not to be cached by the browser  
	            processData:false, //To avoid making query String instead of JSON
			    success: function(response){
			    	if(response.errorDto!=0){
		   		    	$(response.errorDto).each(function(index, objeto){  
		   		    		consoleAlterQ("matches: response="+objeto.errorDto);
		   		    		$('#matchesFormResponse').append(objeto.stringError+" - ");
					    });
		   		    	
		   		    }
		   		    else{
		   		    	//consoleAlterQ("open: response= OK"+response.userAlterQ.name);
						//$('#loginFormResponse').text(response.userAlterQ.name);
						consoleAlterQ("matches: response= OK");
						$('#matchesFormResponse').text("Admin - Matches - OK");
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
//	$('form#resultForm').submit(function(event) {
//		 var dataJson=JSON.stringify($('form#resultForm').serializeObject());
//		 consoleAlterQ(dataJson);
//		 jQuery.ajax ({
//			 url: ctx+'/admin/' + $("input[id=seasonResults]").val() + '/' +$("input[id=roundResults]").val() + '/' + $("input[id=betResults]").val() + '/resultBet',
//			    type: "POST",
//			    data: dataJson,
//			    contentType: "application/json; charset=utf-8",
//			    async: false,    //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
//	            cache: false,    //This will force requested pages not to be cached by the browser  
//	            processData:false, //To avoid making query String instead of JSON
//			    success: function(response){
//			    	if(response.errorDto!=0){
//		   		    	$(response.errorDto).each(function(index, objeto){  
//		   		    		consoleAlterQ("result: response="+objeto.errorDto);
//		   		    		$('#matchesFormResponse').append(objeto.stringError+" - ");
//					    });
//		   		    	
//		   		    }
//		   		    else{
//		   		    	//consoleAlterQ("open: response= OK"+response.userAlterQ.name);
//						//$('#loginFormResponse').text(response.userAlterQ.name);
//						consoleAlterQ("result: response= OK");
//						$('#resutFormResponse').text("Admin - Result - OK");
//						//userLoged=true;
//						//idUserAlterQ=response.userAlterQ.id;
//						//$('#idData').val(response.userAlterQ.id);
//						//$('#nameData').val(response.userAlterQ.name);
//						//$('#phoneNumberData').val(response.userAlterQ.phoneNumber);
//						//$('#idSaldo').val(response.userAlterQ.id);
//						//$('#balanceSaldo').val(response.userAlterQ.balance);
//						//getMainMenuItems(userLoged, userLoged?response.userAlterQ.name:null);
//						//showDiv(bHome);
//		   		    }
//				    //round=response.generalData.round;
//				    //season=response.generalData.season;
//			    }.val()
//			});
//		 	event.preventDefault(); // prevent actual form submit and page reload
//	});
	
	$('form#resultForm').submit(function(event) {
		 var dataJson=JSON.stringify($('form#resultForm').serializeObject());
		 consoleAlterQ(dataJson);
		 jQuery.ajax ({
			 url: ctx+'/admin/'+ $("input[id=seasonResults]").val() + '/' + $("input[id=roundResults]").val() + '/' + $("input[id=betResults]").val() + '/resultBet',
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
		   		    		$('#resutFormResponse').text(objeto.stringError+" - ");
					    });
		   		    	
		   		    }
		   		    else{
		   		    	//consoleAlterQ("open: response= OK"+response.userAlterQ.name);
						//$('#loginFormResponse').text(response.userAlterQ.name);
						consoleAlterQ("result: response= OK");
						$('#resutFormResponse').text("Admin - Result Bet - OK");
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
  	 $('#admin_getQuiniela_btn').click(function( event ) {
		consoleAlterQ("temporada_jornada="+$("input[id=getSeasonQuiniela]").val()+"-"+$("input[id=getRoundQuiniela]").val());
		callResum($("input[id=getSeasonQuiniela]").val(),$("input[id=getRoundQuiniela]").val());
		event.preventDefault(); // prevent actual form submit and page reload
   	 });

	$('form#prizesForm').submit(function(event) {
		 var dataJson=JSON.stringify($('form#prizesForm').serializeObject());
		 consoleAlterQ(dataJson);
		 jQuery.ajax ({
			 url: ctx+'/admin' + '/season/'+ $("input[id=seasonPrizes]").val() + '/round/' + $("input[id=roundPrizes]").val() + '/prizesBet',
			    type: "POST",
			    data: $(this).serialize(),
			    //contentType: "application/json; charset=utf-8",
			    async: false,    //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
	            cache: false,    //This will force requested pages not to be cached by the browser  
	            processData:false, //To avoid making query String instead of JSON
			    success: function(response){
			    	if(response.errorDto!=0){
		   		    	$(response.errorDto).each(function(index, objeto){  
		   		    		consoleAlterQ("result: response="+objeto.errorDto);
		   		    		$('#prizesFormResponse').append(objeto.stringError+" - ");
					    });
		   		    	
		   		    }
		   		    else{
		   		    	//consoleAlterQ("open: response= OK"+response.userAlterQ.name);
						//$('#loginFormResponse').text(response.userAlterQ.name);
						consoleAlterQ("result: response= OK");
						$('#prizesFormResponse').text("Admin - Quiniela - OK");
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
	$('form#updateBalanceForm').submit(function(event) {
		 var dataJson=JSON.stringify($('form#updateBalanceForm').serializeObject());
		 consoleAlterQ(dataJson);
		 var balance = ($("input[id=updateBalanceBalance]").val()=="")?"none":$("input[id=updateBalanceBalance]").val();
		 var balanceInc = ($("input[id=updateIncreaseBalanceBalance]").val()=="")?"none":$("input[id=updateIncreaseBalanceBalance]").val();
		 var balanceDec = ($("input[id=updateDecreaseBalanceBalance]").val()=="")?"none":$("input[id=updateDecreaseBalanceBalance]").val();
		 jQuery.ajax ({
			 url: ctx+'/admin'+ '/company/' + company + '/user/'+ $("input[id=updateBalanceUser]").val() + '/balance/' + balance + '/' + balanceInc + '/' + balanceDec + '/updateBalanceUser',
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
		   		    		$('#updateBalanceFormResponse').text(objeto.stringError+" - ");
					    });
		   		    	
		   		    }
		   		    else{
		   		    	//consoleAlterQ("open: response= OK"+response.userAlterQ.name);
						//$('#loginFormResponse').text(response.userAlterQ.name);
						consoleAlterQ("result: response= OK");
						$('#updateBalanceFormResponse').text("Admin - UpdateBalanceUser - OK");
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

	$('form#getFileForm').submit(function(event) {
		 var dataJson=JSON.stringify($('form#getFileForm').serializeObject());
		 consoleAlterQ(dataJson);
		 
		 var url= ctx+'/admin'+ '/company/' + company + '/season/'+ $("input[id=seasonGetFile]").val() + '/round/' + $("input[id=roundFGetile]").val() + '/getFile';
//		 alert(url);
		 var NWin = window.open(url, '', 'height=800,width=800');
 	     if (window.focus)
 	     {
 	       NWin.focus();
 	     }
 //	     return false;		 

 	     event.preventDefault(); // prevent actual form submit and page reload
	});

});

$("#homeBtn").on('click', function(event){
	menuEvent($(this).text(),  "home");
	event.preventDefault(); // prevent actual form submit and page reload
});
$("#openBtn").on('click', function(event){
	menuEvent($(this).text(),  "#openDiv");
	event.preventDefault(); // prevent actual form submit and page reload
});
$("#closeBtn").on('click', function(event){
	menuEvent($(this).text(),  "#closeDiv");
	event.preventDefault(); // prevent actual form submit and page reload
});
$("#matchesBtn").on('click', function(event){
	menuEvent($(this).text(),  "#matchesDiv");
	event.preventDefault(); // prevent actual form submit and page reload
});
$("#resultsBtn").on('click', function(event){
	menuEvent($(this).text(),  "#resultsDiv");
	event.preventDefault(); // prevent actual form submit and page reload
});
$("#prizesBtn").on('click', function(event){
	menuEvent($(this).text(),  "#prizesDiv");
	event.preventDefault(); // prevent actual form submit and page reload
});
$("#updateBalanceBtn").on('click', function(event){
	menuEvent($(this).text(),  "#updateBalanceDiv");
	event.preventDefault(); // prevent actual form submit and page reload
});
$("#fileBtn").on('click', function(event){
	menuEvent($(this).text(),  "#getFileDiv");
	event.preventDefault(); // prevent actual form submit and page reload
});

$("#homeBtn1").on('click', function(event){
	menuEvent($(this).text(),  "#homeDiv");
	event.preventDefault(); // prevent actual form submit and page reload
});
$("#homeBtn2").on('click', function(event){
	menuEvent($(this).text(),  "#homeDiv");
	event.preventDefault(); // prevent actual form submit and page reload
});
$("#homeBtn3").on('click', function(event){
	menuEvent($(this).text(),  "#homeDiv");
	event.preventDefault(); // prevent actual form submit and page reload
});
$("#homeBtn4").on('click', function(event){
	menuEvent($(this).text(),  "#homeDiv");
	event.preventDefault(); // prevent actual form submit and page reload
});
$("#homeBtn04").on('click', function(event){
	menuEvent($(this).text(),  "#homeDiv");
	event.preventDefault(); // prevent actual form submit and page reload
});
$("#homeBtn5").on('click', function(event){
	menuEvent($(this).text(),  "#homeDiv");
	event.preventDefault(); // prevent actual form submit and page reload
});
$("#homeBtn6").on('click', function(event){
	menuEvent($(this).text(),  "#homeDiv");
	event.preventDefault(); // prevent actual form submit and page reload
});
$("#homeBtn7").on('click', function(event){
	menuEvent($(this).text(),  "#homeDiv");
	event.preventDefault(); // prevent actual form submit and page reload
});
$("#homeBtn8").on('click', function(event){
	menuEvent($(this).text(),  "#homeDiv");
	event.preventDefault(); // prevent actual form submit and page reload
});

function consoleAlterQ(text){
	if( (window['console'] !== undefined) ){
		console.log(text);
	}
}
function callResum(company,temporada,jornada){
	//Remove table
	$('#resumTable').find("tr").remove();
	var mygames;
	jQuery.ajax ({
	    url: ctx+'/myaccount/mail@mail.es/'+company+'/'+ temporada+'/'+jornada+'/round',
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
		    else{
		    	mygames=response.round.games;
		    }
	    }
	});

	
	jQuery.ajax ({
		url: ctx+'/myaccount/mail@mail.es/'+company+'/'+temporada+'/'+jornada+'/bet',
		type: "GET",
		data: null,
		contentType: "application/json; charset=utf-8",
		async: false,    //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
		cache: false,    //This will force requested pages not to be cached by the browser  
		processData:false, //To avoid making query String instead of JSON
		success: function(response){
			if(response.errorDto!=0){
				consoleAlterQ("error:"+response.errorDto);
				$('#resumTable').append('<tr id="rowBetTitle" class="quinielatitulo"><td colspan="4">ERROR</td></tr></tr>');       
			}
			else{
				consoleAlterQ("response:"+response);
				consoleAlterQ("response.roundBet:"+response.roundBet);
				consoleAlterQ("response.roundBet.bets:"+response.roundBet.bets);
		    	$('#resumTable').append('<tr id="rowBetTitle" class="quinielatitulo"><td colspan="4">Jornada '+ jornada+'</td></tr>');
				$(response.roundBet.bets).each(function(index, element){
					console.log("index="+index);
					console.log("user="+element.user + " bet="+element.bet);
					var row="";
					row+='<div align="center" id="apuesta'+index+'"><h3>'+getTableMatches(element.bet, mygames)+'</h3></div>';
					$('#resumTable').append(row);
				});
			}
		}
	});
}



