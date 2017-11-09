<c:if test="${not empty buildingList}">
	
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
						<th><fmt:message key="management.building.state.title" /></th>
						<th><fmt:message key="management.building.city.title" /></th>
						<th></th>
					</tr>
				</thead>
				<tbody>
	
					<c:forEach items="${buildingList}" var="building">
						<tr>
							<th scope="row">${building.id}</th>
							<td>
							
								<div class="box-body">
									<dl class="dl-horizontal">
										<dt><fmt:message key="management.building.createdDate.title"/></dt>
										<dd><b><fmt:formatDate type="both" dateStyle="long" timeStyle="long"  value="${building.createdDate}" /></b></dd>
										
										<dt><fmt:message key="management.building.lastUpdatedDate.title"/></dt>
										<dd><b><fmt:formatDate type="both" dateStyle="long" timeStyle="long"  value="${building.lastUpdatedDate}" /></b></dd>

										<dt><fmt:message key="management.building.name.title" /></dt>
										<dd>${building.displayName}</dd>
										
										<dt><fmt:message key="management.building.address.title" /></dt>
										<dd>${building.street}</dd>
										
										<dt><fmt:message key="management.building.neighborhood.title" /></dt>
										<dd>${building.neighborhood}</dd>
										
										<dt><fmt:message key="management.building.number.title" /></dt>
										<dd>${building.number}</dd>
										
										<dt><fmt:message key="management.building.country.title" /></dt>
										<dd>${building.country}</dd>
										
									</dl>
								</div>
							
							</td>
							<td>${building.state}</td>
							<td>${building.city}</td>
							<td style="width:7%;">
								<button type="button" class="btn wow tada btn-embossed btn-primary" data-toggle="modal" onclick="openModalEditBuilding('${building.id}')" data-target="#modal">
									<fmt:message key="management.building.edit.action" />
								</button>
							</td>
							<td style="width:7%;">
								<form role="form" action="${linkTo[AdminController].deleteBuilding}" method="POST">
									<input name="buildingObject.id" value="${building.id}" type="hidden" /> 
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

		</script>
	</div>
</c:if>
