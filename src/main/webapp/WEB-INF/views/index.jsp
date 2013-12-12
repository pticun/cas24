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
  
    $(document).ready(function() {
    	var jqxhr =
    	    $.ajax({
    	        url: "${pageContext.request.contextPath}/login",
     	    })
    	    .success (function(response) { 
    		    if(response.errorDto!=null){
    		    	$('#nameUserNav').text("sin user");
					$('#dataDiv').show();
    		    }
    		    else{
					$('#nameUserNav').text(response.userAlterQ.name);
					$('#dataDiv').hide();
					$('#data_Div').hide();
					//move to start page
					var new_position = $('#bodyClass').offset();
	    		    window.scrollTo(new_position.left,new_position.top);
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
	    		    }
	    		    else{
						$('#loginFormResponse').text(response.userAlterQ.name);
						$('#nameUserNav').text(response.userAlterQ.name);
						$('#dataDiv').hide();
						//move to start page
						var new_position = $('#bodyClass').offset();
		    		    window.scrollTo(new_position.left,new_position.top);
	    		    }
 			   	 });
    	        e.preventDefault(); // prevent actual form submit and page reload
    	 });
    	 $('#signupForm').submit(function(e) {
    	        // will pass the form date using the jQuery serialize function
    	        $.post('${pageContext.request.contextPath}/myaccount', $(this).serialize(), function(response) {
	    		    if(response.errorDto!=null){
	    		    	$('#loginFormResponse').text(response.errorDto.stringError);
	    		    }
	    		    else{
						$('#signupFormResponse').text(response.userAlterQ.name);
						$('#nameUserNav').text(response.userAlterQ.name);
						$('#signupDiv').hide();
						//move to start page
						var new_position = $('#bodyClass').offset();
		    		    window.scrollTo(new_position.left,new_position.top);
	    		    }
 			   	 });
    	        e.preventDefault(); // prevent actual form submit and page reload
    	 });
    	 $('#data_Div').click(function(){
    		    var jump = $(this).attr('href');
    		    var new_position = $('#'+jump).offset();
    		    window.scrollTo(new_position.left,new_position.top);
    		    $('#loginFormResponse').show();
    		    return false;
    	});    	 
    	 $('#signup_Div').click(function(){
    		    var jump = $(this).attr('href');
    		    var new_position = $('#'+jump).offset();
    		    window.scrollTo(new_position.left,new_position.top);
    		    $('#signupFormResponse').show();
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
							<h1><a href="#" id="logo">alterQ</a></h1>
							<hr />
							<span class="byline">Los amantes de las quinielas</span>
						</header>
						<footer>
							<a href="quiniela" class="button circled scrolly">Quiniela</a>
						</footer>
					</div>


				<!-- Nav -->
					<nav id="nav">
						<ul>
							<li><a href="index">Inicio</a></li>
							<li><a href="dataDiv" id="data_Div">login</a></li>
							<li><a href="signupDiv" id="signup_Div">Signup</a></li>
							<li><a href="quiniela">Quiniela</a></li>
							<li><a href="myaccount">Mi Cuenta</a></li>
							<li><a href="pendiente">Contacto</a></li>
							<li id="nameUserNav">nombre usuario</li>
						</ul>
					</nav>

			</div>
			
			<!-- login -->
			<div id="dataDiv">
				<div class="row flush">
				  <div class="4u">&nbsp;</div>
				  <div class="4u">
					<div>
					   <form id="loginForm">
					        <p>Username: <input id="id" name="id" type="text"/>
					          <br />
					          Password: <input type="password" name="pwd" id="pwd"/>
					          <br />
					          <button id="login_btn" class="button" name="login" value="login">Login</button>
					        </p>
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
					<div>
					   <form id="signupForm">
					        <p>Username: <input id="id" name="id" type="text"/>
					          <br />
					          Password: <input type="password" name="pwd" id="pwd"/>
					          <br />
					          Name: <input type="text" name="name" id="name"/>
					          <br />
					          PhoneNumber: <input type="text" name="phoneNumber" id="phoneNumber"/>
					          <br />
					          <button id="login_btn" class="button" name="signup" value="signup">signup</button>
					        </p>
					         <div id="signupFormResponse">respuesta </div>
				        </form>
					</div>
				  </div>
				  <div class="4u">&nbsp;</div>
				</div>
			</div>
			<!-- login -->

		<!-- Footer -->
			<div id="footer">
				<div class="container">
					<div class="row">
						<div class="12u">
							<!-- Contact -->
								<section class="contact">
									<header>
										<h3>�Quieres estar bien informado?</h3>
									</header>
									<p>S�guenos a trav�s de las redes sociales y haz de las quinielas tu vida.</p>
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
