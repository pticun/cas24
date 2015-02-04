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

<!-- My Account -->
<div id="myaccountDiv" class="page">
<div class="container">
    	<div class="row">
            <div class="span12">
            	<div class="well" id="myDataBtn">Mis Datos</div>
            	<div class="well" id="myBalanceBtn">Mi Saldo</div>
            	<div class="well" id="myBetsBtn">Mis Apuestas</div>
            	<div class="well" id="myRankBtn">Ranking</div>
            	<div class="well" id="myResumBtn">Resumen</div>
            </div>
        </div>
</div>
</div>
<!-- End My Account -->


<!-- MyData Section -->
<div id="mydataDiv" class="page">
<div class="container">
    <!-- MyData Form -->
    <div class="row">
		<div align="center">
		   <form id="myDataForm">
		   		<table class="quiniela">
		   			<tr class="quinielatitulo">
						<td colspan="2">My Account</td>
					</tr>
			   		<tr class="quiniela">
			   			<td class="partidoLast">Username:</td>
			   			<td class="partidoLast"><input  class="textbox" id="idData" name="id" type="text" readonly="readonly"/></td>
			        </tr>
			   		<tr class="quiniela">
			   			<td class="partidoLast">Nick:</td>
			   			<td class="partidoLast"><input  class="textbox" id="nickData" name="nick" type="text"/></td>
			        </tr>
			   		<tr class="quiniela">
			   			<td class="partidoLast">Name:</td>
			   			<td class="partidoLast"><input class="textbox" name="name" id="nameData" type="text"/></td>
			        </tr>
			   		<tr class="quiniela">
			   			<td class="partidoLast">Surnames:</td>
			   			<td class="partidoLast"><input  class="textbox" id="idSurnames" name="surnames" type="text"/></td>
			        </tr>
			   		<tr class="quiniela">
			   			<td class="partidoLast">
			   			<SELECT NAME="typeID" id="typeIDSign" width="100%">
   							<OPTION VALUE="1">NIF</OPTION>
   							<OPTION VALUE="2">NIE</OPTION>
   							<OPTION VALUE="3">Pasaporte</OPTION>
							</SELECT> 
			   			</td>
			   			<td class="partidoLast"><input class="textbox" name="idCard" id="idCardData" type="text"/></td>
			        </tr>
			   		<tr class="quiniela">
			   			<td class="partidoLast">PhoneNumber:</td>
			   			<td class="partidoLast"><input class="textbox" name="phoneNumber" id="phoneNumberData" type="text"/></td>
			        </tr>
			   		<tr>
			   			<td class="partidoLast">BirthDate:</td>
			   			<td class="partidoLast"><input class="textbox" name="birthDate" id="birthDateSign" type="text"/></td>
			        </tr>
			   		<tr>
			   			<td class="partidoLast">City:</td>
			   			<td class="partidoLast">
			   			<SELECT NAME="city" id="citySign">
   							<OPTION VALUE="1">Alicante</OPTION>
   							<OPTION VALUE="2">Barcelona</OPTION>
   							<OPTION VALUE="3">Castellon</OPTION>
   							<OPTION VALUE="4">Girona</OPTION>
   							<OPTION VALUE="5">Lleida</OPTION>
   							<OPTION VALUE="6">Tarragona</OPTION>
   							<OPTION VALUE="6">Valencia</OPTION>
   							<OPTION VALUE="0">No Residente</OPTION>
							</SELECT> 
			   			</td>
			        </tr>
			   		<tr class="quiniela">
			   			<td class="partidoLast" colspan="2"><input class="textbox" name="accept" id="acceptData" type="checkbox"/>Aceptar terminos y condiciones de uso.</td>
			        </tr>
			   		<tr class="quiniela" align="center">
			   			<td align="center" colspan="2" class="partidoLast"><button id="myDataFormSubmit_btn" class="button" name="submitBtn" value="submitBtn">Enviar</button></td>
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
			   			<td class="partidoLast">Username:</td>
			   			<td class="partidoLast"><input class="textbox" id="idSaldo" name="id" type="text" readonly="readonly"/></td>
			        </tr>
			   		<tr>
			   			<td class="partidoLast">Saldo:</td>
			   			<td class="partidoLast"><input class="textbox" name="balance" id="balanceSaldo" type="text" readonly="true"/></td>
			        </tr>
			   		<tr align="right" style="display:none" >
			   			<td class="partidoLast"></td>
			   			<td class="partidoLast"><button id="balanceAlterQFormSubmit_btn" class="button" name="submitBtn" value="submitBtn">Enviar</button></td>
			        </tr>
		   		</table>
		         <div id="balanceAlterQFormResponse" style="display:none">Actualiza tu saldo y pulsa Enviar.</div>
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
    <!-- Quiniela Form -->
		<div align="center">
			<form id="betsForm">
				    <table class="quiniela" border="1" id="apuestasTable">
				    </table>
			</form>
		</div>
    
    <!-- End Sign Up Form -->
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
		   		<a href="#forgotDiv">He olvidado mi contraseña</a>
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



<!-- Sing Up Section -->
<div id="signDiv" class="page">
<div class="container">
    <!-- Sign Up Form -->
    <div class="row">
		<div align="center">
		   <form id="signupForm">
		   		<table class="quiniela">
		   			<tr class="quinielatitulo">
						<td colspan="2">Sign up</td>
					</tr>
			   		<tr>
			   			<td colspan="2" class="partidoLast"><input id="nickSign" name="nick" type="text" placeholder="Nick"/></td>
			        </tr>
			   		<tr>
			   			<td colspan="2" class="partidoLast"><input type="text" name="name" id="nameSign" placeholder="Name"/></td>
			        </tr>
			   		<tr>
			   			<td colspan="2" class="partidoLast"><input type="text" name="surnames" id="surnamesSign" placeholder="Surnames"/></td>
			        </tr>
			   		<tr>
			   			<td colspan="2" class="partidoLast">
			   			<SELECT NAME="typeID" id="typeIDSign">
   							<OPTION VALUE="1">NIF</OPTION>
   							<OPTION VALUE="2">NIE</OPTION>
   							<OPTION VALUE="3">Pasaporte</OPTION>
							</SELECT> 
			   			</td>
			        </tr>
			   		<tr>
			   			<td colspan="2" class="partidoLast"><input type="text" name="idCard" id="nifSign" placeholder="ID"/></td>
			        </tr>
			   		<tr>
			   			<td colspan="2" class="partidoLast"><input id="idSign" name="id" type="text" placeholder="E-Mail"/></td>
			        </tr>
			   		<tr>
			   			<td colspan="2" class="partidoLast"><input type="password" name="pwd" id="pwdSign" placeholder="Password"/></td>
			        </tr>
			   		<tr>
			   			<td colspan="2" class="partidoLast"><input type="text" name="phoneNumber" id="phoneNumberSign" placeholder="Phone Number"/></td>
			        </tr>
			   		<tr>
			   			<td colspan="2" class="partidoLast"><input type="text" name="birthDate" id="birthDateSign" placeholder="Birth Date"/></td>
			        </tr>
			   		<tr>
			   			<td colspan="2" class="partidoLast">
			   			<SELECT NAME="city" id="citySign">
   							<OPTION VALUE="1">Alicante</OPTION>
   							<OPTION VALUE="2">Barcelona</OPTION>
   							<OPTION VALUE="3">Castellon</OPTION>
   							<OPTION VALUE="4">Girona</OPTION>
   							<OPTION VALUE="5">Lleida</OPTION>
   							<OPTION VALUE="6">Tarragona</OPTION>
   							<OPTION VALUE="6">Valencia</OPTION>
   							<OPTION VALUE="0">No Residente</OPTION>
							</SELECT> 
			   			</td>
			        </tr>
			        
			   		<tr>
			   			<td class="partidoLast"><input name="accept" id="acceptData" type="checkbox"/></td>
			   			<td class="partidoLast">Acepto los <a href="accept.html" target="_blank">terminos y condiciones de uso.</a></td>
			        </tr>
			   		<tr class="quiniela" align="center">
			   			<td align="center" colspan="2" class="partidoLast"><button id="signup_btn" class="button" name="signup" value="signup">signup</button></td>
			        </tr>
			        
			   		<tr class="quiniela" align="center">
			   			<td colspan="2" class="partido">
				   			<div class="alert fade in" id="signupFormResponse"></div>
						</td>
			        </tr>
		   		</table>
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
    <!-- Quiniela Form -->
		<div align="center">
			<form id="betForm">
				    <table class="quiniela" border="1" id="quinielaTable">
				    </table>
				    <!-- <input type="submit" value="Enviar"> -->
				    <div align="center" id="quinielaFormResponse">Rellena tu apuesta y pulsa enviar.</div>
				    <button id="quinielaButton" class="button" name="quiniela" value="Enviar">Enviar</button>
			</form>
		</div>
    
    <!-- End Sign Up Form -->
</div>
</div>
<!-- End Sign Up Section -->

<!-- Quiniela Detail Section -->
<div id="quinielaDetailDiv" class="page">
<div class="container">
		<div align="center">
	    	<table class="quiniela" border="1" id="quinielaDetailTable">
		    </table>
		</div>
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
                        <li><a href="https://twitter.com/alterqnews" title="Follow Me on Twitter" target="_blank"><span class="font-icon-social-twitter"></span></a></li>
                        <li><a href="https://www.facebook.com/pages/AlterQ/494875553982412" title="Follow Me on Facebook" target="_blank"><span class="font-icon-social-facebook"></span></a></li>
                        <li><a href="https://plus.google.com/117228446295186578088" title="Follow Me on Google Plus" target="_blank"><span class="font-icon-social-google-plus"></span></a></li>
                    </ul>
                </nav>
            </div>
        </div>
    </div>
</div>
<!-- End Socialize -->

<!-- Footer -->
<footer>
	<p class="credits">&copy;2014 alterQ. </p>
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
