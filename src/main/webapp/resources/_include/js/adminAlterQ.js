var round=0;
var season=0;
var idUserAlterQ="";
var company=1; // "quiniGoldClassic = 1" depends environment
var sCompany ="";
var sCompanyDefault ="LAE";


function toggleButton(idUniqueButton){
	if ($('#'+idUniqueButton).attr("disabled")) {
		$('#'+idUniqueButton).removeAttr("disabled");
	} else {
		$('#'+idUniqueButton).attr("disabled", "disabled");
	}
}

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
	consoleAlterQ("adminAlterQ ready: round="+window.round+" season="+window.season);
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
		 consoleAlterQ("prevent double submit-");
		 toggleButton("admin_open_btn");
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
						toggleButton("admin_open_btn");
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
		 consoleAlterQ(ctx+'/admin' + '/season/'+ $("input[id=seasonPrizes]").val() + '/round/' + $("input[id=roundPrizes]").val() + '/prizesBet');
		 jQuery.ajax ({
			 url: ctx+'/admin' + '/season/'+ $("input[id=seasonPrizes]").val() + '/round/' + $("input[id=roundPrizes]").val() + '/prizesBet',
			    type: "POST",
			    data: $(this).serialize(),
//			    contentType: "application/json; charset=utf-8",
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
		 toggleButton("admin_updateBalance_btn");
		 var balance = ($("input[id=updateBalanceBalance]").val()=="")?"none":$("input[id=updateBalanceBalance]").val();
		 var balanceInc = ($("input[id=updateIncreaseBalanceBalance]").val()=="")?"none":$("input[id=updateIncreaseBalanceBalance]").val();
		 var balanceDec = ($("input[id=updateDecreaseBalanceBalance]").val()=="")?"none":$("input[id=updateDecreaseBalanceBalance]").val();
		 jQuery.ajax ({
//			 url: ctx+'/admin'+ '/company/' + company + '/user/'+ $("input[id=updateBalanceUser]").val() + '/balance/' + balance + '/' + balanceInc + '/' + balanceDec + '/updateBalanceUser',
			 url: ctx+'/admin'+ '/company/' + company + '/user/'+ $("input[id=updateBalanceUser]").val() + '/balance' ,
			    type: "PUT",
			    data: dataJson,
			    contentType: "application/json; charset=utf-8",
			    async: false,    //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
	            cache: false,    //This will force requested pages not to be cached by the browser  
	            processData:false, //To avoid making query String instead of JSON
			    success: function(response){
			    	toggleButton("admin_updateBalance_btn");
			    	if(response.errorDto!=0){
		   		    	$(response.errorDto).each(function(index, objeto){  
		   		    		consoleAlterQ("result: response="+objeto.errorDto);
		   		    		$('#updateBalanceFormResponse').text(objeto.stringError+" - ");
					    });
		   		    }
		   		    else{
		   		    	//consoleAlterQ("open: response= OK"+response.userAlterQ.name);
						//$('#loginFormResponse').text(response.userAlterQ.name);
						consoleAlterQ("result: response= OK balance:"+response.userAlterQ.balance);
						$('#updateBalanceFormResponse').text("Admin - UpdateBalanceUser - OK balance:"+response.userAlterQ.balance);
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
		 
		 var url= ctx+'/admin'+ '/season/'+ $("input[id=seasonGetFile]").val() + '/round/' + $("input[id=roundFGetile]").val() + '/type/' + $('input:radio[name=opciones]:checked').val() + '/getFile';
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
	//obtenemos los usuarios
	getUsers();
	menuEvent($(this).text(),  "#updateBalanceDiv");
	event.preventDefault(); // prevent actual form submit and page reload
});
$("#fileBtn").on('click', function(event){
	menuEvent($(this).text(),  "#getFileDiv");
	event.preventDefault(); // prevent actual form submit and page reload
});
$("#accountingBtn").on('click', function(event){
	getAccountingInfo(0); //round = 0 para mostrar el ranking global
	menuEvent($(this).text(),  "#accountingDiv");
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
$("#homeBtn9").on('click', function(event){
	menuEvent($(this).text(),  "#homeDiv");
	event.preventDefault(); // prevent actual form submit and page reload
});

$("#listaUsuarios").change(function() {
	 $("#updateBalanceUser").val($("#listaUsuarios").val());
});

$( "#accountingSelectTable" ).on( "click", "li a", function( event ) {
	consoleAlterQ('accountingSelect');
	texto=this.id;
	pos = texto.indexOf('_');
	temporada=texto.substring(0,pos);
	jornada=texto.substring(pos+1);
	consoleAlterQ("company_temporada_jornada="+window.company+"-"+temporada+"-"+jornada);
	//callRanking(window.idUserAlterQ,window.company,temporada,jornada);
	getAccountingInfo(jornada);
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

function getUsers(){
	//Remove table
	$('option').remove();
	jQuery.ajax ({
	    url: ctx+'/admin/users',
	    type: "GET",
	    data: null,
	    contentType: "application/json; charset=utf-8",
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
				$(response.users).each(function(index, element){
					console.log("index="+index);
					console.log("user="+element.id + " name="+element.name);
					var row="";
					row+='<option value="'+element.id+'">'+element.name+' '+element.surnames+'</option>';
					$('#listaUsuarios').append(row);
				});		    	
		    }
	    }
	});

	
}

function getAccountingInfo(round){
	consoleAlterQ('getAccounting');
	consoleAlterQ('loadBetUser='+window.loadBetUser);
	
	//De momento va a piñon pq no funcionan las variables globales
	window.loadBetUser = true;
	
	if(window.loadBetUser){
		window.loadBetUser=false;
		consoleAlterQ("loadBetUser: FALSE");
		var row="";
	
		cleanAccountings();
		
		
		///myaccount/{id:.+}/{company}/{season}/{round}/ranking
   		consoleAlterQ('antes jQuery.ajax - company='+window.company+' season='+window.season+' round='+round);
   		
   		consoleAlterQ('url:'+ctx+'/admin/'+window.company+'/'+ window.season+'/'+round+'/accounting/');  		
		jQuery.ajax ({
			url: ctx+'/admin/'+ window.company+'/'+ window.season+'/'+round+'/accounting',
		    type: "GET",
		    data: null,
		    contentType: "application/json; charset=utf-8",
		    async: false,    //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
	        cache: false,    //This will force requested pages not to be cached by the browser  
	        processData:false, //To avoid making query String instead of JSON
		    success: function(response){
			    if(response.errorDto!=0){
					row="";
			        row+='<tr align="center">';
					row+='<td><br><br>SIN DATOS<br><br></td>';
					row+='</tr>';
					$('#accountingTable').append(row);
			    }
			    else{
			    	if (response.accountEntry.accounts == null)
			    	{
						row="";
				    	row+='<tr align="center">';
				    	row+='<td>'+((sCompany == '')?sCompanyDefault:sCompany)+'</td>';
				    	row+='</tr>';
				    	row+='<tr align="center">';
						row+='<td><br><br>SIN DATOS<br><br></td>';
						row+='</tr>';
						$('#accountingTable').append(row);
						fillRoundSeasonCompany(response);
			    	}
			    	else
			    	{
			    		row='<thead align="center"><tr><td>CONCEPT</td><td>CREDIT</td><td>DEBIT</td></tr></thead>';
			    		$('#summaryAccountingTable').append(row);

			    		row='<tr align="center"><td colspan="3">&nbsp</td></tr>';
			    		$('#summaryAccountingTable').append(row);

			    		if(round == 0){
			    			row='<tr align="center"><td>BALANCE</td><td>'+Math.round(response.accountEntry.balance * 100) / 100 +'</td><td>&nbsp</td></tr>';
			    			$('#summaryAccountingTable').append(row);
			    		}
			    		
			    		row='<tr align="center"><td colspan="3">&nbsp</td></tr>';
			    		$('#summaryAccountingTable').append(row);

			    		row='<tr align="center"><td>BETS</td><td>'+Math.round(response.accountEntry.bets * 100) / 100 +'</td><td>&nbsp</td></tr>';
			    		$('#summaryAccountingTable').append(row);

			    		row='<tr align="center"><td>FINAL BETS</td><td>&nbsp</td><td>'+Math.round(response.accountEntry.finalBets * 100) / 100 +'</td></tr>';
			    		$('#summaryAccountingTable').append(row);

			    		row='<tr align="center"><td>JACKPOTS</td><td>&nbsp</td><td>'+Math.round(response.accountEntry.jackpots * 100) / 100 +'</td></tr>';
			    		$('#summaryAccountingTable').append(row);

			    		row='<tr align="center"><td>REFUNDS</td><td>&nbsp</td><td>'+Math.round(response.accountEntry.refunds * 100) / 100 +'</td></tr>';
			    		$('#summaryAccountingTable').append(row);

			    		row='<tr align="center"><td colspan="3">&nbsp</td></tr>';
			    		$('#summaryAccountingTable').append(row);

			    		row='<tr align="center"><td>INI BALANCES</td><td>'+Math.round(response.accountEntry.initialBalances * 100) / 100 +'</td><td>&nbsp</td></tr>';
			    		$('#summaryAccountingTable').append(row);

			    		row='<tr align="center"><td>DEPOSITS</td><td>'+Math.round(response.accountEntry.deposits * 100) / 100 +'</td><td>&nbsp</td></tr>';
			    		$('#summaryAccountingTable').append(row);

			    		row='<tr align="center"><td>WITHDRAWALS</td><td>&nbsp</td><td>'+Math.round(response.accountEntry.withDrawals * 100) / 100 +'</td></tr>';
			    		$('#summaryAccountingTable').append(row);

			    		row='<tr align="center"><td colspan="3">&nbsp</td></tr>';
			    		$('#summaryAccountingTable').append(row);

			    		row='<tr align="center"><td>PRIZES</td><td>&nbsp</td><td>'+Math.round(response.accountEntry.prizes * 100) / 100 +'</td></tr>';
			    		$('#summaryAccountingTable').append(row);

			    		row='<tr align="center"><td colspan="3">&nbsp</td></tr>';
			    		$('#summaryAccountingTable').append(row);

			    		row='<tr align="center"><td colspan="3">SUMMARY</td></tr>';
			    		$('#summaryAccountingTable').append(row);

			    		row='<tr align="center"><td>CREDIT</td><td>'+Math.round(response.accountEntry.credit * 100) / 100 +'</td><td>&nbsp</td></tr>';
			    		$('#summaryAccountingTable').append(row);
			    		row='<tr align="center"><td>DEBIT</td><td>&nbsp</td><td>'+ Math.round(response.accountEntry.debit * 100) / 100 +'</td></tr>';
			    		$('#summaryAccountingTable').append(row);
			    		
			    		row='<tr align="center"><td colspan="3">&nbsp</td></tr>';
			    		$('#summaryAccountingTable').append(row);

			    		row='<tr align="center"><td colspan="3">TOTAL</td></tr>';
			    		$('#summaryAccountingTable').append(row);
			    		if (response.accountEntry.credit == response.accountEntry.debit){
				    		row='<tr align="center"><td>&nbsp</td><td>0</td><td>0</td></tr>';
				    		$('#summaryAccountingTable').append(row);
			    		}else if (response.accountEntry.credit > response.accountEntry.debit){
				    		row='<tr align="center"><td>&nbsp</td><td>'+Math.round((response.accountEntry.credit - response.accountEntry.debit)*100)/100+'</td><td>&nbsp</td></tr>';
				    		$('#summaryAccountingTable').append(row);
			    		}else{
				    		row='<tr align="center"><td>&nbsp</td><td>&nbsp</td><td>'+Math.round((response.accountEntry.debit - response.accountEntry.credit) * 100) / 100 +'</td></tr>';
				    		$('#summaryAccountingTable').append(row);
			    		}

			    		
			    		
			    		row='<thead align="center"><tr><td>ID</td><td>COMPANY</td><td>USER</td><td>SEASON</td><td>ROUND</td><td>TYPE</td><td>DATE</td><td>DESCRIPTION</td><td>CREDIT</td><td>DEBIT</td></tr></thead>';
			    		$('#accountingTable').append(row);
						$(response.accountEntry.accounts).each(function(index, element){
							console.log("index="+index);
							console.log("type="+element.type + " description="+element.description + " amount="+element.amount);
							row="";
							
//							if (element.type == CONST_TYPE_BET_FINAL)
//								row+='<tr bgcolor="#FFFFBB">';
//							else
//								row+='<tr>';
							row+='<tr align="center">';
					    	row+='<td>';
					    	row+=''+(((index+1)<10)?'0':'')+(index+1);
					    	row+='</td>';
					    	
					    	row+='<td>';
					    	row+=''+element.company;
					    	row+='</td>';

					    	row+='<td>';
					    	row+=''+element.user;
					    	row+='</td>';

					    	row+='<td>';
					    	row+=''+element.season;
					    	row+='</td>';

					    	row+='<td>';
					    	row+=''+element.round;
					    	row+='</td>';

					    	row+='<td>';
					    	row+=''+element.type;
					    	row+='</td>';

					    	var date = new Date(element.date);
					    	row+='<td>';
					    	row+=''+ (date.getDate() + 1) + '/' + date.getMonth() + '/' +  date.getFullYear();
					    	row+='</td>';

					    	row+='<td>';
					    	row+=''+element.description;
					    	row+='</td>';
					    	
					    	row+='<td>';
					    	switch(element.type)
					    	{
					    	case 1: 
					    	case 3: 
					    	case 5:
					    		row+=''+ Math.round(element.amount * 100) / 100;
					    		break;
					    	case 2:
					    	case 4:
					    	case 6:
					    	case 7:
					    	case 8:
					    		row+='&nbsp';
					    		break;
					    	
					    	}
					    	
					    	row+='</td>';
					    	row+='<td>';
					    	switch(element.type)
					    	{
					    	case 1: 
					    	case 3: 
					    	case 5:
					    		row+='&nbsp';
					    		break;
					    	case 2:
					    	case 4:
					    	case 6:
					    	case 7:
					    	case 8:
					    		row+=''+ Math.round(element.amount * 100) / 100;
					    		break;
					    	
					    	}
					    	row+='</td>';
					    	row+='</tr>';
							$('#accountingTable').append(row);
						});
						fillRoundSeasonCompany(response);
			    	}
			    }
		    },
		    error : function (xhr, textStatus, errorThrown) {
				var row="";
		    	row+='<tr align="center">';
		    	row+='<td>('+sCompany+')<br><br></td>';
		    	row+='</tr>';
		        row+='<tr>';
				row+='<td><br><br>ERROR AL OBTENER</td>';
				row+='</tr>';
		        row+='<tr>';
				row+='<td>EL RANKING<br><br></td>';
				row+='</tr>';
				$('#accountingTable').append(row);
            }
	 });
	consoleAlterQ('despues jQuery.ajax');
	
	row="";
	row+='<tr align="center">';
	row+='<td>ACCOUNTING</td>';
	row+='</tr>';
    row+='<tr align="center">';
	row+='<td>';
	row+='<div class="dropdown">';
	row+='<button class="btn btn-danger dropdown-toggle" type="button" data-toggle="dropdown">Selecciona Jornada';
	row+='<span class="caret"></span></button>';
	row+='<ul id="accountingSelect" class="dropdown-menu scrollable-menu" role="menu">';
	row+='<li><a id='+window.season+'_0'+' href=\'#\'>Global</a></li>';
	row+='<li class="divider"></li>';
	var num=1;
	for ( num = 1; num < window.round + 1; num++) {
		row+='<li><a id='+window.season+"_"+num+' href=\'#\'>Jornada '+num+'</a></li>';
	}
	row+='</ul>';
	row+='</div>';		
	row+='</td>';
	row+='</tr>';
	row+='<tr align="center">';
	row+='<td>&nbsp</td>';
	row+='</tr>';
	$('#accountingSelectTable').append(row);
	row="";	
	showDiv(bAccountingRef);
	}	
}
function cleanAccountings(){
	consoleAlterQ('cleanAccountings');
	$('#accountingTable').empty();
	$('#accountingSelectTable').empty();
	$('#summaryAccountingTable').empty();
	

}


