<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<!--[if lt IE 7]><html class="no-js lt-ie9 lt-ie8 lt-ie7" lang="en"> <![endif]-->
<!--[if (IE 7)&!(IEMobile)]><html class="no-js lt-ie9 lt-ie8" lang="en"><![endif]-->
<!--[if (IE 8)&!(IEMobile)]><html class="no-js lt-ie9" lang="en"><![endif]-->
<!--[if (IE 9)]><html class="no-js ie9" lang="en"><![endif]-->
<!--[if gt IE 8]><!--> <html lang="en-US"> <!--<![endif]-->
<head>
<!-- JQuery -->
<script src="<c:url value="/static/resources/js/jquery.min.js"/>"></script>
<script src="<c:url value="/static/resources/js/jquery.dropotron.js"/>"></script>

<!-- Meta Tags -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<title>alterQ</title>   

<meta name="description" content="Insert Your Site Description" /> 

<!-- Mobile Specifics -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="HandheldFriendly" content="true"/>
<meta name="MobileOptimized" content="320"/>   

<!-- Mobile Internet Explorer ClearType Technology -->
<!--[if IEMobile]>  <meta http-equiv="cleartype" content="on">  <![endif]-->

<!-- Bootstrap -->
<link href="<c:url value="/static/resources/_include/css/bootstrap.min.css"/>" rel="stylesheet">
<!-- <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css"> --> 

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

<!-- Google Font -->
<link href='http://fonts.googleapis.com/css?family=Titillium+Web:400,200,200italic,300,300italic,400italic,600,600italic,700,700italic,900' rel='stylesheet' type='text/css'>

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
var ctx = "<%=request.getContextPath()%>"
</script>

</head>

	
<script type="text/javascript">

	//Divs Graphics
	var bActual					= 0;
	var bHome					= 0;
	var bOpenRoundSession		= 1;
	var bCloseRoundSession		= 2;
	var bFinalQuiniela			= 3;
	var bGetQuinielaRef			= 4;
	var bAdminIni				= 5;

	//Refs
	var sHomeRef 					= "#homeDiv";
	var sOpenRoundSessionRef		= "#openDiv";
	var sCloseRoundSessionRef		= "#closeDiv";
	var sAddMatchesRounnSessionRef	= "#matchesDiv";
	var sFinalQuinielaRef			= "#quinielaDiv";
	var sGetQuinielaRef 			= "#getQuinielaDiv";
	var sAdminIni					= "home";
	
	function initDiv() {
		consoleAlterQ("initDiv");		
		$(sHomeRef).show();
		$(sOpenRoundSessionRef).hide();
		$(sCloseRoundSessionRef).hide();
		$(sFinalQuinielaRef).hide();
		$(sGetQuinielaRef).hide();
		
		bActual = bHome;
	}
	
	function showDiv(elem) {
		if (elem == bActual)
			return;
		
		switch (elem){
		case bAdminIni:
			window.history.back();
			break;
		case bHome:
			$(sHomeRef).show();
			break;
		case bOpenRoundSession:
			$(sOpenRoundSessionRef).show();
			break;
		case bCloseRoundSession:
			$(sCloseRoundSessionRef).show();
			break;
		case bFinalQuiniela:
			$(sFinalQuinielaRef).show();
			break;
		case bGetQuinielaRef:
			$(sGetQuinielaRef).show();
			break;
		}

		switch (bActual){
		case bHome:
			$(sHomeRef).hide();
			break;
		case bOpenRoundSession:
			$(sOpenRoundSessionRef).hide();
			break;
		case bCloseRoundSession:
			$(sCloseRoundSessionRef).hide();
			break;
		case bFinalQuiniela:
			$(sFinalQuinielaRef).hide();
			break;
		case bGetQuinielaRef:
			$(sGetQuinielaRef).hide();
			break;
		}
		
		bActual = elem;
	}
	
	//Manage Menu Events 
	function menuEvent(name, href)
	{
  		consoleAlterQ("menuEvent elem="+ name +" href="+href);
  		
		if (href == sAdminIni){
			consoleAlterQ("AdminIni");
			showDiv(bAdminIni);
		}else if (href == sHomeRef){
			consoleAlterQ("Home");
			showDiv(bHome);
		}else if (href == sOpenRoundSessionRef){
			consoleAlterQ("Open");
			showDiv(bOpenRoundSession);
		}else if (href == sCloseRoundSessionRef){
			consoleAlterQ("Close");
			showDiv(bCloseRoundSession);
		}else if (href == sFinalQuinielaRef){
			consoleAlterQ("FinalQuiniela");
			showDiv(bFinalQuiniela);
		}else if (href == sGetQuinielaRef){
			consoleAlterQ("bGetQuinielaRef");
			showDiv(bGetQuinielaRef);
		}
		return false;
		
	}

	
	
</script>

<body>

<!-- Header -->
<header>
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
                <h3 class="title-description">Administration.</h3>
            </div>
        </div>
    </div>
    <!-- End Title Page -->

    	<div class="row" align="center">
          		<table class="tablaQuiniGold">
		   		<tr align="center">
		   			<td><button id="homeBtn" class="btn btn-danger" name="homeBtn" value="homeBtn">Home</button><br><br></td>
		        </tr>
		   		<tr align="center">
		   			<td><button id="openBtn" class="btn btn-danger" name="openBtn" value="openBtn">Open</button><br><br></td>
		        </tr>
		   		<tr align="center">
		   			<td><button id="closeBtn" class="btn btn-danger" name="closeMenu" value="close">Close</button><br><br></td>
		        </tr>
		   		<tr align="center">
		   			<td><button id="quinielaFinalBtn" class="btn btn-danger" name="quinielaFinalMenu" value="quinelaFinal">Quiniela</button><br><br></td>
		        </tr>
		   		<tr align="center">
		   			<td><button id="getQuinielaFinalBtn" class="btn btn-danger" name="getQuinielaFinalMenu" value="getQuinelaFinal">Quiniela Final</button></td>
		        </tr>
	   			</table>
        </div>
</div>
</div>
<!-- End Principal -->

<!-- OpenRound Section -->
<div id="openDiv" class="page">
<div class="container">
    <!-- Title Page -->
    <div class="row">
        <div class="span12">
            <div class="title-page">
                <h2 class="title">Admin - Open Round</h2>
            </div>
        </div>
    </div>
    <!-- End Title Page -->
    
    <!-- OpenRound Form -->
    <div class="row table-responsive">
		<div align="center">
			<form id="openForm">
			   		<table class="quiniela">
				   		<tr>
				   			<td class="partido">Season:</td>
				   			<td class="partido"><input id="seasonOpen" name="season" type="text"/></td>
				        </tr>
				   		<tr>
				   			<td class="partido">Round:</td>
				   			<td class="partido"><input id="roundOpen" name="round" type="text"/></td>
				        </tr>
				   		<tr align="right">
				   			<td class="partido">&nbsp;</td>
				   			<td class="partido"><button id="admin_open_btn" class="button" name="openBtn" value="openBtn">Open</button></td>
				        </tr>
			   		</table>
			   		<div id="openFormResponse">respuesta </div>
			</form>
	   		<table class="quiniela">
	   			<tr align="center">
		   			<td class="partido"><button id="homeBtn1" class="button" name="homeBtn1" value="homeBtn1">Admin Menu</button></td>
		        </tr>
	       </table>
		</div>
    </div>
    <!-- End OpenRound Form -->
</div>
</div>
<!-- End OpenRound Section -->

<!-- CloseRound Section -->
<div id="closeDiv" class="page">
<div class="container">
    <!-- Title Page -->
    <div class="row">
        <div class="span12">
            <div class="title-page">
                <h2 class="title">Admin - Close Round</h2>
            </div>
        </div>
    </div>
    <!-- End Title Page -->
    
    <!-- CloseRound Form -->
    <div class="row table-responsive">
		<div align="center">
			<form id="closeForm">
			   		<table class="quiniela">
				   		<tr>
				   			<td class="partido">Season:</td>
				   			<td class="partido"><input id="seasonClose" name="season" type="text"/></td>
				        </tr>
				   		<tr>
				   			<td class="partido">Round:</td>
				   			<td class="partido"><input id="roundClose" name="round" type="text"/></td>
				        </tr>
				   		<tr align="right">
				   			<td class="partido">&nbsp;</td>
				   			<td class="partido"><button id="admin_close_btn" class="button" name="closeBtn" value="closeBtn">Close</button></td>
				        </tr>
			   		</table>
			   		<div id="closeFormResponse">respuesta </div>
			</form>
	   		<table class="quiniela">
	   			<tr align="center">
		   			<td class="partido"><button id="homeBtn2" class="button" name="homeBtn2" value="homeBtn2">Admin Menu</button></td>
		        </tr>
	       </table>
		</div>
    </div>
    <!-- End CloseRound Form -->
</div>
</div>
<!-- End CloseRound Section -->

<!-- FinalQuinielaRound Section -->
<div id="quinielaDiv" class="page">
<div class="container">
    <!-- Title Page -->
    <div class="row">
        <div class="span12">
            <div class="title-page">
                <h2 class="title">Admin - Final Quiniela Round</h2>
            </div>
        </div>
    </div>
    <!-- End Title Page -->
    
    <!-- FinalQuinielaRound Form -->
    <div class="row table-responsive">
		<div align="center">
			<form id="quinielaForm">
			   		<table class="quiniela">
				   		<tr>
				   			<td class="partido">Season:</td>
				   			<td class="partido"><input id="seasonQuiniela" name="season" type="text"/></td>
				        </tr>
				   		<tr>
				   			<td class="partido">Round:</td>
				   			<td class="partido"><input id="roundQuiniela" name="round" type="text"/></td>
				        </tr>
				   		<tr>
				   			<td class="partido">Type Quiniela(0=normal, 1=R 4T, 2= R 3T+3D):</td>
				   			<td class="partido"><input id="tipoQuiniela" name="quiniela" type="text"/></td>
				        </tr>
				   		<tr align="right">
				   			<td class="partido">&nbsp;</td>
				   			<td class="partido"><button id="admin_quiniela_btn" class="button" name="quinielaBtn" value="quinielaBtn">Quiniela</button></td>
				        </tr>
			   		</table>
			   		<div id="quinielaFormResponse">respuesta </div>
			</form>
	   		<table class="quiniela">
	   			<tr align="center">
		   			<td class="partido"><button id="homeBtn4" class="button" name="homeBtn4" value="homeBtn4">Admin Menu</button></td>
		        </tr>
	       </table>
		</div>
    </div>
    <!-- End FinalQuinielaRound Form -->
</div>
</div>
<!-- End FinalQuinielaRound Section -->

<!-- GetQuinielaRound Section -->
<div id="getQuinielaDiv" class="page">
<div class="container">
    <!-- Title Page -->
    <div class="row">
        <div class="span12">
            <div class="title-page">
                <h2 class="title">Admin - Get Final Quiniela Round</h2>
            </div>
        </div>
    </div>
    <!-- End Title Page -->
    <div class="row">
        <div class="span12">
            <div class="title-page">
                <h2 class="title">Quiniela</h2>
                <h3 class="title-description" id="quinielaTitle">Jornada <c:out value="${jornada}" /> Temporada <c:out value="${temporada}" />/<c:out value="${temporada+1-2000}" /></h3>
            </div>
        </div>
    </div>
    <!-- End Title Page -->
    
	<div id="resumResponse" align="center">
		    <table class="quiniela" id="resumTable"></table>
	</div>
    
    <!-- FinalQuinielaRound Form -->
    <div class="row table-responsive">
		<div align="center">
 			<div id="getQuinielaDiv" >  
			   		<table class="quiniela">
				   		<tr>
				   			<td class="partido">Season:</td>
				   			<td class="partido"><input id="getSeasonQuiniela" name="season" type="text"/></td>
				        </tr>
				   		<tr>
				   			<td class="partido">Round:</td>
				   			<td class="partido"><input id="getRoundQuiniela" name="round" type="text"/></td>
				        </tr>
				   		<tr align="right">
				   			<td class="partido">&nbsp;</td>
				   			<td class="partido"><button id="admin_getQuiniela_btn" class="button" name="getQuinielaBtn" value="quinielaBtn">Quiniela</button></td>
				        </tr>
			   		</table>
			   		<div id="getQuinielaFormResponse">respuesta </div>
 			</div>
	   		<table class="quiniela">
	   			<tr align="center">
		   			<td class="partido"><button id="homeBtn04" class="button" name="homeBtn04" value="homeBtn4">Admin Menu</button></td>
		        </tr>
	       </table>
		</div>
    </div>
    <!-- End GetQuinielaRound Form -->
</div>
</div>
<!-- End GetQuinielaRound Section -->




<!-- Footer -->
<footer>
	<p class="credits">&copy;2014 goldbittle.</p>
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
<script src="<c:url value="/static/resources/_include/js/adminAlterQ.js"/>"></script>
<script src="<c:url value="/static/resources/_include/js/jquery.dropotron.js"/>"></script>

</body>
</html>
