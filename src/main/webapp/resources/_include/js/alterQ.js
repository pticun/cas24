var round=0;
var season=0;
var idUserAlterQ="";

var loadBet=true;
var loadBetUser=true;

var userLoged=false;

//Divs Graphics
var bActual  = 0;
var bHome    = 1;
var bLogin   = 2;
var bSign    = 3;
var bForgot  = 4;
var bQuiniela = 5;
var bMyAccount = 6;
var bMyData = 7;
var bMyBalance = 8;
var bMyBets = 9;
var bMyRank = 10;
var bMyResum = 11;
var bMyAdmin = 12;
var bQuinielaDetail = 13;
var bMyModal = 14;

//Texts
var sHome    = "Inicio";
var sLogin   = "Login";
var sSign    ="";
var sForgot = "";
var sQuininiela = "Quiniela";
var sGuest = "Invitado";
var sLogout = "Logout";
var sAdmin = "Administration";
var sQuiniDetail = "";
var sMyModal = "";

//Refs
var sHomeRef = "#homeDiv";
var sLoginRef = "#loginDiv";
var sSignRef = "#signDiv";
var sForgotRef ="#forgotDiv";
var sQuinielaRef = "#quinielaDiv";
var sGuestRef = "#";
var sMyaccountRef = "#myaccountDiv";
var sLogoutRef = "#logoutDiv";
var sMyDataRef = "#mydataDiv";
var sMyBalanceRef = "#mybalanceDiv";
var sMyBetsRef = "#mybetsDiv";
var sMyRankRef = "#myRankDiv";
var sMyResumRef = "#myResumDiv";
var sMyAdminRef = "admin";
var sQuinielaDetailRef = "#quinielaDetailDiv";
var sMyModalRef = "#myModal";

function initDiv() {
	//document.getElementById("homeDiv").style.display = "block";
	$(sHomeRef).show();
	//document.getElementById("loginDiv").style.display = "none";
	$(sLoginRef).hide();
	//document.getElementById("signDiv").style.display = "none";
	$(sSignRef).hide();
	$('#signupFormResponse').hide();
	//document.getElementById("forgotDiv").style.display = "none";
	$(sForgotRef).hide();
	//document.getElementById("quinielaDiv").style.display = "none";
	$(sQuinielaRef).hide();
	$(sMyaccountRef).hide();
	$(sMyDataRef).hide();
	$(sMyBalanceRef).hide();
	$(sMyBetsRef).hide();
	$(sMyRankRef).hide();
	$(sMyResumRef).hide();
	$(sQuinielaDetailRef).hide();
	$(sMyModalRef).hide();
	
	bActual = bHome;
	
	//document.getElementById("contact").style.display = "none";
	$('#contact').hide();
	//document.getElementById("about").style.display = "none";
	$('#about').hide();
}

function showDiv(elem) {
//alert("showDiv elem="+elem+" actual="+bActual);		
	if (elem == bActual)
		return;
	
	switch (elem){
	case bHome:
		$(sHomeRef).show();
		//document.getElementById("homeDiv").style.display = "block";
		break;
	case bLogin:
		$(sLoginRef).show();
		//document.getElementById("logingDiv").style.display = "block";
		break;
	case bSign:
		$(sSignRef).show();
		//document.getElementById("signDiv").style.display = "block";
		break;
	case bForgot:
		$(sForgotRef).show();
		//document.getElementById("forgotDiv").style.display = "block";
		break;
	case bQuiniela:
		getQuiniela();
		$(sQuinielaRef).show();
		//document.getElementById("quinielaDiv").style.display = "block";
		break;
	case bMyAccount:
		$(sMyaccountRef).show();
		break;
	case bMyData:
		$(sMyDataRef).show();
		break;
	case bMyBalance:
		$(sMyBalanceRef).show();
		break;
	case bMyBets:
		$(sMyBetsRef).show();
		break;
	case bMyRank:
		$(sMyRankRef).show();
		break;
	case bMyResum:
		$(sMyResumRef).show();
		break;
	case bMyAdmin:
		$(sMyAdminRef).open();
		break;
	case bQuinielaDetail:
		$(sQuinielaDetailRef).show();
		break;
	case bMyModal:
		$(sMyModalRef).show();
		break;
	}

	switch (bActual){
	case bHome:
		$(sHomeRef).hide();
		//document.getElementById("homeDiv").style.display = "none";
		break;
	case bLogin:
		$(sLoginRef).hide();
		//document.getElementById("logiDiv").style.display = "none";
		break;
	case bSign:
		$(sSignRef).hide();
		//document.getElementById("signDiv").style.display = "none";
		break;
	case bForgot:
		$(sForgotRef).hide();
		//document.getElementById("forgotDiv").style.display = "none";
		break;
	case bQuiniela:
		$(sQuinielaRef).hide();
		//document.getElementById("quinielaDiv").style.display = "none";
		break;
	case bMyAccount:
		$(sMyaccountRef).hide();
		break;
	case bMyData:
		$(sMyDataRef).hide();
		break;
	case bMyBalance:
		$(sMyBalanceRef).hide();
		break;
	case bMyBets:
		$(sMyBetsRef).hide();
		break;
	case bMyRank:
		$(sMyRankRef).hide();
		break;
	case bMyResum:
		$(sMyResumRef).hide();
		break;
	case bQuinielaDetail:
		$(sQuinielaDetailRef).hide();
		break;
	case bMyModal:
		$(sMyModalRef).hide();
		break;
	}
	
	bActual = elem;
//	alert ("actual="+bActual);
	$('html, body').animate({ scrollTop: '0px' }, 0);
}

//Manage Menu Events 
function menuEvent(name, href)
{
		consoleAlterQ("menuEvent elem="+ name +" href="+href);

	if (href == sHomeRef){
		consoleAlterQ("Home");
		showDiv(bHome);
	}else if (href == sLoginRef){
		consoleAlterQ("Login");
		showDiv(bLogin);
	}else if (href == sSignRef){
		consoleAlterQ("Sign");
		showDiv(bSign);
	}else if (href == sForgotRef){
		consoleAlterQ("Forget");
		showDiv(bForget);
	}else if (href == sQuinielaRef){
		consoleAlterQ("Quiniela");
		showDiv(bQuiniela);
	}else if (href == sGuestRef){
		consoleAlterQ("Guest");
	}else if (href == sMyaccountRef){
		consoleAlterQ("Myaccount");
		showDiv(bMyAccount);
	}else if (href == sLogoutRef){
		consoleAlterQ("Logout");
		//vamos a hacer el logout
		doLogout();
	}else if (href == sMyDataRef){
		consoleAlterQ("Mydata");
		showDiv(bMyData);
	}else if (href == sMyBalanceRef){
		consoleAlterQ("Mybalance");
		showDiv(bMyBalance);
	}else if (href == sMyBetsRef){
		consoleAlterQ("Mybets");
		getUserBets();
		showDiv(bMyBets);
	}else if (href == sMyResumRef){
		consoleAlterQ("MyResum");
		paintResum();
		showDiv(bMyResum);
	}else if (href == sMyRankRef){
		consoleAlterQ("MyRank");
		paintRanking();
		showDiv(bMyRank);
	}else if (href == sMyAdminRef){
		consoleAlterQ("MyAdmin");
		showDiv(bMyAdmin);
	}
	return false;
	
}

function getMainMenuItems(userLoged, user, admin)
	{
	consoleAlterQ("getMainMenuItems userLoged="+userLoged+" user="+user+" admin="+admin);
	//MENU WEB 
	$('#menu-nav li').remove();
	
	if (!admin)
	{
		$('#menu-nav').append('<li><a href="'+sHomeRef+'">' + sHome + '</a></li>');
		$('#menu-nav').append('<li><a href="' + sQuinielaRef + '">' + sQuininiela + '</a></li>');
	}
	
	if (userLoged){
		if (admin)
			$('#menu-nav').append('<li><a href="' + sMyAdminRef + '">' + sAdmin + '</a></li>');
		else
			$('#menu-nav').append('<li><a href="' + sMyaccountRef + '">' + user + '</a></li>');
		$('#menu-nav').append('<li><a href="' + sLogoutRef + '">' + sLogout + '</a></li>');
	}
	else{
		$('#menu-nav').append('<li><a href="' + sGuestRef + '">'+sGuest+'</a></li>');
		$('#menu-nav').append('<li><a href="' + sLoginRef + '">' + sLogin + '</a></li>');
	}
	
	// MENU MOBILE 
	
	$('#menu-nav-mobile li').remove();
	
	if (!admin)
	{
    	$('#menu-nav-mobile').append('<li><a href="'+sHomeRef+'">' + sHome + '</a></li>');
    	$('#menu-nav-mobile').append('<li><a href="' + sQuinielaRef + '">' + sQuininiela + '</a></li>');
	}
	if (userLoged){
		if (admin)
			$('#menu-nav-mobile').append('<li><a href="' + sMyAdminRef + '">' + sAdmin + '</a></li>');
		else
			$('#menu-nav-mobile').append('<li><a href="' + sMyaccountRef + '">' + user + '</a></li>');
		$('#menu-nav-mobile').append('<li><a href="' + sLogoutRef + '">' + sLogout + '</a></li>');
	}
	else{
		$('#menu-nav-mobile').append('<li><a href="' + sGuestRef + '">'+sGuest+'</a></li>');
		$('#menu-nav-mobile').append('<li><a href="' + sLoginRef + '">' + sLogin + '</a></li>');
	}
	
	}

function paintRanking(){
	$('#rankingSelect li').remove();
	$('#rankingSelect').append($("<li><a class='list-group-item' id="+season+"_0>"+(season-1)+"/"+season+"</a></li>"));
	$('#rankingSelect').append($("<li><a class='divider'></a></li>"));
	var num=1;
	for ( num = 1; num < round + 1; num++) {
		$('#rankingSelect').append($("<li><a id='"+season+"_"+num+"' href='#'>"+num+"</a></li>"));
	}
	callRanking(idUserAlterQ,season,round);
}

function paintResum(){
	$('#resumSelect li').remove();
	$('#resumSelect').append($("<li><a class='list-group-item' id="+season+"_0>"+(season-1)+"/"+season+"</a></li>"));
	$('#resumSelect').append($("<li><a class='divider'></a></li>"));
	for ( var num = 1; num < round + 1; num++) {
		$('#resumSelect').append($("<li><a id='"+season+"_"+num+"' href='#'>"+num+"</a></li>"));
	}
	callResum(season,round);
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
	getMainMenuItems(userLoged, userLoged?response.userAlterQ.name:null, userLoged?response.userAlterQ.admin:false);
    
	//Menu Click Events
	$('div').on("click", "nav#menu ul#menu-nav li a", function( event ) {
		menuEvent($(this).text(), $(this).attr("href"));
  		event.preventDefault();
	});
	//Menu Mobile Click Events
	$('div').on("click", "nav#navigation-mobile ul#menu-nav-mobile li a", function( event ) {
		menuEvent($(this).text(), $(this).attr("href"));
		event.preventDefault();
	});
	

	
	var jqxhr =
	    $.ajax({
	        url: ctx+"/login",
 	    })
	    .success (function(response) { 
		    if(response.errorDto!=0){
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
			getMainMenuItems(userLoged, userLoged?response.userAlterQ.name:null, userLoged?response.userAlterQ.admin:false);
	    });
	
	
	$("form a").click(function( event ){
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
  		event.preventDefault();
    });
	
	$('form#loginForm').submit(function( event ) {
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
		   		    if(response.errorDto!=0){
		   		    	consoleAlterQ("login: response="+response.errorDto);
		   		    	$(response.errorDto).each(function(index, objeto){  
		   		    		$('#loginFormResponse').append(objeto.stringError+" - ");
					    });
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
						getMainMenuItems(userLoged, userLoged?response.userAlterQ.name:null, userLoged?response.userAlterQ.admin:false);
						showDiv(bHome);
		   		    }
				    round=response.generalData.round;
				    season=response.generalData.season;
			    }
			});
		 	event.preventDefault(); // prevent actual form submit and page reload
	});
	
	 $('form#forgotPwdForm').submit(function( event ) {
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
		   		    if(response.errorDto!=0){
		   		    	$(response.errorDto).each(function(index, objeto){  
		   		    		$('#forgotPwdFormResponse').append(objeto.stringError+" - ");
					    });
		   		    }
		   		    else{
		   		    }
			    }
			});
		 	event.preventDefault(); // prevent actual form submit and page reload
	 });
	 $('form#signupForm').submit(function( event ) {
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
		   		    if(response.errorDto!=0){
		   		    	$('#signupFormResponse').html("");
		   		    	$(response.errorDto).each(function(index, objeto){  
		   		    		$('#signupFormResponse').append(objeto.stringError+" <br>");
					    });
						showDiv(bSign);
						$('#signupFormResponse').show();
		   		    }
		   		    else{
						userLoged=true;
						getMainMenuItems(userLoged, userLoged?response.userAlterQ.name:null, userLoged?response.userAlterQ.admin:false);
						showDiv(bHome);
						
		   		    }
			    }
			});
		 	event.preventDefault(); // prevent actual form submit and page reload
	 });
	 $('form#betForm').submit(function( event ) {
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
				if(response.errorDto!=0){
	   		    	$(response.errorDto).each(function(index, objeto){  
	   		    		$('#quinielaFormResponse').append(objeto.stringError+" - ");
				    });
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
					$('#14_0').removeAttr('checked');$('#14_1').removeAttr('checked');$('#14_2').removeAttr('checked');$('#14_3').removeAttr('checked');
					$('#15_0').removeAttr('checked');$('#15_1').removeAttr('checked');$('#15_2').removeAttr('checked');$('#15_3').removeAttr('checked');
					$('#quinielaFormResponse').text("Apuesta realizada correctamente");
					doLogin();					
				}
		    }
		});
		event.preventDefault(); // prevent actual form submit and page reload
	 });	 
	
   	 $('form#myDataForm').submit(function( event ) {
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
				    if(response.errorDto!=0){
		   		    	$(response.errorDto).each(function(index, objeto){  
		   		    		$('#userAlterQFormResponse').append(objeto.stringError+" - ");
					    });
				    }
				    else{
						$('#userAlterQFormResponse').text(response.userAlterQ.name+", tus datos han sido actualizados.");
				    }
			    }
		 });
		event.preventDefault(); // prevent actual form submit and page reload
   	 });
			  	 
	$('form#balanceAlterQForm').submit(function( event ) {
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
				    if(response.errorDto!=0){
		   		    	$(response.errorDto).each(function(index, objeto){  
		   		    		$('#balanceAlterQFormResponse').append(objeto.stringError+" - ");
					    });
				    }
				    else{
						$('#balanceAlterQFormResponse').text(response.userAlterQ.name+", tu saldo ha sido actualizado.");
				    }
			    }
		 });
		event.preventDefault(); // prevent actual form submit and page reload
	});	
	$("#goUp").click(function( event ){
		menuEvent($(this).text(), $(this).attr("href"));
		event.preventDefault(); // prevent actual form submit and page reload
    });
	
	$("#quinielaBtn").click(function( event ){
		menuEvent($(this).text(), $(this).attr("href"));
		event.preventDefault(); // prevent actual form submit and page reload
   });
	$("#myDataBtn").on('click', function( event ){
		menuEvent($(this).text(),  "#mydataDiv");
		event.preventDefault(); // prevent actual form submit and page reload
    });
	$("#myBalanceBtn").click(function( event ){
		menuEvent($(this).text(), "#mybalanceDiv");
		event.preventDefault(); // prevent actual form submit and page reload
   });
	$("#myBetsBtn").click(function( event ){
		menuEvent($(this).text(), "#mybetsDiv");
		event.preventDefault(); // prevent actual form submit and page reload
    });
	$("#myRankBtn").click(function( event ){
		menuEvent($(this).text(), "#myRankDiv");
		event.preventDefault(); // prevent actual form submit and page reload
	});
	$("#myResumBtn").click(function( event ){
		menuEvent($(this).text(), "#myResumDiv");
		event.preventDefault(); // prevent actual form submit and page reload
	});
   	$('mydataDiv').click(function( event ){
		$(sMyDataRef).show();
		event.preventDefault(); // prevent actual form submit and page reload
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
		callRanking(idUserAlterQ,temporada,jornada);
		event.preventDefault(); // prevent actual form submit and page reload
    });
   	$( "#resumSelect" ).on( "click", "a", function( event ) {
   		consoleAlterQ('resumSelect');
   		texto=this.id;
   		pos = texto.indexOf('_');
   		temporada=texto.substring(0,pos);
   		jornada=texto.substring(pos+1);
   		consoleAlterQ("temporada_jornada="+temporada+"-"+jornada);
   		callResum(temporada,jornada);
   		event.preventDefault(); // prevent actual form submit and page reload
   	});
	
});

function callRanking(idUserAlterQ,temporada,jornada){
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
		    if(response.errorDto!=0){
   		    	$(response.errorDto).each(function(index, objeto){  
   		    		consoleAlterQ("error:"+response.errorDto);
			    });
		    	$('#rankingTable').append('<tr id="rowBetTitle" class="quinielatitulo"><td colspan="4">ERROR</td></tr></tr>');       
		    }
		    else{
		    	$('#rankingTable').append('<tr id="rowBetTitle" class="quinielatitulo"><td colspan="4">Jornada '+ jornada+'</td></tr>');
			    $(response.roundRanking.rankings).each(function(index, objeto){  
			    	$('#rankingTable').append('<tr id="rowBetTitle" class="quinielatitulo"><td colspan="2" align="left">'+ objeto.user.id+'</td><td colspan="2" align="right">'+ objeto.points+'</td></tr>');
			    });
		    }
	    }
	});
}

function callResum(temporada,jornada){
	//Remove table
	$('#resumTable').find("tr").remove();
	var mygames;
	jQuery.ajax ({
	    url: ctx+'/myaccount/mail@mail.es/season/'+ temporada+'/round/'+jornada,
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
		url: ctx+'/myaccount/mail@mail.es/season/'+season+'/round/'+round+'/bet',
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

function doLogin(){
	var jqxhr =
	    $.ajax({
	        url: ctx+"/login",
 	    })
	    .success (function(response) { 
		    if(response.errorDto==0){
		    	if (response.userAlterQ!=null){
					idUserAlterQ=response.userAlterQ.id;
					$('#idData').val(response.userAlterQ.id);
					$('#nameData').val(response.userAlterQ.name);
					$('#phoneNumberData').val(response.userAlterQ.phoneNumber);
					$('#idSaldo').val(response.userAlterQ.id);
					$('#balanceSaldo').val(response.userAlterQ.balance);
		    	}
		    }
		    round=response.generalData.round;
		    season=response.generalData.season;
	    });
	
}

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
	   		    if(response.errorDto!=0){
	   		    }
	   		    else{
					userLoged=false;
					getMainMenuItems(userLoged, userLoged?response.userAlterQ.name:null, userLoged?response.userAlterQ.admin:false);
	   		    }
		    }
		});

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
			if(response.errorDto!=0){
   		    	$(response.errorDto).each(function(index, objeto){  
   		    		$('#quinielaPrice').append(objeto.stringError+" - ");
			    });
			}
			else{
				$('#quinielaPrice').text(response.roundBet.bets[0].price);
			}
	    }
	});
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
				    if(response.errorDto!=0){
		   		    	$(response.errorDto).each(function(index, objeto){  
		   		    		$('#temporada').append(objeto.stringError+" - ");
					    });
				    }
				    else{
						$('#quinielaTitle').text("Jornada "+ response.round.round+ " Temporada "+response.round.season+"/"+(response.round.season+1-2000));
					    $('#quinielaTable').append('<input type="hidden" name="season" id="season" value="'+ response.round.season+'"/>');       
					    $('#quinielaTable').append('<input type="hidden" name="round" id="round" value="'+ response.round.round+'"/>');       
						var temp= "Jorn "+ response.round.round + " - Temp "+ (response.round.season - 2000)+"/"+(response.round.season + 1 - 2000);
					    $('#quinielaTable').append('<tr id="rowBetTitle" class="quinielatitulo"><td>'+ temp +'</td><td colspan="3">APUESTA</td></tr><tr><td colspan="4"></td></tr>');       
			
						$(response.round.games).each(function(index, element){  
							consoleAlterQ(element);
							var row="";
							var temp=formatMatches(element.player1, element.player2);
							if(index>8){
								temp=temp+(index+1);
							}
							else{
								temp=temp+" "+(index+1);
							}
							if(index==0 || index==4 || index==8 || index==11){
								row+='<tr id="rowBet_'+index+'"><td style="white-space: nowrap" class="partidolinea"><label>'+temp+'</label></td>';
							}
							
							else if (index==13){
								row+='<tr id="rowBet_'+index+'"><td style="white-space: nowrap" class="partidoLast"><label>'+temp+'</label></td>';
							}else if (index!=14){
								row+='<tr id="rowBet_'+index+'"><td style="white-space: nowrap" class="partido"><label>'+temp+'</label></td>';
							}
							
							if (index == 14)
							{
								row+='<tr class="quinielatitulo"><td colspan="5" style="text-align: center; white-space: nowrap"><label>PLENO AL 15</label></td><tr>';
								row+='<tr id="rowBet_'+index+'"><td style="white-space: nowrap" class="partidoLast"><label>'+element.player1+'</label></td>';
								
								row+='<td class="pronostico"><input class="class0" type="checkbox" id="'+index+'_0" name="'+index+'_0" />';
								row+='<label class="quiniela" for="'+index+'_0"></label>';
								row+='</td>';
								row+='<td class="pronostico"><input class="class1" type="checkbox" id="'+index+'_1" name="'+index+'_1" />';
								row+='<label class="quiniela" for="'+index+'_1"></label>';
								row+='</td>';
								row+='<td class="pronostico"><input class="class2" type="checkbox" id="'+index+'_2" name="'+index+'_2" />';
								row+='<label class="quiniela" for="'+index+'_2"></label>';
								row+='</td>';
								row+='<td class="pronostico"><input class="classM" type="checkbox" id="'+index+'_3" name="'+index+'_3" />';
								row+='<label class="quiniela" for="'+index+'_3"></label>';
								row+='</td>';
								row+='</tr>';

								row+='<tr id="rowBet_'+(index+1)+'"><td style="white-space: nowrap" class="partidoLast"><label>'+element.player2+'</label></td>';
								row+='<td class="pronostico"><input class="class0" type="checkbox" id="'+(index+1)+'_0" name="'+(index+1)+'_0" />';
								row+='<label class="quiniela" for="'+(index+1)+'_0"></label>';
								row+='</td>';
								row+='<td class="pronostico"><input class="class1" type="checkbox" id="'+(index+1)+'_1" name="'+(index+1)+'_1" />';
								row+='<label class="quiniela" for="'+(index+1)+'_1"></label>';
								row+='</td>';
								row+='<td class="pronostico"><input class="class2" type="checkbox" id="'+(index+1)+'_2" name="'+(index+1)+'_2" />';
								row+='<label class="quiniela" for="'+(index+1)+'_2"></label>';
								row+='</td>';
								row+='<td class="pronostico"><input class="classM" type="checkbox" id="'+(index+1)+'_3" name="'+(index+1)+'_3" />';
								row+='<label class="quiniela" for="'+(index+1)+'_3"></label>';
								row+='</td>';
								row+='</tr>';

								$('#quinielaTablePleno15').append(row);
							}
							else{
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
							}
							
						});
				    }
			    }
		 });
		}
}

function getUserBets(){
	consoleAlterQ('getUserBets');
	if(loadBetUser){
		loadBetUser=false;
//		loadBetUser=true;
		var row="";
		
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
			    if(response.errorDto!=0){
					row="";
			    	row+='<tr class="quinielatitulo">';
			    	row+='<td>My Bets</td>';
			    	row+='</tr>';
			        row+='<tr class="quiniela">';
					row+='<td class="partidoLast"><h3>SIN APUESTAS</h3></td>';
					row+='</tr>';
					$('#apuestasTable').append(row);
			    }
			    else{
			    	row+='<tr class="quinielatitulo">';
			    	row+='<td colspan="2">My Bets</td>';
			    	row+='</tr>';
					$('#apuestasTable').append(row);
					$(response.roundBet.bets).each(function(index, element){
						console.log("index="+index);
						console.log("user="+element.user + " bet="+element.bet);
						row="";
				    	row+='<tr class="quinielatitulo">';
				    	row+='<td class="partidoLast">';
				    	row+='<a href="javascript:betDetail('+index+','+element.bet+')" >';
				    	row+='Apuesta'+(((index+1)<10)?'0':'')+(index+1)+' Jornada'+response.roundBet.round+ ' '+element.price + ' EUR';
				    	row+='</a>';
				    	row+='</td>';
				    	row+='</tr>';
						$('#apuestasTable').append(row);
					});
			    }
		    },
		    error : function (xhr, textStatus, errorThrown) {
				var row="";
		    	row+='<tr class="quinielatitulo">';
		    	row+='<td colspan="2">My Bets</td>';
		    	row+='</tr>';
		        row+='<tr class="quiniela">';
				row+='<td class="partidoLast"><h3>ERROR AL OBTENER</h3><h3>LAS APUESTAS</h3></td>';
				row+='</tr>';
				$('#apuestasTable').append(row);
            }
	 });
	consoleAlterQ('despues jQuery.ajax');
	showDiv(bMyBets);
	}	
}

function betDetail(index, bet)
{
	var row="";
	var mygames;
	
	consoleAlterQ('antes jQuery.ajax mygames');
	consoleAlterQ('url:'+ctx+'/myaccount/mail@mail.es/season/'+ season+'/round/'+round);
	
	$('#quinielaDetailTable').empty();
    jQuery.ajax ({
		    url: ctx+'/myaccount/mail@mail.es/season/'+ season+'/round/'+round,
		    type: "GET",
		    data: null,
		    contentType: "application/json; charset=utf-8",
		    async: false,    //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
            cache: false,    //This will force requested pages not to be cached by the browser  
            processData:false, //To avoid making query String instead of JSON
		    success: function(response2){
	   		    if(response2.errorDto!=0){
	   		    	$(response2.errorDto).each(function(index, objeto){  
	   		    		consoleAlterQ("getUserBets: response="+objeto.stringError);
				    });
	   		    }
	   		    else{
	   		    	consoleAlterQ("getUserBets: response OK");
	   		    	mygames=response2.round.games;
	   		    	consoleAlterQ("mygames="+mygames);
	   		    }
		    },
		    error : function (xhr, textStatus, errorThrown) {
		    	var indicators="";
		    	indicators+='<ol  class="carousel-indicators">';
		        indicators+='<li data-target="#myCarousel" data-slide-to="0" class="active"></li>';
		        indicators+='</ol>';   
		        $('#myIndicators').append(indicators);
		    	
				var row="";
		    	row+='<tr class="quinielatitulo">';
		    	row+='<td colspan="2">My Bets</td>';
		    	row+='</tr>';
		        row+='<tr class="quiniela">';
				row+='<td class="partidoLast"><h3>ERROR AL OBTENER</h3><h3>LOS PARTIDOS</h3></td>';
				row+='</tr>';
				$('#quinielaDetailTable').append(row);
		    }
		    
	});
    consoleAlterQ('despues jQuery.ajax mygames');
    row+='<tr>';
    row+='<td>';
    row+='<article>';
    row+='<header>';
    row+='<div align="center"><p> APUESTA '+ (index+1) +'</p></div>';
	row+='<div align="center" id="apuesta'+index+'"><h3>'+getTableMatches(bet.toString(), mygames)+'</h3></div>';
	row+='</header>';
	row+='</article>';
    row+='</td>';
    row+='</tr>';
	$('#quinielaDetailTable').append(row);
	showDiv(bQuinielaDetail);
}
