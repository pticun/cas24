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
		<title>quinimobile</title>
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
  	//elementos gráficos de index
  	var bLogo=1;
  	var bLogoTitle=false;
  	var bLogoSubtitle=false;
  	var bLogoButton=false;

  	var bLogin=2;
  	var bLoginDiv=false;
  	var bLoginFormResponse=false;

  	var bSign=3;
  	var bSignupDiv=false;
  	var bSignupFormResponse=false;
  	
  	var bQuiniela=4
  	var bQuinielaTitle=false;
  	var bQuinielaDiv=false;
  	var bQuinielaFormResponse=false;
  	var bQuinielaButton=false;
  	
	
	function refreshDivs(elem) {
		if (elem == bLogo){
			$('#logoTitle').show();
			$('#logoSubtitle').show();
			$('#logoButton').show();

			$('#loginDiv').hide();
			$('#loginFormResponse').hide();

			$('#signupDiv').hide();
			$('#signupFormResponse').hide();

			$('#quinielaTitle').hide();
			$('#quinielaDiv').hide();
			$('#quinielaFormResponse').hide();
			$('#quinielaButton').hide();
		}
		if (elem == bLogin){
			$('#logoTitle').show();
			$('#logoSubtitle').hide();
			$('#logoButton').hide();

			$('#loginDiv').show("blind");
			$('#loginFormResponse').show();

			$('#signupDiv').hide();
			$('#signupFormResponse').hide();

			$('#quinielaTitle').hide();
			$('#quinielaDiv').hide();
			$('#quinielaFormResponse').hide();
			$('#quinielaButton').hide();
		}		
		if(elem == bSign){
			$('#logoTitle').show();
			$('#logoSubtitle').hide();
			$('#logoButton').hide();

			$('#loginDiv').hide();
			$('#loginFormResponse').hide();

			$('#signupDiv').show();
			$('#signupFormResponse').show();

			$('#quinielaTitle').show();
			$('#quinielaDiv').hide();
			$('#quinielaFormResponse').hide();
			$('#quinielaButton').hide();
		}

		if(elem == bQuiniela){
			$('#logoTitle').show();
			$('#logoSubtitle').hide();
			$('#logoButton').hide();

			$('#loginDiv').hide();
			$('#loginFormResponse').hide();

			$('#signupDiv').hide();
			$('#signupFormResponse').hide();

			$('#quinielaTitle').show();
			$('#quinielaDiv').show("blind");
			$('#quinielaFormResponse').show();
			$('#quinielaButton').show();
		}
	}
  
    $(document).ready(function() {
		var userLoged=false;
		refreshDivs(bLogo);
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

					refreshDivs(bLogo);
					userLoged=false;
    		    }
    		    else{
    		    	if (response.userAlterQ!=null){
    					$('#menu_Login').text("Logout");
    					$('#menu_Login').attr("href", "Logout");
    					$('#menu_User').text(response.userAlterQ.name);
    					$('#menu_User').attr("href", "myaccount");

    					refreshDivs(bLogo);    					
    					userLoged=true;
    		    	}
    		    	else{
    					$('#menu_Login').text("Login");
    					$('#menu_Login').attr("href", "loginDiv");
    					$('#menu_User').text("Invitado");
    					$('#menu_User').attr("href", "#");

    					refreshDivs(bLogo);    					
    					userLoged=false;
    		    	}


    		    }
    	    })
    	    .error   (function()     { alert("Error")   ; })
//    	    .complete(function()     { alert("complete"); })
    	    ;    	
    	

    	var loadBet=true;
	   	$('#menu_Quiniela').click(function(){
	   		$('#logoTitle').text("Quiniela");
	   		refreshDivs(bQuiniela);
		    //$('#cabecera').hide();
 	        var url= '${pageContext.request.contextPath}/bet';
        	if(loadBet){
 	        	loadBet=false;
	 	        $.get(url, $(this).serialize(), function(response) {
	    		    if(response.errorDto!=null){
	    		    	$('#temporada').text(response.errorDto.stringError);
	    		    }
	    		    else{
						$('#quinielaTitle').text("Jornada "+ response.round.round+ " Temporada "+response.round.season+"/"+(response.round.season+1-2000));
					    $('#quinielaTable').append('<tr class="quinielatitulo"><td>Jornada '+ response.round.round+'</td><td colspan="3">APUESTA</td></tr><tr><td colspan="4"></td></tr>');       
	
						$(response.round.games).each(function(index, element){  
							console.log(element);
							var row="";
						
							var temp=padding_right(element.player1+'-'+element.player2,".",28);
							if(index>8){
								temp=temp+(index+1);
							}
							else{
								temp=temp+" "+(index+1);
							}
							if(index==0 || index==4 || index==8 || index==11 || index==14){
								row+='<tr><td class="partidolinea">'+temp+'</td>';
							}
							else{
								row+='<tr><td class="partido">'+temp+'</td>';
							}
							row+='<td class="pronostico"><input class="class1" type="checkbox" id="'+index+'_1" name="'+index+'_1" />';
							row+='<label class="quiniela" hidden for="'+index+'_1"><span hidden>1</span></label>';
							row+='</td>';
							row+='<td class="pronostico"><input class="classX" type="checkbox" id="'+index+'_X" name="'+index+'_X" />';
							row+='<label class="quiniela" hidden for="'+index+'_X"><span hidden>X</span></label>';
							row+='</td>';
							row+='<td class="pronostico"><input class="class2" type="checkbox" id="'+index+'_2" name="'+index+'_2" />';
							row+='<label class="quiniela" hidden for="'+index+'_2"><span hidden>2</span></label>';
							row+='</td>';
							row+='</tr>';
							$('#quinielaTable').append(row);
						});
						
						refreshDivs(bQuiniela);
	    		    }
				});
 	        }
		    return false;
		});
    	
    	
		$('#betForm').submit(function(e) {
			console.log('betForm');
			// will pass the form date using the jQuery serialize function
			var url= '${pageContext.request.contextPath}/bet/';
			$.post(url, $(this).serialize(), function(response) {
				if(response.errorDto!=null){
					$('#quinielaFormResponse').text(response.errorDto.stringError);
				}
				else{
					$('#quinielaFormResponse').text("Apuesta realizada correctamente");
					//$('#nameUserNav').text(response.userAlterQ.name);
					//move to start page
					//var new_position = $('#bodyClass').offset();
					//window.scrollTo(new_position.left,new_position.top);
					
				}
			});
		    e.preventDefault(); // prevent actual form submit and page reload
		});
    	
    	
    	
    	
    	 $('#loginForm').submit(function(e) {
    	        // will pass the form date using the jQuery serialize function
    	        $.post('${pageContext.request.contextPath}/login', $(this).serialize(), function(response) {
	    		    if(response.errorDto!=null){
	    		    	$('#loginFormResponse').text(response.errorDto.stringError);
	    		    	refreshDivs(bLogin);
	    		    	userLoged=false;
	    		    }
	    		    else{
						$('#loginFormResponse').text(response.userAlterQ.name);
						$('#menu_User').text(response.userAlterQ.name);
    					$('#menu_User').attr("href", "myaccount");
						$('#menu_Login').text("Logout");
						
						refreshDivs(bLogo);
						userLoged=true;
	    		    }
 			   	 });
    	        e.preventDefault(); // prevent actual form submit and page reload
    	 });
    	 $('#signupForm').submit(function(e) {
    	        // will pass the form date using the jQuery serialize function
    	        $.post('${pageContext.request.contextPath}/myaccount', $(this).serialize(), function(response) {
	    		    if(response.errorDto!=null){
	    		    	$('#loginFormResponse').text(response.errorDto.stringError);
	    		    	refreshDivs(bSign);
	    		    }
	    		    else{
						$('#signupFormResponse').text(response.userAlterQ.name);
						$('#menu_User').text(response.userAlterQ.name);
    					$('#menu_User').attr("href", "myaccount");
						$('#menu_Login').text("Logout");
						
						refreshDivs(bLogo);
						userLoged=true;
	    		    }
 			   	 });
    	        e.preventDefault(); // prevent actual form submit and page reload
    	 });
    	 $('#menu_Login').click(function(){
    		    //var jump = $(this).attr('href');
    		    //var new_position = $('#'+jump).offset();
    		    //window.scrollTo(new_position.left,new_position.top);
    		    $('#logoTitle').text("Login");
    		    if (userLoged){
    				console.log('Hay usuario, vamos a hacer el logoTitleut');
    				// will pass the form date using the jQuery serialize function
    				var url= '${pageContext.request.contextPath}/logout';
    				$.get(url, $(this).serialize(), function(response) {
    					if(response.errorDto!=null){
    					}
    					else{
    						$('#menu_Login').text("Login");
    						$('#menu_Login').attr("href", "loginDiv");
    						$('#menu_User').text("Invitado");
    						$('#menu_User').attr("href", "#");
    						
    						userLoged=false;
    						//visualizamos boton quiniela
    						$('#logoTitle').text("alterQ");
    						refreshDivs(bLogo);
    					}
    				});
//    			    e.preventDefault(); // prevent actual form submit and page reload
    		    }
    		    else{
					refreshDivs(bLogin);
    		    }
    		    return false;
    	});    	 
    	 $('#signup_Div').click(function(){
    		 	$('#logoTitle').text("Sign up");
    		    //var jump = $(this).attr('href');
    		    //var new_position = $('#'+jump).offset();
    		    //window.scrollTo(new_position.left,new_position.top);
				refreshDivs(bSign);
    		    return false;
    	});    	 
    	 
    	 $('#logoButton').click(function(){
			$('#menu_Quiniela').click();
			return false;
 	});    	 
    	 
    });
    
    </script>
   	
	<body class="homepage" id="bodyClass">

		<!-- Header -->
			<div id="header">
						
				<!-- Inner -->
					<div class="inner">
						<header id="cabecera">
							<h1><a href="#" id="logoTitle">alterQ</a></h1>
							<hr />
							<span class="byline" id="logoSubtitle">Los amantes de las quinielas</span>
						</header>
						<footer>
							<a id="logoButton" href="quinielaDiv" class="button circled scrolly">Quiniela</a>
							<!-- login -->
							<div id="loginDiv">
								<div class="row flush">
								  <div class="4u">&nbsp;</div>
								  <div class="4u">
									<div align="center">
									   <form id="loginForm">
									   		<table class="quiniela">
									   			<TR class="quinielatitulo">
													<TD colspan="2">Login</TD>
												</TR>
										   		<tr>
										   			<td class="partido">Username:</td>
										   			<td class="partido"><input id="id" name="id" type="text"/></td>
										        </tr>
										   		<tr>
										   			<td class="partido">Password:</td>
										   			<td class="partido"><input type="password" name="pwd" id="pwd"/></td>
										        </tr>
										   		<tr align="right">
										   			<td class="partido">&nbsp;</td>
										   			<td class="partido"><button id="login_btn" class="button" name="login" value="login">Login</button></td>
										        </tr>
									   		</table>
									   		<a href="signupDiv" id="signup_Div">Crear un nuevo usuario</a>
									        <div id="loginFormResponse">respuesta </div>
								        </form>
									</div>
								  </div>
								  <div class="4u">&nbsp;</div>
								</div>
							</div>
							<!-- login -->
							<!-- signup -->
							<div id="signupDiv">
								<div class="row flush">
								  <div class="4u">&nbsp;</div>
								  <div class="4u">
									<div align="center">
									   <form id="signupForm">
									   		<table class="quiniela">
									   			<TR class="quinielatitulo">
													<TD colspan="2">Sign up</TD>
												</TR>
										   		<tr>
										   			<td class="partido">Username:</td>
										   			<td class="partido"><input id="id" name="id" type="text"/></td>
										        </tr>
										   		<tr>
										   			<td class="partido">Password:</td>
										   			<td class="partido"><input type="password" name="pwd" id="pwd"/></td>
										        </tr>
										   		<tr>
										   			<td class="partido">Name:</td>
										   			<td class="partido"><input type="text" name="name" id="name"/></td>
										        </tr>
										   		<tr>
										   			<td class="partido">PhoneNumber:</td>
										   			<td class="partido"><input type="text" name="phoneNumber" id="phoneNumber"/></td>
										        </tr>
										   		<tr align="right">
										   			<td class="partido">&nbsp</td>
										   			<td class="partido"><button id="login_btn" class="button" name="signup" value="signup">signup</button></td>
										        </tr>
									   		</table>
								            <div id="signupFormResponse">respuesta </div>
								        </form>
									</div>
								  </div>
								  <div class="4u">&nbsp;</div>
								</div>
							</div>
							<!-- signup -->
							<!-- quiniela -->
							<div id="quinielaDiv">
								<div class="row flush">
								  <div class="4u">&nbsp;</div>
								  <div class="4u">
									<div align="center">
										<span class="byline" id="quinielaTitle">Jornada <c:out value="${jornada}" /> Temporada <c:out value="${temporada}" />/<c:out value="${temporada+1-2000}" /></span>										
										<form id="betForm">
												
											    <table class="quiniela" id="quinielaTable"></table>
											    <!-- <input type="submit" value="Enviar"> -->
											    <div id="quinielaFormResponse">respuesta </div>
											    <button id="quinielaButton" class="button" name="quiniela" value="Enviar">Enviar</button>
										</form>
									</div>
								  </div>
								  <div class="2u">&nbsp;</div>
								</div>
							</div>
							<!-- quiniela -->
							
							
							
						</footer>
					</div>		
				<!-- Nav -->
					<nav id="nav">
						<ul>
							<li><a href="index">Inicio</a></li>
							<li><a href="loginDiv" id="menu_Login">Login</a></li>
							<!-- <li><a href="signupDiv" id="signup_Div">Signup</a></li> -->
							<li><a href="quinielaDiv" id="menu_Quiniela" >Quiniela</a></li>
							<li><a href="#" id="menu_User">Invitado</a></li>
							<!-- <li id="nameUserNav"><a href="myaccount">nombre usuario</a></li> -->
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
