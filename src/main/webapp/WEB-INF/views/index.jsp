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
  	//elementos grÃ¡ficos de index
  	var bLogo=1;
  	var bLogin=2;
  	var bSign=3;
  	var bForget=4;
  	
	function refreshDivs(elem) {
		if (elem == bLogo){
			$('#logoTitle').text("alterQ");

			$('#logoTitle').show();
			$('#logoSubtitle').show();
			$('#logoButton').show();

			$('#loginDiv').hide();
			$('#loginFormResponse').hide();

			$('#signupDiv').hide();
			$('#signupFormResponse').hide();

			$('#forgotPwdDiv').hide();
		}
		if (elem == bLogin){
			$('#logoTitle').text("Login");

			$('#logoTitle').show();
			$('#logoSubtitle').hide();
			$('#logoButton').hide();

			$('#loginDiv').show("blind");
			$('#loginFormResponse').show();

			$('#menu_Login').text("Login");
			$('#menu_Login').attr("href", "index?WHERE=login");

			$('#signupDiv').hide();
			$('#signupFormResponse').hide();

			$('#forgotPwdDiv').hide();
		}		
		if(elem == bSign){
			$('#logoTitle').text("Sign up");

			$('#logoTitle').show();
			$('#logoSubtitle').hide();
			$('#logoButton').hide();

			$('#loginDiv').hide();
			$('#loginFormResponse').hide();

			$('#signupDiv').show();
			$('#signupFormResponse').show();

			$('#forgotPwdDiv').hide();
		}
		if(elem == bForget){
			$('#logoTitle').text("Forget Pwd");

			$('#logoTitle').show();
			$('#logoSubtitle').hide();
			$('#logoButton').hide();

			$('#loginDiv').hide();
			$('#loginFormResponse').hide();

			$('#signupDiv').hide();
			
			$('#forgotPwdDiv').show("clip");
		}

	}
  
    $(document).ready(function() {
		var userLoged=false;
		var bGoLogin = location.search.indexOf("WHERE=login",0) == 1;

			refreshDivs(bLogo);
			
    	var jqxhr =
    	    $.ajax({
    	        url: "${pageContext.request.contextPath}/login",
     	    })
    	    .success (function(response) { 
    		    if(response.errorDto!=null){
					$('#menu_Login').text("Login");
					$('#menu_Login').attr("href", "index?WHERE=login");
					$('#menu_User').text("Invitado");
					$('#menu_User').attr("href", "#");

    				if (bGoLogin)
    					refreshDivs(bLogin);
    				else
    					refreshDivs(bLogo);

    				userLoged=false;
    		    }
    		    else{
    		    	if (response.userAlterQ!=null){
    					$('#menu_Login').text("Logout");
    					//$('#menu_Login').attr("href", "Logout");
    					$('#menu_User').text(response.userAlterQ.name);
    					$('#menu_User').attr("href", "myaccount");

    					refreshDivs(bLogo);    					
    					userLoged=true;
    		    	}
    		    	else{
    					$('#menu_Login').text("Login");
    					$('#menu_Login').attr("href", "index?WHERE=login");
    					$('#menu_User').text("Invitado");
    					$('#menu_User').attr("href", "#");

        				if (bGoLogin)
        					refreshDivs(bLogin);
        				else
        					refreshDivs(bLogo);

        				userLoged=false;
    		    	}
    		    }
    	    })
    	    .error   (function()     { alert("Error")   ; })
//    	    .complete(function()     { alert("complete"); })
    	    ;    	
    	
    	
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
    	 $('#forgotPwdForm').submit(function(e) {
    	        // will pass the form date using the jQuery serialize function
    	        $.post('${pageContext.request.contextPath}/myaccount/forgotPwd', $(this).serialize(), function(response) {
	    		    if(response.errorDto!=null){
	    		    	$('#forgotPwdFormResponse').text(response.errorDto.stringError);
	    		    }
	    		    else{
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
					if( (window['console'] !== undefined) ){
    					console.log('Hay usuario, vamos a hacer el logout');
				    }
    				// will pass the form date using the jQuery serialize function
    				var url= '${pageContext.request.contextPath}/logout';
    				$.get(url, $(this).serialize(), function(response) {
    					if(response.errorDto!=null){
    					}
    					else{
    						$('#menu_Login').text("Login");
    						$('#menu_Login').attr("href", "index?WHERE=login");
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
    	$('#forgetPwd_Div').click(function(){
			refreshDivs(bForget);
   		    return false;
    	});    	     	
    	
    });
    
    </script>
   	
	<body class="homepage" id="bodyClass">

		<!-- Header -->
			<div id="header">
						
				<!-- Inner -->
					<div class="inner">
						<!-- <header id="cabecera" style="background-image: url(<c:url value='/static/resources/images/logo.png'/>)"> -->
						<header id="cabecera">
							<h1><a href="#" id="logoTitle">alterQ</a></h1>
							<hr />
							<span class="byline" id="logoSubtitle">Los amantes de las quinielas</span>
						</header>
						<footer>
							<a id="logoButton" href="quiniela" class="button circled scrolly">Quiniela</a>
							
							<!-- login -->
							<div id="loginDiv">
								<div class="row flush">
								  <div class="4u">&nbsp;</div>
								  <div class="4u">
									<div align="center">
									   <form id="loginForm">
									   		<table class="quiniela">
									   			<tr class="quinielatitulo">
													<td colspan="2">Login</td>
												</tr>
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
									   		<a href="signupDiv" id="signup_Div">Crear un nuevo usuario</a><br>
									   		<a href="#" id="forgetPwd_Div">He olvidado mi contraseña</a>
											<div id="loginFormResponse">respuesta </div>
								        </form>
									</div>
								  </div>
								  <div class="4u">&nbsp;</div>
								</div>
							</div>
							<!-- login -->
							<!-- forgotPwd -->
							<div id="forgotPwdDiv">
								<div class="row flush">
								  <div class="4u">&nbsp;</div>
								  <div class="4u">
									<div align="center">
									   <form id="forgotPwdForm">
									   		<table class="quiniela">
									   			<tr class="quinielatitulo">
													<td colspan="2">Enter your email address and we'll send you a link to reset your password.</td>
												</tr>
										   		<tr class="quinielatitulo">
										   			<td  colspan="2"><input id="id" type="text" size="20" name="id" /></td>
										        </tr>
										   		<tr class="quinielatitulo" align="right">
										   			<td colspan="2"><button id="login_btn" class="button" name="signup" value="send">Send</button></td>
										        </tr>
									   		</table>
									   		<div id="forgotPwdFormResponse">respuesta </div>
								        </form>
									</div>
								  </div>
								  <div class="4u">&nbsp;</div>
								</div>
							</div>
							
							
							<!-- forgotPwd -->
							
							
							<!-- signup -->
							<div id="signupDiv">
								<div class="row flush">
								  <div class="4u">&nbsp;</div>
								  <div class="4u">
									<div align="center">
									   <form id="signupForm">
									   		<table class="quiniela">
									   			<tr class="quinielatitulo">
													<td colspan="2">Sign up</td>
												</tr>
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
										   			<td class="partido">&nbsp;</td>
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
							<!-- forgotPwd -->
							
						</footer>
					</div>		
				<!-- Nav -->
					<nav id="nav">
						<ul>
							<li><a id="menu_Index" href="index">Inicio</a></li>
							<li><a id="menu_Login" href="index?WHERE=login">Login</a></li>
							<li><a id="menu_Quiniela" href="quiniela" >Quiniela</a></li>
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
									<p>Sí­guenos a través de las redes sociales y haz de las quinielas tu vida.</p>
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

