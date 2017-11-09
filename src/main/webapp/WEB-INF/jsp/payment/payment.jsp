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
			
				<section class="content-header">
					<h1>
						<fmt:message key="management.building.page.title" />
					</h1>
					<ol class="breadcrumb">
						<li><a href="#"><i class="fa fa-dashboard"></i>Home</a></li>
						<li class="active"><fmt:message key="management.breadcrumbs.payment" /></li>
					</ol>
				</section>

				
				<section class="content">
					<div class="row">

						<section class="col-lg-12 connectedSortable">
							<jsp:include page="create_payment.jsp"/>
						</section>
						
						<section class="col-lg-12 connectedSortable">
							<jsp:include page="all_payments.jsp"/>
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
