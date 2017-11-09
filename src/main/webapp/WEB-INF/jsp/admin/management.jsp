<%-- It works!! ${variable} ${linkTo[HomeController].index} --%>
<template:insert template='/includes/defaultTemplateLogged.jsp'>

	<template:put name="metatags" direct="true">
	</template:put>

	<template:put name="scriptsHeader" direct="true">
	</template:put>

	<template:put name="header" direct="true">
	</template:put>

	<template:put name="content" direct="true">

		<div class="wrapper">
			
			<jsp:include page="/components/logged/left_menu.jsp"/>
			
			<div class="content-wrapper">
			
			    <fmt:message var="title" key="management.dashboard.breadcrumb.title" />
				<fmt:message var="smallTitle" key="management.dashboard.breadcrumb.smallTitle" />
				<fmt:message var="activeTitle" key="management.dashboard.breadcrumb.activeTitle" />

				<jsp:include page="/components/logged/breadcrumbs.jsp">
					<jsp:param name="title" value="${title}"/>
					<jsp:param name="smallTitle" value="${smallTitle}"/>
					<jsp:param name="activeTitle" value="${activeTitle}"/>
				</jsp:include>


		    	<section class="content" >
    
	    			<div class="row">
							
						<div class="col-lg-3 col-xs-6">
							<div class="small-box bg-aqua">
								<div class="inner">
									<h3>150</h3>
									<p><fmt:message key="management.dashboard.menu.building"/></p>
								</div>
								<div class="icon">
									<i class="ion ion-stats-bars"></i>
								</div>
								<a href="${linkTo[AdminController].building}" class="small-box-footer">
									<fmt:message key="management.dashboard.more.details"/>&nbsp;
									<i class="fa fa-arrow-circle-right"></i>
								</a>
							</div>
						</div>

						<div class="col-lg-3 col-xs-6">
							<div class="small-box bg-yellow">
								<div class="inner">
									<h3>150</h3>
									<p><fmt:message key="management.dashboard.menu.appartment"/></p>
								</div>
								<div class="icon">
									<i class="ion ion-pie-graph"></i>
								</div>
								<a href="${linkTo[AdminController].building}" class="small-box-footer">
									<fmt:message key="management.dashboard.more.details"/>&nbsp;
									<i class="fa fa-arrow-circle-right"></i>
								</a>
							</div>
						</div>
													
						<div class="col-lg-3 col-xs-6">
							<div class="small-box bg-green">
								<div class="inner">
									<h3>150</h3>
									<p><fmt:message key="management.dashboard.menu.user"/></p>
								</div>
								<div class="icon">
									<i class="ion ion-person-add"></i>
								</div>
								<a href="${linkTo[AdminController].user}" class="small-box-footer">
									<fmt:message key="management.dashboard.more.details"/>&nbsp;
									<i class="fa fa-arrow-circle-right"></i>
								</a>
							</div>
						</div>
						
						<div class="col-lg-3 col-xs-6">
							<div class="small-box bg-red">
								<div class="inner">
									<h3>150</h3>
									<p><fmt:message key="management.dashboard.menu.payment"/></p>
								</div>
								<div class="icon">
									<i class="ion ion-bag"></i>
								</div>
								<a href="${linkTo[PaymentController].payment}" class="small-box-footer">
									<fmt:message key="management.dashboard.more.details"/>&nbsp;
									<i class="fa fa-arrow-circle-right"></i>
								</a>
							</div>
						</div>
								
					</div>
				
					<div class="row">
						
						<section class="col-lg-5 connectedSortable">
							
						</section>
						
					</div>
				</section>
			</div>

			<jsp:include page="/components/logged/footer.jsp"/>
	
			<div class="control-sidebar-bg"></div>
		</div>


	</template:put>

	<template:put name="scriptsFooter" direct="true">
	</template:put>

</template:insert>
