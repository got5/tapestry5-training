Tapestry.Validator.future = function(field,message, flag){
	field.addValidator(function(value){
		
		var today = new Date();
		if (flag && Date.parse(today) > Date.parse(value)) 
			throw message;
		if (!flag && Date.parse(today) < Date.parse(value)) 
			throw message;
		
	});
}
