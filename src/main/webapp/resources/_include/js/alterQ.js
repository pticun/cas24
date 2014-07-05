var round=0;
var season=0;
var idUserAlterQ="";

//alert("context:"+ctx);
$(document).ready(function() {
	
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
	
	initDiv(bHome);
	//Paint Main Menu Items
	getMainMenuItems(userLoged, userLoged?response.userAlterQ.name:null);
    
	//Menu Click Events
	$('div').on("click", "nav#menu ul#menu-nav li a", function(event) {
		menuEvent($(this).text(), $(this).attr("href"));
  		event.preventDefault();
	});
	//Menu Mobile Click Events
	$('div').on("click", "nav#navigation-mobile ul#menu-nav-mobile li a", function(event) {
		menuEvent($(this).text(), $(this).attr("href"));
		event.preventDefault();
	});
	

	
	var jqxhr =
	    $.ajax({
	        url: ctx+"/login",
 	    })
	    .success (function(response) { 
		    if(response.errorDto!=null){
		    	if (bActual == bLogin)
		    		showDiv(bLogin);
		    	else
					showDiv(bHome);
		    	
				userLoged=false;
		    }
		    else{
		    	if (response.userAlterQ!=null){
					showDiv(bHome);
					idUserAlterQ=response.userAlterQ.id;

					$('#idData').val(response.userAlterQ.id);
					$('#nameData').val(response.userAlterQ.name);
					$('#phoneNumberData').val(response.userAlterQ.phoneNumber);
					$('#idSaldo').val(response.userAlterQ.id);
					$('#balanceSaldo').val(response.userAlterQ.balance);
					
					userLoged=true;
		    	}
		    	else{
   					showDiv(bHome);
    				userLoged=false;
		    	}
		    }
		    round=response.generalData.round;
		    season=response.generalData.season;
			//Paint Main Menu Items
			consoleAlterQ("Menu: pintamos los elementos del menu");
			getMainMenuItems(userLoged, userLoged?response.userAlterQ.name:null);
	    });
	
	
	
	$("form a").click(function(){
//alert("Form ="+ $(this).text() +" href="+$(this).attr("href"));
		var elem = $(this).text();
		var href = $(this).attr("href");
		
		if (href == sSignRef){
			consoleAlterQ("Sign");
			showDiv(bSign);
		}else if (href == sForgotRef){
			consoleAlterQ("Forget");
			showDiv(bForgot);
		}		
    });
	
	$('form#loginForm').submit(function(event) {
		 var dataJson=JSON.stringify($('form#loginForm').serializeObject());
		 consoleAlterQ(dataJson);
		 jQuery.ajax ({
			    url: ctx+'/login',
			    type: "POST",
			    data: dataJson,
			    contentType: "application/json; charset=utf-8",
			    async: false,    //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
	            cache: false,    //This will force requested pages not to be cached by the browser  
	            processData:false, //To avoid making query String instead of JSON
			    success: function(response){
		   		    if(response.errorDto!=null){
		   		    	consoleAlterQ("login: response="+response.errorDto.stringError);
		   		    	$('#loginFormResponse').text(response.errorDto.stringError);
		   		    	userLoged=false;
		   		    }
		   		    else{
		   		    	consoleAlterQ("login: response="+response.userAlterQ.name);
						$('#loginFormResponse').text(response.userAlterQ.name);
						userLoged=true;
						idUserAlterQ=response.userAlterQ.id;
						$('#idData').val(response.userAlterQ.id);
						$('#nameData').val(response.userAlterQ.name);
						$('#phoneNumberData').val(response.userAlterQ.phoneNumber);
						$('#idSaldo').val(response.userAlterQ.id);
						$('#balanceSaldo').val(response.userAlterQ.balance);
						getMainMenuItems(userLoged, userLoged?response.userAlterQ.name:null);
						showDiv(bHome);
		   		    }
				    round=response.generalData.round;
				    season=response.generalData.season;
			    }
			});
		 	event.preventDefault(); // prevent actual form submit and page reload
	});
	
	 $('form#forgotPwdForm').submit(function(event) {
		 var dataJson=JSON.stringify($('form#forgotPwdForm').serializeObject());
		 consoleAlterQ(dataJson);
		 jQuery.ajax ({
			    url: ctx+'/myaccount/forgotPwd',
			    type: "POST",
			    data: dataJson,
			    contentType: "application/json; charset=utf-8",
			    async: false,    //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
	            cache: false,    //This will force requested pages not to be cached by the browser  
	            processData:false, //To avoid making query String instead of JSON
			    success: function(response){
		   		    if(response.errorDto!=null){
		 		    	$('#forgotPwdFormResponse').text(response.errorDto.stringError);
		   		    }
		   		    else{
		   		    }
			    }
			});
		 	event.preventDefault(); // prevent actual form submit and page reload
	 });
	 $('form#signupForm').submit(function(event) {
		 var dataJson=JSON.stringify($('form#signupForm').serializeObject());
		 consoleAlterQ(dataJson);
		 jQuery.ajax ({
			    url: ctx+'/myaccount',
			    type: "POST",
			    data: dataJson,
			    contentType: "application/json; charset=utf-8",
			    async: false,    //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
	            cache: false,    //This will force requested pages not to be cached by the browser  
	            processData:false, //To avoid making query String instead of JSON
			    success: function(response){
		   		    if(response.errorDto!=null){
		 		    	$('#signupFormResponse').text(response.errorDto.stringError);
						showDiv(bSign);
		   		    }
		   		    else{
						userLoged=true;
						getMainMenuItems(userLoged, userLoged?response.userAlterQ.name:null);
						showDiv(bHome);
		   		    }
			    }
			});
		 	event.preventDefault(); // prevent actual form submit and page reload
	 });
	 $('form#betForm').submit(function(event) {
		var dataJson=JSON.stringify($('form#betForm').serializeObject());
		consoleAlterQ('betForm:'+dataJson);
		// will pass the form date using the jQuery serialize function
		jQuery.ajax ({
			url: ctx+'/myaccount/'+ idUserAlterQ+'/season/'+ season+'/round/'+round+'/bet',
		    type: "POST",
		    data: $(this).serialize(),
//		    contentType: "application/json; charset=utf-8",
		    async: false,    //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
		    cache: false,    //This will force requested pages not to be cached by the browser  
		    processData:false, //To avoid making query String instead of JSON
		    success: function(response){
				if(response.errorDto!=null){
					$('#quinielaFormResponse').text(response.errorDto.stringError);
				}
				else{
					$('#0_1').removeAttr('checked');$('#0_X').removeAttr('checked');$('#0_2').removeAttr('checked');
					$('#1_1').removeAttr('checked');$('#1_X').removeAttr('checked');$('#1_2').removeAttr('checked');
					$('#2_1').removeAttr('checked');$('#2_X').removeAttr('checked');$('#2_2').removeAttr('checked');
					$('#3_1').removeAttr('checked');$('#3_X').removeAttr('checked');$('#3_2').removeAttr('checked');
					$('#4_1').removeAttr('checked');$('#4_X').removeAttr('checked');$('#4_2').removeAttr('checked');
					$('#5_1').removeAttr('checked');$('#5_X').removeAttr('checked');$('#5_2').removeAttr('checked');
					$('#6_1').removeAttr('checked');$('#6_X').removeAttr('checked');$('#6_2').removeAttr('checked');
					$('#7_1').removeAttr('checked');$('#7_X').removeAttr('checked');$('#7_2').removeAttr('checked');
					$('#8_1').removeAttr('checked');$('#8_X').removeAttr('checked');$('#8_2').removeAttr('checked');
					$('#9_1').removeAttr('checked');$('#9_X').removeAttr('checked');$('#9_2').removeAttr('checked');
					$('#10_1').removeAttr('checked');$('#10_X').removeAttr('checked');$('#10_2').removeAttr('checked');
					$('#11_1').removeAttr('checked');$('#11_X').removeAttr('checked');$('#11_2').removeAttr('checked');
					$('#12_1').removeAttr('checked');$('#12_X').removeAttr('checked');$('#12_2').removeAttr('checked');
					$('#13_1').removeAttr('checked');$('#13_X').removeAttr('checked');$('#13_2').removeAttr('checked');
					$('#14_1').removeAttr('checked');$('#14_X').removeAttr('checked');$('#14_2').removeAttr('checked');
					$('#quinielaFormResponse').text("Apuesta realizada correctamente");
				}
		    }
		});
		event.preventDefault(); // prevent actual form submit and page reload
	 });	 
	
   	 $('form#myDataForm').submit(function(event) {
   		 var dataJson=JSON.stringify($('form#myDataForm').serializeObject());
   		 consoleAlterQ('updateDataJsonAlterQ:'+dataJson);
		 jQuery.ajax ({
			    url: ctx+'/myaccount/'+ idUserAlterQ +'/update',
			    type: "PUT",
			    data: dataJson,
			    contentType: "application/json; charset=utf-8",
			    async: false,    //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
		        cache: false,    //This will force requested pages not to be cached by the browser  
		        processData:false, //To avoid making query String instead of JSON
			    success: function(response){
				    if(response.errorDto!=null){
				    	$('#userAlterQFormResponse').text(response.errorDto.stringError);
				    }
				    else{
						$('#userAlterQFormResponse').text(response.userAlterQ.name+", tus datos han sido actualizados.");
				    }
			    }
		 });
		event.preventDefault(); // prevent actual form submit and page reload
   	 });
			  	 
	$('form#balanceAlterQForm').submit(function(event) {
  		 var dataJson=JSON.stringify($('form#balanceAlterQForm').serializeObject());
   		 consoleAlterQ('update:balanceAlterQForm:'+dataJson);
		 jQuery.ajax ({
			    url: ctx+'/myaccount/'+ idUserAlterQ +'/update',
			    type: "PUT",
			    data: dataJson,
			    contentType: "application/json; charset=utf-8",
			    async: false,    //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
		        cache: false,    //This will force requested pages not to be cached by the browser  
		        processData:false, //To avoid making query String instead of JSON
			    success: function(response){
				    if(response.errorDto!=null){
				    	$('#balanceAlterQFormResponse').text(response.errorDto.stringError);
				    }
				    else{
						$('#balanceAlterQFormResponse').text(response.userAlterQ.name+", tu saldo ha sido actualizado.");
				    }
			    }
		 });
		event.preventDefault(); // prevent actual form submit and page reload
	});	
	$("#goUp").click(function(){
		menuEvent($(this).text(), $(this).attr("href"));
    });
	
	$("#quinielaBtn").click(function(){
		menuEvent($(this).text(), $(this).attr("href"));
    });
	$("#myDataBtn").on('click', function(event){
		menuEvent($(this).text(),  "#mydataDiv");
    });
	$("#myBalanceBtn").click(function(){
		menuEvent($(this).text(), "#mybalanceDiv");
    });
	$("#myBetsBtn").click(function(){
		menuEvent($(this).text(), "#mybetsDiv");
    });
	$("#myRankBtn").click(function(){
		menuEvent($(this).text(), "#myRankDiv");
	});
	$("#myResumBtn").click(function(){
		menuEvent($(this).text(), "#myResumDiv");
	});
   	$('mydataDiv').click(function(){
		$(sMyDataRef).show();
   	}); 
//    $("ul[id*=myid] li")
//	$("ul[id*=rankingSelect]").click(function(event){
   	$( "#rankingSelect" ).on( "click", "a", function( event ) {
   		consoleAlterQ('rankingSelect');
		texto=this.id;
		pos = texto.indexOf('_');
		temporada=texto.substring(0,pos);
		jornada=texto.substring(pos+1);
		consoleAlterQ("temporada_jornada="+temporada+"-"+jornada);
		//Remove table
		$('#rankingTable').find("tr").remove();
		jQuery.ajax ({
		    url: ctx+'/myaccount/'+ idUserAlterQ +'/season/'+temporada+'/round/'+jornada+'/ranking',
		    type: "GET",
		    data: null,
		    contentType: "application/json; charset=utf-8",
		    async: false,    //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
		    cache: false,    //This will force requested pages not to be cached by the browser  
		    processData:false, //To avoid making query String instead of JSON
		    success: function(response){
			    if(response.errorDto!=null){
			    	consoleAlterQ("error:"+response.errorDto);
			    	$('#rankingTable').append('<tr id="rowBetTitle" class="quinielatitulo"><td colspan="4">ERROR</td></tr></tr>');       
			    }
			    else{
			    	consoleAlterQ("response:"+response);
			    	$('#rankingTable').append('<tr id="rowBetTitle" class="quinielatitulo"><td colspan="4">Jornada '+ jornada+'</td></tr>');
				    $(response.roundRanking.rankings).each(function(index, objeto){  
				    	$('#rankingTable').append('<tr id="rowBetTitle" class="quinielatitulo"><td colspan="2" align="left">'+ objeto.user.id+'</td><td colspan="2" align="right">'+ objeto.points+'</td></tr>');
				    });
			    }
		    }
		});
		event.preventDefault(); // prevent actual form submit and page reload
    });
   	$( "#resumSelect" ).on( "click", "a", function( event ) {
   		consoleAlterQ('resumSelect');
   		texto=this.id;
   		pos = texto.indexOf('_');
   		temporada=texto.substring(0,pos);
   		jornada=texto.substring(pos+1);
   		consoleAlterQ("temporada_jornada="+temporada+"-"+jornada);
   		//Remove table
   		$('#resumTable').find("tr").remove();
   		var mygames;
		jQuery.ajax ({
		    url: ctx+'/myaccount/mail@mail.es/season/'+ season+'/round/'+round,
		    type: "GET",
		    data: null,
		    contentType: "application/json; charset=utf-8",
		    async: false,    //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
	        cache: false,    //This will force requested pages not to be cached by the browser  
	        processData:false, //To avoid making query String instead of JSON
		    success: function(response){
			    if(response.errorDto!=null){
			    	$('#temporada').text(response.errorDto.stringError);
			    }
			    else{
			    	mygames=response.round.games;
			    }
		    }
		});

   		
   		jQuery.ajax ({
   			url: ctx+'/myaccount/admin/season/'+temporada+'/round/'+jornada+'/bet',
   			type: "GET",
   			data: null,
   			contentType: "application/json; charset=utf-8",
   			async: false,    //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
   			cache: false,    //This will force requested pages not to be cached by the browser  
   			processData:false, //To avoid making query String instead of JSON
   			success: function(response){
   				if(response.errorDto!=null){
   					consoleAlterQ("error:"+response.errorDto);
   					$('#resumTable').append('<tr id="rowBetTitle" class="quinielatitulo"><td colspan="4">ERROR</td></tr></tr>');       
   				}
   				else{
   					consoleAlterQ("response:"+response);
   					consoleAlterQ("response.roundBet:"+response.roundBet);
   					consoleAlterQ("response.roundBet.bets:"+response.roundBet.bets);
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
   		event.preventDefault(); // prevent actual form submit and page reload
   	});
	
	
});

function doLogout(){
	 jQuery.ajax ({
		    url: ctx+'/logout',
		    type: "GET",
		    data: null,
		    contentType: "application/json; charset=utf-8",
		    async: false,    //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
			cache: false,    //This will force requested pages not to be cached by the browser  
			processData:false, //To avoid making query String instead of JSON
		    success: function(response){
	   		    if(response.errorDto!=null){
	   		    }
	   		    else{
					userLoged=false;
					getMainMenuItems(userLoged, userLoged?response.userAlterQ.name:null);
	   		    }
		    }
		});
		event.preventDefault(); // prevent actual form submit and page reload

	showDiv(bHome);
	consoleAlterQ("LogOut: userLoged="+userLoged);     
	
}

function consoleAlterQ(text){
	if( (window['console'] !== undefined) ){
		console.log(text);
	}

}

function calculatePrice(){
	consoleAlterQ('calculatePrice');
//	var dataJson=JSON.stringify($("form#betForm").serializeObject());
	var dataJson=$("form#betForm").serialize();
	jQuery.ajax ({
		url: ctx+'/myaccount/'+ idUserAlterQ+'/season/'+ season+'/round/'+round+'/bet/price',
	    type: "POST",
	    data: dataJson,
//	    contentType: "application/json; charset=utf-8",
	    async: false,    //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
	    cache: false,    //This will force requested pages not to be cached by the browser  
	    processData:false, //To avoid making query String instead of JSON
	    success: function(response){
			if(response.errorDto!=null){
				$('#quinielaPrice').text(response.errorDto.stringError);
			}
			else{
				$('#quinielaPrice').text(response.roundBet.bets[0].price);
			}
	    }
	});
	event.preventDefault(); // prevent actual form submit and page reload
	return false;
}		


function getQuiniela(){
		consoleAlterQ('getQuiniela');
		consoleAlterQ(loadBet);
		if(loadBet){
		 	loadBet=false;
			jQuery.ajax ({
			    url: ctx+'/myaccount/mail@mail.es/season/'+ season+'/round/'+round,
			    type: "GET",
			    data: null,
			    contentType: "application/json; charset=utf-8",
			    async: false,    //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
		        cache: false,    //This will force requested pages not to be cached by the browser  
		        processData:false, //To avoid making query String instead of JSON
			    success: function(response){
				    if(response.errorDto!=null){
				    	$('#temporada').text(response.errorDto.stringError);
				    }
				    else{
						$('#quinielaTitle').text("Jornada "+ response.round.round+ " Temporada "+response.round.season+"/"+(response.round.season+1-2000));
					    $('#quinielaTable').append('<input type="hidden" name="season" id="season" value="'+ response.round.season+'"/>');       
					    $('#quinielaTable').append('<input type="hidden" name="round" id="round" value="'+ response.round.round+'"/>');       
						var temp=padding_right("Jornada","&nbsp;",95);
						temp=padding_left(temp,"&nbsp;",165);
					    $('#quinielaTable').append('<tr id="rowBetTitle" class="quinielatitulo"><td>'+ temp +'</td><td colspan="3">APUESTA</td></tr><tr><td colspan="4"></td></tr>');       
			
						$(response.round.games).each(function(index, element){  
							consoleAlterQ(element);
							var row="";
							var temp=padding_right(element.player1+'-'+element.player2,".",28);
							if(index>8){
								temp=temp+(index+1);
							}
							else{
								temp=temp+" "+(index+1);
							}
							if(index==0 || index==4 || index==8 || index==11 || index==14){
								row+='<tr id="rowBet_'+index+'"><td class="partidolinea">'+temp+'</td>';
							}
							else{
								row+='<tr id="rowBet_'+index+'"><td class="partido">'+temp+'</td>';
							}
							row+='<td class="pronostico"><input class="class1" type="checkbox" id="'+index+'_1" name="'+index+'_1" />';
							row+='<label class="quiniela" for="'+index+'_1"></label>';
							row+='</td>';
							row+='<td class="pronostico"><input class="classX" type="checkbox" id="'+index+'_X" name="'+index+'_X" />';
							row+='<label class="quiniela" for="'+index+'_X"></label>';
							row+='</td>';
							row+='<td class="pronostico"><input class="class2" type="checkbox" id="'+index+'_2" name="'+index+'_2" />';
							row+='<label class="quiniela" for="'+index+'_2"></label>';
							row+='</td>';
							row+='</tr>';
							$('#quinielaTable').append(row);
						});
				    }
			    }
		 });
		event.preventDefault(); // prevent actual form submit and page reload
		}
}



function getUserBets(){
	consoleAlterQ('getUserBets');
	if(loadBetUser){
//		loadBetUser=false;
		loadBetUser=true;

   		consoleAlterQ('antes jQuery.ajax');
		
   		consoleAlterQ('url:'+ctx+'/myaccount/'+ idUserAlterQ+'/season/'+ season+'/round/'+round+'/bet');  		
		jQuery.ajax ({
			url: ctx+'/myaccount/'+ idUserAlterQ+'/season/'+ season+'/round/'+round+'/bet',
		    type: "GET",
		    data: null,
		    contentType: "application/json; charset=utf-8",
		    async: false,    //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
	        cache: false,    //This will force requested pages not to be cached by the browser  
	        processData:false, //To avoid making query String instead of JSON
		    success: function(response){
			    if(response.errorDto!=null){
			    	var indicators="";
			    	indicators+='<ol  class="carousel-indicators">';
			        indicators+='<li data-target="#myCarousel" data-slide-to="0" class="active"></li>';
			        indicators+='</ol>';   
			        $('#myIndicators').append(indicators);
			    	
					var row="";
			    	row+='<div class="active item">';
			        row+='<img src="slide-1.jpg" alt="Slide">';
			        row+='<div class="carousel-caption">';
			        row+='<article>';
			        row+='<header>';
					row+='<div align="center"><h3>SIN APUESTAS</h3></div>';
					row+='</header>';
					row+='</article>';
				    row+='</div>';
				    row+='</div>';
					$('#myItems').append(row);
			    }
			    else{
					//hacemos la llamada para obtener los partidos
					var mygames;
					consoleAlterQ('antes jQuery.ajax mygames');
					consoleAlterQ('url:'+ctx+'/myaccount/mail@mail.es/season/'+ season+'/round/'+round);
					
				    jQuery.ajax ({
						    url: ctx+'/myaccount/mail@mail.es/season/'+ season+'/round/'+round,
						    type: "GET",
						    data: null,
						    contentType: "application/json; charset=utf-8",
						    async: false,    //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
				            cache: false,    //This will force requested pages not to be cached by the browser  
				            processData:false, //To avoid making query String instead of JSON
						    success: function(response2){
					   		    if(response2.errorDto!=null){
					   		    	consoleAlterQ("getUserBets: response="+response2.errorDto.stringError);
					   		    }
					   		    else{
					   		    	consoleAlterQ("getUserBets: response OK");
					   		    	mygames=response2.round.games;
					   		    }
						    },
						    error : function (xhr, textStatus, errorThrown) {
						    	var indicators="";
						    	indicators+='<ol  class="carousel-indicators">';
						        indicators+='<li data-target="#myCarousel" data-slide-to="0" class="active"></li>';
						        indicators+='</ol>';   
						        $('#myIndicators').append(indicators);
						    	
								var row="";
						    	row+='<div class="active item">';
						        row+='<img class="mybetsimg" src="slide-1.jpg" alt="Slide">';
						        row+='<div class="carousel-caption">';
						        row+='<article>';
						        row+='<header>';
								row+='<div><h3>&nbsp;</h3><h3>ERROR AL OBTENER</h3><h3>LOS PARTIDOS</h3></div>';
								row+='</header>';
								row+='</article>';
							    row+='</div>';
							    row+='</div>';
								$('#myItems').append(row);						    
						    }
						    
					});
				    consoleAlterQ('despues jQuery.ajax mygames');
			    	var indicators="";
			    	indicators+='<ol  class="carousel-indicators">';

					$(response.roundBet.bets).each(function(index, element){
						console.log("index="+index);
						console.log("user="+element.user + " bet="+element.bet);
						var row="";
					    if (index==0){
					    	row+='<div class="active item">';
					        indicators+='<li data-target="#myCarousel" data-slide-to="'+index+'" class="active"></li>';
					    }
					    else{
					    	row+='<div class="item">';
					        indicators+='<li data-target="#myCarousel" data-slide-to="'+index+'"></li>';
					    }
					    row+='<img class="mybetsimg" src="slide-1.jpg" alt="Slide">';
					    row+='<div class="carousel-caption">';
					    row+='<article>';
					    row+='<header>';
						row+='<h3> APUESTA '+index+'</h3>';
						row+='<h3> JORNADA '+response.roundBet.round+'</h3>';
						row+='<div align="center" id="apuesta'+index+'"><h3>'+getTableMatches(element.bet, mygames)+'</h3></div>';
						row+='</header>';
						row+='</article>';
					    row+='</div>';
					    row+='</div>';
						$('#myItems').append(row);
					});
			    }
		        indicators+='</ol>';   
		        $('#myIndicators').append(indicators);
		    },
		    error : function (xhr, textStatus, errorThrown) {
		    	var indicators="";
		    	indicators+='<ol  class="carousel-indicators">';
		        indicators+='<li data-target="#myCarousel" data-slide-to="0" class="active"></li>';
		        indicators+='</ol>';   
		        $('#myIndicators').append(indicators);
		    	
				var row="";
		    	row+='<div class="active item">';
		        row+='<img class="mybetsimg" src="slide-1.jpg" alt="Slide">';
		        row+='<div class="carousel-caption">';
		        row+='<article>';
		        row+='<header>';
				row+='<div><h3>&nbsp;</h3><h3>ERROR AL OBTENER</h3><h3>LAS APUESTAS</h3></div>';
				row+='</header>';
				row+='</article>';
			    row+='</div>';
			    row+='</div>';
				$('#myItems').append(row);
            }
	 });
	consoleAlterQ('despues jQuery.ajax');
	showDiv(bMyBets);
	event.preventDefault(); // prevent actual form submit and page reload
	}	
}
