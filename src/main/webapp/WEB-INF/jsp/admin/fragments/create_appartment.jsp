<div class="box box-info">
	<div class="box-header ui-sortable-handle" style="cursor: move;">
		<button type="button" class="close" data-dismiss="modal">&times;</button>
		<i class="fa fa-appartment"></i>

		<h3 class="box-title"><fmt:message key="management.appartment.create.title"/></h3>
	</div>

	<c:choose>
		<c:when test="${not empty buildingList}">
	
			<form role="form" class="form" action="${linkTo[AdminController].createNewAppartment}" method="post">
				
				<div class="box-body">
					
					<div class="input-group-sm form-group col-xs-12">
					
						<fmt:message var="buildingPlaceHolder" key='management.appartment.building.placeholder' />
						<label for="appartmentBuilding" class="control-label">${buildingPlaceHolder}</label>
						
						<select class="form-control" name="appartmentObject.building.id" id="appartmentBuilding">
							<c:forEach items="${buildingList}" var="building">
								<option value="${building.id}">${building.displayName}</option>
							</c:forEach>
						</select>
					</div>
			
					<div class="input-group-sm form-group col-xs-6">
						<fmt:message var="numberPlaceHolder" key='management.appartment.number.placeholder' />
						<label for="appartmentNumber" class="control-label">${numberPlaceHolder}</label>
						<input type="text" class="form-control" autocomplete="off" name="appartmentObject.number" id="appartmentNumber" placeholder="${numberPlaceHolder}" required> 
					</div>
					
					<div class="input-group-sm form-group col-xs-6">
						<fmt:message var="floorPlaceHolder" key='management.appartment.floor.placeholder' />
						<label for="appartmentFloor" class="control-label">${floorPlaceHolder}</label>
						<input type="text" class="form-control" autocomplete="off" name="appartmentObject.floor" id="appartmentFloor" placeholder="${floorPlaceHolder}" required>
					</div>
				</div>	
				
				<div class="box-footer">
					<div class="col-xs-12">
						<input type="submit" name="submit" id="submit" value="Criar" onclick="showModalLoading();" class="btn wow tada btn-embossed btn-primary pull-right">
					</div>
				</div>
				
			</form>
		</c:when>
		<c:otherwise>
			<fmt:message key='management.appartment.building.message.notfound' />
		</c:otherwise>
	</c:choose>
	
</div>
