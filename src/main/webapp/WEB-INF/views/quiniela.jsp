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
		function calculatePrice(){
			console.log('calculatePrice');
			// will pass the form date using the jQuery serialize function
			var url= '${pageContext.request.contextPath}/bet/price';
			$.post(url, $("#betForm").serialize(), function(response) {
				if(response.errorDto!=null){
					$('#quinielaPrice').text(response.errorDto.stringError);
				}
				else{
					$('#quinielaPrice').text(response.price);
				}
			});
			
			return false;
		}		
	</script>
  <script type="text/javascript">
	var userLoged=false;
  	var loadBet=true;
    $(document).ready(function() {
    	var jqxhr =
    	    $.ajax({
    	        url: "${pageContext.request.contextPath}/login",
     	    })
    	    .success (function(response) { 
  	    	
    		    if(response.errorDto!=null){
					$('#menu_Login').text("Login");
					//$('#menu_Login').attr("href", "loginDiv");
					$('#menu_User').text("Invitado");
					$('#menu_User').attr("href", "#");

					userLoged=false;
    		    }
    		    else{
    		    	if (response.userAlterQ!=null){
    					$('#menu_Login').text("Logout");
    					//$('#menu_Login').attr("href", "Logout");
    					$('#menu_User').text(response.userAlterQ.name);
    					$('#menu_User').attr("href", "myaccount");

    					userLoged=true;
    		    	}
    		    	else{
    					$('#menu_Login').text("Login");
    					$('#menu_Login').attr("href", "index?WHERE=login");
    					$('#menu_User').text("Invitado");
    					$('#menu_User').attr("href", "#");

    					userLoged=false;
    		    	}
    		    }
				var url= '${pageContext.request.contextPath}/bet';
				if(loadBet){
				 	loadBet=false;
				     $.get(url, $(this).serialize(), function(response) {
					    if(response.errorDto!=null){
					    	$('#temporada').text(response.errorDto.stringError);
					    }
					    else{
							$('#quinielaTitle').text("Jornada "+ response.round.round+ " Temporada "+response.round.season+"/"+(response.round.season+1-2000));
						    $('#quinielaTable').append('<input type="hidden" name="season" id="season" value="'+ response.round.season+'"/>');       
						    $('#quinielaTable').append('<input type="hidden" name="round" id="round" value="'+ response.round.round+'"/>');       
						    $('#quinielaTable').append('<tr id="rowBetTitle" class="quinielatitulo"><td>Jornada '+ response.round.round+'</td><td colspan="3">APUESTA</td></tr><tr><td colspan="4"></td></tr>');       
				
							$(response.round.games).each(function(index, element){  
								if( (window['console'] !== undefined) ){
									console.log(element);
							    }
								var row="";
								var temp=padding_right(element.player1+'-'+element.player2,".",28);
								if(index>8){
									temp=temp+(index+1);
								}
								else{
									temp=temp+" "+(index+1);
								}
								if(index==0 || index==4 || index==8 || index==11 || index==14){
									row+='<tr id="rowBet_'+index+'"><td class="partidolinea">'+temp+'</td>';
								}
								else{
									row+='<tr id="rowBet_'+index+'"><td class="partido">'+temp+'</td>';
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
							
							
							//$('#dataDiv').show();
					    }
					});
				 }
    	    
    	    })
    	    .error   (function()     { alert("Error")   ; })
//    	    .complete(function()     { alert("complete"); })
    	    ;   
 
    	
	   	$('#data_Div').click(function(){
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
							if( (window['console'] !== undefined) ){
								console.log(element);
						    }
							var row="";
							var temp=padding_right(element.player1+'-'+element.player2,".",28);
							if(index>8){
								temp=temp+(index+1);
							}
							else{
								temp=temp+" "+(index+1);
							}
							if(index==0 || index==4 || index==8 || index==11 || index==14){
								row+='<tr id="rowBet_'+index+'"><td class="partidolinea">'+temp+'</td>';
							}
							else{
								row+='<tr id="rowBet_'+index+'"><td class="partido">'+temp+'</td>';
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
						
						
						//$('#dataDiv').show();
	    		    }
				});
 	        }
		    //var jump = $(this).attr('href');
		    //var new_position = $(jump).offset();
		    //window.scrollTo(new_position.left,new_position.top);
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
					$('#0_1').removeAttr('checked');$('#0_X').removeAttr('checked');$('#0_2').removeAttr('checked');
					$('#1_1').removeAttr('checked');$('#1_X').removeAttr('checked');$('#1_2').removeAttr('checked');
					$('#2_1').removeAttr('checked');$('#2_X').removeAttr('checked');$('#2_2').removeAttr('checked');
					$('#3_1').removeAttr('checked');$('#3_X').removeAttr('checked');$('#3_2').removeAttr('checked');
					$('#4_1').removeAttr('checked');$('#4_X').removeAttr('checked');$('#4_2').removeAttr('checked');
					$('#5_1').removeAttr('checked');$('#5_X').removeAttr('checked');$('#5_2').removeAttr('checked');
					$('#6_1').removeAttr('checked');$('#6_X').removeAttr('checked');$('#6_2').removeAttr('checked');
					$('#7_1').removeAttr('checked');$('#7_X').removeAttr('checked');$('#7_2').removeAttr('checked');
					$('#8_1').removeAttr('checked');$('#8_X').removeAttr('checked');$('#8_2').removeAttr('checked');
					$('#9_1').removeAttr('checked');$('#9_X').removeAttr('checked');$('#9_2').removeAttr('checked');
					$('#10_1').removeAttr('checked');$('#10_X').removeAttr('checked');$('#10_2').removeAttr('checked');
					$('#11_1').removeAttr('checked');$('#11_X').removeAttr('checked');$('#11_2').removeAttr('checked');
					$('#12_1').removeAttr('checked');$('#12_X').removeAttr('checked');$('#12_2').removeAttr('checked');
					$('#13_1').removeAttr('checked');$('#13_X').removeAttr('checked');$('#13_2').removeAttr('checked');
					$('#14_1').removeAttr('checked');$('#14_X').removeAttr('checked');$('#14_2').removeAttr('checked');
					$('#quinielaFormResponse').text("Apuesta realizada correctamente");
				}
			});
		    e.preventDefault(); // prevent actual form submit and page reload
		});

	   	
   	 $('#menu_Login').click(function(){
   		 
		    if (userLoged){
				if( (window['console'] !== undefined) ){
					console.log('Hay usuario, vamos a hacer el logoTitleut');
			    }
				// will pass the form date using the jQuery serialize function
				var url= '${pageContext.request.contextPath}/logout';
				$.get(url, $(this).serialize(), function(response) {
					if(response.errorDto!=null){
					}
					else{
						userLoged=false;
						
						$('#menu_Login').text("Login");
						$('#menu_Login').attr("href", "index?WHERE=login");
						$('#menu_User').text("Invitado");
						$('#menu_User').attr("href", "#");

						//var new_position = $('index').offset();
						//window.scrollTo(new_position.left,new_position.top);
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
			<div id="header" align="center">
						
				<!-- Inner -->
					<div class="inner" align="center">
						<header>
							<h1><a href="#" id="logo">Quiniela</a></h1>
						</header>

							<!-- quiniela -->
							<div id="quinielaDiv" align="center">
								<div class="row flush" align="center">
								  <div class="4u" align="center">&nbsp;</div>
								  <div class="4u" align="center">
									<div align="center" style="width:576px">
										<span class="byline" id="quinielaTitle">Jornada <c:out value="${jornada}" /> Temporada <c:out value="${temporada}" />/<c:out value="${temporada+1-2000}" /></span>										
										<form id="betForm">
											    <table class="quiniela" id="quinielaTable"></table>
											    <!-- <input type="submit" value="Enviar"> -->
											    <div align="center" id="quinielaFormResponse">Rellena tu apuesta y pulsa enviar.</div>
											    <button id="quinielaButton" class="button" name="quiniela" value="Enviar">Enviar</button>
										</form>
									</div>
								  </div>
								  <div align="center" class="2u">&nbsp;</div>
								</div>
							    <div align="center">
								    <span id="quinielaPriceCaption">PRECIO:</span>
								    <span id="quinielaPrice">0</span>
								    <span id="quinielaPriceEur"> EUR</span>
							    </div>
							    <div align="center">
								    <button class="button" onclick="javascript:calculatePrice();">Precio</button>
							    </div>
							</div>
							<!-- quiniela -->
						<footer>
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
