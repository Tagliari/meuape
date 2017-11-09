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
			<title><fmt:message key="admin.register.title"/></title>
	
	
			<link rel="stylesheet" type='text/css' href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
			<link rel="stylesheet" type='text/css' href="${pageContext.request.contextPath}/static/font-awesome/css/font-awesome.min.css" >
			<link rel="stylesheet" type='text/css' href="${pageContext.request.contextPath}/static/css/lte/AdminLTE.min.css">
		</head>
		
		<body class="hold-transition register-page">
			<div class="register-box">
				<div class="register-logo">
					<a href="../../index2.html"><fmt:message key="admin.register.logo"/></a>
				</div>
		
				<div class="register-box-body">
					<p class="login-box-msg">Novo Cadastro</p>
		
					<form action="../../index.html" method="post">
						<div class="form-group has-feedback">
							<input type="text" class="form-control" placeholder="Full name"> <span class="glyphicon glyphicon-user form-control-feedback"></span>
						</div>
						<div class="form-group has-feedback">
							<input type="email" class="form-control" placeholder="Email"> <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
						</div>
						<div class="form-group has-feedback">
							<input type="password" class="form-control" placeholder="Password"> <span class="glyphicon glyphicon-lock form-control-feedback"></span>
						</div>
						<div class="form-group has-feedback">
							<input type="password" class="form-control" placeholder="Retype password"> <span class="glyphicon glyphicon-log-in form-control-feedback"></span>
						</div>
						<div class="row">
							<div class="col-xs-6">
								<button type="submit" class="btn btn-block btn-flat">Voltar</button>
							</div>
							<div class="col-xs-6">
								<button type="submit" class="btn btn-primary btn-block btn-flat">Cadastrar</button>
							</div>
						</div>
					</form>
		
				</div>
			</div>
		
		    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.10.2.js" ></script>
		    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/bootstrap.js"  ></script>
		    
		</body>
	</html>
</fmt:bundle>
