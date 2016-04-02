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
<!-- <link href='http://fonts.googleapis.com/css?family=Titillium+Web:400,200,200italic,300,300italic,400italic,600,600italic,700,700italic,900' rel='stylesheet' type='text/css'>-->
<link href='/static/resources/_include/css/fonts/googlefont.css' rel='stylesheet' type='text/css'>

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
	var bPrizesRoundSession		= 6;
	var bUpdateBalanceUser		= 7;
	var bGetQuinielaRef			= 8;
	var bGetFileRef				= 9;
	var bAdminIni				= 10;

	//Refs
	var sHomeRef 					= "#homeDiv";
	var sOpenRoundSessionRef		= "#openDiv";
	var sCloseRoundSessionRef		= "#closeDiv";
	var sAddMatchesRounnSessionRef	= "#matchesDiv";
	var sFinalQuinielaRef			= "#quinielaDiv";
	var sResultsRoundSessionRef		= "#resultsDiv";
	var sPrizesRoundSessionRef		= "#prizesDiv";
	var sUpdateBalanceUserRef		= "#updateBalanceDiv";
	var sGetQuinielaRef 			= "#getQuinielaDiv";
	var sGetFileRef 				= "#getFileDiv";
	var sAdminIni					= "home";
	
	function initDiv() {
		consoleAlterQ("initDiv");		
		$(sHomeRef).show();
		$(sOpenRoundSessionRef).hide();
		$(sCloseRoundSessionRef).hide();
		$(sAddMatchesRounnSessionRef).hide();
		$(sFinalQuinielaRef).hide();
		$(sResultsRoundSessionRef).hide();
		$(sPrizesRoundSessionRef).hide();
		$(sUpdateBalanceUserRef).hide();
		$(sGetQuinielaRef).hide();
		$(sGetFileRef).hide();
		
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
		case bAddMatchesRounnSession:
			$(sAddMatchesRounnSessionRef).show();
			break;
		case bFinalQuiniela:
			$(sFinalQuinielaRef).show();
			break;
		case bResultsRoundSession:
			$(sResultsRoundSessionRef).show();
			break;
		case bPrizesRoundSession:
			$(sPrizesRoundSessionRef).show();
			break;
		case bUpdateBalanceUser:
			$(sUpdateBalanceUserRef).show();
			break;
		case bGetQuinielaRef:
			$(sGetQuinielaRef).show();
			break;
		case bGetFileRef:
			$(sGetFileRef).show();
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
		case bPrizesRoundSession:
			$(sPrizesRoundSessionRef).hide();
			break;
		case bUpdateBalanceUser:
			$(sUpdateBalanceUserRef).hide();
			break;
		case bGetQuinielaRef:
			$(sGetQuinielaRef).hide();
			break;
		case bGetFileRef:
			$(sGetFileRef).hide();
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
		}else if (href == sAddMatchesRounnSessionRef){
			consoleAlterQ("AddMatches");
			showDiv(bAddMatchesRounnSession);
		}else if (href == sFinalQuinielaRef){
			consoleAlterQ("FinalQuiniela");
			showDiv(bFinalQuiniela);
		}else if (href == sResultsRoundSessionRef){
			consoleAlterQ("ResultRoundSession");
			showDiv(bResultsRoundSession);
		}else if (href == sPrizesRoundSessionRef){
			consoleAlterQ("PrizesRoundSession");
			showDiv(bPrizesRoundSession);
		}else if (href == sUpdateBalanceUserRef){
			consoleAlterQ("UpdateBalanceUser");
			showDiv(bUpdateBalanceUser);
		}else if (href == sGetQuinielaRef){
			consoleAlterQ("bGetQuinielaRef");
			showDiv(bGetQuinielaRef);
		}else if (href == sGetFileRef){
			consoleAlterQ("bGetFileRef");
			showDiv(bGetFileRef);
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
    <div class="row" align="center">
	    <div class="title-page" align="center">
	        <h2 class="title">alterQ</h2>
	    </div>
        <table class="tablaQuiniGold">
	   		<tr align="center">
	   			<td>Administration</td>
	   		</tr>
		</table>
        <br>
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
		   			<td><button id="matchesBtn" class="btn btn-danger" name="matchesMenu" value="matches">Matches</button><br><br></td>
		        </tr>
		   		<tr align="center">
		   			<td><button id="resultsBtn" class="btn btn-danger" name="resultsMenu" value="results">Results</button><br><br></td>
		        </tr>
		   		<tr align="center">
		   			<td><button id="prizesBtn" class="btn btn-danger" name="prizesMenu" value="prizes">Prizes</button><br><br></td>
		        </tr>
		   		<tr align="center">
		   			<td><button id="updateBalanceBtn" class="btn btn-danger" name="updateBalanceprizesMenu" value="updateBalance">Update Balance</button><br><br></td>
		        </tr>
		   		<tr align="center">
		   			<td><button id="fileBtn" class="btn btn-danger" name="fileMenu" value="file">File</button><br></td>
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
			   		<table class="tablaQuiniGold">
				   		<tr>
				   			<td>Season:</td>
				   			<td><input id="seasonOpen" name="season" type="text"/></td>
				        </tr>
				   		<tr>
				   			<td>Round:</td>
				   			<td><input id="roundOpen" name="round" type="text"/></td>
				        </tr>
				   		<tr align="center">
				   			<td colspan="2"><button id="admin_open_btn" class="btn btn-danger" name="openSeasonBtn" value="openSeasonBtn">Open</button><br><br></td>
				        </tr>
			   			<tr align="center">
				   			<td colspan="2"><button id="homeBtn1" class="btn btn-danger" name="homeBtn1" value="homeBtn1">Admin Menu</button></td>
				        </tr>
			   		</table>
			   		<br>
			   		<div id="openFormResponse" class="linkQuiniGold">respuesta </div>
			</form>
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
			   		<table class="tablaQuiniGold">
				   		<tr>
				   			<td>Season:</td>
				   			<td><input id="seasonClose" name="season" type="text"/></td>
				        </tr>
				   		<tr>
				   			<td>Round:</td>
				   			<td><input id="roundClose" name="round" type="text"/></td>
				        </tr>
				   		<tr align="center">
				   			<td colspan="2"><button id="admin_close_btn" class="btn btn-danger" name="closeBtn" value="closeBtn">Close</button><br><br></td>
				        </tr>
			   			<tr align="center">
				   			<td colspan="2"><button id="homeBtn2" class="btn btn-danger" name="homeBtn2" value="homeBtn2">Admin Menu</button></td>
				        </tr>
			   		</table>
			   		<br>
			   		<div id="closeFormResponse" class="linkQuiniGold">respuesta </div>
			</form>
	   		<table class="quiniela">
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
            </div>
        </div>
    </div>
    <!-- End Title Page -->
    
    <!-- MatchesRound Form -->
    <div class="row table-responsive">
		<div align="center">
			<form id="matchesForm">
			   		<table class="tablaQuiniGold">
				   		<tr>
				   			<td>Season:</td>
				   			<td  align="center" colspan=2><input id="seasonMatches" name="season" type="text"/></td>
				        </tr>
				   		<tr>
				   			<td>Round:</td>
				   			<td align="center" colspan=2><input id="roundMatches" name="round" type="text"/></td>
				        </tr>
				   		<tr>
				   			<td>Date:</td>
				   			<td align="center" colspan=2><input id="dateMatches" name="dateMatches" placeholder="aaaa-mm-dd" type="date"/></td>
				        </tr>
				   		<tr>
				   			<td>1</td>
				   			<td><input id="matchlocal01" name="matchlocal01" type="text"/></td>
				   			<td><input id="matchvisit01" name="matchvisit01" type="text"/></td>
				        </tr>
				   		<tr>
				   			<td>2</td>
				   			<td><input id="matchlocal02" name="matchlocal02" type="text"/></td>
				   			<td><input id="matchvisit02" name="matchvisit02" type="text"/></td>
				        </tr>
				   		<tr>
				   			<td>3</td>
				   			<td><input id="matchlocal03" name="matchlocal03" type="text"/></td>
				   			<td><input id="matchvisit03" name="matchvisit03" type="text"/></td>
				        </tr>
				   		<tr>
				   			<td>4</td>
				   			<td><input id="matchlocal04" name="matchlocal04" type="text"/></td>
				   			<td><input id="matchvisit04" name="matchvisit04" type="text"/></td>
				        </tr>
				   		<tr>
				   			<td>5</td>
				   			<td><input id="matchlocal05" name="matchlocal05" type="text"/></td>
				   			<td><input id="matchvisit05" name="matchvisit05" type="text"/></td>
				        </tr>
				   		<tr>
				   			<td>6</td>
				   			<td><input id="matchlocal06" name="matchlocal06" type="text"/></td>
				   			<td><input id="matchvisit06" name="matchvisit06" type="text"/></td>
				        </tr>
				   		<tr>
				   			<td>7</td>
				   			<td><input id="matchlocal07" name="matchlocal07" type="text"/></td>
				   			<td><input id="matchvisit07" name="matchvisit07" type="text"/></td>
				        </tr>
				   		<tr>
				   			<td>8</td>
				   			<td><input id="matchlocal08" name="matchlocal08" type="text"/></td>
				   			<td><input id="matchvisit08" name="matchvisit08" type="text"/></td>
				        </tr>
				   		<tr>
				   			<td>9</td>
				   			<td><input id="matchlocal09" name="matchlocal09" type="text"/></td>
				   			<td><input id="matchvisit09" name="matchvisit09" type="text"/></td>
				        </tr>
				   		<tr>
				   			<td>10</td>
				   			<td><input id="matchlocal10" name="matchlocal10" type="text"/></td>
				   			<td><input id="matchvisit10" name="matchvisit10" type="text"/></td>
				        </tr>
				   		<tr>
				   			<td>11</td>
				   			<td><input id="matchlocal11" name="matchlocal11" type="text"/></td>
				   			<td><input id="matchvisit11" name="matchvisit11" type="text"/></td>
				        </tr>
				   		<tr>
				   			<td>12</td>
				   			<td><input id="matchlocal12" name="matchlocal12" type="text"/></td>
				   			<td><input id="matchvisit12" name="matchvisit12" type="text"/></td>
				        </tr>
				   		<tr>
				   			<td>13</td>
				   			<td><input id="matchlocal13" name="matchlocal13" type="text"/></td>
				   			<td><input id="matchvisit13" name="matchvisit13" type="text"/></td>
				        </tr>
				   		<tr>
				   			<td>14</td>
				   			<td><input id="matchlocal14" name="matchlocal14" type="text"/></td>
				   			<td><input id="matchvisit14" name="matchvisit14" type="text"/></td>
				        </tr>
				   		<tr>
				   			<td>15</td>
				   			<td><input id="matchlocal15" name="matchlocal15" type="text"/></td>
				   			<td><input id="matchvisit15" name="matchvisit15" type="text"/></td>
				        </tr>
				   		<tr align="center">
				   			<td colspan=3><button id="admin_matches_btn" class="btn btn-danger" name="matchesBtn" value="matchesBtn">Update Matches</button><br><br></td>
				        </tr>
			   			<tr align="center">
				   			<td colspan=3><button id="homeBtn3" class="btn btn-danger" name="homeBtn3" value="homeBtn3">Admin Menu</button></td>
				        </tr>
			   		</table>
			   		<br>
			   		<div id="matchesFormResponse" class="linkQuiniGold">respuesta </div>
			</form>
		</div>
    </div>
    <!-- End MatchesRound Form -->
</div>
</div>
<!-- End MatchesRound Section -->


<!-- ResultsRound Section -->
<div id="resultsDiv" class="page">
<div class="container">
<!-- Title Page -->
    <div class="row">
        <div class="span12">
            <div class="title-page">
                <h2 class="title">Admin - Results Round</h2>
            </div>
        </div>
    </div>
<!-- End Title Page -->    
<!-- ResultsRound Form -->
    <div class="row table-responsive">
		<div align="center">
			<form id="resultForm">
			   		<table class="tablaQuiniGold">
				   		<tr>
				   			<td>Season:</td>
				   			<td><input id="seasonResults" name="season" type="text"/></td>
				        </tr>
				   		<tr>
				   			<td>Round:</td>
				   			<td><input id="roundResults" name="round" type="text"/></td>
				        </tr>
				   		<tr>
				   			<td>Result(1-X-2)[012M]:</td>
				   			<td><input id="betResults" name="results" type="text"/></td>
				        </tr>
				   		<tr align="center">
				   			<td colspan=2><button id="admin_results_btn" class="btn btn-danger" name="resultsBtn" value="resultsBtn">Set Results</button><br><br></td>
				        </tr>
			   			<tr align="center">
				   			<td colspan=2><button id="homeBtn5" class="btn btn-danger" name="homeBtn5" value="homeBtn5">Admin Menu</button></td>
				        </tr>
			   		</table>
			   		<br>
			   		<div id="resutFormResponse" class="linkQuiniGold">respuesta </div>
			</form>
		</div>
    </div>
<!-- End ResultsRound Form -->    
</div>
</div>
<!-- End ResultsRound Section -->


<!-- PrizesRound Section -->
<div id="prizesDiv" class="page">
<div class="container">
    <!-- Title Page -->
    <div class="row">
        <div class="span12">
            <div class="title-page">
                <h2 class="title">Admin - Prizes Round</h2>
            </div>
        </div>
    </div>
    <!-- End Title Page -->
    
    <!-- PrizesRound Form -->
    <div class="row table-responsive">
		<div align="center">
			<form id="prizesForm">
			   		<table class="tablaQuiniGold">
				   		<tr>
				   			<td>Season:</td>
				   			<td><input id="seasonPrizes" name="season" type="text"/></td>
				        </tr>
				   		<tr>
				   			<td>Round:</td>
				   			<td><input id="roundPrizes" name="round" type="text"/></td>
				        </tr>
				   		<tr>
				   			<td>Prize10:</td>
				   			<td><input id="prize10" name="prize10" type="text"/></td>
				        </tr>
				   		<tr>
				   			<td>Prize11:</td>
				   			<td><input id="prize11" name="prize11" type="text"/></td>
				        </tr>
				   		<tr>
				   			<td>Prize12:</td>
				   			<td><input id="prize12" name="prize12" type="text"/></td>
				        </tr>
				   		<tr>
				   			<td>Prize13:</td>
				   			<td><input id="prize13" name="prize13" type="text"/></td>
				        </tr>
				   		<tr>
				   			<td>Prize14:</td>
				   			<td><input id="prize14" name="prize14" type="text"/></td>
				        </tr>
				   		<tr>
				   			<td>Prize15:</td>
				   			<td><input id="prize15" name="prize15" type="text"/></td>
				        </tr>
				   		<tr align="center">
				   			<td colspan=2><button id="admin_prizes_btn" class="btn btn-danger" name="prizesBtn" value="prizesBtn">Set Prizes</button><br><br></td>
				        </tr>
			   			<tr align="center">
				   			<td colspan=2><button id="homeBtn6" class="btn btn-danger" name="homeBtn6" value="homeBtn6">Admin Menu</button></td>
				        </tr>
			   		</table>
			   		<br>
			   		<div id="prizesFormResponse" class="linkQuiniGold">respuesta</div>
			</form>
	   		<table class="quiniela">
	       </table>
		</div>
    </div>
    <!-- End PrizesRound Form -->
</div>
</div>
<!-- End PrizesRound Section -->

<!-- ResultsRound Section -->
<div id="updateBalanceDiv" class="page">
<div class="container">
    <!-- Title Page -->
    <div class="row">
        <div class="span12">
            <div class="title-page">
                <h2 class="title">Admin - Update User Balance</h2>
            </div>
        </div>
    </div>
    <!-- End Title Page -->
    
    <!-- ResultsRound Form -->
    <div class="row table-responsive">
		<div align="center">
			<form id="updateBalanceForm">
			   		<table class="tablaQuiniGold">
				   		<tr>
				   			<td>User:</td>
				   			<td><input id="updateBalanceUser" name="user" type="text"/></td>
				        </tr>
				   		<tr>
				   			<td>Reset Balance:</td>
				   			<td><input id="updateBalanceBalance" name="balance" type="text"/></td>
				        </tr>
				   		<tr>
				   			<td>Increase Balance:</td>
				   			<td><input id="updateIncreaseBalanceBalance" name="increaseBalance" type="text"/></td>
				        </tr>
				   		<tr>
				   			<td>Decrease Balance:</td>
				   			<td><input id="updateDecreaseBalanceBalance" name="decreaseBalance" type="text"/></td>
				        </tr>
				   		<tr align="center">
				   			<td colspan=2><button id="admin_updateBalance_btn" class="btn btn-danger" name="updateBalanceBtn" value="updateBalanceBtn">Update Balance</button><br><br></td>
				        </tr>
			   			<tr align="center">
				   			<td colspan=2><button id="homeBtn7" class="btn btn-danger" name="homeBtn7" value="homeBtn7">Admin Menu</button></td>
				        </tr>
			   		</table>
			   		<br>
			   		<div id="updateBalanceFormResponse" class="linkQuiniGold">respuesta </div>
			</form>
		</div>
    </div>
    <!-- End ResultsRound Form -->
</div>
</div>
<!-- End ResultsRound Section -->




<!-- GetFile Section -->
<div id="getFileDiv" class="page">
<div class="container">
    <!-- Title Page -->
    <div class="row">
        <div class="span12">
            <div class="title-page">
                <h2 class="title">Admin - Get Final File</h2>
            </div>
        </div>
    </div>
   
	<div id="resumResponse" align="center">
		    <table class="quiniela" id="resumTable"></table>
	</div>
    
    <!-- FinalQuinielaRound Form -->
    <div class="row table-responsive">
		<div align="center">
			<form id="getFileForm">
			   		<table class="tablaQuiniGold">
				   		<tr>
				   			<td>Season:</td>
				   			<td><input id="seasonGetFile" name="season" type="text"/></td>
				        </tr>
				   		<tr>
				   			<td>Round:</td>
				   			<td><input id="roundFGetile" name="round" type="text"/></td>
				        </tr>
				   		<tr align="center">
				   			<td colspan=2><button id="admin_getFile_btn" class="btn btn-danger" name="getFileBtn" value="fileBtn">Get File</button><br><br></td>
				        </tr>
			   			<tr align="center">
				   			<td colspan=2><button id="homeBtn8" class="btn btn-danger" name="homeBtn04" value="homeBtn8">Admin Menu</button></td>
				        </tr>
			   		</table>
			   		<br>
			   		<div id="getFileFormResponse" class="linkQuiniGold">respuesta </div>
			</form>
		</div>
    </div>
    <!-- End GetQuinielaRound Form -->
</div>
</div>

<!-- Footer -->
<footer>
	<p class="credits">&copy;2015 quiniGold. v:0.5.5</p>
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
