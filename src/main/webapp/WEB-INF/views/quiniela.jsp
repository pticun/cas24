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
		<title>alterQ - Quiniela</title>
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
					//move to start page
					var new_position = $('#bodyClass').offset();
	    		    window.scrollTo(new_position.left,new_position.top);
    		    }
				$('#dataDiv').hide();
    	    })
    	    .error   (function()     { alert("Error")   ; })
//    	    .complete(function()     { alert("complete"); })
    	    ;   
 
    	var loadBet=true;
	   	$('#data_Div').click(function(){
 	        var url= '${pageContext.request.contextPath}/bet';
        	if(loadBet){
 	        	loadBet=false;
	 	        $.get(url, $(this).serialize(), function(response) {
	    		    if(response.errorDto!=null){
	    		    	$('#temporada').text(response.errorDto.stringError);
	    		    }
	    		    else{
						$('#titleJornada').text("Jornada "+ response.round.round+ " Temporada "+response.round.season+"/"+(response.round.season+1-2000));
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
						})
						
						
						$('#dataDiv').show();
	    		    }
				});
 	        }
		    var jump = $(this).attr('href');
		    var new_position = $(jump).offset();
		    window.scrollTo(new_position.left,new_position.top);
		    return false;
		});

		$('#betForm').submit(function(e) {
			console.log('betForm');
			// will pass the form date using the jQuery serialize function
			var url= '${pageContext.request.contextPath}/bet/';
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
							<h1><a href="#" id="logo">Quiniela</a></h1>
						</header>

					<!-- Nav -->
						<nav id="nav">
							<ul>
								<li><a href="index">Inicio</a></li>
								<li><a href="quiniela">Quiniela</a></li>
								<li><a href="micuenta">Mi Cuenta</a></li>
								<li><a href="pendiente">Contacto</a></li>
								<li id="nameUserNav">nombre usuario</li>
							</ul>
						</nav>


						<!-- Carousel -->
						<div id="carousel" class="carousel">
							<div class="reel">
								<article>
									<a href="#dataDiv" id="data_Div" class="image featured"><img src="<c:url value="/static/resources/images/pic01.jpg"/>" alt="" />
									<header>
										<h3>Hacer Quiniela</h3>
									</header>
									<h4>¿A qué está esperando para hacer tu quiniela semanal?</h4>
									</a>
								</article>
							
								<article>
									<a href="pendiente.html" class="image featured"><img src="<c:url value="/static/resources/images/pic02.jpg"/>" alt="" />
									<header>
										<h3>Estadísticas de la Jornada</h3>
									</header>
									<h4>Conoce todo lo que tienes que saber sobre los partidos de la semana.</h4>	
									</a>						
								</article>
							</div>
						</div>

					</div>
				
			</div>


			<div id="dataDiv">
				<div class="row flush">
				  <div class="2u">&nbsp;</div>
				  <div class="8u">
					<div>
					<span class="byline" id="titleJornada">Jornada <c:out value="${jornada}" /> Temporada <c:out value="${temporada}" />/<c:out value="${temporada+1-2000}" /></span>
					
						<form id="betForm">
						<center>
						
							    <table class="quiniela" id="quinielaTable">
							    </table>
							    <p><input type="submit" value="Enviar"></p>
						</center>
						</form>
					</div>
				  </div>
				  <div class="2u">&nbsp;</div>
				</div>
			</div>






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