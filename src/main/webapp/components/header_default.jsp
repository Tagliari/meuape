<nav class="navbar-default" role="navigation">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
				<span class="sr-only"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#home"><fmt:message key="navigation.title"/></a>
		</div>

		<div class="collapse navbar-collapse navbar-right navbar-ex1-collapse">
			<ul class="nav navbar-nav">
				<li class="menuItem"><a class="btn btn-link-1 launch-modal" href="#" onclick="$('#loginModal').modal();">Login&nbsp;</a></li>
				<li class="menuItem"><a href="#whatis"><fmt:message key="navigation.whatis"/></a></li>
				<li class="menuItem"><a href="#useit"><fmt:message key="navigation.useit"/></a></li>
				<li class="menuItem"><a href="#screen"><fmt:message key="navigation.screen"/></a></li>
				<li class="menuItem"><a href="#credits"><fmt:message key="navigation.credits"/></a></li>
				<%--<li class="menuItem"><a href="#syndic"><fmt:message key="navigation.syndic"/></a></li> --%>
			</ul>
		</div>
	</div>
</nav> 
 
<div class="modal fade in" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="modal-login-label" aria-hidden="true" style="display: none; margin-top: 30vh;">
	<div class="modal-dialog">
		<div class="modal-content">

			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">x</span><span class="sr-only"><fmt:message key="home.login.modal.close"/></span>
				</button>
				<h3 class="modal-title" id="modal-login-label"><fmt:message key="home.login.modal.title"/></h3>
				<p><fmt:message key="home.login.modal.message"/></p>
			</div>
			
			<div class="modal-body">
				
	             <form id="loginForm" role="form" action="login" method="post" class="login-form" accept-charset="UTF-8">
	             	<div class="form-group">
	             		<label class="sr-only" for="form-username"><fmt:message key="home.login.modal.email"/></label>
	                 	<input type="text" name="userObject.email" placeholder="Email..." class="form-username form-control" id="form-username">
	                 </div>
	                 <div class="form-group">
	                 	<label class="sr-only" for="form-password"><fmt:message key="home.login.modal.password"/></label>
	                 	<input type="password" name="userObject.password" placeholder="Senha..." class="form-password form-control" id="form-password">
	                 </div>
	                 <button type="button" class="btn btn-block" onclick="loginForm();"><fmt:message key="home.login.modal.enter"/></button>
	             </form>
							
			</div>
		</div>
	</div>
</div>
            
 
<div class="intro-header">
<%-- TODO --%>
	<c:choose>
		<c:when test="${homeConfig ne null && not empty homeConfig.mainBannerUrl}">
			<c:set var="fullImgUrl">
				<custom:img imgUrl="${homeConfig.mainBannerUrl[0]}" />
			</c:set>
			
			<img class="imgFull" src="${fullImgUrl}" />
		</c:when>
		<c:otherwise>
		
			
			<iframe src="https://www.youtube.com/embed/pvs_3zfiH3Q?autoplay=1&loop=1&list=PLRqZRxpOcvlZ69UCOK5krJ6XP7fO-3cKF&showinfo=0&vq=hd720" frameborder="0" allowfullscreen class="iframe_video_home"></iframe>
			<div class="col-xs-12 text-center abcen1">
				<h1 class="h3_home wow fadeIn" data-wow-delay="0.4s"><fmt:message key="home.title"/></h1>
				<h3 class="h3_home wow fadeIn" data-wow-delay="0.6s"><fmt:message key="home.message"/></h3>
				
				<%--<ul class="list-inline intro-social-buttons">
					<li>
						<a href="${pageContext.request.contextPath}/boleto/download" class="btn  btn-lg mybutton_cyano wow fadeIn" data-wow-delay="0.8s">
							<span class="network-name">Minha Conta</span>
						</a>
					</li>
					<li id="download" >
						<a href="#" class="btn  btn-lg mybutton_standard wow swing wow fadeIn" data-wow-delay="1.2s">
							<span class="network-name">Gastos Mensais</span>
						</a>
					</li>
				</ul>
				 --%>
			</div>  
		</c:otherwise>
	</c:choose>
  

	<div class="col-xs-12 text-center abcen wow fadeIn">
		<div class="button_down "> 
			<a class="imgcircle wow bounceInUp" data-wow-duration="1.5s"  href="#whatis"> <img class="img_scroll" src="${staticUrl}/static/images/icon/circle.png" alt=""> </a>
		</div>
	</div>
</div>

