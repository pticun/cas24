<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML>
<!--
	Helios 1.0 by HTML5 UP
	html5up.net | @n33co
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
	<head>
		<title>alterQ - Mi Cuenta</title>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<meta name="description" content="" />
		<meta name="keywords" content="" />
		<!--[if lte IE 8]><script src="<c:url value="/static/resources/js/html5shiv.js"/>"></script><![endif]-->
		<script src="<c:url value="/static/resources/js/jquery.min.js"/>"></script>
		<script src="<c:url value="/static/resources/js/jquery.dropotron.js"/>"></script>
		<script src="<c:url value="/static/resources/js/skel.min.js"/>"></script>
		<script src="<c:url value="/static/resources/js/skel-panels.min.js"/>"></script>
		<script src="<c:url value="/static/resources/js/init.js"/>"></script>
		<link rel="stylesheet" href="<c:url value="/static/resources/css/skel-noscript.css"/>" />
		<link rel="stylesheet" href="<c:url value="/static/resources/css/style.css"/>" />
		<link rel="stylesheet" href="<c:url value="/static/resources/css/style-desktop.css"/>" />
		<link rel="stylesheet" href="<c:url value="/static/resources/css/style-noscript.css"/>" />
		<!--[if lte IE 8]><link rel="stylesheet" href="<c:url value="/static/resources/css/ie8.css"/>" /><![endif]-->
	</head>
	<script type="text/javascript">
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
	</script>
	
  <script type="text/javascript">

  var userLoged=false;
  var loadBet=true;
  var tableBet;
  
  	//Graphics elements constants 
	var cMenuSlice	= 0;
	var cMyData		= 1;
	var cBalance	= 2;
	var cMyBets		= 3;
	
	function goToMainMenu(){
		$('#button_menuMain').click();
		return false;
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
		tableBet='<table style="font-size:16px">';
		$(loadGames).each(function(index, element){  
			var temp=padding_right(element.player1+'-'+element.player2,".",28);
			tableBet+='<tr><td>' + temp + '</td><td align="left">'+ getSign(bet.charAt(index)) + '</td>';
			tableBet+='</tr>';
		});
		tableBet+='</table>';		
	        return tableBet;
	}
	function refreshDivs(elem) {
		if (elem == cMenuSlice){
			$('#menuSlice').show();
			$('#mydataDiv').hide();
			$('#balanceDiv').hide();
			$('#menuBets').hide();
			$('#logoTitle').text("Mi Cuenta");			
		}
		if (elem == cMyData){
			$('#menuSlice').hide();
			$('#mydataDiv').show();
			$('#balanceDiv').hide();
			$('#menuBets').hide();
			$('#logoTitle').text("Mis Datos");
		}
		if (elem == cBalance){
			$('#menuSlice').hide();
			$('#mydataDiv').hide();
			$('#balanceDiv').show();
			$('#menuBets').hide();
			$('#logoTitle').text("Mi Saldo");
		}
		if (elem == cMyBets){
			$('#menuSlice').hide();
			$('#mydataDiv').hide();
			$('#balanceDiv').hide();
			$('#menuBets').show();
			$('#logoTitle').text("Mis Apuestas");			
		}
	}  
    $(document).ready(function() {
    	refreshDivs(cMenuSlice);
    	var jqxhr =
    	    $.ajax({
    	        url: "${pageContext.request.contextPath}/login",
     	    })
    	    .success (function(response) { 
    		    if(response.errorDto!=null){
					$('#menu_Login').text("Login");
					$('#menu_Login').attr("href", "loginDiv");
					$('#menu_User').text("Invitado");
					$('#menu_User').attr("href", "#");

					userLoged=false;
    		    }
    		    else{
    		    	if (response.userAlterQ!=null){
    					$('#menu_Login').text("Logout");
    					$('#menu_Login').attr("href", "Logout");
    					$('#menu_User').text(response.userAlterQ.name);
    					$('#menu_User').attr("href", "myaccount");

    					$('#id').val(response.userAlterQ.id);
    					$('#name').val(response.userAlterQ.name);
    					$('#phoneNumber').val(response.userAlterQ.phoneNumber);
    					$('#idSaldo').val(response.userAlterQ.id);
    					$('#balance').val(response.userAlterQ.balance);
    					
    					userLoged=true;
    		    	}
    		    	else{
    					$('#menu_Login').text("Login");
    					$('#menu_Login').attr("href", "loginDiv");
    					$('#menu_User').text("Invitado");
    					$('#menu_User').attr("href", "#");

    					userLoged=false;
    		    	}
    		    }

    		    refreshDivs(cMenuSlice);
    	    })
    	    .error   (function()     { alert("Error")   ; })
//    	    .complete(function()     { alert("complete"); })
    	    ;   
 
    	
	   	$('#menuSlice_mydata').click(function(){
   			refreshDivs(cMyData);
		    return false;
	});    	 
		$('#menuSlice_balance').click(function(){
			refreshDivs(cBalance);
			return false;
		});    	 

		$('#button_menuMain').click(function(){
			refreshDivs(cMenuSlice);
			return false;
		});    	 

    	 $('#myDataForm').submit(function(e) {
 	        console.log('update:userAlterQForm');
 	        
	        // will pass the form date using the jQuery serialize function
 	        var url= '${pageContext.request.contextPath}/myaccount/'+ $('#id').val();
 	        $.post(url, $(this).serialize(), function(response) {
	    		    if(response.errorDto!=null){
	    		    	$('#userAlterQFormResponse').text(response.errorDto.stringError);
	    		    }
	    		    else{
						$('#userAlterQFormResponse').text(response.userAlterQ.name);
	    		    }
			});
 	        e.preventDefault(); // prevent actual form submit and page reload
		 });
			  	 
	  	 $('#balanceAlterQForm').submit(function(e) {
		        console.log('update:balanceAlterQForm');
		        
	        // will pass the form date using the jQuery serialize function
		        var url= '${pageContext.request.contextPath}/myaccount/'+ $('#id').val();
		        $.post(url, $(this).serialize(), function(response) {
	    		    if(response.errorDto!=null){
	    		    	$('#balanceAlterQFormResponse').text(response.errorDto.stringError);
	    		    }
	    		    else{
						$('#balanceAlterQFormResponse').text(response.userAlterQ.name);
	    		    }
			});
		        e.preventDefault(); // prevent actual form submit and page reload
		 });

     	var loadUserBets=true;
	   	$('#menuSlice_mybets').click(function(){
	   		refreshDivs(cMyBets);
	   		var season=2013;
	   		var round=11;
	   		var user="idmail@arroba.es";
		    		
 	        //var url= '${pageContext.request.contextPath}/bet/betsUser?season='+season+'&round='+round+'&user='+user;
 	        var url= '${pageContext.request.contextPath}/bet/season/'+season+'/round/'+round+'/user/'+user;
        	if(loadUserBets){
        		loadUserBets=false;
	 	        $.get(url, $(this).serialize(), function(response) {
	 	        	
	    		    if(response.errorDto!=null){
	    		    	//$('#temporada').text(response.errorDto.stringError);
						var row="";
						row+='<article>';
						row+='<header>';
						row+='<div align="center"><h3>SIN APUESTAS</h3></div>';
						row+='</header>';
						row+='</article>';
						$('#userBets').append(row);
	    		    }
	    		    else{
						// Dejamos las llamadas AJAX síncronas
						$.ajaxSetup({
						async: false
						});
						
						//hacemos la llamada para obtener los partidos
						var mygames; 
						var url= '${pageContext.request.contextPath}/bet';
					     $.get(url, $(this).serialize(), function(response2) {
					    	 mygames=response2.round.games;
					     });
					     
					  	// Volvemos a dejar las llamadas AJAX asíncronas
					     $.ajaxSetup({
					     async: true
					     });					     
	    		    	//$('#quinielaTitle').text("Jornada "+ response.round.round+ " Temporada "+response.round.season+"/"+(response.round.season+1-2000));
					    //$('#quinielaTable').append('<tr class="quinielatitulo"><td>Jornada '+ response.round.round+'</td><td colspan="3">APUESTA</td></tr><tr><td colspan="4"></td></tr>');       
						$(response.roundBet.bets).each(function(index, element){
							console.log("user="+element.user + " bet="+element.bet);
							var row="";
							row+='<article>';
							row+='<header>';
							row+='<h3> APUESTA '+index+'</h3>';
							row+='<h3> JORNADA '+response.roundBet.round+'</h3>';
							response.roundBet.round
							row+='<div id="apuesta'+index+'"><h3>'+getTableMatches(element.bet, mygames)+'</h3></div>';
							row+='</header>';
							row+='</article>';
							$('#userBets').append(row);
						});
						refreshDivs(cMyBets);
	    		    }
				}).error(function(){
					var row="";
					row+='<article>';
					row+='<header>';
					row+='<div align="center"><h3>&nbsp;</h3><h3>ERROR AL OBTENER</h3><h3>LAS APUESTAS</h3></div>';
					row+='</header>';
					row+='</article>';
					$('#userBets').append(row);
				}
				);
 	        }
		    return false;
		});

    
    
	   	 $('#menu_Login').click(function(){
	   		 
			    if (userLoged){
					console.log('Hay usuario, vamos a hacer el logoTitleut');
					// will pass the form date using the jQuery serialize function
					var url= '${pageContext.request.contextPath}/logout';
					$.get(url, $(this).serialize(), function(response) {
						if(response.errorDto!=null){
						}
						else{
							userLoged=false;
							
							$('#menu_Login').text("Login");
							$('#menu_Login').attr("href", "loginDiv");
							$('#menu_User').text("Invitado");
							$('#menu_User').attr("href", "#");
							
							//deberiamos saltar al logo del  index...
							window.location.href="index";
						}
					});
			    }
			    else{
			    	//hay que llamar al login del index...
			    	window.location.href="index?WHERE=login";
			    }
			    return false;
		});    	     
    
    });
    
    </script>

    	<body class="homepage" id="bodyClass">

		<!-- Header -->
			<div id="header">
						
				<!-- Inner -->
					<div class="inner">
						<header>
							<h1><a href="#" id="logoTitle">Mi Cuenta</a></h1>
						</header>

						<!-- menuSlice -->
						<div id="menuSlice" class="carousel">
							<div class="reel">
			
								<article>
									<a href="#mydataDiv" id="menuSlice_mydata" class="image featured"><img src="<c:url value="/static/resources/images/pic01.jpg"/>" alt="" />
									<header>
										<h3>Mis Datos</h3>
									</header>
									<h4>Gestiona los datos personales, de tu usuario o los bancarios.</h4>
									</a>
								</article>
							
								<article>
									<a href="#balanceDiv" id="menuSlice_balance" class="image featured"><img src="<c:url value="/static/resources/images/pic02.jpg"/>" alt="" />
									<header>
										<h3>Mi Saldo</h3>
									</header>
									<h4>Gestiona tu saldo, aumentándolo o disminuyéndolo a tu antojo.</h4>	
									</a>						
								</article>
							
								<article>
									<a href="#mybetsDiv" id="menuSlice_mybets" class="image featured"><img src="<c:url value="/static/resources/images/pic03.jpg"/>" alt="" />
									<header>
										<h3>Mis Apuestas</h3>
									</header>
									<h4>Revisa tus apuestas y consulta tu histórico de manera sencilla.</h4>	
									</a>						
								</article>
							</div>
						</div>
						<!-- menuSlice -->

						<!-- menuBets -->
						<div id="menuBets" class="carousel">
							<div id="userBets" class="reel">
							</div>
							<button id="button_menuMain" class="button" name="menuMain" value="menuMain">Menu</button>
						</div>
						<!-- menuBets -->






						<!-- myData -->
						<div id="mydataDiv">
							<div class="row flush">
							  <div class="4u">&nbsp;</div>
							  <div class="4u">
								<div align="center">
								   <form id="myDataForm">
								   		<table class="quiniela">
								   			<TR class="quinielatitulo">
												<TD colspan="2">My Account</TD>
												</TR>
								   		
								   		<tr>
								   			<td class="partido">Username:</td>
								   			<td class="partido"><input id="id" name="id" type="text" readonly="readonly"/></td>
								        </tr>
								   		<tr>
								   			<td class="partido">Name:</td>
								   			<td class="partido"><input name="name" id="name" type="text"/></td>
								        </tr>
								   		<tr>
								   			<td class="partido">Phone Number:</td>
								   			<td class="partido"><input name="phoneNumber" id="phoneNumber" type="text"/></td>
								        </tr>
								   		<tr align="right">
								   			<td class="partido"><button class="button" onclick="javascript:goToMainMenu();">Menu</button></td>
								   			<td class="partido"><button type="submit" id="submit_btn" class="button" name="submitBtn" value="submitBtn">Submit</button></td>
								        </tr>
								        </table>
								        	<div id="userAlterQFormResponse">respuesta </div>
							        </form>
								</div>
							  </div>
							  <div class="4u">&nbsp;</div>
							</div>
						</div>
						<!-- myData -->
			
						<!-- balance -->
						<div id="balanceDiv">
							<div class="row flush">
							  <div class="4u">&nbsp;</div>
							  <div class="4u">
								<div align="center">
								   <form id="balanceAlterQForm">
								   		<table class="quiniela">
								   			<TR class="quinielatitulo">
												<TD colspan="2">Saldo</TD>
												</TR>
								   		
								   		<tr>
								   			<td class="partido">Username:</td>
								   			<td class="partido"><input id="idSaldo" name="id" type="text" readonly="readonly"/></td>
								        </tr>
								   		<tr>
								   			<td class="partido">Saldo:</td>
								   			<td class="partido"><input name="balance" id="balance" type="text"/></td>
								        </tr>
								   		<tr align="right">
								   			<td class="partido"><button class="button" onclick="javascript:goToMainMenu();">Menu</button></td>
								   			<td class="partido"><button type="submit" id="submit_btn" class="button" name="submitBtn" value="submitBtn">Submit</button></td>
								        </tr>
								   		</table>
								         <div id="balanceAlterQFormResponse">respuesta </div>
							        </form>
								</div>
							  </div>
							  <div class="4u">&nbsp;</div>
							</div>
						</div>
						<!-- balance -->

					</div>
					
				<!-- Nav -->
					<nav id="nav">
						<ul>
							<li><a href="index">Inicio</a></li>
							<li><a href="loginDiv" id="menu_Login">Login</a></li>
							<li><a href="quiniela" >Quiniela</a></li>
							<li><a href="#" id="menu_User">Invitado</a></li>
						</ul>
					</nav>

			</div>




		<!-- Footer -->
			<div id="footer">
				<div class="container">
					<div class="row">
						<div class="12u">
							<!-- Contact -->
								<section class="contact">
									<header>
										<h3>¿Quieres estar bien informado?</h3>
									</header>
									<p>Síguenos a través de las redes sociales y haz de las quinielas tu vida.</p>
									<ul class="icons">
										<li><a href="#" class="icon icon-twitter"><span>Twitter</span></a></li>
										<li><a href="#" class="icon icon-facebook"><span>Facebook</span></a></li>
										<li><a href="#" class="icon icon-google-plus"><span>Google+</span></a></li>
									</ul>
								</section>
							
							<!-- Copyright -->
								<div class="copyright">
									<ul class="menu">
										<li>&copy; 2013 Goldbittle.</li>
									</ul>
								</div>
							
						</div>
					
					</div>
				</div>
			</div>

	</body>
</html>
