<div class="box box-info">
	
	<div class="box-header with-border" style="cursor: move;">
		<i class="fa fa-user"></i>

		<h3 class="box-title"><fmt:message key="management.user.create.title"/></h3>
	</div>
	
	
	<form id="createUserForm" role="form" class="form" action="${linkTo[AdminController].createNewUser}" method="post">
		<div class="box-body">
					
			<div class="form-group col-sm-12 input_fields_wrap">
			
				<fmt:message var="appartmentPlaceHolder" key='management.user.appartment.placeholder' />
				<label for="userAppartment" class="control-label">${appartmentPlaceHolder}</label>
					
				<div class="input-group input-group-sm field_0 field_auto">
						 
					<span class="input-group-sm input-group-btn">
						<button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown" aria-expanded="true">
						<fmt:message key="management.user.appartment.actions"/>&nbsp;
             			<span class="fa fa-caret-down"></span></button>
						<ul class="dropdown-menu">
							<li><a href="#" data-toggle="modal" onclick="openModalCreateAppartment();" data-target="#modalMessage"><fmt:message key="management.user.appartment.create.action" /></a></li>
							<li><a href="#" class="add_field_appartment_button"><fmt:message key="management.user.appartment.link.another"/></a></li>
						</ul>
					</span>
					
					<select class="input-group-sm form-control" name="userObject.appartmentList[0].id" >
						<c:forEach items="${appartmentList}" var="appartment">
							<option value="${appartment.id}">${appartment.building.displayName}&nbsp;-&nbsp;${appartment.number}</option>
						</c:forEach>
					</select>
				</div>
				
			</div>
			
			<div class="input-group-sm form-group col-sm-6">
				<fmt:message var="namePlaceHolder" key='management.user.name.placeholder' />
				<label for="userDisplayName" class="control-label">${namePlaceHolder}</label>
				
				<input type="text" class="form-control" autocomplete="off" name="userObject.displayName" id="userDisplayName" placeholder="${namePlaceHolder}" required> 
			</div>
						
			<div class="input-group-sm form-group col-sm-6">
				<fmt:message var="emailPlaceHolder" key='management.user.email.placeholder' />
				<label for="userEmail" class="control-label">${emailPlaceHolder}</label>
				
				<input type="text" class="form-control email-mask" autocomplete="off" name="userObject.email" id="userEmail" placeholder="${emailPlaceHolder}" required>
			</div>
			
			<div class="input-group-sm form-group col-sm-4">
				<fmt:message var="accountTypePlaceHolder" key='management.user.accountType.placeholder' />
				<label for="userAccountType" class="control-label">${accountTypePlaceHolder}</label>
				<input type="text" class="form-control" autocomplete="off" name="userObject.accountType" id="userAccountType" placeholder="${accountTypePlaceHolder}" required>
			</div>
			
			<div class="input-group-sm form-group col-sm-4">
				<fmt:message var="birthdatePlaceHolder" key='management.user.birthdate.placeholder' />
				<label for="userBirthdate" class="control-label">${birthdatePlaceHolder}</label>
				<input type="text" class="form-control" autocomplete="off" data-inputmask="'mask': '99/99/9999'" data-with-mask-submit id="userBirthdate" name="userObject.birthdate"  required>
			</div>
			
			<div class="input-group-sm form-group col-sm-4">
				<fmt:message var="phonePlaceHolder" key='management.user.phone.placeholder' />
				<label for="userPhone" class="control-label">${phonePlaceHolder}</label>
				<input type="text" class="form-control" autocomplete="off" data-inputmask="'mask': '(99) 9 9999-9999'" data-without-mask-submit  name="userObject.phone" id="userPhone" placeholder="${phonePlaceHolder}" required>
			</div>

			<div class="input-group-sm form-group col-sm-4">
				<fmt:message var="cpfCnpjPlaceHolder" key='management.user.cpf.cnpj.placeholder' />
				<label for="userCPFCNPJ" class="control-label">${cpfCnpjPlaceHolder}</label>
				<input type="text" class="form-control cpfcnpj" autocomplete="off" data-without-mask-submit name="userObject.cpf" id="userCPFCNPJ" placeholder="${cpfPlaceHolder}">
			</div>
		
			<div class="input-group-sm form-group col-sm-4">
				<fmt:message var="passwordPlaceHolder" key='management.user.password.placeholder' />
				<label for="userPassword" class="control-label">${passwordPlaceHolder}</label>
				<input type="text" class="form-control" autocomplete="off" name="userObject.password" id="userPassword" placeholder="${passwordPlaceHolder}" required>
			</div>
			
	    </div> 
	    
		<div class="box-footer">
			<div class="col-xs-12">
				<fmt:message var="createUserAction" key="management.user.create.action"/>
				<input type="button" name="submit" id="submit" onclick="createNewUser(); return false;" value="${createUserAction}" class="btn wow tada btn-embossed btn-primary pull-right">
			</div>
		</div>
	    
	</form>
		
	<script>
		$(document).ready(function() {
			generateNewAppartmentField();
		});
		

		function generateNewAppartmentField(){
		    var max_fields      = 10; //maximum input boxes allowed
		    var wrapper         = $(".input_fields_wrap"); //Fields wrapper
		    var add_button      = $(".add_field_appartment_button"); //Add button ID
		    
		    $(add_button).click(function(e){ //on add input button click
		        e.preventDefault();
		    
		    	var size = $(".field_auto").size();
		    	if (size < max_fields){
		    		for (i = 0; i < max_fields ; i++){
		    			
		    			var count = $(".field_" + i).size();
		    			if (count == 0){
		    				
		    				$(wrapper).append('<p></p><div class="input-group input-group-sm field_'+ i +' field_auto"><span class="input-group-btn"><button type="button" class="btn btn-danger remove_field">&nbsp;-</button></span><select class="form-control" name="userObject.appartmentList['+ i +'].id"><c:forEach items="${appartmentList}" var="appartment"><option value="${appartment.id}">${appartment.building.displayName} - ${appartment.number}</option></c:forEach></select></div>');
		    				
		    				break;
		    			}
		    		}
		    	}
		    
		    });
		    
		    $(wrapper).on("click",".remove_field", function(e){ //user click on remove text
		        e.preventDefault(); 
		    	$(this).parent().parent().remove(); x--;
		    })
		}
	</script>
	
</div>
