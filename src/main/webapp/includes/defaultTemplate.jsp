<c:set var="lang" value="${not empty param.lang ? param.lang : not empty lang ? lang : originatingRequest.locale}" scope="session" />

<fmt:setLocale value="${lang}" />
<fmt:bundle basename="com.meuap.Resources" >
	
<!DOCTYPE html>
	<html lang="${lang}">					
		<head>
 			<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
			<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />			
			<meta http-equiv="content-language" content="pt,en,es">
			<meta http-equiv="Access-Control-Allow-Origin" content="*" />				
			<meta charset="utf-8" />
			
			<meta name="viewport" content="width=device-width" />
			<meta name="author" content="Douglas Tagliari">
			<meta name="robots" content="index,noodp,noarchive">
			
			<script type="application/ld+json">
      				{
  						"@context": "http://schema.org",
  						"@type": "WebSite",
  						"url": "http:${completeUrl}",
  						"potentialAction": {
    						"@type": "SearchAction",
	    					"target": "http:${completeUrl}${URLs.browseProducts}?Ntt={search_term_string}",
    						"query-input": "required name=search_term_string"
  						}
					}
				</script>
			
			<script>
			
				var contextPath = '${pageContext.request.contextPath}';
					
			</script>

		    <title><fmt:message key="application.title"/></title>
		
		    <%-- Bootstrap core CSS --%>
		    <link rel="stylesheet" type='text/css' href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" >

		    <%-- Custom Google Web Font --%>
		    <link rel="stylesheet" type='text/css' href="${pageContext.request.contextPath}/static/font-awesome/css/font-awesome.min.css" >
		    <link rel="stylesheet" type='text/css' href='http://fonts.googleapis.com/css?family=Lato:100,300,400,700,900,100italic,300italic,400italic,700italic,900italic'>
			<link rel="stylesheet" type='text/css' href='http://fonts.googleapis.com/css?family=Arvo:400,700' >
		    <%-- Custom CSS--%>
		    <link rel="stylesheet" type='text/css' href="${pageContext.request.contextPath}/static/css/general.css">
			<%-- Owl-Carousel --%>
		    <link rel="stylesheet" type='text/css' href="${pageContext.request.contextPath}/static/css/custom.css" >
			<link rel="stylesheet" type='text/css' href="${pageContext.request.contextPath}/static/css/owl.carousel.css">
		    <link rel="stylesheet" type='text/css' href="${pageContext.request.contextPath}/static/css/owl.theme.css">
			<link rel="stylesheet" type='text/css' href="${pageContext.request.contextPath}/static/css/style.css" >
			<link rel="stylesheet" type='text/css' href="${pageContext.request.contextPath}/static/css/animate.css" >
			
			<%-- Magnific Popup core CSS file --%>
			<link rel="stylesheet" type='text/css' href="${pageContext.request.contextPath}/static/css/magnific-popup.css">
			
			<%-- Custom CSS --%>
			<link rel="stylesheet" type='text/css' href="${pageContext.request.contextPath}/static/css/custom/custom.css">  

			<%-- JavaScript --%>
		    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.10.2.js" ></script>
		    				
			<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/modernizr-2.8.3.min.js"></script> 
			<!--[if IE 9]>
				<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/PIE_IE9.js"></script>
			<![endif]-->
			<!--[if lt IE 9]>
				<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/PIE_IE678.js"></script>
			<![endif]-->
			<!--[if lt IE 9]>
				<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/html5shiv.js"></script>
			<![endif]-->

			<template:get name="metatags" />
							
			<link rel="shortcut icon" type="image/x-icon" href="${favicon}"/>
						
			<template:get name="scriptsHeader"/>
			
		</head>
		
		<body id="home" class="hold-transition skin-purple sidebar-mini">
			<template:get name="header"/>
			<template:get name="content" />
			
			<%-- BEGIN Don't remove these tags! --%>
			<a id="show-popup" href="#popup-dialog-confirmation" class="mfp-hide"></a>
			
			<div id="popup-dialog-confirmation" class="mfp-hide popup-block zoom-anim-dialog">
				<div id="message-display" class="message-display"></div>
				<div id="message-confirm" style="display: none;">						
					<a onclick="confirmAction();" class="popup-modal-confirm button-yes save modal-button"><fmt:message key="common.yes"/></a>
					<a id="show-popup-close" class="popup-modal-dismiss button-no save modal-button"><fmt:message key="common.no"/></a>
				</div>
				<a class="mfp-close btn-close-modal" ></a>
			</div>
			
			<div id="img-ajax-loader" class="mfp-hide" style="width: 44px;" >
			
				<div class="mfp-content">
					<div class="mfp-figure">
						<figure>
							<img src="${modalImgLoading}" style="max-height: 585px;">
							<figcaption>
								<div class="mfp-bottom-bar">
									<div class="mfp-title"></div>
									<div class="mfp-counter"></div>
								</div>
							</figcaption>
						</figure>
					</div>
				</div>
			
			</div>
			
		    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/bootstrap.js"  ></script>
			<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/owl.carousel.js" ></script>
			<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/script.js"  ></script>
			<%-- StikyMenu --%>
			<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/stickUp.min.js"  ></script>
			<%-- Smoothscroll --%>
			<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.corner.js" ></script> 
			<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/wow.min.js"></script>
			<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/classie.js"></script>
			<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/uiMorphingButton_inflow.js"></script>
			<%-- Magnific Popup core JS file --%>
			<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.magnific-popup.js"></script> 

			<script>
			  jQuery(function($) {
				$(document).ready( function() {
				  $('.navbar-default').stickUp();
				  new WOW().init();
				});
			  });
			</script>
			
			<div id="modalMessage"  tabindex="-1"  class="modal fade" role="dialog" aria-labelledby="modal-login-label" style="margin-top: 40vh;">
				<div class="modal-dialog">
			        <div class="modal-content modal-body modal-lg" >
			        	<button type="button" class="close" data-dismiss="modal">&times;</button>
						<div id="modalContent" >
							<%-- best modal --%>
						</div>
					</div>
				</div>
			</div>
			<input id="showModalMessage" type="hidden" data-backdrop="static" data-keyboard="false" data-toggle="modal" data-target="#modalMessage"></input>
			
			<div id="modalLoader" class="modal fade" role="dialog">
		    	<div class="modal-dialog" style="width: 0; padding: 15%;">
			        <div class="modal-body" >
			        	<div class="col-md-12">
          					<div class="box box-danger">
					        	<div class="overlay">
			            			<i class="fa fa-refresh fa-spin" style="color:white;"></i>
				            	</div>
				            </div>
						</div>
			        </div>
			  	</div>
			</div>
			<input id="showModalLoader" type="hidden" data-backdrop="static" data-keyboard="false" data-toggle="modal" data-target="#modalLoader"></input>
			
			
			<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/custom/custom.js"></script>


		  <%--  <footer>
				<div class="container">
		        	<div class="row">
		          		<div class="col-md-7">
		            		<h3 class="footer-title">Follow Me!</h3>
				            <p>Vuoi ricevere news su altri template?<br/>
				              Visita Andrea Galanti.it e vedrai tutte le news riguardanti nuovi Theme!<br/>
				              Go to: <a  href="http://andreagalanti.it" target="_blank">andreagalanti.it</a>
				            </p>
							
							<a rel="cc:attributionURL" href="http://www.andreagalanti.it/flatfy" property="dc:title">Flatfy Theme </a> by
				  			<a rel="dc:creator" href="http://www.andreagalanti.it" property="cc:attributionName">Andrea Galanti</a>is licensed to the public under <BR>the 
				  			<a rel="license" href="http://creativecommons.org/licenses/by-nc/3.0/it/deed.it">Creative Commons Attribution 3.0 License - NOT COMMERCIAL</a>.
			   
		          		</div>
		
						<div class="col-md-5">
							<div class="footer-banner">
								<h3 class="footer-title">Flatfy Theme</h3>
								<ul>
									<li>12 Column Grid Bootstrap</li>
									<li>Form Contact</li>
									<li>Drag Gallery</li>
									<li>Full Responsive</li>
									<li>Lorem Ipsum</li>
								</ul>
								Go to: <a href="http://andreagalanti.it/flatfy" target="_blank">andreagalanti.it/flatfy</a>
							</div>
						</div>
					</div>
				</div>
			</footer>
	    
	     --%>
	     
			<template:get name="scriptsFooter"/>

		</body>		
	</html>
	
</fmt:bundle>
 				
