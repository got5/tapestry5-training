Tapestry.Validator.future = function(field,message, flag){
	field.addValidator(function(value){
		
		var dateValue = new Date(value);
		var today = new Date();
		//add 100 years to fix a bug! getFullYear() return 19xx instead of 20xx
		dateValue.setYear(dateValue.getFullYear()+100);
		
		if(flag){
			if(dateValue.getYear() < today.getYear() ||
					(dateValue.getYear() == today.getYear() && dateValue.getMonth() < today.getMonth()) ||
					(dateValue.getYear() == today.getYear() && dateValue.getMonth() == today.getMonth() && dateValue.getDate() <= today.getDate() )){
				//Use tapestry js api to show error messages
				$(field).showValidationMessage(message);
			}
		}else {
			if(dateValue.getYear() > today.getYear() ||
					(dateValue.getYear() == today.getYear() && dateValue.getMonth() > today.getMonth()) ||
					(dateValue.getYear() == today.getYear() && dateValue.getMonth() == today.getMonth() && dateValue.getDate() >= today.getDate()) ){
				//Use tapestry js api to show error messages
				$(field).showValidationMessage(message);
			}
		}
	});
}
