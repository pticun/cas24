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

<script src="<c:url value="/static/resources/_include/js/alterQ.js"/>"></script>

</head>

	
<script type="text/javascript">

	//Divs Graphics
	var bActual					= 0;
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
		$(sHomeRef).show();
		$(sOpenRoundSessionRef).hide();
		$(sAddMatchesRounnSessionRef).hide();
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

    	<div class="row">
            <div class="span9">
        			<ul>
						<!-- Item Project and Filter Name -->
                    	<li class="item-thumbs span3">
                        	<a class="hover-wrap" id="openBtn" href="#openDiv">
                            	<span class="overlay-img"></span>
                                <span class="overlay-img-thumb font-icon-plus"></span>
                            </a>
                            <!-- Thumb Image and Description -->
                            <img src="<c:url value='/static/resources/_include/img/work/thumbs/image-01.jpg'/>" alt="Open">
                        </li>
                    	<!-- End Item Project -->
						<!-- Item Project and Filter Name -->
                    	<li class="item-thumbs span3">
                        	<a class="hover-wrap" id="closeBtn" href="#closeDiv">
                            	<span class="overlay-img"></span>
                                <span class="overlay-img-thumb font-icon-plus"></span>
                            </a>
                            <!-- Thumb Image and Description -->
                            <img src="<c:url value='/static/resources/_include/img/work/thumbs/image-01.jpg'/>" alt="Close">
                        </li>
                    	<!-- End Item Project -->
						<!-- Item Project and Filter Name -->
                    	<li class="item-thumbs span3">
                        	<a class="hover-wrap" id="matchesBtn" href="#matchesDiv">
                            	<span class="overlay-img"></span>
                                <span class="overlay-img-thumb font-icon-plus"></span>
                            </a>
                            <!-- Thumb Image and Description -->
                            <img src="<c:url value='/static/resources/_include/img/work/thumbs/image-01.jpg'/>" alt="Matches">
                        </li>
                    	<!-- End Item Project -->
						<!-- Item Project and Filter Name -->
                    	<li class="item-thumbs span3">
                        	<a class="hover-wrap" id="quinielaBtn" href="#quinielasDiv">
                            	<span class="overlay-img"></span>
                                <span class="overlay-img-thumb font-icon-plus"></span>
                            </a>
                            <!-- Thumb Image and Description -->
                            <img src="<c:url value='/static/resources/_include/img/work/thumbs/image-01.jpg'/>" alt="Quiniela">
                        </li>
                    	<!-- End Item Project -->
						<!-- Item Project and Filter Name -->
                    	<li class="item-thumbs span3">
                        	<a class="hover-wrap" id="resultsBtn" href="#resultsDiv">
                            	<span class="overlay-img"></span>
                                <span class="overlay-img-thumb font-icon-plus"></span>
                            </a>
                            <!-- Thumb Image and Description -->
                            <img src="<c:url value='/static/resources/_include/img/work/thumbs/image-01.jpg'/>" alt="Results">
                        </li>
                    	<!-- End Item Project -->
        			</ul>
            </div>
        </div>
</div>
</div>
<!-- End Principal -->



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
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script> <!-- jQuery Core -->
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
<!-- End Js -->

</body>
</html>
