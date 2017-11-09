<form class="form-horizontal" role="form" action="${linkTo[AdminController].updateBuilding}${buildingObject.id}" method="post">
	<div class="box-body">
	
		<div class="callout callout-info">
        	<h4><fmt:message key="management.building.edit.modal.title"/></h4>
        	<fmt:message key="management.building.edit.modal.description"/><b>${buildingObject.id}</b>
      	</div>
	
		<div class="form-group">
			<fmt:message var="namePlaceHolder" key='management.building.name.placeholder' />
			<label for="buildingEditDisplayName" class="col-sm-2 control-label">${namePlaceHolder}</label>
			
			<div class="col-sm-10">
				<input type="text" class="form-control" name="buildingObject.displayName" id="buildingEditDisplayName" required value="${buildingObject.displayName}"> 
			</div>
		</div>
					
		<%--<div class="form-group">
			<fmt:message var="countryPlaceHolder" key='management.building.country.placeholder' />
			<label for="buildingEditCountry" class="col-sm-2 control-label">${countryPlaceHolder}</label>
			
			<div class="col-sm-10">
				
			</div> 
		</div>
		 --%>
		<input type="hidden" class="form-control" name="buildingObject.country" id="buildingEditCountry" required value="${buildingObject.country}" >
		
		<div class="form-group">
			<fmt:message var="buildingEditZipCode" key='management.building.zipCode.placeholder' />
			<label for="buildingEditZipCode" class="col-sm-2 control-label">${buildingEditZipCode}</label>
			
			<div class="col-sm-10">
				<input type="text" class="form-control zipcode" name="buildingObject.zipcode" id="buildingEditZipCode" required value="${buildingObject.zipcode}">
			</div> 
		</div>
		
		<div class="form-group">
			<fmt:message var="cityPlaceHolder" key='management.building.city.placeholder' />
			<label for="buildingEditCity" class="col-sm-2 control-label">${cityPlaceHolder}</label>
			
			<div class="col-sm-10">
				<input type="text" class="form-control" name="buildingObject.city" id="buildingEditCity" required readonly value="${buildingObject.city}">
			</div> 
		</div>
		
		
		<div class="form-group">
			<fmt:message var="statePlaceHolder" key='management.building.state.placeholder' />
			<label for="buildingEditState" class="col-sm-2 control-label">${statePlaceHolder}</label>
			
			<div class="col-sm-10">
				<input type="text" class="form-control" name="buildingObject.state" id="buildingEditState" required readonly value="${buildingObject.state}">
			</div> 
		</div>
		
		<div class="form-group">
			<fmt:message var="neighborhoodPlaceHolder" key='management.building.neighborhood.placeholder' />
			<label for="buildingEditNeighborhood" class="col-sm-2 control-label">${neighborhoodPlaceHolder}</label>
			
			<div class="col-sm-10">
				<input type="text" class="form-control" name="buildingObject.neighborhood" id="buildingEditNeighborhood" required readonly value="${buildingObject.neighborhood}">
			</div> 
		</div>
		

		<div class="form-group">
			<fmt:message var="addressPlaceHolder" key='management.building.address.placeholder' />
			<label for="buildingEditStreet" class="col-sm-2 control-label">${addressPlaceHolder}</label>
			
			<div class="col-sm-10">
				<input type="text" class="form-control" name="buildingObject.street"  id="buildingEditStreet" disabled readonly value="${buildingObject.street}">
			</div> 
		</div>
		
		<div class="form-group">
			<fmt:message var="numberPlaceHolder" key='management.building.number.placeholder' />
			<label for="buildingEditNumber" class="col-sm-2 control-label">${numberPlaceHolder}</label>
			
			<div class="col-sm-10">
				<input type="text" class="form-control" name="buildingObject.number" id="buildingEditNumber" required value="${buildingObject.number}">
			</div> 
		</div>
		
		<input type="hidden" class="form-control" name="building.id" value="${buildingObject.id}" id="buildingId" required>
		<input type="hidden" name="_method" value="PUT"/>
		
	</div>

	<div class="box-footer">
		<button type="button" class="btn btn-default pull-left" data-dismiss="modal">Fechar</button>
		<input type="submit" value="Atualizar" class="btn wow tada btn-embossed btn-primary pull-right"/>
	</div>
</form>