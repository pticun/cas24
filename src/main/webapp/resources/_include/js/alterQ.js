
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
var bConfirmQuiniela = 15;
var bModalReduced = 16;
var bConfirmedQuiniela = 17;
var bCompany = 18;
var bMyAdminCompany = 19;
var bNewPassword = 20;

//Texts
var sHome    = "Inicio";
var sLogin   = "Login";
var sSign    ="";
var sForgot = "";
var sQuininiela = "Quiniela";
var sGuest = "Invitado";
var sLogout = "Logout";
var sAdmin = "Administration";
var sAdminCompany = "AdminCompany";
var sQuiniDetail = "";
var sMyModal = "";
var sConfirmQuiniela = "";
var sConfirmedQuiniela = "";
var sModalReduced ="";
var sCompany ="";
var sCompanyDefault ="QuiniGoldClassic";

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
var sMyAdminCompanyRef = "adminCompany";
var sQuinielaDetailRef = "#quinielaDetailDiv";
var sMyModalRef = "#myModal";
var sConfirmQuinielaRef = "#confirmarQuinielaDiv";
var sConfirmedQuinielaRef = "#confirmadaQuinielaDiv";
var sModalReducedRef = "#modalReduced";
var sCompanyRef ="#myCompanyDiv";
var sMyAdminCompanyRef = "adminCompany";
var sNewPasswordRef = "#newPasswordDiv";

var buttonpressed;

Array.prototype.uniqueArray = function()
{
	this.sort();
	var re=[this[0]];
	for(var i = 1; i < this.length; i++)
	{
		if( this[i] !== re[re.length-1])
		{
			re.push(this[i]);
		}
	}
	return re;
};


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
	$(sConfirmQuinielaRef).hide();
	$(sConfirmedQuinielaRef).hide();
	$(sModalReducedRef).hide();
	$(sCompanyRef).hide();
	$(sNewPasswordRef).hide();
	
	
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
	case bMyAdminCompany:
		$(sMyAdminCompanyRef).open();
		break;
	case bQuinielaDetail:
		$(sQuinielaDetailRef).show();
		break;
	case bMyModal:
		$(sMyModalRef).show();
		break;
	case bConfirmQuiniela:
		$(sConfirmQuinielaRef).show();
		break;
	case bConfirmedQuiniela:
		$(sConfirmedQuinielaRef).show();
		break;
	case bModalReduced:
		$(sModalReducedRef).show();
		break;
	case bCompany:
		getCompanies();
		$(sCompanyRef).show();
		break;
	case bNewPassword:
		$(sNewPasswordRef).show();
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
	case bConfirmQuiniela:
		$(sConfirmQuinielaRef).hide();
		break;
	case bConfirmedQuiniela:
//alert("$(sConfirmedQuinielaRef).hide()");		
		$(sConfirmedQuinielaRef).hide();
		break;
	case bModalReduced:
		$(sModalReducedRef).hide();
		break;
	case bCompany:
		$(sCompanyRef).hide();
		break;
	case bNewPassword:
		$(sNewPasswordRef).hide();
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
	}else if (href == sMyAdminCompanyRef){
		consoleAlterQ("MyAdminCompany");
		showDiv(bMyAdminCompany);
	}else if (href == sCompanyRef){
		consoleAlterQ("MyCompany");
		showDiv(bCompany);
	}else if (href == sNewPasswordRef){
		consoleAlterQ("NewPassword");
		showDiv(bNewPassword);
	}
	return false;
	
}

function getMainMenuItems(userLoged, user)
	{
	consoleAlterQ("getMainMenuItems userLoged="+userLoged+" user="+user+" admin="+window.admin + " superAdmin="+window.superAdmin);
	//MENU WEB 
	$('#menu-nav li').remove();
	
	if (!superAdmin)
	{
		$('#menu-nav').append('<li><a href="'+sHomeRef+'">' + sHome + '</a></li>');
		$('#menu-nav').append('<li><a href="' + sQuinielaRef + '">' + sQuininiela + '</a></li>');
	}
	
	if (userLoged){
		if (superAdmin)
			$('#menu-nav').append('<li><a href="' + sMyAdminRef + '">' + sAdmin + '</a></li>');
		else{
			$('#menu-nav').append('<li><a href="' + sMyaccountRef + '">' + user + '</a></li>');
			if (companySelected){
				$('#menu-nav').append('<li><a href="' + sCompanyRef + '">['+sCompany+']</a></li>');
			}else{
				$('#menu-nav').append('<li><a href="' + sCompanyRef + '">['+sCompanyDefault+']</a></li>');
			}
			if (admin)
				$('#menu-nav').append('<li><a href="' + sMyAdminCompanyRef + '">' + sAdminCompany + '</a></li>');

		}
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
		else{
			$('#menu-nav-mobile').append('<li><a href="' + sMyaccountRef + '">' + user + '</a></li>');
			if (companySelected){
				$('#menu-nav-mobile').append('<li><a href="' + sCompanyRef + '">['+sCompany+']</a></li>');
			}else{
				$('#menu-nav-mobile').append('<li><a href="' + sCompanyRef + '">['+sCompanyDefault+']</a></li>');
			}
		}
		$('#menu-nav-mobile').append('<li><a href="' + sLogoutRef + '">' + sLogout + '</a></li>');
	}
	else{
		$('#menu-nav-mobile').append('<li><a href="' + sGuestRef + '">'+sGuest+'</a></li>');
		$('#menu-nav-mobile').append('<li><a href="' + sLoginRef + '">' + sLogin + '</a></li>');
	}
	

	consoleAlterQ("getMainMenuItems fin");
	}

function paintRanking(){
	$('#rankingSelect li').remove();
	$('#rankingSelect').append($("<li><a class='list-group-item' id="+window.season+"_0>"+(window.season-1)+"/"+window.season+"</a></li>"));
	$('#rankingSelect').append($("<li><a class='divider'></a></li>"));
	var num=1;
	for ( num = 1; num < window.round + 1; num++) {
		$('#rankingSelect').append($("<li><a id='"+window.season+"_"+num+"' href='#'>"+num+"</a></li>"));
	}
	callRanking(window.idUserAlterQ,window.company,window.season,window.round);
}

function paintResum(){
	$('#resumSelect li').remove();
	$('#resumSelect').append($("<li><a class='list-group-item' id="+window.season+"_0>"+(window.season-1)+"/"+window.season+"</a></li>"));
	$('#resumSelect').append($("<li><a class='divider'></a></li>"));
	for ( var num = 1; num < window.round + 1; num++) {
		$('#resumSelect').append($("<li><a id='"+window.season+"_"+num+"' href='#'>"+num+"</a></li>"));
	}
	callResum(window.season,window.round);
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


$(document).ready(function() {
	
	consoleAlterQ("alterQ ready: round="+window.round+" season="+window.season);
		
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
		    	
				window.userLoged=false;
		    }
		    else{
		    	if (response.userAlterQ!=null){
	    			showDiv(bHome);
		    			
					fillUserData(response);
					
					window.userLoged=true;
		    	}
		    	else{
		    		showDiv(bHome);
		    		window.userLoged=false;
		    	}
		    }
		    fillRoundSeasonCompany(response);

			//Paint Main Menu Items
			consoleAlterQ("Menu: pintamos los elementos del menu");
			getMainMenuItems(window.userLoged, window.userLoged?response.userAlterQ.name:null);
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
		   		    	$('#loginFormResponse').html("");
		   		    	$(response.errorDto).each(function(index, objeto){  
		   		    		$('#loginFormResponse').append(objeto.stringError+" - ");
					    });
		   		    	userLoged=false;
		   		    }
		   		    else{
		   		    	consoleAlterQ("login: response="+response.userAlterQ.name);
						$('#loginFormResponse').text(response.userAlterQ.name);
						userLoged=true;
						fillUserData(response);
						getMainMenuItems(userLoged, userLoged?response.userAlterQ.name:null);
						showDiv(bHome);
		   		    }
				    fillRoundSeasonCompany(response);
			    }
			});
		 	event.preventDefault(); // prevent actual form submit and page reload
	});
	
	 $('form#newPwdForm').submit(function( event ) {
		 var dataJson=JSON.stringify($('form#newPwdForm').serializeObject());
		 consoleAlterQ(dataJson);
		 jQuery.ajax ({
			    url: ctx+'/myaccount/'+ idUserAlterQ + '/' + $("input[id=pwdOldNewPwd]").val() + '/' + $("input[id=pwdNewNewPwd]").val() + '/newPwd',
			    type: "POST",
			    data: dataJson,
			    contentType: "application/json; charset=utf-8",
			    async: false,    //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
	            cache: false,    //This will force requested pages not to be cached by the browser  
	            processData:false, //To avoid making query String instead of JSON
			    success: function(response){
		   		    if(response.errorDto!=0){
		   		    	$('#newPwdFormResponse').text("");
		   		    	$(response.errorDto).each(function(index, objeto){  
		   		    		$('#newPwdFormResponse').append(objeto.stringError+" - ");
					    });
		   		    }
		   		    else{
		   		    	consoleAlterQ("newPassword: response= OK");
		   		    	$('#newPwdFormResponse').text("Password Changed - OK");
		   		    }
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
						fillUserData(response);
						getMainMenuItems(userLoged, userLoged?response.userAlterQ.name:null);
						showDiv(bHome);
						
		   		    }
			    }
			});
		 	event.preventDefault(); // prevent actual form submit and page reload
	 });
	 
	 $('form#betForm button#quinielaButton').click(function() {
		 buttonpressed = $('form#betForm button#quinielaButton').val();
		});
	 $('form#betForm button#prizeButton').click(function() {
		 buttonpressed = $('form#betForm button#prizeButton').val();
		});
		
	 $('form#betForm').submit(function( event ) {

		var dataJson=JSON.stringify($('form#betForm').serializeObject());
		consoleAlterQ('betForm:'+dataJson);
		if (buttonpressed == 'Enviar')
		{
			if (idUserAlterQ != ''){
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
	
	 $('form#myCompanyForm').submit(function( event ) {
   		 var dataJson=JSON.stringify($('form#myCompanyForm').serializeObject());
   		 consoleAlterQ('updateDataJsonAlterQ:'+dataJson);
		event.preventDefault(); // prevent actual form submit and page reload
   	 });
	
	 $('form#myCompanyForm').submit(function( event ) {
			showDiv(bHome);
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
	$("#myPwdBtn").click(function( event ){
		menuEvent($(this).text(), "#newPasswordDiv");
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
		consoleAlterQ("company_temporada_jornada="+window.company+"-"+temporada+"-"+jornada);
		callRanking(window.idUserAlterQ,window.company,temporada,jornada);
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
   	
	$('div').on("click", "#reducedCHK", function( event ) {
   		if ($("#reducedCHK").is(':checked')){
   			showReducciones();
   		}
   		else{
   			hideReducciones();
   		}
   		
	});
	$('#companyToChoose').on('change', function() {
		window.company=this.value;
		sCompany = $(this).find(":selected").text();
		window.companySelected = true;
		consoleAlterQ('vamos a repintar el menu');
		window.admin = false;
		window.superAdmin = false;
		//if company == 0 (defect company) all user are admin 
		if (window.company!= window.DEFECT_COMPANY){
			window.admin = window.rols.some(function(boy) { return hasRolCompanyValue(boy, 100, windo.company); });
		}
		window.superAdmin = window.rols.some(function(boy) { return hasRolCompanyValue(boy, 1000, window.DEFECT_COMPANY); });

		consoleAlterQ("Admin:"+window.admin);
		consoleAlterQ("superAdmin:"+window.superAdmin);
		
		getMainMenuItems(true, $('#nameData').val());
		window.loadBetUser = true;
		cleanUserBets();
	});
	
	$('form#detalleUserBetForm').submit(function( event ) {
		consoleAlterQ("Mybets Btn");
		getUserBets();
		showDiv(bMyBets);
		event.preventDefault(); // prevent actual form submit and page reload
	});
	
});

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
function callRanking(idUserAlterQ,company,temporada,jornada){
	//Remove table
	$('#rankingTable').find("tr").remove();
	jQuery.ajax ({
	    url: ctx+'/myaccount/'+ idUserAlterQ +'/'+company+'/'+temporada+'/'+jornada+'/ranking',
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
		url: ctx+'/myaccount/mail@mail.es/'+window.company+'/'+window.season+'/'+window.round+'/bet',
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
					row+='<div align="center" id="apuesta'+index+'"><h3>'+getTableMatches(element.bet, mygames, "#0000ff")+'</h3></div>';
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
					fillUserData(response);
		    	}
		    }
		    fillRoundSeasonCompany(response);
	    });
}
function fillRoundSeasonCompany(response){
	window.round = response.adminData.round;
	window.season = response.adminData.season;
//    company = response.adminData.company;
	consoleAlterQ("fillRoundSeasonCompany: round="+window.round+" season="+window.season);	
}
function fillUserData(response){
	window.idUserAlterQ=response.userAlterQ.id;
	
	$('#idData').val(response.userAlterQ.id);
	$('#nameData').val(response.userAlterQ.name);
	$('#phoneNumberData').val(response.userAlterQ.phoneNumber);
	$('#nickData').val(response.userAlterQ.nick);
	$('#idSurnamesData').val(response.userAlterQ.surnames);
	$('#birthDateData').val(response.userAlterQ.birthday);
	$('#idCardData').val(response.userAlterQ.idCard);
	$('#citySign').val(response.userAlterQ.city);
	$('#typeIDSign').val(response.userAlterQ.typeID);
	
	$('#idSaldo').val(response.userAlterQ.id);
	$('#balanceSaldo').val(response.userAlterQ.balance);	
	window.rols=response.userAlterQ.rols;
	
	//if company == 0 (defect company) all user are admin 
	if (window.company!= window.DEFECT_COMPANY){
		window.admin = window.rols.some(function(boy) { return hasRolCompanyValue(boy, 100, window.company); });
	}
	window.superAdmin = window.rols.some(function(boy) { return hasRolCompanyValue(boy, 1000, window.DEFECT_COMPANY); });

	consoleAlterQ("Admin:"+window.admin);
	consoleAlterQ("superAdmin:"+window.superAdmin);
//	consoleAlterQ("tienes rol:"+rols.some(function(boy) { return hasValue(boy, "rol", 100); }));	
//	consoleAlterQ("tienes rolcompany:"+rols.some(function(boy) { return hasRolCompanyValue(boy, 1000, 0); }));	
	
}

function initializeVars(){
alter("round="+window.round+" season="+window.season);	
	window.round=0;
	window.season=0;
	window.company=1; //companyDefault "quiniGoldClassic = 1"
	window.rols="";
	window.idUserAlterQ="";

	window.loadBet=true;
	window.loadBetUser=true;
	window.loadCompanies=true;

	window.userLoged=false;

	window.companySelected = false;

	window.admin = false;
	window.superAdmin = false;

	//delete combo company
	 $("#companyToChoose option:selected").remove();

}

function hasValue(obj, key, value) {
//	consoleAlterQ(obj+"-"+key+"-"+value);
    return obj.hasOwnProperty(key) && obj[key] === value;

}
function hasRolCompanyValue(obj, rolValue, companyValue) {
//	consoleAlterQ("hasRolCompanyValue: obj[rol]=" + obj["rol"] + " rolValue="+rolValue + " obj[company]=" + obj["company"] + " companyValue="+companyValue);
	return (obj.hasOwnProperty("rol") && obj["rol"]) == rolValue && ( obj.hasOwnProperty("company") && obj["company"] == companyValue);
	
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
					window.userLoged=false;
					window.loadCompanies = false;
					getMainMenuItems(false, null);
	   		    }
		    }
		});

	initializeVars();
	showDiv(bHome);
	consoleAlterQ("LogOut: userLoged="+window.userLoged);     
	
}

function consoleAlterQ(text){
	if( (window['console'] !== undefined) ){
		console.log(text);
	}

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

function getCompanies(){
	consoleAlterQ('getCompanies');
	consoleAlterQ(loadCompanies);
	
	if(window.loadCompanies)
	{
		window.loadCompanies=false;
		
		//delete combo company
		 $("#companyToChoose option:selected").remove();
		
	   		consoleAlterQ('url:'+ctx+'/company/myaccount/'+ window.idUserAlterQ+'/');  		
			jQuery.ajax ({
				url: ctx+'/company/myaccount/'+ window.idUserAlterQ+'/',
			    type: "GET",
			    data: null,
			    contentType: "application/json; charset=utf-8",
			    async: false,    //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
		        cache: false,    //This will force requested pages not to be cached by the browser  
		        processData:false, //To avoid making query String instead of JSON
			    success: function(response){
				    if(response.errorDto!=0){
						consoleAlterQ('Success: no hay companies');
						$('#companyToChoose').append('<option value="'+window.DEFECT_COMPANY+'">QuiniGold</option>');
						window.loadCompanies=true;
				    }
				    else{
				    	var responseCompanyOrder = [];
				    	responseCompanyOrder = jQuery.unique($(response.company));
						$(responseCompanyOrder).each(function(index, element){
							console.log("index="+index+"-id="+element.id + "-company="+element.company+"-nick="+element.nick);
							if (element.company!=window.DEFECT_COMPANY){
								$('#companyToChoose').append('<option value="'+element.company+'">'+element.nick+'</option>');
							}
						});
				    }
			    },
			    error : function (xhr, textStatus, errorThrown) {
					consoleAlterQ('Error: no hay companies');
					$('#companyToChoose').append('<option value="'+window.DEFECT_COMPANY+'">QuiniGold</option>');
					window.loadCompanies=true;
	            }
		 });
	}
}
function cleanUserBets(){
	consoleAlterQ('cleanUserBets');
	$('#apuestasTable').empty();
}
function getUserBets(){
	consoleAlterQ('getUserBets');
	consoleAlterQ('loadBetUser='+loadBetUser);
	if(window.loadBetUser){
		window.loadBetUser=false;
//		loadBetUser=true;
		var row="";
		
		cleanUserBets();
		
   		consoleAlterQ('antes jQuery.ajax - idUserAlterQ='+idUserAlterQ+' company='+window.company+' season='+window.season+' round='+window.round);
   		
   		consoleAlterQ('url:'+ctx+'/myaccount/'+ window.idUserAlterQ+'/'+window.company+'/'+ window.season+'/'+window.round+'/bet');  		
		jQuery.ajax ({
			url: ctx+'/myaccount/'+ window.idUserAlterQ+'/'+window.company+'/'+ window.season+'/'+window.round+'/bet',
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
			    	row+='<td>MIS APUESTAS</td>';
			    	row+='</tr>';
			        row+='<tr align="center">';
					row+='<td><br><br>SIN APUESTAS<br><br></td>';
					row+='</tr>';
					$('#apuestasTable').append(row);
			    }
			    else{
			    	if (response.roundBet == null)
			    	{
						row="";
				    	row+='<tr align="center">';
				    	row+='<td>MIS APUESTAS</td>';
				    	row+='</tr>';
				    	row+='<tr align="center">';
				    	row+='<td>'+((sCompany == '')?sCompanyDefault:sCompany)+'</td>';
				    	row+='</tr>';
				    	row+='<tr align="center">';
						row+='<td><br><br>SIN APUESTAS<br><br></td>';
						row+='</tr>';
						$('#apuestasTable').append(row);
			    	}
			    	else
			    	{
				    	row+='<tr align="center">';
				    	row+='<td colspan="2">MIS APUESTAS</td>';
				    	row+='</tr>';
				    	row+='<tr align="center">';
				    	row+='<td>'+((sCompany == '')?sCompanyDefault:sCompany)+'</td>';
				    	row+='</tr>';
						$('#apuestasTable').append(row);
						$(response.roundBet.bets).each(function(index, element){
							console.log("index="+index);
							console.log("user="+element.user + " bet="+element.bet);
							row="";
							
							if (element.finalBet)
								row+='<tr bgcolor="#FFFFBB">';
							else
								row+='<tr>';
					    	row+='<td>';
					    	row+='<a href="javascript:betDetail('+index+',\''+element.bet+'\')" >';
					    	row+='Apuesta'+(((index+1)<10)?'0':'')+(index+1)+' Jornada'+response.roundBet.round+ ' '+parseFloat(element.price).toFixed(2) + ' EUR';
					    	row+='</a>';
					    	row+='</td>';
					    	row+='</tr>';
							$('#apuestasTable').append(row);
						});
			    	}
			    }
		    },
		    error : function (xhr, textStatus, errorThrown) {
				var row="";
		    	row+='<tr align="center>';
		    	row+='<td>MIS APUESTAS</td>';
		    	row+='</tr>';
		    	row+='<tr align="center">';
		    	row+='<td>('+sCompany+')<br><br></td>';
		    	row+='</tr>';
		        row+='<tr>';
				row+='<td><br><br>ERROR AL OBTENER</td>';
				row+='</tr>';
		        row+='<tr>';
				row+='<td>LAS APUESTAS<br><br></td>';
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
	consoleAlterQ('url:'+ctx+'/myaccount/mail@mail.es/'+window.company+'/'+ window.season+'/'+window.round+'/round');
	
	$('#quinielaDetailTable').empty();
    jQuery.ajax ({
		    url: ctx+'/myaccount/mail@mail.es/'+window.company+'/'+ window.season+'/'+window.round+'/round',
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
	row+='<div align="center" id="apuesta'+index+'"><h3>'+getTableMatches(bet.toString(), mygames, "#0000ff")+'</h3></div>';
	row+='</header>';
	row+='</article>';
    row+='</td>';
    row+='</tr>';
	$('#quinielaDetailTable').append(row);
	row='<tr align="center"><td><button id="detalleUserBetBtn" class="btn btn-danger" name="detalleUserBet" value="detalleUserBet">Mis Apuestas</button></td></tr>';
	$('#quinielaDetailTable').append(row);

	showDiv(bQuinielaDetail);
}

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
