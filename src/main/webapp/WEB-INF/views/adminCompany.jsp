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
<link href="<c:url value="/static/resources/_include/css/fonts/googlefont.css"/>" rel='stylesheet' type='text/css'>

<!-- Fav Icon -->
<link rel="shortcut icon" href="#">

<link rel="apple-touch-icon" href="#">
<link rel="apple-touch-icon" sizes="114x114" href="#">
<link rel="apple-touch-icon" sizes="72x72" href="#">
<link rel="apple-touch-icon" sizes="144x144" href="#">

<!-- Modernizr -->
<!-- <script src="_include/js/modernizr.js"></script> -->
<script src="<c:url value="/static/resources/_include/js/modernizr.js"/>"></script>
<!-- <script src="<c:url value="/static/resources/_include/js/alterQ.js"/>"></script>-->
<script type="text/javascript">
var ctx = "<%=request.getContextPath()%>";



</script>


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

	//Divs Graphics
	var bActual					= 0;
	var bHome					= 0;
	var bCloseAC				= 2;
	var bAdminIni				= 3;
	var bFinalQuiniela			= 5;
	var bQuinielaDetail = 13;
	var bMyModal = 14;
	var bConfirmQuiniela = 15;
	var bModalReduced = 16;
	var bConfirmedQuiniela = 17;

	//Refs
	var sHomeRef 					= "#homeDiv";
	var sCloseACRef					= "#closeACDiv";
	var sFinalQuinielaRef			= "#quinielaDiv";
	var sAdminIni					= "home";
	var sConfirmQuinielaRef 		= "#confirmarQuinielaDiv";
	var sConfirmedQuinielaRef 		= "#confirmadaQuinielaDiv";
	var sModalReduced				= "#modalReduced";

	
	function initDiv() {
		consoleAlterQ("initDiv");		
		$(sHomeRef).show();
		$(sCloseACRef).hide();
		$(sFinalQuinielaRef).hide();
		$(sConfirmQuinielaRef).hide();
		$(sConfirmedQuinielaRef).hide();
		$(modalReduced).hide();
		
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
		case bCloseAC:
			$(sCloseACRef).show();
			break;
		case bFinalQuiniela:
			$(sFinalQuinielaRef).show();
			break;
		case bConfirmQuiniela:
			$(sConfirmQuinielaRef).show();
			break;
		case bConfirmedQuiniela:
			$(sConfirmedQuinielaRef).show();
			break;
		}

		switch (bActual){
		case bHome:
			$(sHomeRef).hide();
			break;
		case bCloseAC:
			$(sCloseACRef).hide();
			break;
		case bFinalQuiniela:
			$(sFinalQuinielaRef).hide();
			break;
		case bConfirmQuiniela:
			$(sConfirmQuinielaRef).hide();
			break;
		case bConfirmedQuiniela:
			$(sConfirmedQuinielaRef).hide();
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
		}else if (href == sCloseACRef){
			consoleAlterQ("Close");
			setRounSeasoCloseAC(window.round, window.season);
			showDiv(bCloseAC);
		}else if (href == sFinalQuinielaRef){
			consoleAlterQ("FinalQuiniela");
			showDiv(bFinalQuiniela);
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
	   			<td>Company Administration</td>
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
		   			<td><button id="closeACBtn" class="btn btn-danger" name="closeACBtn" value="closeACBtn">Close</button><br><br></td>
		        </tr>
		   		<tr align="center">
		   			<td><button id="quinielaFinalBtn" class="btn btn-danger" name="quinielaFinalMenu" value="quinelaFinal">Quiniela</button><br><br></td>
		        </tr>
	   			</table>
        </div>
</div>
</div>
<!-- End Principal -->

<%@ include file="/WEB-INF/views/quiniela.jsp" %>

<!-- CloseAC Section -->
<div id="closeACDiv" class="page">
<div class="container">
    <!-- Title Page -->
    <div class="row">
        <div class="span12">
            <div class="title-page">
                <h2 class="title">AdminCompany - Close</h2>
            </div>
        </div>
    </div>
    <!-- End Title Page -->
    
    <!-- CloseAC Form -->
    <div class="row table-responsive">
		<div align="center">
			<form id="closeACForm">
			   		<table class="tablaQuiniGold">
				   		<tr>
				   			<td>Season:</td>
				   			<td><input id="seasonCloseAC" name="season" type="text" readonly="readonly"/></td>
				        </tr>
				   		<tr>
				   			<td>Round:</td>
				   			<td><input id="roundCloseAC" name="round" type="text" readonly="readonly"/></td>
				        </tr>
				   		<tr align="center">
				   			<td colspan=2><button id="adminCompany_closeAC_btn" class="btn btn-danger" name="closeACBtn" value="closeACBtn">Cerrar</button><br><br></td>
				        </tr>
			   			<tr align="center">
				   			<td colspan=2><button id="homeBtn9" class="btn btn-danger" name="homeBtn9" value="homeBtn9">Admin Menu</button></td>
				        </tr>
			   		</table>
			   		<br>
			   		<div id="closeACFormResponse" class="linkQuiniGold">respuesta </div>
			</form>
		</div>
    </div>
    <!-- End CloseAC Form -->
</div>
</div>
<!-- End CloseAC Section -->

<!-- FinalQuinielaRound Section -->
<!-- <div id="quinielaDiv" class="page"> -->
<!-- <div class="container"> -->
<!--     Title Page -->
<!--     <div class="row"> -->
<!--         <div class="span12"> -->
<!--             <div class="title-page"> -->
<!--                 <h2 class="title">AdminCompany - Final Quiniela</h2> -->
<!--             </div> -->
<!--         </div> -->
<!--     </div> -->
<!--     End Title Page -->
    
<!--     FinalQuinielaRound Form -->
<!--     <div class="row table-responsive"> -->
<!-- 		<div align="center"> -->
<%-- 			<form id="finalQuinielaForm"> --%>
<!-- 			   		<table class="tablaQuiniGold"> -->
<!-- 				   		<tr> -->
<!-- 				   			<td>Season:</td> -->
<!-- 				   			<td><input id="seasonFinalQuiniela" name="season" type="text"/></td> -->
<!-- 				        </tr> -->
<!-- 				   		<tr> -->
<!-- 				   			<td>Round:</td> -->
<!-- 				   			<td><input id="roundFinalQuiniela" name="round" type="text"/></td> -->
<!-- 				        </tr> -->
<!-- 				   		<tr> -->
<!-- 				   			<td>NumBets:</td> -->
<!-- 				   			<td><input id="numBetsFinalQuiniela" name="round" type="text"/></td> -->
<!-- 				        </tr> -->
<!-- 				   		<tr align="center"> -->
<!-- 				   			<td colspan=2><button id="adminCompany_finalQuiniela_btn" class="btn btn-danger" name="finalQuinielaBtn" value="finalQuinielaBtn">Quiniela</button><br><br></td> -->
<!-- 				        </tr> -->
<!-- 			   			<tr align="center"> -->
<!-- 				   			<td colspan=2><button id="homeBtn10" class="btn btn-danger" name="homeBtn10" value="homeBtn10">Admin Menu</button></td> -->
<!-- 				        </tr> -->
<!-- 			   		</table> -->
<!-- 			   		<br> -->
<!-- 			   		<div id="finalQuinielaFormResponse" class="linkQuiniGold">respuesta </div> -->
<%-- 			</form> --%>
<!-- 		</div> -->
<!--     </div> -->
<!--     End FinalQuinielaRound Form -->
<!-- </div> -->
<!-- </div> -->
<!-- End FinalQuinielaRound Section -->

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
<script src="<c:url value="/static/resources/_include/js/variables.js"/>"></script>
<script src="<c:url value="/static/resources/_include/js/quiniela.js"/>"></script>
<!-- <script src="<c:url value="/static/resources/_include/js/adminCompany.js"/>"></script>-->
<script src="<c:url value="/static/resources/_include/js/jquery.dropotron.js"/>"></script>

<script type="text/javascript">

var requestUserDto=${requestUserDto};

alert(requestUserDto.company);
alert(requestUserDto.round);
alert(requestUserDto.season);
alert(requestUserDto.idUserAlterQ);





alert("context:"+ctx);
$(document).ready(function() {
alert("$(document).ready: INICIO");	
	consoleAlterQ("AdminCompany - ready");
	consoleAlterQ("000document ready: round="+requestUserDto.round+" season="+requestUserDto.season);

	
	$.fn.serializeObject = function()
	{
	    var o = {};
	    var a = this.serializeArray();
	    $.each(a, function() {
	        if (o[this.name] !== undefined) {
	            if (!o[this.name].push) {
	                o[this.name] = [o[this.name]];
	            }
	            o[this.name].push(this.value || '');
	        } else {
	            o[this.name] = this.value || '';
	        }
	    });
	    return o;
	};
	
	initDiv();
	
	
	var jqxhr =
	    $.ajax({
	        url: ctx+"/login",
 	    })
	    .success (function(response) { 
		    if(response.errorDto!=0){
		    	if (bActual == bLogin)
		    		showDiv(bLogin);
		    	else
					showDiv(bHome);
		    	
				window.userLoged=false;
		    }
		    else{
		    	if (response.userAlterQ!=null){
	    			showDiv(bHome);
		    			
					fillUserData(response);
					
					window.userLoged=true;
					window.loadBetUser = true;
					consoleAlterQ("loadBetUser: TRUE");
		    	}
		    	else{
		    		showDiv(bHome);
		    		window.userLoged=false;
		    	}
		    }
		    fillRoundSeasonCompany(response);

			//Paint Main Menu Items
			consoleAlterQ("Menu: pintamos los elementos del menu");
			getMainMenuItems(window.userLoged, window.userLoged?response.userAlterQ.name:null);
	    });
	
	$('form#closeACForm').submit(function(event) {
		 var dataJson=JSON.stringify($('form#closeACForm').serializeObject());
		 consoleAlterQ(dataJson);
		 jQuery.ajax ({
			 url: ctx+'/adminCompany'+ '/company/' + window.company + '/season/'+ $("input[id=seasonCloseAC]").val() + '/round/' + $("input[id=roundCloseAC]").val() + '/closeAC',
//			 url: ctx+'/adminCompany/closeAC',
			 	type: "POST",
			    data: dataJson,
			    contentType: "application/json; charset=utf-8",
			    async: false,    //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
	            cache: false,    //This will force requested pages not to be cached by the browser  
	            processData:false, //To avoid making query String instead of JSON
			    success: function(response){
			    	if(response.errorDto!=0){
		   		    	$(response.errorDto).each(function(index, objeto){  
		   		    		consoleAlterQ("closeAC: response="+objeto.stringError);
		   		    		$('#closeACFormResponse').append(objeto.stringError+" - ");
					    });
		   		    	
		   		    }
		   		    else{
		   		    	//consoleAlterQ("open: response= OK"+response.userAlterQ.name);
						//$('#loginFormResponse').text(response.userAlterQ.name);
						consoleAlterQ("closeAC: response= OK");
						$('#closeACFormResponse').text("AdminCompany - Close - OK");
						//userLoged=true;
						//idUserAlterQ=response.userAlterQ.id;
						//$('#idData').val(response.userAlterQ.id);
						//$('#nameData').val(response.userAlterQ.name);
						//$('#phoneNumberData').val(response.userAlterQ.phoneNumber);
						//$('#idSaldo').val(response.userAlterQ.id);
						//$('#balanceSaldo').val(response.userAlterQ.balance);
						//getMainMenuItems(userLoged, userLoged?response.userAlterQ.name:null);
						//showDiv(bHome);
		   		    }
				    //round=response.generalData.round;
				    //season=response.generalData.season;
			    }
			});
		 	event.preventDefault(); // prevent actual form submit and page reload
	});	

	$('form#finalQuinielaForm').submit(function(event) {
		 var dataJson=JSON.stringify($('form#quinielaForm').serializeObject());
		 consoleAlterQ(dataJson);
		 jQuery.ajax ({
			 url: ctx+'/adminCompany'+ '/company/' + window.company + '/season/'+ $("input[id=seasonQuiniela]").val() + '/round/' + $("input[id=roundQuiniela]").val() + '/finalBet/' + $("input[id=tipoQuiniela]").val(),
			    type: "POST",
			    data: dataJson,
			    //contentType: "application/json; charset=utf-8",
			    async: false,    //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
	            cache: false,    //This will force requested pages not to be cached by the browser  
	            processData:false, //To avoid making query String instead of JSON
			    success: function(response){
			    	if(response.errorDto!=0){
		   		    	$(response.errorDto).each(function(index, objeto){  
		   		    		consoleAlterQ("result: response="+objeto.errorDto);
		   		    		$('#quinielaFormResponse').append(objeto.stringError+" - ");
					    });
		   		    	
		   		    }
		   		    else{
		   		    	//consoleAlterQ("open: response= OK"+response.userAlterQ.name);
						//$('#loginFormResponse').text(response.userAlterQ.name);
						consoleAlterQ("result: response= OK");
						$('#quinielaFormResponse').text("Admin - Quiniela - OK");
						//userLoged=true;
						//idUserAlterQ=response.userAlterQ.id;
						//$('#idData').val(response.userAlterQ.id);
						//$('#nameData').val(response.userAlterQ.name);
						//$('#phoneNumberData').val(response.userAlterQ.phoneNumber);
						//$('#idSaldo').val(response.userAlterQ.id);
						//$('#balanceSaldo').val(response.userAlterQ.balance);
						//getMainMenuItems(userLoged, userLoged?response.userAlterQ.name:null);
						//showDiv(bHome);
		   		    }
				    //round=response.generalData.round;
				    //season=response.generalData.season;
			    }
			});
		 	event.preventDefault(); // prevent actual form submit and page reload
	});

});

$("#homeBtn").on('click', function(event){
	menuEvent($(this).text(),  "home");
	event.preventDefault(); // prevent actual form submit and page reload
});
$("#quinielaFinalBtn").on('click', function(event){
	window.quinielaFinal = true;
	getSummary();
	getQuiniela();
	menuEvent($(this).text(),  "#quinielaDiv");
	event.preventDefault(); // prevent actual form submit and page reload
});
$("#closeACBtn").on('click', function(event){
//alert("closeACBtn CLICK");	
	menuEvent($(this).text(),  "#closeACDiv");
	event.preventDefault(); // prevent actual form submit and page reload
});

$("#homeBtn9").on('click', function(event){
	menuEvent($(this).text(),  "#homeDiv");
	event.preventDefault(); // prevent actual form submit and page reload
});
$("#homeBtn10").on('click', function(event){
	menuEvent($(this).text(),  "#homeDiv");
	event.preventDefault(); // prevent actual form submit and page reload
});

function setRounSeasoCloseAC (round, season){
	if (round != 0)
		$('#seasonCloseAC').val(season);
	if (season != 0)
		$('#roundCloseAC').val(round);
}

function consoleAlterQ(text){
	if( (window['console'] !== undefined) ){
		console.log(text);
	}
}


</script>

</body>
</html>
