<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<!--[if lt IE 7]><html class="no-js lt-ie9 lt-ie8 lt-ie7" lang="en"> <![endif]-->
<!--[if (IE 7)&!(IEMobile)]><html class="no-js lt-ie9 lt-ie8" lang="en"><![endif]-->
<!--[if (IE 8)&!(IEMobile)]><html class="no-js lt-ie9" lang="en"><![endif]-->
<!--[if (IE 9)]><html class="no-js ie9" lang="en"><![endif]-->
<!--[if gt IE 8]><!--> <html lang="en-US"> <!--<![endif]-->
<head>

<!-- Meta Tags -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<!--  CACHE -->
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Expires" content="Sat, 01 Dec 2001 00:00:00 GMT">
<title>Quinigold</title>   

<meta name="description" content="quinigold" /> 

<!-- Mobile Specifics -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="HandheldFriendly" content="true"/>
<meta name="MobileOptimized" content="320"/>   

<!-- Mobile Internet Explorer ClearType Technology -->
<!--[if IEMobile]>  <meta http-equiv="cleartype" content="on">  <![endif]-->

<!-- Bootstrap -->
<!-- <link href="_include/css/bootstrap.min.css" rel="stylesheet"> --> 
<link href="<c:url value="/static/resources/_include/css/bootstrap.min.css"/>" rel="stylesheet">
<!--<link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css">-->


<!--<link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet">-->

<!-- Main Style -->
<!-- <link href="_include/css/main.css" rel="stylesheet">-->
<link href="<c:url value="/static/resources/_include/css/main.css"/>" rel="stylesheet">

<!-- Supersized -->
<!-- <link href="_include/css/supersized.css" rel="stylesheet"> -->
<!-- <link href="_include/css/supersized.shutter.css" rel="stylesheet"> -->
<link href="<c:url value="/static/resources/_include/css/supersized.css"/>" rel="stylesheet">
<link href="<c:url value="/static/resources/_include/css/supersized.shutter.css"/>" rel="stylesheet">

<!-- FancyBox -->
<!-- <link href="_include/css/fancybox/jquery.fancybox.css" rel="stylesheet"> -->
<link href="<c:url value="/static/resources/_include/css/fancybox/jquery.fancybox.css"/>" rel="stylesheet">

<!-- Font Icons -->
<!-- <link href="_include/css/fonts.css" rel="stylesheet"> -->
<link href="<c:url value="/static/resources/_include/css/fonts.css"/>" rel="stylesheet">

<!-- Shortcodes -->
<!-- <link href="_include/css/shortcodes.css" rel="stylesheet"> -->
<link href="<c:url value="/static/resources/_include/css/shortcodes.css"/>" rel="stylesheet">

<!-- Responsive -->
<!-- <link href="_include/css/bootstrap-responsive.min.css" rel="stylesheet"> -->
<!-- <link href="_include/css/responsive.css" rel="stylesheet"> -->
<link href="<c:url value="/static/resources/_include/css/bootstrap-responsive.min.css"/>" rel="stylesheet">
<link href="<c:url value="/static/resources/_include/css/responsive.css"/>" rel="stylesheet">

<!-- Supersized -->
<!-- <link href="_include/css/supersized.css" rel="stylesheet"> -->
<!-- <link href="_include/css/supersized.shutter.css" rel="stylesheet"> -->
<link href="<c:url value="/static/resources/_include/css/supersized.css"/>" rel="stylesheet">
<link href="<c:url value="/static/resources/_include/css/supersized.shutter.css"/>" rel="stylesheet">

<!-- Google Font 
<link href='http://fonts.googleapis.com/css?family=Titillium+Web:400,200,200italic,300,300italic,400italic,600,600italic,700,700italic,900' rel='stylesheet' type='text/css'>
-->
<!-- Fav Icon -->
<link rel="shortcut icon" href="#">

<link rel="apple-touch-icon" href="#">
<link rel="apple-touch-icon" sizes="114x114" href="#">
<link rel="apple-touch-icon" sizes="72x72" href="#">
<link rel="apple-touch-icon" sizes="144x144" href="#">

<!-- Modernizr -->
<!-- <script src="_include/js/modernizr.js"></script> -->
<script src="<c:url value="/static/resources/_include/js/modernizr.js"/>"></script>
<script type="text/javascript">
var ctx = "<%=request.getContextPath()%>";
</script>

<!-- Analytics 
<script type="text/javascript">

  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'Insert Your Code']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();

</script>
End Analytics -->

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


</script>

<body>

<!-- This section is for Splash Screen -->
<div class="ole">
<section id="jSplash">
	<div id="circle"></div>
</section>
</div>

<!-- Header -->
<header>
    <div class="sticky-nav">
    	<a id="mobile-nav" class="menu-nav" href="#menu-nav"></a>
        
        <div id="logo">
        	<a id="goUp" href="#homeDiv" title="alterQ | Los amantes de las quinielas">alterQ</a>
        </div>
        
        <nav id="menu">
        	<ul id="menu-nav">
            </ul>
        </nav>
        
    </div>
</header>
<!-- End Header -->

<!-- Principal -->
<div id="homeDiv" class="page">
<div class="container">
    <!-- End Title Page -->

    	<div class="row">
            <div class="span12">

            			<ul>
							<!-- Item Project and Filter Name -->
                        	<li class="item-thumbs span3">
                            </li>
                        	<!-- End Item Project -->
							<!-- Item Project and Filter Name -->
                        	<li class="item-thumbs span5">
                            	<!-- Fancybox Media - Gallery Enabled - Title - Link to Video -->
                            	<a class="hover-wrap" id="quinielaBtn" href="#quinielaDiv">
                                </a>
                                <!-- Thumb Image and Description -->
                                <img src="<c:url value='/static/resources/_include/img/work/thumbs/logo.gif'/>" alt="Quiniela">
                            </li>
                        	<!-- End Item Project -->

            			</ul>

            </div>
        </div>
</div>
</div>
<!-- End Principal -->


<%@ include file="/WEB-INF/views/accountUserAlterQ.jsp" %>
<%@ include file="/WEB-INF/views/myBalance.jsp" %>


<!-- Ranking Section -->
<div id="myRankDiv" class="page">
<div class="container">
    <!-- Title Page -->
    <div class="row">
        <div class="span12">
            <div class="title-page">
                <h2 class="title">Ranking</h2>
            </div>
        </div>
    </div>
    <!-- End Title Page -->
    <!-- MyData Form -->
    <div class="row">
		<div class="span3">&nbsp;</div>
		<div class="span6">
			<div class="btn-group dropdown">
			  <button type="button" class="btn btn-default">Ranking</button>
			  <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"><span class="caret"></span></button>
			  <ul class="dropdown-menu" style="z-index: 100;position: relative;" id="rankingSelect" role="menu" aria-labelledby="dropdownMenu">
			    <li value="2013/2014"><a tabindex="-1" href="#">2013/2014</a></li>
			    <li class="divider"></li>
			    <li value="2012/2013"><a tabindex="-1" href="#">2012/2013</a></li>
			    <li value="2012/2013"><a tabindex="-1" href="#">2012/2013</a></li>
			    <li value="2012/2013"><a tabindex="-1" href="#">2012/2013</a></li>
			    <li value="2012/2013"><a tabindex="-1" href="#">2012/2013</a></li>
			    <li value="2012/2013"><a tabindex="-1" href="#">2012/2013</a></li>
			    <li value="2012/2013"><a tabindex="-1" href="#">2012/2013</a></li>
			    <li value="2012/2013"><a tabindex="-1" href="#">2012/2013</a></li>
			    <li value="2012/2013"><a tabindex="-1" href="#">2012/2013</a></li>
			    <li value="2012/2013"><a tabindex="-1" href="#">2012/2013</a></li>
			    <li value="2012/2013"><a tabindex="-1" href="#">2012/2013</a></li>
			    <li value="2012/2013"><a tabindex="-1" href="#">2012/2013</a></li>
			    <li value="2012/2013"><a tabindex="-1" href="#">2012/2013</a></li>
			    <li value="2012/2013"><a tabindex="-1" href="#">2012/2013</a></li>
			    <li value="2012/2013"><a tabindex="-1" href="#">2012/2013</a></li>
			    <li value="2012/2013"><a tabindex="-1" href="#">2012/2013</a></li>
			    <li value="2012/2013"><a tabindex="-1" href="#">2012/2013</a></li>
			    <li value="2012/2013"><a tabindex="-1" href="#">2012/2013</a></li>
			  </ul>
			</div>    
			<div id="rankingResponse">
				    <table class="quiniela" id="rankingTable"></table>
			</div>
		</div>
		<div class="span3">&nbsp;</div>
    </div>
    <!-- End MyData Form -->
</div>
</div>
<!-- End Ranking Section -->
<!-- Resum Section -->
<div id="myResumDiv" class="page">
<div class="container">
    <!-- Title Page -->
    <div class="row">
        <div class="span12">
            <div class="title-page">
                <h2 class="title">Resumen</h2>
            </div>
        </div>
    </div>
    <!-- End Title Page -->
    <!-- MyData Form -->
    <div class="row">
		<div class="span3">&nbsp;</div>
		<div class="span6">
			<div class="btn-group dropdown">
			  <button type="button" class="btn btn-default">Resum</button>
			  <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"><span class="caret"></span></button>
			  <ul class="dropdown-menu" style="z-index: 100;position: relative;" id="resumSelect" role="menu" aria-labelledby="dropdownMenu">
			    <li value="2013/2014"><a tabindex="-1" href="#">2013/2014</a></li>
			    <li class="divider"></li>
			    <li value="2012/2013"><a tabindex="-1" href="#">2012/2013</a></li>
			    <li value="2012/2013"><a tabindex="-1" href="#">2012/2013</a></li>
			    <li value="2012/2013"><a tabindex="-1" href="#">2012/2013</a></li>
			    <li value="2012/2013"><a tabindex="-1" href="#">2012/2013</a></li>
			    <li value="2012/2013"><a tabindex="-1" href="#">2012/2013</a></li>
			    <li value="2012/2013"><a tabindex="-1" href="#">2012/2013</a></li>
			    <li value="2012/2013"><a tabindex="-1" href="#">2012/2013</a></li>
			    <li value="2012/2013"><a tabindex="-1" href="#">2012/2013</a></li>
			    <li value="2012/2013"><a tabindex="-1" href="#">2012/2013</a></li>
			    <li value="2012/2013"><a tabindex="-1" href="#">2012/2013</a></li>
			    <li value="2012/2013"><a tabindex="-1" href="#">2012/2013</a></li>
			    <li value="2012/2013"><a tabindex="-1" href="#">2012/2013</a></li>
			    <li value="2012/2013"><a tabindex="-1" href="#">2012/2013</a></li>
			    <li value="2012/2013"><a tabindex="-1" href="#">2012/2013</a></li>
			    <li value="2012/2013"><a tabindex="-1" href="#">2012/2013</a></li>
			    <li value="2012/2013"><a tabindex="-1" href="#">2012/2013</a></li>
			    <li value="2012/2013"><a tabindex="-1" href="#">2012/2013</a></li>
			  </ul>
			</div>    
			<div id="resumResponse">
				    <table class="quiniela" id="resumTable"></table>
			</div>
		</div>
		<div class="span3">&nbsp;</div>
    </div>
    <!-- End MyData Form -->
</div>
</div>
<!-- End Resum Section -->

<%@ include file="/WEB-INF/views/about.jsp" %>

<%@ include file="/WEB-INF/views/contact.jsp" %>

<!-- Login Section -->
<div id="loginDiv" class="page">
    <!-- Login Form -->
    <div class="row">
		<div align="center">
		   <form id="loginForm">
		   		<table class="quiniela">
		   			<tr class="quinielatitulo">
						<td colspan="2">LOGIN</td>
					</tr>
			   		<tr class="quiniela">
			   			<td class="partidoLast">Username:</td>
			   			<td class="partidoLast"><input id="idLogin" name="id" type="text"/></td>
			        </tr>
			   		<tr class="quiniela">
			   			<td class="partidoLast">Password:</td>
			   			<td class="partidoLast"><input type="password" name="pwd" id="pwdLogin"/></td>
			        </tr>

			   		<tr class="quiniela" align="center">
			   			<td align="center" colspan="2" class="partidoLast"><button id="login_btn" class="button" name="login" value="login">Login</button></td>
			        </tr>

		   		</table>
		   		<a id="signupA" href="#signDiv">Crear un nuevo usuario</a><br>
		   		<a href="#forgotDiv">He olvidado mi contrase&ntilde;a</a>
				<div id="loginFormResponse">respuesta </div>
	        </form>
		</div>
    </div>
    <!-- End Login Form -->
</div>
<!-- End Login Section -->


<!-- Forgot Section -->
<div id="forgotDiv" class="page">
<div class="container">
    <!-- Forgot Form -->
    <div class="row">
		<div align="center">
		   <form id="forgotPwdForm">
		   		<table class="quiniela">
		   			<tr class="quinielatitulo">
						<td colspan="2">RECORDAR PASSWORD</td>
					</tr>
			   		<tr class="quiniela">
			   			<td class="partidoLast">Login:</td>
			   			<td class="partidoLast"><input id="idForgot" type="text" size="20" name="id" placeholder="idLogin"/></td>
			        </tr>
			   		<tr class="quiniela" align="center">
			   			<td align="center" colspan="2" class="partidoLast"><button id="forgot_btn" class="button" name="signup" value="send">Send</button></td>
			        </tr>
		   		</table>
		   		<div id="forgotPwdFormResponse">respuesta </div>
	        </form>
		</div>
    </div>
    <!-- End Forgot Form -->
</div>
</div>
<!-- End Forgot Section -->

<!-- Quiniela Section -->
<div id="quinielaDiv" class="page">
<div class="container">
    <!-- Quiniela Form -->
		<div align="center">
			<form id="betForm">
					<table>
						<tr>
							<td>
							    <table class="quiniela" border="1" id="quinielaTable">
							    </table>
							</td>
							<td>
							    <table class="quiniela" border="1" id="quinielaTableRec" style="display: none">
							    </table>
							</td>
						</tr>
					</table>
				    <table class="quiniela" border="1" id="quinielaTablePleno15">
				    </table>
				    <p></p>
				    <table class="quiniela" border="1" id="quinielaTableReduced">
				    </table>
				    <p></p>
				    <table class="quiniela" border="1" id="quinielaTablePrize">
				    <tr class="quinielatitulo">
				    <td class="pronostico">APUESTAS</td>
				    <td class="pronostico" width="70"><label id="labelApuestas">&nbsp;</label></td>
				    <td class="pronostico">PRECIO</td>
				    <td class="pronostico" width="70"><label id="labelPrecio">&nbsp;</label></td>
				    <td class="pronostico"><button id="prizeButton" class="button button-mini" name="prize" value="Precio">Precio</button></td>
				    </tr>
				    </table>
				    <p></p>
				    <!-- <input type="submit" value="Enviar"> -->
				    <div align="center" id="quinielaFormResponse">Rellena tu apuesta y pulsa enviar.</div>
				    <button id="quinielaButton" class="button" name="quiniela" value="Enviar">Enviar</button>
				    
			</form>
		</div>
    
    <!-- End Quiniela Form -->
</div>
</div>
<!-- End Quiniela Section -->

<!-- Confirm Quiniela Section -->
<div id="confirmarQuinielaDiv" class="page">
<div class="container">
    <!-- Confirm Bet Form -->
		<div align="center">
			<form id="confirmBetForm">
				<input type="hidden" id="param_apuesta" name="param_apuesta" value="">
				<input type="hidden" id="param_reduccion" name="param_reduccion" value="">
				<input type="hidden" id="param_tiporeduccion" name="param_tiporeduccion" value="">
				<input type="hidden" id="param_numbets" name="param_numbets" value="">
				<table>
					<tr>
						<td>
						    <table class="quiniela" border="1" id="confirmarQuinielaTable">
						    </table>
						</td>
					</tr>
				</table>
			    <p></p>
			    <div align="center" id="confirmarQuinielaFormResponse">pulsa Confirmar para validar tu apuesta.</div>
			    <button id="modificarQuinielaButton" class="button" name="modificarQuiniela" value="Modificar">Modificar</button>
			    <button id="confirmarQuinielaButton" class="button" name="confirmarQuiniela" value="Confirmar">Confirmar</button>
			</form>
		</div>
    
    <!-- End Confirm Bet Form -->
</div>
</div>
<!-- End Confirm Quiniela Section -->

<!-- Confirmed Quiniela Section -->
<div id="confirmadaQuinielaDiv" class="page">
<div class="container">
    <!-- Confirm Bet Form -->
		<div align="center">
			<form id="confirmedBetForm">
				<table>
					<tr>
						<td>
						    <table class="quiniela" border="1" id="confirmadaQuinielaTable">
						    </table>
						</td>
					</tr>
				</table>
			    <p></p>
			    <button id="confirmadaQuinielaButton" class="button" name="confirmadaQuiniela" value="Finalizar">Finalizar</button>
			</form>
		</div>
    
    <!-- End Confirm Bet Form -->
</div>
</div>
<!-- End Confirm Quiniela Section -->

<!-- Quiniela Detail Section -->
<div id="quinielaDetailDiv" class="page">
<div class="container">
		<div align="center">
	    	<table class="quiniela" border="1" id="quinielaDetailTable">
		    </table>
		</div>
</div>
</div>
<!-- End Quiniela Section -->

<%@ include file="/WEB-INF/views/social.jsp" %>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
 <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <p class="modal-title" id="myModalLabel">Condiciones Legales</p>
      </div>
      <div class="modal-body">
			Condiciones de uso de los servicios  
<p>1.1. Aceptaci&oacute;n y disponibilidad de las CGU</p>  
<p>El usuario declara que acepta la vinculaci&oacute;n de este acuerdo y que entiende y acepta totalmente las presentes CGU establecidas en el presente documento para la utilizaci&oacute;n de los servicios prestados en <strong>LA WEB</strong>. La no aceptaci&oacute;n de las presentes CGU impedir&aacute; la utilizaci&oacute;n de los servicios ofrecidos en <strong>LA WEB</strong>.</p>  

      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div>

<!-- Modal Reduced-->
<div class="modal fade" id="modalReduced" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
 <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <p class="modal-title" id="myModalLabel">REDUCCIONES PERMIIDAS</p>
      </div>
      <div class="modal-body">
		<p align="center">REDUCCIONES PERMITIDAS</p>  
		<p><strong>REDUCCION PRIMERA:</strong> 4 Triples (9 apuestas).</p>  
		<p><strong>REDUCCION SEGUNDA:</strong> 7 Dobles (16 apuestas).</p>  
		<p><strong>REDUCCION TERCERA:</strong> 3 Triples y 3 Dobles (24 apuestas).</p>  
		<p><strong>REDUCCION CUARTA :</strong> 2 Triples y 6 Dobles (64 apuestas).</p>  
		<p><strong>REDUCCION QUINTA :</strong> 8 Triples (81 apuestas).</p>  
		<p><strong>REDUCCION SEXTA  :</strong> 11 Dobles (132 apuestas).</p>  
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div>
<!-- End Modal Reduced-->

<!-- Footer -->
<footer>
	<p class="credits">&copy;2015 quiniGold. v:0.3.4</p>
</footer>
<!-- End Footer -->

<!-- Js -->
<script src="<c:url value="/static/resources/_include/js/jquery.1.9.1.min.js"/>"></script> <!-- jQuery Core -->
<script src="<c:url value="/static/resources/_include/js/bootstrap.min.js"/>"></script> <!-- Bootstrap -->
<script src="<c:url value="/static/resources/_include/js/supersized.3.2.7.min.js"/>"></script> <!-- Slider -->
<script src="<c:url value="/static/resources/_include/js/waypoints.js"/>"></script> <!-- WayPoints -->
<script src="<c:url value="/static/resources/_include/js/waypoints-sticky.js"/>"></script> <!-- Waypoints for Header -->
<script src="<c:url value="/static/resources/_include/js/jquery.isotope.js"/>"></script> <!-- Isotope Filter -->
<script src="<c:url value="/static/resources/_include/js/jquery.fancybox.pack.js"/>"></script> <!-- Fancybox -->
<script src="<c:url value="/static/resources/_include/js/jquery.fancybox-media.js"/>"></script> <!-- Fancybox for Media -->
<script src="<c:url value="/static/resources/_include/js/jquery.tweet.js"/>"></script> <!-- Tweet -->
<script src="<c:url value="/static/resources/_include/js/plugins.js"/>"></script> <!-- Contains: jPreloader, jQuery Easing, jQuery ScrollTo, jQuery One Page Navi -->
<script src="<c:url value="/static/resources/_include/js/main.js"/>"></script> <!-- Default JS -->
<script src="<c:url value="/static/resources/_include/js/alterQ.js"/>"></script>
<script src="<c:url value="/static/resources/_include/js/jquery.dropotron.js"/>"></script>

</body>
</html>
