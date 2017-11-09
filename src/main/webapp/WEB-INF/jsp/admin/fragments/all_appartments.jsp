<c:if test="${not empty appartmentList}">
	
	<div class="box box-info">
		<div class="box-header ui-sortable-handle" style="cursor: move;">
			<i class="fa fa-appartment"></i>
	
			<h3 class="box-title"><fmt:message key="management.appartment.list.title"/></h3>
		</div>
		<div class="box-body">
			<table class="table">
				<thead>
					<tr>
						<th>#</th>
						<th><fmt:message key="management.appartment.name.title" /></th>
						<th><fmt:message key="management.appartment.state.title" /></th>
						<th><fmt:message key="management.appartment.city.title" /></th>
						<th></th>
						<th></th>
					</tr>
				</thead>
				<tbody>
	
					<c:forEach items="${appartmentList}" var="appartment">
						<tr>
							<th scope="row">${appartment.id}</th>
							<td>${appartment.displayName}</td>
							<td>${appartment.state}</td>
							<td>${appartment.city}</td>
							<td style="width:7%;">
								<button type="button" class="btn wow tada btn-embossed btn-primary" data-toggle="modal" onclick="openModalEditAppartment('${appartment.id}')" data-target="#modal">
									<fmt:message key="management.appartment.edit.action" />
								</button>
							</td>
							<td style="width:7%;">
								<form role="form" action="${linkTo[AdminController].deleteAppartment}" method="POST">
									<input name="appartmentObject.id" value="${appartment.id}" type="hidden" /> 
									<input type="hidden" name="_method" value="DELETE"/>
									<input type="submit" value="Excluir" class="btn wow tada btn-embossed btn-danger" />
								</form>
							</td>
						</tr>
					</c:forEach>
	
				</tbody>
			</table>
		</div>
		
		<script>
			function openModalEditAppartment(appartmentId) {
				console.log(appartmentId);
				if (appartmentId != null) {
					$.ajax({
						type : "GET",
						async : true,
						cache : false,
						url : contextPath + "/admin/appartment/find/" + appartmentId,
						success : function(response) {
							$('#modalContent').html(response);
						},
						error : function(response) {
							console.log(response);
						}
					});
				}
			}
		</script>
	</div>
</c:if>
