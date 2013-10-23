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
							<h1><a href="#" id="logo">Apuesta</a></h1>
							<hr />
							<span class="byline">Jornada <c:out value="${jornada}" /> Temporada <c:out value="${temporada}" />/<c:out value="${temporada+1-2000}" /></span>
						</header>
						<footer>
<form action="enviarapuesta.php" method="get">
	    <table border="1">
	    <c:forEach var="partido" items="${partidos}">
		    <TR>
		       	<TD  align="center">
		    		<c:out value="${partido.getPos()}" />
		    	</TD>
		       	<TD  align="center">
		       		<c:out value="${partido.getEquipo1()}" />
		    	</TD>
		       	<TD  align="center">
		       		<c:out value="${partido.getEquipo2()}" />
		    	</TD>
		    
		    	<TD width="5" height="5" align="center">
		    		<input type="radio" name=R<c:out value="${partido.getPos()}" /> />
		    	</TD>
		    	<TD width="5" height="5" align="center">
		    		<input type="radio" name=R<c:out value="${partido.getPos()}" /> />
		    	</TD>
		    	<TD width="5" height="5" align="center">
		    		<input type="radio" name=R<c:out value="${partido.getPos()}" /> />
		    	</TD>
		    </TR>
	    </c:forEach>  
	    </TABLE>
	    
	    <p><input type="submit" value="Enviar"></p>
</form>
						
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