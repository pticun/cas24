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
		<link href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600" rel="stylesheet" type="text/css" />
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
    		    }
    		    else{
					$('#nameUserNav').text(response.userAlterQ.name);
					$('#id').val(response.userAlterQ.id);
					$('#name').val(response.userAlterQ.name);
					$('#phoneNumber').val(response.userAlterQ.phoneNumber);
					$('#idSaldo').val(response.userAlterQ.id);
					$('#balance').val(response.userAlterQ.balance);
					//move to start page
					var new_position = $('#bodyClass').offset();
	    		    window.scrollTo(new_position.left,new_position.top);
    		    }
				$('#dataDiv').hide();
				$('#balanceDiv').hide();
    	    })
    	    .error   (function()     { alert("Error")   ; })
//    	    .complete(function()     { alert("complete"); })
    	    ;   
 
    	
	   	$('#data_Div').click(function(){
				$('#balanceDiv').hide();
				$('#dataDiv').show();
			    var jump = $(this).attr('href');
			    var new_position = $('#'+jump).offset();
			    window.scrollTo(new_position.left,new_position.top);
			    return false;
		});    	 
		$('#balance_Div').click(function(){
			$('#dataDiv').hide();
			$('#balanceDiv').show();
		   	var jump = $(this).attr('href');
			var new_position = $('#'+jump).offset();
			window.scrollTo(new_position.left,new_position.top);
			return false;
		});    	 

	   	  	 
    	 $('#userAlterQForm').submit(function(e) {
 	        console.log('update:userAlterQForm');
	        // will pass the form date using the jQuery serialize function
 	        var url= '${pageContext.request.contextPath}/myaccount/'+ $('#id').val();
 	        $.post(url, $(this).serialize(), function(response) {
	    		    if(response.errorDto!=null){
	    		    	$('#userAlterQFormResponse').text(response.errorDto.stringError);
	    		    }
	    		    else{
						$('#userAlterQFormResponse').text(response.userAlterQ.name);
						$('#nameUserNav').text(response.userAlterQ.name);
						//move to start page
						var new_position = $('#bodyClass').offset();
		    		    window.scrollTo(new_position.left,new_position.top);
	    		    }
			   	 });
 	        e.preventDefault(); // prevent actual form submit and page reload
 	 });
    	
    });
    
    </script>

    	<body class="homepage" id="bodyClass">

		<!-- Header -->
			<div id="header">
						
				<!-- Inner -->
					<div class="inner">
						<header>
							<h1><a href="#" id="logo">Mi Cuenta</a></h1>
						</header>

						<!-- Carousel -->
						<div class="carousel">
							<div class="reel">
			
								<article>
									<a href="#dataDiv" id="data_Div" class="image featured"><img src="<c:url value="/static/resources/images/pic01.jpg"/>" alt="" />
									<header>
										<h3>Mis Datos</h3>
									</header>
									<h4>Gestiona los datos personales, de tu usuario o los bancarios.</h4>
									</a>
								</article>
							
								<article>
									<a href="#balanceDiv" id="balance_Div" class="image featured"><img src="<c:url value="/static/resources/images/pic02.jpg"/>" alt="" />
									<header>
										<h3>Mi Saldo</h3>
									</header>
									<h4>Gestiona tu saldo, aumentándolo o disminuyéndolo a tu antojo.</h4>	
									</a>						
								</article>
							
								<article>
									<a href="pendiente" class="image featured"><img src="<c:url value="/static/resources/images/pic03.jpg"/>" alt="" />
									<header>
										<h3>Mis Apuestas</h3>
									</header>
									<h4>Revisa tus apuestas y consulta tu histórico de manera sencilla..</h4>	
									</a>						
								</article>
			
								<article>
									<a href="pendiente" class="image featured"><img src="<c:url value="/static/resources/images/pic04.jpg"/>" alt="" />
									<header>
										<h3>Mis Amigos</h3>
									</header>
									<h4>Informa a otros amantes de las quinielas de tus movimientos.</h4>	
									</a>						
								</article>
							</div>
						</div>

					</div>
					
				<!-- Nav -->
					<nav id="nav">
						<ul>
							<li><a href="index">Inicio</a></li>
							<li><a href="quiniela">Quiniela</a></li>
							<li><a href="myaccount">Mi Cuenta</a></li>
							<li><a href="pendiente">Contacto</a></li>
							<li id="nameUserNav">nombre usuario</li>
						</ul>
					</nav>

			</div>

			<!-- userAlterQ -->
			<div id="dataDiv">
				<div class="row flush">
				  <div class="4u">&nbsp;</div>
				  <div class="4u">
					<div align="center">
					   <form id="userAlterQForm">
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
					   			<td class="partido">&nbsp</td>
					   			<td class="partido"><button id="submit_btn" class="button" name="submitBtn" value="submitBtn">Submit</button></td>
					        </tr>
					        </table>
					        	<div id="userAlterQFormResponse">respuesta </div>
				        </form>
					</div>
				  </div>
				  <div class="4u">&nbsp;</div>
				</div>
			</div>
			<!-- userAlterQ -->

			<!-- balance -->
			<div id="balanceDiv">
				<div class="row flush">
				  <div class="4u">&nbsp;</div>
				  <div class="4u">
					<div>
					   <form id="balanceAlterQForm">
					        <p>Username: <input id="idSaldo" name="id" type="text" readonly="readonly"/>
					          <br />
					          SALDO: <input name="balance" id="balance" type="text"/>
					          <button id="submit_btn" class="button" name="submitBtn" value="submitBtn">Submit</button>
					        </p>
					         <div id="balanceAlterQFormResponse">respuesta </div>
				        </form>
					</div>
				  </div>
				  <div class="4u">&nbsp;</div>
				</div>
			</div>
			<!-- balance -->



		<!-- Footer -->
			<div id="footer">
				<div class="container">
					<div class="row">
						<div class="12u">
						
							<!-- Contact -->
								<section class="contact">
									<header>
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