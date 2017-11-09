<c:if test="${not empty userList}">
	
	<div class="box box-info">
		<div class="box-header ui-sortable-handle" style="cursor: move;">
			<i class="fa fa-building"></i>
	
			<h3 class="box-title"><fmt:message key="management.building.list.title"/></h3>
		</div>
		<div class="box-body">
			<table class="table">
				<thead>
					<tr>
						<th>#</th>
						<th></th>
						<th><fmt:message key="management.user.state.title" /></th>
						<th><fmt:message key="management.user.city.title" /></th>
						<th></th>
					</tr>
				</thead>
				<tbody>
	
					<c:forEach items="${userList}" var="user">
						<tr>
							<th scope="row">${user.id}</th>
							<td>
							
								<div class="box-body">
									<dl class="dl-horizontal">
										<dt><fmt:message key="management.user.createdDate.title"/></dt>
										<dd><b><fmt:formatDate type="both" dateStyle="long" timeStyle="long"  value="${user.createdDate}" /></b></dd>
										
										<dt><fmt:message key="management.user.lastUpdatedDate.title"/></dt>
										<dd><b><fmt:formatDate type="both" dateStyle="long" timeStyle="long"  value="${user.lastUpdatedDate}" /></b></dd>

										<dt><fmt:message key="management.user.name.title" /></dt>
										<dd>${user.displayName}</dd>
										
										<dt><fmt:message key="management.user.address.title" /></dt>
										<dd>${user.street}</dd>
										
										<dt><fmt:message key="management.user.neighborhood.title" /></dt>
										<dd>${user.neighborhood}</dd>
										
										<dt><fmt:message key="management.user.number.title" /></dt>
										<dd>${user.number}</dd>
										
										<dt><fmt:message key="management.user.country.title" /></dt>
										<dd>${user.country}</dd>
										
									</dl>
								</div>
							
							</td>
							<td>${user.state}</td>
							<td>${user.city}</td>
							<td style="width:7%;">
								<button type="button" class="btn wow tada btn-embossed btn-primary" data-toggle="modal" onclick="openModalEdituser('${user.id}')" data-target="#modal">
									<fmt:message key="management.user.edit.action" />
								</button>
							</td>
							<td style="width:7%;">
								<form role="form" action="${linkTo[AdminController].deleteuser}" method="POST">
									<input name="userObject.id" value="${user.id}" type="hidden" /> 
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
			function openModalEdituser(userId) {
				if (userId != null) {
					$.ajax({
						type : "GET",
						async : true,
						cache : false,
						url : contextPath + "/admin/user/find/"
								+ userId,
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
