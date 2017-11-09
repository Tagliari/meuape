<div class="box box-info">

	<div class="box-header with-border" style="cursor: move;">
		<i class="fa fa-building"></i>

		<h3 class="box-title"><fmt:message key="management.building.create.title"/></h3>
	</div>
	
	<form role="form" class="form" action="${linkTo[AdminController].createNewBuilding}" method="post">
		<div class="box-body">
			<div class="input-group-sm form-group col-xs-12">
				<fmt:message var="namePlaceHolder" key='management.building.name.placeholder' />
				<label for="buildingDisplayName" >${namePlaceHolder}</label>
				<input type="text" class="form-control" autocomplete="off" name="buildingObject.displayName" id="buildingDisplayName" required> 
			</div>
						
			<%--<div class="input-group-sm form-group col-xs-2">
				<fmt:message var="countryPlaceHolder" key='management.building.country.placeholder' />
				<label for="buildingCountry" >${countryPlaceHolder}</label>
				
			</div>
			 --%>
			 <input type="hidden" class="form-control" autocomplete="off"  name="buildingObject.country" id="buildingCountry" required value="brasil">

			<div class="input-group-sm form-group col-xs-2">
				<fmt:message var="zipCodePlaceHolder" key='management.building.zipCode.placeholder' />
				<label for="buildingZipCode" >${zipCodePlaceHolder}</label>
				<input type="text" class="form-control zipcode" autocomplete="off" name="buildingObject.zipcode" id="buildingZipCode" required>
			</div>
			
			<div class="input-group-sm form-group col-xs-1">
				<fmt:message var="statePlaceHolder" key='management.building.state.placeholder' />
				<label for="buildingState" >${statePlaceHolder}</label>
				<input type="text" class="form-control" autocomplete="off" name="buildingObject.state" id="buildingState" readonly required>
			</div>
			
			<div class="input-group-sm form-group col-xs-4">
				<fmt:message var="cityPlaceHolder" key='management.building.city.placeholder' />
				<label for="buildingCity" >${cityPlaceHolder}</label>
				<input type="text" class="form-control" autocomplete="off" name="buildingObject.city" id="buildingCity" readonly required>
			</div>
			
			<div class="input-group-sm form-group col-xs-5">
				<fmt:message var="neighborhoodPlaceHolder" key='management.building.neighborhood.placeholder' />
				<label for="buildingNeighborhood" >${neighborhoodPlaceHolder}</label>
				<input type="text" class="form-control" autocomplete="off" name="buildingObject.neighborhood" id="buildingNeighborhood" readonly required>
			</div>

			<div class="input-group-sm form-group col-xs-8">
				<fmt:message var="addressPlaceHolder" key='management.building.address.placeholder' />
				<label for="buildingStreet" >${addressPlaceHolder}</label>
				<input type="text" class="form-control" autocomplete="off" name="buildingObject.street"  id="buildingStreet" readonly required>
			</div>
						
			<div class="input-group-sm form-group col-xs-4">
				<fmt:message var="numberPlaceHolder" key='management.building.number.placeholder' />
				<label for="buildingNumber" >${numberPlaceHolder}</label>
				<input type="text" class="form-control" autocomplete="off" name="buildingObject.number" id="buildingNumber" required>
			</div>
		</div>
		<div class="box-footer">
			<div class="col-xs-12">
				<input type="submit" name="submit" id="submit" value="Adicionar" onclick="showModalLoading();" class="btn wow tada btn-embossed btn-primary pull-right">
			</div>
		</div>	  
	</form>
	
	<script>
		$(document).ready(function(){
			updateZipCode();
		});
	</script>
</div>
