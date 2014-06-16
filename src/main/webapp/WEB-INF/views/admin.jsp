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
	var bAddMatchesRounnSession = 3;
	var bFinalQuiniela			= 4;
	var bResultsRoundSession	= 5;

	//Refs
	var sHomeRef 					= "#homeDiv";
	var sOpenRoundSessionRef		= "#openDiv";
	var sCloseRoundSessionRef		= "#closeDiv";
	var sAddMatchesRounnSessionRef	= "#matchesDiv";
	var sFinalQuinielaRef			= "#quinielaDiv";
	var sResultsRoundSessionRef		= "#resultsDiv";
	
	function initDiv() {
		consoleAlterQ("initDiv");		
		$(sHomeRef).show();
		$(sOpenRoundSessionRef).hide();
		$(sCloseRoundSessionRef).hide();
		$(sAddMatchesRounnSessionRef).hide();
		$(sFinalQuinielaRef).hide();
		$(sResultsRoundSessionRef).hide();
		
		bActual = bHome;
	}
	
	function showDiv(elem) {
		if (elem == bActual)
			return;
		
		switch (elem){
		case bHome:
			$(sHomeRef).show();
			break;
		case bOpenRoundSession:
			$(sOpenRoundSessionRef).show();
			break;
		case bCloseRoundSession:
			$(sCloseRoundSessionRef).show();
			break;
		case bAddMatchesRounnSession:
			$(sAddMatchesRounnSessionRef).show();
			break;
		case bFinalQuiniela:
			$(sFinalQuinielaRef).show();
			break;
		case bResultsRoundSession:
			$(sResultsRoundSessionRef).show();
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
		case bAddMatchesRounnSession:
			$(sAddMatchesRounnSessionRef).hide();
			break;
		case bFinalQuiniela:
			$(sFinalQuinielaRef).hide();
			break;
		case bResultsRoundSession:
			$(sResultsRoundSessionRef).hide();
			break;
		}
		
		bActual = elem;
	}
	
	//Manage Menu Events 
	function menuEvent(name, href)
	{
  		consoleAlterQ("menuEvent elem="+ name +" href="+href);

		if (href == sHomeRef){
			consoleAlterQ("Home");
			showDiv(bHome);
		}else if (href == sOpenRoundSessionRef){
			consoleAlterQ("Open");
			showDiv(bOpenRoundSession);
		}else if (href == sCloseRoundSessionRef){
			consoleAlterQ("Close");
			showDiv(bCloseRoundSession);
		}else if (href == sAddMatchesRounnSessionRef){
			consoleAlterQ("AddMatches");
			showDiv(bAddMatchesRounnSession);
		}else if (href == sFinalQuinielaRef){
			consoleAlterQ("FinalQuiniela");
			showDiv(bFinalQuiniela);
		}else if (href == sResultsRoundSessionRef){
			consoleAlterQ("ResultRoundSession");
			showDiv(bResultsRoundSession);
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
          		<table class="quiniela">
		   		<tr align="center">
		   			<td class="partido"><button id="openBtn" class="button" name="openBtn" value="openBtn">Open</button></td>
		        </tr>
          		</table>
          		<br>
          		<table class="quiniela">
		   		<tr align="center">
		   			<td class="partido"><button id="closeBtn" class="button" name="closeMenu" value="close">Close</button></td>
		        </tr>
          		</table>
          		<br>
          		<table class="quiniela">
		   		<tr align="center">
		   			<td class="partido"><button id="matchesBtn" class="button" name="matchesMenu" value="matches">Matches</button></td>
		        </tr>
          		</table>
          		<br>
          		<table class="quiniela">
		   		<tr align="center">
		   			<td class="partido"><button id="quinielaFinalBtn" class="button" name="quinielaFinalMenu" value="quinelaFinal">Quiniela</button></td>
		        </tr>
          		</table>
          		<br>
          		<table class="quiniela">
		   		<tr align="center">
		   			<td class="partido"><button id="resultsBtn" class="button" name="resultsMenu" value="results">Results</button></td>
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
                <h3 class="title-description" id="quinielaTitle">Jornada <c:out value="${jornada}" /> Temporada <c:out value="${temporada}" />/<c:out value="${temporada+1-2000}" /></h3>
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
				   			<td class="partido"><button id="admin_open_btn" class="button" name="openRound" value="open">Open</button></td>
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
                <h3 class="title-description" id="quinielaTitle">Jornada <c:out value="${jornada}" /> Temporada <c:out value="${temporada}" />/<c:out value="${temporada+1-2000}" /></h3>
            </div>
        </div>
    </div>
    <!-- End Title Page -->
    
    <!-- CloseRound Form -->
    <div class="row table-responsive">
		<div align="center">
			<form id="openFormClose">
			   		<table class="quiniela">
				   		<tr>
				   			<td class="partido">Season:</td>
				   			<td class="partido"><input id="seasonClose" name="season" type="text"/></td>
				        </tr>
				   		<tr>
				   			<td class="partido">Round:</td>
				   			<td class="partido"><input id="roundSeason" name="round" type="text"/></td>
				        </tr>
				   		<tr align="right">
				   			<td class="partido">&nbsp;</td>
				   			<td class="partido"><button id="admin_close_btn" class="button" name="closeRound" value="open">Close</button></td>
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

<!-- MatchesRound Section -->
<div id="matchesDiv" class="page">
<div class="container">
    <!-- Title Page -->
    <div class="row">
        <div class="span12">
            <div class="title-page">
                <h2 class="title">Admin - Matches Round</h2>
                <h3 class="title-description" id="quinielaTitle">Jornada <c:out value="${jornada}" /> Temporada <c:out value="${temporada}" />/<c:out value="${temporada+1-2000}" /></h3>
            </div>
        </div>
    </div>
    <!-- End Title Page -->
    
    <!-- MatchesRound Form -->
    <div class="row table-responsive">
		<div align="center">
			<form id="matchesForm">
			   		<table class="quiniela">
				   		<tr>
				   			<td class="partido">Season:</td>
				   			<td  align="center" colspan=2 class="partido"><input id="seasonOpen" name="season" type="text"/></td>
				        </tr>
				   		<tr>
				   			<td class="partido">Round:</td>
				   			<td align="center" colspan=2 class="partido"><input id="roundOpen" name="round" type="text"/></td>
				        </tr>
				   		<tr>
				   			<td class="partido">1</td>
				   			<td class="partido"><input id="matchlocal01" name="matchlocal01" type="text"/></td>
				   			<td class="partido"><input id="matchvisit01" name="matchvisit01" type="text"/></td>
				        </tr>
				   		<tr>
				   			<td class="partido">2</td>
				   			<td class="partido"><input id="matchlocal02" name="matchlocal02" type="text"/></td>
				   			<td class="partido"><input id="matchvisit02" name="matchvisit02" type="text"/></td>
				        </tr>
				   		<tr>
				   			<td class="partido">3</td>
				   			<td class="partido"><input id="matchlocal03" name="matchlocal03" type="text"/></td>
				   			<td class="partido"><input id="matchvisit03" name="matchvisit03" type="text"/></td>
				        </tr>
				   		<tr>
				   			<td class="partido">4</td>
				   			<td class="partido"><input id="matchlocal04" name="matchlocal04" type="text"/></td>
				   			<td class="partido"><input id="matchvisit04" name="matchvisit04" type="text"/></td>
				        </tr>
				   		<tr>
				   			<td class="partido">5</td>
				   			<td class="partido"><input id="matchlocal05" name="matchlocal05" type="text"/></td>
				   			<td class="partido"><input id="matchvisit05" name="matchvisit05" type="text"/></td>
				        </tr>
				   		<tr>
				   			<td class="partido">6</td>
				   			<td class="partido"><input id="matchlocal06" name="matchlocal06" type="text"/></td>
				   			<td class="partido"><input id="matchvisit06" name="matchvisit06" type="text"/></td>
				        </tr>
				   		<tr>
				   			<td class="partido">7</td>
				   			<td class="partido"><input id="matchlocal07" name="matchlocal07" type="text"/></td>
				   			<td class="partido"><input id="matchvisit07" name="matchvisit07" type="text"/></td>
				        </tr>
				   		<tr>
				   			<td class="partido">8</td>
				   			<td class="partido"><input id="matchlocal08" name="matchlocal08" type="text"/></td>
				   			<td class="partido"><input id="matchvisit08" name="matchvisit08" type="text"/></td>
				        </tr>
				   		<tr>
				   			<td class="partido">9</td>
				   			<td class="partido"><input id="matchlocal09" name="matchlocal09" type="text"/></td>
				   			<td class="partido"><input id="matchvisit09" name="matchvisit09" type="text"/></td>
				        </tr>
				   		<tr>
				   			<td class="partido">10</td>
				   			<td class="partido"><input id="matchlocal10" name="matchlocal10" type="text"/></td>
				   			<td class="partido"><input id="matchvisit10" name="matchvisit10" type="text"/></td>
				        </tr>
				   		<tr>
				   			<td class="partido">11</td>
				   			<td class="partido"><input id="matchlocal11" name="matchlocal11" type="text"/></td>
				   			<td class="partido"><input id="matchvisit11" name="matchvisit11" type="text"/></td>
				        </tr>
				   		<tr>
				   			<td class="partido">12</td>
				   			<td class="partido"><input id="matchlocal12" name="matchlocal12" type="text"/></td>
				   			<td class="partido"><input id="matchvisit12" name="matchvisit12" type="text"/></td>
				        </tr>
				   		<tr>
				   			<td class="partido">13</td>
				   			<td class="partido"><input id="matchlocal13" name="matchlocal13" type="text"/></td>
				   			<td class="partido"><input id="matchvisit13" name="matchvisit13" type="text"/></td>
				        </tr>
				   		<tr>
				   			<td class="partido">14</td>
				   			<td class="partido"><input id="matchlocal14" name="matchlocal14" type="text"/></td>
				   			<td class="partido"><input id="matchvisit14" name="matchvisit14" type="text"/></td>
				        </tr>
				   		<tr>
				   			<td class="partido">15</td>
				   			<td class="partido"><input id="matchlocal15" name="matchlocal15" type="text"/></td>
				   			<td class="partido"><input id="matchvisit15" name="matchvisit15" type="text"/></td>
				        </tr>
				   		<tr align="right">
				   			<td class="partido">&nbsp;</td>
				   			<td align="center" colspan=2 class="partido"><button id="admin_matches_btn" class="button" name="metchesRound" value="matches">Update Matches</button></td>
				        </tr>
			   		</table>
			   		<div id="matchesFormResponse">respuesta </div>
			</form>
	   		<table class="quiniela">
	   			<tr align="center">
		   			<td class="partido"><button id="homeBtn3" class="button" name="homeBtn3" value="homeBtn3">Admin Menu</button></td>
		        </tr>
	       </table>
		</div>
    </div>
    <!-- End MatchesRound Form -->
</div>
</div>
<!-- End MatchesRound Section -->

<!-- FinalQuinielaRound Section -->
<div id="quinielaDiv" class="page">
<div class="container">
    <!-- Title Page -->
    <div class="row">
        <div class="span12">
            <div class="title-page">
                <h2 class="title">Admin - Final Quiniela Round</h2>
                <h3 class="title-description" id="quinielaTitle">Jornada <c:out value="${jornada}" /> Temporada <c:out value="${temporada}" />/<c:out value="${temporada+1-2000}" /></h3>
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
				   			<td class="partido"><input id="seasonOpen" name="season" type="text"/></td>
				        </tr>
				   		<tr>
				   			<td class="partido">Round:</td>
				   			<td class="partido"><input id="roundOpen" name="round" type="text"/></td>
				        </tr>
				   		<tr>
				   			<td class="partido">Final Quiniela:</td>
				   			<td class="partido"><input id="quinielaOpen" name="quiniela" type="text"/></td>
				        </tr>
				   		<tr align="right">
				   			<td class="partido">&nbsp;</td>
				   			<td class="partido"><button id="admin_open_btn" class="button" name="openRound" value="open">Open</button></td>
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

<!-- ResultsRound Section -->
<div id="resultsDiv" class="page">
<div class="container">
    <!-- Title Page -->
    <div class="row">
        <div class="span12">
            <div class="title-page">
                <h2 class="title">Admin - Results Round</h2>
                <h3 class="title-description" id="quinielaTitle">Jornada <c:out value="${jornada}" /> Temporada <c:out value="${temporada}" />/<c:out value="${temporada+1-2000}" /></h3>
            </div>
        </div>
    </div>
    <!-- End Title Page -->
    
    <!-- ResultsRound Form -->
    <div class="row table-responsive">
		<div align="center">
			<form id="resultForm">
			   		<table class="quiniela">
				   		<tr>
				   			<td class="partido">Season:</td>
				   			<td class="partido"><input id="seasonOpen" name="season" type="text"/></td>
				        </tr>
				   		<tr>
				   			<td class="partido">Round:</td>
				   			<td class="partido"><input id="roundOpen" name="round" type="text"/></td>
				        </tr>
				   		<tr>
				   			<td class="partido">Result:</td>
				   			<td class="partido"><input id="resultOpen" name="result" type="text"/></td>
				        </tr>
				   		<tr align="right">
				   			<td class="partido">&nbsp;</td>
				   			<td class="partido"><button id="admin_open_btn" class="button" name="openRound" value="open">Open</button></td>
				        </tr>
			   		</table>
			   		<div id="resutFormResponse">respuesta </div>
			</form>
	   		<table class="quiniela">
	   			<tr align="center">
		   			<td class="partido"><button id="homeBtn5" class="button" name="homeBtn5" value="homeBtn5">Admin Menu</button></td>
		        </tr>
	       </table>
		</div>
    </div>
    <!-- End ResultsRound Form -->
</div>
</div>
<!-- End ResultsRound Section -->


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
