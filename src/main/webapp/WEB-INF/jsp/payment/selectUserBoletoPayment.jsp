<div class="form-group col-sm-12">
			
	<fmt:message var="paymentAppartmentPlaceHolder" key='management.payment.appartment.placeholder' />
	<label for="paymentAppartment" class="control-label">${paymentAppartmentPlaceHolder}</label>
		
	<div class="input-group input-group-sm col-sm-12">
			 
		<select class="form-control" name="pg.appartment.id" id="paymentAppartment">
			<c:forEach items="${appartmentList}" var="appartment">
				<option value="${appartment.id}">${appartment.building.displayName}&nbsp;-&nbsp;${appartment.number}</option>
			</c:forEach>
		</select>
	</div>
	
</div>

<div class="form-group col-sm-12">
			
	<fmt:message var="paymentExpirationDatePlaceHolder" key='management.payment.expiration.date.placeholder' />
	<label for="paymentExpirationDate" class="control-label">${paymentExpirationDatePlaceHolder}</label>
		
	<div class="input-group input-group-sm col-sm-12">
		<input type="text" class="form-control col-sm-12 datepicker" data-inputmask="'mask': '99/99/9999'" data-without-mask-submit autocomplete="off" id="paymentExpirationDate" name="pg.expirationDate" placeholder="${paymentExpirationDate}" required>
	</div>
	
</div>

<div class="form-group col-sm-12">
			
	<fmt:message var="paymentObservationsPlaceHolder" key='management.payment.observations.placeholder' />
	<label for="paymentObservations" class="control-label">${paymentObservationsPlaceHolder}</label>
		
	<div class="input-group input-group-sm col-sm-12">
		<input type="text" class="form-control col-sm-12" autocomplete="off" id="paymentObservation" name="pg.observations" placeholder="${paymentObservations}">
	</div>
	
</div>

<div class="input_fields_wrap_payment_item">

	<div class="field_0 field_auto">
		<div class="form-group col-sm-3">
		
			<fmt:message var="paymentItemNameHolder" key='management.payment.item.name.placeholder' />
			<label class="control-label">${paymentItemNameHolder}</label>
				
			<div class="input-group input-group-sm">
					 
				 <span class="input-group-btn">
					<button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown" aria-expanded="true">
					<fmt:message key="management.payment.item.actions"/>&nbsp;
					
					<span class="fa fa-caret-down"></span></button>
					<ul class="dropdown-menu">
						<li><a href="javascript:void(0);" class="add_field_payment_item_button"><fmt:message key="management.payment.item.link.another"/></a></li>
					</ul>
				</span>
				
				
				<input type="text" class="form-control col-sm-6" autocomplete="off" name="pg.itemList[0].displayName" required>
				
			</div>
		</div>
		<div class="input-group-sm form-group col-sm-5">
			<fmt:message var="paymentItemDescriptionPlaceHolder" key='management.payment.item.description.placeholder' />
			<label class="control-label">${paymentItemDescriptionPlaceHolder}</label>
			<input type="text" class="form-control col-sm-5" autocomplete="off" name="pg.itemList[0].description" required>
		</div>
		<div class="input-group-sm form-group col-sm-2">
			<fmt:message var="paymentItemListPriceHolder" key='management.payment.item.listprice.placeholder' />
			<label class="control-label">${paymentItemListPriceHolder}</label>
			<input type="text" class="form-control col-sm-2 decimal-mask" autocomplete="off" name="pg.itemList[0].listPrice" required>
		</div>
		<div class="input-group-sm form-group col-sm-2">
			<fmt:message var="paymentItemSalePriceHolder" key='management.payment.item.saleprice.placeholder' />
			<label class="control-label">${paymentItemSalePriceHolder}</label>
			<input type="text" class="form-control col-sm-2 decimal-mask" autocomplete="off" name="pg.itemList[0].salePrice" required>
		</div>
	</div>
</div>

<div class="input-group-sm form-group col-xs-12">
	<fmt:message var="paymentAmountHolder" key='management.payment.amount.final.placeholder' />
	<label class="control-label">${paymentAmountHolder}</label>
	<input type="text" class="form-control decimal-mask" autocomplete="off" name="pg.amount" id="paymentAmountObject" required> 
</div>

<div class="form-group col-xs-12">
	<input type="button" name="submit" onclick="submitBoletoPayment();" value="Gerar Pagamento" class="btn wow tada btn-embossed btn-danger pull-right">
</div>	

