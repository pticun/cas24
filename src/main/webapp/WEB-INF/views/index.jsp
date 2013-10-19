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
	
	<body class="homepage">

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
							<li><a href="quiniela">Quiniela</a></li>
							<li><a href="micuenta">Mi Cuenta</a></li>
							<li><a href="pendiente">Contacto</a></li>
						</ul>
					</nav>

			</div>
			
			<div id="footer">
			
				<!-- login -->
				<div class="row flush">
				  <div class="4u">&nbsp;</div>
				  <div class="4u">
					<div>
					   <form:form commandName="login" id="reg">
					
					        <p>Username: <form:input path="name" id="username"/>
					          <br />
					          Password: <form:password path="pwd" id="password"/>
					          <br />
					          <form:button id="login_btn" class="button" name="login" value="login">Login</form:button>
					        </p>
				        </form:form>
					</div>
				  </div>
				  <div class="4u">&nbsp;</div>
				</div>
				<!-- login -->
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