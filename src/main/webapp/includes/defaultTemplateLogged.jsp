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

			<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
			<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
			<link rel="stylesheet" type='text/css' href="${pageContext.request.contextPath}/static/css/lte/AdminLTE.min.css">
			<link rel="stylesheet" type='text/css' href="${pageContext.request.contextPath}/static/css/lte/_all-skins.min.css">
			<link rel="stylesheet" type='text/css' href="${pageContext.request.contextPath}/static/css/lte/blue.css">
			<link rel="stylesheet" type='text/css' href="${pageContext.request.contextPath}/static/css/lte/morris.css">
			<link rel="stylesheet" type='text/css' href="${pageContext.request.contextPath}/static/css/lte/jquery-jvectormap-1.2.2.css">
			<link rel="stylesheet" type='text/css' href="${pageContext.request.contextPath}/static/css/lte/datepicker3.css">
			<link rel="stylesheet" type='text/css' href="${pageContext.request.contextPath}/static/css/lte/daterangepicker.css">
			<link rel="stylesheet" type='text/css' href="${pageContext.request.contextPath}/static/css/lte/bootstrap3-wysihtml5.min.css">


			<%-- Custom CSS --%>
			<link rel="stylesheet" type='text/css' href="${pageContext.request.contextPath}/static/css/custom/custom.css">  

			<%-- JavaScript --%>
		    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-2.2.3.min.js" ></script>
		    				

			<template:get name="metatags" />
							
			<link rel="shortcut icon" type="image/x-icon" href="${favicon}"/>
						
			<template:get name="scriptsHeader"/>
			
		</head>
		
		<body id="home" class="skin-blue layout-boxed sidebar-mini">
			<template:get name="header"/>
			<template:get name="content" />
			
			
		    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/bootstrap.js"  ></script>

			<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.11.2/moment.min.js"></script>
	
			<script>
				$.widget.bridge('uibutton', $.ui.button);
			</script>
			<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/lte/morris.min.js"></script>
			<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/lte/jquery.sparkline.min.js"></script>
			<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/lte/jquery-jvectormap-1.2.2.min.js"></script>
			<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/lte/jquery-jvectormap-world-mill-en.js"></script>
			<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/lte/jquery.knob.js"></script>
			<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/lte/daterangepicker.js"></script>
			<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/lte/bootstrap-datepicker.min.js"></script>
			<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/lte/bootstrap3-wysihtml5.all.min.js"></script>
			<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/lte/jquery.slimscroll.min.js"></script>
			<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/lte/fastclick.min.js"></script>
			<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/lte/app.min.js"></script>
			<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/lte/dashboard.js"></script>
			<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/lte/demo.js"></script>
			<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/lte/jquery.inputmask.js"></script>
			<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/lte/jquery.inputmask.extensions.js"></script>
			<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/lte/jquery.inputmask.date.extensions.js"></script>
			<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/lte/jquery.inputmask.numeric.extensions.js"></script>
		
			<div id="modalMessage" class="modal fade" role="dialog" style="margin-top: 40vh;">
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
			<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/custom/validate_cpfcnpj.min.js"></script>
	     
			<template:get name="scriptsFooter"/>
			
		</body>		
	</html>
	
</fmt:bundle>
 				
