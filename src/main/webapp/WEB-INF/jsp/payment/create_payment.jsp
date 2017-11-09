<div class="box box-info">

	<div class="box-header with-border" style="cursor: move;">
		<i class="fa fa-credit-card"></i>

		<h3 class="box-title"><fmt:message key="management.payment.create.title"/></h3>
	</div>
	
	<form id="paymentBoleto" role="form" class="form" action="${contextPath}/blank.jsp" method="post">
		<div class="box-body">
		
			<div class="form-group col-sm-12 input_fields_wrap">
			
				<fmt:message var="paymentUserPlaceHolder" key='management.payment.user.placeholder' />
				<label for="paymentUser" class="control-label">${paymentUserPlaceHolder}</label>
					
				<div class="input-group input-group-sm col-sm-12">
						 
					<select class="form-control col-sm-12" name="pg.userObject.id" id="paymentUser">
						<c:forEach items="${userList}" var="user">
							<option value="${user.id}">${user.displayName}&nbsp;-&nbsp;${user.email}&nbsp;-&nbsp;${user.cpf}</option>
						</c:forEach>
					</select>
					
				</div>
				
				<c:if test="${not empty message}">
					<div class="input-group input-group-sm box-body col-sm-12">
						<div class="alert alert-info alert-dismissible">
							<button type="button" class="close" data-dismiss="alert" aria-hidden="true">x</button>
							<h4><i class="icon fa fa-info"></i>&nbsp;Alerta!</h4>${message}
						</div>
					</div>
				</c:if>
				
			</div>
		
		</div>
		
		<div class="box-footer">
			<div class="col-xs-12">
				<input type="button" name="submit" onclick="selectUserPayment();" value="Selecionar" class="btn wow tada btn-embossed btn-primary pull-right">
			</div>
		</div>	
		
		<div class="box-body">
			<div id="lastOfSelectPayment">
			</div>
		</div>  
	</form>
</div>

<script>
	function submitBoletoPayment() {
		showModalLoading();
		$.ajax({
			type : "POST",
			async : true,
			cache : false,
			data : $('#paymentBoleto').serialize(),
			url : contextPath + "/admin/payment/boleto/create",
			success : function(response) {
				
				closeModalLoading();
				
				if (response.success == true){
					$('#paymentBoleto')[0].reset();
					setTimeout(function(){ 
						showModalMessage(response.message);
					}, 250);
				} else {
					setTimeout(function(){ 
						showModalMessage(response.message);
					}, 250);
				}
				
			},
			error : function(response) {
				console.log(response);
			}
		});
	}

</script>
