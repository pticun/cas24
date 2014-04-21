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
<title>alterQ</title>   

<meta name="description" content="Insert Your Site Description" /> 

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

	var loadBet=true;
	var loadBetUser=true;
	
	var userLoged=false;
	
	//Divs Graphics
	var bActual  = 0;
	var bHome    = 1;
	var bLogin   = 2;
	var bSign    = 3;
	var bForgot  = 4;
	var bQuiniela = 5;
	var bMyAccount = 6;
	var bMyData = 7;
	var bMyBalance = 8;
	var bMyBets = 9;
	var bMyRank = 10;
	
	//Texts
	var sHome    = "Inicio";
	var sLogin   = "Login";
	var sSign    ="";
	var sForgot = "";
	var sQuininiela = "Quiniela";
	var sGuest = "Invitado";
	var sLogout = "Logout";
	
	//Refs
	var sHomeRef = "#homeDiv";
	var sLoginRef = "#loginDiv";
	var sSignRef = "#signDiv";
	var sForgotRef ="#forgotDiv";
	var sQuinielaRef = "#quinielaDiv";
	var sGuestRef = "#";
	var sMyaccountRef = "#myaccountDiv";
	var sLogoutRef = "#logoutDiv";
	var sMyDataRef = "#mydataDiv";
	var sMyBalanceRef = "#mybalanceDiv";
	var sMyBetsRef = "#mybetsDiv";
	var sMyRankRef = "#myRankDiv";
	
	function initDiv() {
		//document.getElementById("homeDiv").style.display = "block";
		$(sHomeRef).show();
		//document.getElementById("loginDiv").style.display = "none";
		$(sLoginRef).hide();
		//document.getElementById("signDiv").style.display = "none";
		$(sSignRef).hide();
		//document.getElementById("forgotDiv").style.display = "none";
		$(sForgotRef).hide();
		//document.getElementById("quinielaDiv").style.display = "none";
		$(sQuinielaRef).hide();
		$(sMyaccountRef).hide();
		$(sMyDataRef).hide();
		$(sMyBalanceRef).hide();
		$(sMyBetsRef).hide();
		$(sMyRankRef).hide();
		
		bActual = bHome;
		
		//document.getElementById("contact").style.display = "none";
		$('#contact').hide();
		//document.getElementById("about").style.display = "none";
		$('#about').hide();
	}
	
	function showDiv(elem) {
//alert("showDiv elem="+elem+" actual="+bActual);		
		if (elem == bActual)
			return;
		
		switch (elem){
		case bHome:
			$(sHomeRef).show();
			//document.getElementById("homeDiv").style.display = "block";
			break;
		case bLogin:
			$(sLoginRef).show();
			//document.getElementById("logingDiv").style.display = "block";
			break;
		case bSign:
			$(sSignRef).show();
			//document.getElementById("signDiv").style.display = "block";
			break;
		case bForgot:
			$(sForgotRef).show();
			//document.getElementById("forgotDiv").style.display = "block";
			break;
		case bQuiniela:
			getQuiniela();
			$(sQuinielaRef).show();
			//document.getElementById("quinielaDiv").style.display = "block";
			break;
		case bMyAccount:
			$(sMyaccountRef).show();
			break;
		case bMyData:
			$(sMyDataRef).show();
			break;
		case bMyBalance:
			$(sMyBalanceRef).show();
			break;
		case bMyBets:
			$(sMyBetsRef).show();
			break;
		case bMyRank:
			$(sMyRankRef).show();
			break;
		}

		switch (bActual){
		case bHome:
			$(sHomeRef).hide();
			//document.getElementById("homeDiv").style.display = "none";
			break;
		case bLogin:
			$(sLoginRef).hide();
			//document.getElementById("logiDiv").style.display = "none";
			break;
		case bSign:
			$(sSignRef).hide();
			//document.getElementById("signDiv").style.display = "none";
			break;
		case bForgot:
			$(sForgotRef).hide();
			//document.getElementById("forgotDiv").style.display = "none";
			break;
		case bQuiniela:
			$(sQuinielaRef).hide();
			//document.getElementById("quinielaDiv").style.display = "none";
			break;
		case bMyAccount:
			$(sMyaccountRef).hide();
			break;
		case bMyData:
			$(sMyDataRef).hide();
			break;
		case bMyBalance:
			$(sMyBalanceRef).hide();
			break;
		case bMyBets:
			$(sMyBetsRef).hide();
			break;
		case bMyRank:
			$(sMyRankRef).hide();
			break;
		}
		
		bActual = elem;
//		alert ("actual="+bActual);		
	}
	
	//Manage Menu Events 
	function menuEvent(name, href)
	{
  		consoleAlterQ("menuEvent elem="+ name +" href="+href);

		if (href == sHomeRef){
			consoleAlterQ("Home");
			showDiv(bHome);
		}else if (href == sLoginRef){
			consoleAlterQ("Login");
			showDiv(bLogin);
		}else if (href == sSignRef){
			consoleAlterQ("Sign");
			showDiv(bSign);
		}else if (href == sForgotRef){
			consoleAlterQ("Forget");
			showDiv(bForget);
		}else if (href == sQuinielaRef){
			consoleAlterQ("Quiniela");
			showDiv(bQuiniela);
		}else if (href == sGuestRef){
			consoleAlterQ("Guest");
		}else if (href == sMyaccountRef){
			consoleAlterQ("Myaccount");
			showDiv(bMyAccount);
		}else if (href == sLogoutRef){
			consoleAlterQ("Logout");
			//vamos a hacer el logout
			doLogout();
		}else if (href == sMyDataRef){
			consoleAlterQ("Mydata");
			showDiv(bMyData);
		}else if (href == sMyBalanceRef){
			consoleAlterQ("Mybalance");
			showDiv(bMyBalance);
		}else if (href == sMyBetsRef){
			consoleAlterQ("Mybets");
			getUserBets();
			showDiv(bMyBets);
		}else if (href == sMyRankRef){
			consoleAlterQ("MyRank");
			showDiv(bMyRank);
		}
		return false;
		
	}
	
	function getMainMenuItems(userLoged, user)
  	{
		consoleAlterQ("getMainMenuItems userLoged="+userLoged+" user="+user);
		//MENU WEB 
		$('#menu-nav li').remove();
		
    	$('#menu-nav').append('<li><a href="'+sHomeRef+'">' + sHome + '</a></li>');
    	$('#menu-nav').append('<li><a href="' + sQuinielaRef + '">' + sQuininiela + '</a></li>');
    	if (userLoged){
    		$('#menu-nav').append('<li><a href="' + sMyaccountRef + '">' + user + '</a></li>');
    		$('#menu-nav').append('<li><a href="' + sLogoutRef + '">' + sLogout + '</a></li>');
    	}
    	else{
    		$('#menu-nav').append('<li><a href="' + sGuestRef + '">'+sGuest+'</a></li>');
    		$('#menu-nav').append('<li><a href="' + sLoginRef + '">' + sLogin + '</a></li>');
    	}
    	
    	// MENU MOBILE 
    	
		$('#menu-nav-mobile li').remove();
		
    	$('#menu-nav-mobile').append('<li><a href="'+sHomeRef+'">' + sHome + '</a></li>');
    	$('#menu-nav-mobile').append('<li><a href="' + sQuinielaRef + '">' + sQuininiela + '</a></li>');
    	if (userLoged){
    		$('#menu-nav-mobile').append('<li><a href="' + sMyaccountRef + '">' + user + '</a></li>');
    		$('#menu-nav-mobile').append('<li><a href="' + sLogoutRef + '">' + sLogout + '</a></li>');
    	}
    	else{
    		$('#menu-nav-mobile').append('<li><a href="' + sGuestRef + '">'+sGuest+'</a></li>');
    		$('#menu-nav-mobile').append('<li><a href="' + sLoginRef + '">' + sLogin + '</a></li>');
    	}
    	
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
	tableBet='<table style="font-size:14px">';
	$(loadGames).each(function(index, element){  
		var temp=padding_right(element.player1+'-'+element.player2,".",28);
		var num = (index+1)<10?(' '+(index+1)):(index+1);
		tableBet+='<tr><td>' + num + ' - </td><td>' + temp + '</td><td align="left">'+ getSign(bet.charAt(index)) + '</td>';
		tableBet+='</tr>';
	});
	tableBet+='</table>';		
        return tableBet;
}


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
    <!-- Title Page -->
    <div class="row">
        <div class="span12">
            <div class="title-page">
                <h2 class="title">alterQ</h2>
                <h3 class="title-description">Los amantes de las quinielas.</h3>
            </div>
        </div>
    </div>
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
                                	<span class="overlay-img"></span>
                                    <span class="overlay-img-thumb font-icon-plus"></span>
                                </a>
                                <!-- Thumb Image and Description -->
                                <img src="<c:url value='/static/resources/_include/img/work/thumbs/image-01.jpg'/>" alt="Quiniela">
                            </li>
                        	<!-- End Item Project -->

            			</ul>

            </div>
        </div>
</div>
</div>
<!-- End Principal -->

<!-- My Account -->
<div id="myaccountDiv" class="page">
<div class="container">
    <!-- Title Page -->
    <div class="row">
        <div class="span12">
            <div class="title-page">
                <h2 class="title">Mi Cuenta</h2>
            </div>
        </div>
    </div>
    <!-- End Title Page -->

    	<div class="row">
            <div class="span12">
            	<div class="well" id="myDataBtn">Mis Datos</div>
            	<div class="well" id="myBalanceBtn">Mi Saldo</div>
            	<div class="well" id="myBetsBtn">Mis Apuestas</div>
            	<div class="well" id="myRankBtn">Ranking</div>
            </div>
        </div>
</div>
</div>
<!-- End My Account -->


<!-- MyData Section -->
<div id="mydataDiv" class="page">
<div class="container">
    <!-- Title Page -->
    <div class="row">
        <div class="span12">
            <div class="title-page">
                <h2 class="title">Mis Datos</h2>
            </div>
        </div>
    </div>
    <!-- End Title Page -->
    
    <!-- MyData Form -->
    <div class="row">
		<div align="center">
		   <form id="myDataForm">
		   		<table class="quiniela">
		   			<tr class="quinielatitulo">
						<td colspan="2">My Account</td>
					</tr>
			   		<tr>
			   			<td class="partido">Username:</td>
			   			<td class="partido"><input id="idData" name="id" type="text" readonly="readonly"/></td>
			        </tr>
			   		<tr>
			   			<td class="partido">Name:</td>
			   			<td class="partido"><input name="name" id="nameData" type="text"/></td>
			        </tr>
			   		<tr>
			   			<td class="partido">Phone Number:</td>
			   			<td class="partido"><input name="phoneNumber" id="phoneNumberData" type="text"/></td>
			        </tr>
			   		<tr align="right">
			   			<td class="partido"></td>
			   			<td class="partido"><button id="myDataFormSubmit_btn" class="button" name="submitBtn" value="submitBtn">Enviar</button></td>
			        </tr>
		        </table>
		        	<div id="userAlterQFormResponse">Actualiza tus datos y pulsa Enviar.</div>
	        </form>
		</div>
    </div>
    <!-- End MyData Form -->
</div>
</div>
<!-- End MyData Section -->

<!-- MyBalande Section -->
<div id="mybalanceDiv" class="page">
<div class="container">
    <!-- Title Page -->
    <div class="row">
        <div class="span12">
            <div class="title-page">
                <h2 class="title">Mis Datos</h2>
            </div>
        </div>
    </div>
    <!-- End Title Page -->
    
    <!-- MyBalance Form -->
    <div class="row">
		<div align="center">
		   <form id="balanceAlterQForm">
		   		<table class="quiniela">
		   			<tr class="quinielatitulo">
						<td colspan="2">Saldo</td>
					</tr>
			   		<tr>
			   			<td class="partido">Username:</td>
			   			<td class="partido"><input id="idSaldo" name="id" type="text" readonly="readonly"/></td>
			        </tr>
			   		<tr>
			   			<td class="partido">Saldo:</td>
			   			<td class="partido"><input name="balance" id="balanceSaldo" type="text"/></td>
			        </tr>
			   		<tr align="right">
			   			<td class="partido"></td>
			   			<td class="partido"><button id="balanceAlterQFormSubmit_btn" class="button" name="submitBtn" value="submitBtn">Enviar</button></td>
			        </tr>
		   		</table>
		         <div id="balanceAlterQFormResponse">Actualiza tu saldo y pulsa Enviar.</div>
	        </form>
		</div>
    </div>
    <!-- End MyBalance Form -->
</div>
</div>
<!-- End MyBalance Section -->

<!-- MyBets Section -->
<div id="mybetsDiv" class="page">
<div class="container">
    <div id="myCarousel" class="carousel slide" data-ride="carousel">
        <!-- Carousel indicators -->
        <div id="myIndicators"> 
        </div>
        
        <!-- Carousel items -->
        <div id="myItems" class="carousel-inner">
        </div>
        <!-- Carousel nav -->
        <a class="carousel-control left" href="#myCarousel" data-slide="prev">
            <span class="glyphicon glyphicon-chevron-left"></span>
        </a>
        <a class="carousel-control right" href="#myCarousel" data-slide="next">
            <span class="glyphicon glyphicon-chevron-right"></span>
        </a>
    </div>
</div>
</div>
<!-- End MyBets Section -->

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
			<div class="btn-group">
			  <button type="button" class="btn btn-default">Ranking</button>
			  <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"><span class="caret"></span></button>
			  <ul class="dropdown-menu">
			    <li><a id="rankingSelect" href="#">2013/2014</a></li>
			    <li><a href="#">2012/2013</a></li>
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

<!-- About Section -->
<div id="about" class="page-alternate">
<div class="container">
    <!-- Title Page -->
    <div class="row">
        <div class="span12">
            <div class="title-page">
                <h2 class="title">About Us</h2>
                <h3 class="title-description">Learn About our Team &amp; Culture.</h3>
            </div>
        </div>
    </div>
    <!-- End Title Page -->
    
    <!-- People -->
    <div class="row">
    	
        <!-- Start Profile -->
    	<div class="span4 profile">
        	<div class="image-wrap">
                <div class="hover-wrap">
                    <span class="overlay-img"></span>
                    <span class="overlay-text-thumb">CTO/Founder</span>
                </div>
                <img src="<c:url value='/static/resources/_include/img/profile/profile-01.jpg'/>" alt="John Doe">
            </div>
            <h3 class="profile-name">John Doe</h3>
            <p class="profile-description">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas ac augue at erat <a href="#">hendrerit dictum</a>. 
            Praesent porta, purus eget sagittis imperdiet, nulla mi ullamcorper metus, id hendrerit metus diam vitae est. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos.</p>
            	
            <div class="social">
            	<ul class="social-icons">
                	<li><a href="#"><i class="font-icon-social-twitter"></i></a></li>
                    <li><a href="#"><i class="font-icon-social-dribbble"></i></a></li>
                    <li><a href="#"><i class="font-icon-social-facebook"></i></a></li>
                </ul>
            </div>
        </div>
        <!-- End Profile -->
        
        <!-- Start Profile -->
    	<div class="span4 profile">
        	<div class="image-wrap">
                <div class="hover-wrap">
                    <span class="overlay-img"></span>
                    <span class="overlay-text-thumb">Creative Director</span>
                </div>
                <img src="<c:url value='/static/resources/_include/img/profile/profile-02.jpg'/>" alt="Jane Helf">
            </div>
            <h3 class="profile-name">Jane Helf</h3>
            <p class="profile-description">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas ac augue at erat <a href="#">hendrerit dictum</a>. 
            Praesent porta, purus eget sagittis imperdiet, nulla mi ullamcorper metus, id hendrerit metus diam vitae est. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos.</p>
            	
            <div class="social">
            	<ul class="social-icons">
                	<li><a href="#"><i class="font-icon-social-twitter"></i></a></li>
                    <li><a href="#"><i class="font-icon-social-email"></i></a></li>
                </ul>
            </div>
        </div>
        <!-- End Profile -->
        
        <!-- Start Profile -->
    	<div class="span4 profile">
        	<div class="image-wrap">
                <div class="hover-wrap">
                    <span class="overlay-img"></span>
                    <span class="overlay-text-thumb">Lead Designer</span>
                </div>
                <img src="<c:url value='/static/resources/_include/img/profile/profile-03.jpg'/>" alt="Joshua Insanus">
            </div>
            <h3 class="profile-name">Joshua Insanus</h3>
            <p class="profile-description">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas ac augue at erat <a href="#">hendrerit dictum</a>. 
            Praesent porta, purus eget sagittis imperdiet, nulla mi ullamcorper metus, id hendrerit metus diam vitae est. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos.</p>
            	
            <div class="social">
            	<ul class="social-icons">
                	<li><a href="#"><i class="font-icon-social-twitter"></i></a></li>
                    <li><a href="#"><i class="font-icon-social-linkedin"></i></a></li>
                    <li><a href="#"><i class="font-icon-social-google-plus"></i></a></li>
                    <li><a href="#"><i class="font-icon-social-vimeo"></i></a></li>
                </ul>
            </div>
        </div>
        <!-- End Profile -->
        
    </div>
    <!-- End People -->
</div>
</div>
<!-- End About Section -->


<!-- Contact Section -->
<div id="contact" class="page">
<div class="container">
    <!-- Title Page -->
    <div class="row">
        <div class="span12">
            <div class="title-page">
                <h2 class="title">Get in Touch</h2>
                <h3 class="title-description">We're currently accepting new client projects. We look forward to serving you.</h3>
            </div>
        </div>
    </div>
    <!-- End Title Page -->
    
    <!-- Contact Form -->
    <div class="row">
    	<div class="span9">
        
        	<form id="contact-form" class="contact-form" action="#">
            	<p class="contact-name">
            		<input id="contact_name" type="text" placeholder="Full Name" value="" name="name" />
                </p>
                <p class="contact-email">
                	<input id="contact_email" type="text" placeholder="Email Address" value="" name="email" />
                </p>
                <p class="contact-message">
                	<textarea id="contact_message" placeholder="Your Message" name="message" rows="15" cols="40"></textarea>
                </p>
                <p class="contact-submit">
                	<a id="contact-submit" class="submit" href="#">Send Your Email</a>
                </p>
                
                <div id="response">
                
                </div>
            </form>
         
        </div>
        
        <div class="span3">
        	<div class="contact-details">
        		<h3>Contact Details</h3>
                <ul>
                    <li><a href="#">hello@brushed.com</a></li>
                    <li>(916) 375-2525</li>
                    <li>
                        Brushed Studio
                        <br>
                        5240 Vanish Island. 105
                        <br>
                        Unknow
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <!-- End Contact Form -->
</div>
</div>
<!-- End Contact Section -->

<!-- Login Section -->
<div id="loginDiv" class="page">
<div class="container">
    <!-- Title Page -->
    <div class="row">
        <div class="span12">
            <div class="title-page">
                <h2 class="title">Login</h2>
                <h3 class="title-description">Entra al mundo de las quinielas</h3>
            </div>
        </div>
    </div>
    <!-- End Title Page -->
    
    <!-- Login Form -->
    <div class="row">
			<div align="center">
			   <form id="loginForm">
			   		<table class="quiniela">
				   		<tr>
				   			<td class="partido">Username:</td>
				   			<td class="partido"><input id="idLogin" name="id" type="text"/></td>
				        </tr>
				   		<tr>
				   			<td class="partido">Password:</td>
				   			<td class="partido"><input type="password" name="pwd" id="pwdLogin"/></td>
				        </tr>
				   		<tr align="right">
				   			<td class="partido">&nbsp;</td>
				   			<td class="partido"><button id="login_btn" class="button" name="login" value="login">Login</button></td>
				        </tr>
			   		</table>
			   		<a href="#signDiv">Crear un nuevo usuario</a><br>
			   		<a href="#forgotDiv">He olvidado mi contraseña</a>
					<div id="loginFormResponse">respuesta </div>
		        </form>
			</div>
    </div>
    <!-- End Login Form -->
</div>
</div>
<!-- End Login Section -->


<!-- Forgot Section -->
<div id="forgotDiv" class="page">
<div class="container">
    <!-- Title Page -->
    <div class="row">
        <div class="span12">
            <div class="title-page">
                <h2 class="title">Login</h2>
                <h3 class="title-description">Entra al mundo de las quinielas</h3>
            </div>
        </div>
    </div>
    <!-- End Title Page -->
    
    <!-- Forgot Form -->
    <div class="row">
		<div align="center">
		   <form id="forgotPwdForm">
		   		<table class="quiniela">
		   			<tr class="quinielatitulo">
						<td colspan="2">Enter your email address and we'll send you a link to reset your password.</td>
					</tr>
			   		<tr class="quinielatitulo">
			   			<td  colspan="2"><input id="idForgot" type="text" size="20" name="id" placeholder="idLogin"/></td>
			        </tr>
			   		<tr class="quinielatitulo" align="right">
			   			<td colspan="2"><button id="forgot_btn" class="button" name="signup" value="send">Send</button></td>
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



<!-- Sing Up Section -->
<div id="signDiv" class="page">
<div class="container">
    <!-- Title Page -->
    <div class="row">
        <div class="span12">
            <div class="title-page">
                <h2 class="title">Login</h2>
                <h3 class="title-description">Entra al mundo de las quinielas</h3>
            </div>
        </div>
    </div>
    <!-- End Title Page -->
    
    <!-- Sign Up Form -->
    <div class="row">
		<div align="center">
		   <form id="signupForm">
		   		<table class="quiniela">
		   			<tr class="quinielatitulo">
						<td colspan="1">Sign up</td>
					</tr>
			   		<tr>
			   			<td class="partido"><input id="idSign" name="id" type="text" placeholder="Login"/></td>
			        </tr>
			   		<tr>
			   			<td class="partido"><input type="password" name="pwd" id="pwdSign" placeholder="password"/></td>
			        </tr>
			   		<tr>
			   			<td class="partido"><input type="text" name="name" id="nameSign" placeholder="name"/></td>
			        </tr>
			   		<tr>
			   			<td class="partido"><input type="text" name="phoneNumber" id="phoneNumberSign" placeholder="Phone Number"/></td>
			        </tr>
			   		<tr align="right">
			   			<td class="partido"><button id="signup_btn" class="button" name="signup" value="signup">signup</button></td>
			        </tr>
		   		</table>
	            <div id="signupFormResponse">respuesta </div>
	        </form>
		</div>
    </div>
    <!-- End Sign Up Form -->
</div>
</div>
<!-- End Sign Up Section -->


<!-- Quiniela Section -->
<div id="quinielaDiv" class="page">
<div class="container">
    <!-- Title Page -->
    <div class="row">
        <div class="span12">
            <div class="title-page">
                <h2 class="title">Quiniela</h2>
                <h3 class="title-description" id="quinielaTitle">Jornada <c:out value="${jornada}" /> Temporada <c:out value="${temporada}" />/<c:out value="${temporada+1-2000}" /></h3>
            </div>
        </div>
    </div>
    <!-- End Title Page -->
    
    <!-- Quiniela Form -->
    <div class="row">
		<div align="center">
			<form id="betForm">
				    <table class="quiniela" id="quinielaTable"></table>
				    <!-- <input type="submit" value="Enviar"> -->
				    <div align="center" id="quinielaFormResponse">Rellena tu apuesta y pulsa enviar.</div>
				    <button id="quinielaButton" class="button" name="quiniela" value="Enviar">Enviar</button>
			</form>
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
    <!-- End Sign Up Form -->
</div>
</div>
<!-- End Sign Up Section -->




<!-- Socialize -->
<div id="social-area" class="page">
	<div class="container">
    	<div class="row">
            <div class="span12">
                <nav id="social">
                    <ul>
                        <li><a href="https://twitter.com/Bluxart" title="Follow Me on Twitter" target="_blank"><span class="font-icon-social-twitter"></span></a></li>
                        <li><a href="http://dribbble.com/Bluxart" title="Follow Me on Dribbble" target="_blank"><span class="font-icon-social-dribbble"></span></a></li>
                        <li><a href="http://forrst.com/people/Bluxart" title="Follow Me on Forrst" target="_blank"><span class="font-icon-social-forrst"></span></a></li>
                        <li><a href="http://www.behance.net/alessioatzeni" title="Follow Me on Behance" target="_blank"><span class="font-icon-social-behance"></span></a></li>
                        <li><a href="https://www.facebook.com/Bluxart" title="Follow Me on Facebook" target="_blank"><span class="font-icon-social-facebook"></span></a></li>
                        <li><a href="https://plus.google.com/105500420878314068694" title="Follow Me on Google Plus" target="_blank"><span class="font-icon-social-google-plus"></span></a></li>
                        <li><a href="http://www.linkedin.com/in/alessioatzeni" title="Follow Me on LinkedIn" target="_blank"><span class="font-icon-social-linkedin"></span></a></li>
                        <li><a href="http://themeforest.net/user/Bluxart" title="Follow Me on ThemeForest" target="_blank"><span class="font-icon-social-envato"></span></a></li>
                        <li><a href="http://zerply.com/Bluxart/public" title="Follow Me on Zerply" target="_blank"><span class="font-icon-social-zerply"></span></a></li>
                    </ul>
                </nav>
            </div>
        </div>
    </div>
</div>
<!-- End Socialize -->

<!-- Footer -->
<footer>
	<p class="credits">&copy;2013 Brushed. <a href="http://themes.alessioatzeni.com/html/brushed/" title="Brushed | Responsive One Page Template">Brushed Template</a> by <a href="http://www.alessioatzeni.com/" title="Alessio Atzeni | Web Designer &amp; Front-end Developer">Alessio Atzeni</a></p>
</footer>
<!-- End Footer -->

<!-- Back To Top -->
<a id="back-to-top" href="#">
	<i class="font-icon-arrow-simple-up"></i>
</a>
<!-- End Back to Top -->


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
