package net.atos.mm.formation.tapestry.services;

import java.util.Date;

import org.apache.tapestry5.Asset;
import org.apache.tapestry5.Field;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.ValidationException;
import org.apache.tapestry5.Validator;
import org.apache.tapestry5.ioc.MessageFormatter;
import org.apache.tapestry5.services.AssetSource;
import org.apache.tapestry5.services.FormSupport;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

public class FutureDateValidator implements Validator<Boolean, Date>{

	final private JavaScriptSupport javaScriptSupport;

	private Asset javascript; 
	
	public FutureDateValidator(JavaScriptSupport renderSupport,AssetSource assetSource){ 
             
			this.javaScriptSupport = renderSupport; 
			javascript = assetSource.getContextAsset("static/js/validator.js", null);
    } 
	
	public Class<Boolean> getConstraintType() {
		return Boolean.class;
	}

	public Class<Date> getValueType() {
		return Date.class;
	}

	public String getMessageKey() {
		return "not-future-date";
	}

	public void validate(Field field, Boolean constraintValue,
			MessageFormatter formatter, Date value) throws ValidationException {
		
		Date today = new Date();
		
		if(constraintValue && today.after(value)){
			throw new ValidationException(formatter.format("futur"));
		}
		else if(!constraintValue && today.before(value)){
			throw new ValidationException(formatter.format("passé"));
		}
		
	}

	public boolean isRequired() {
		return false;
	}

	public void render(Field field, Boolean constraintValue,
			MessageFormatter formatter, MarkupWriter writer,
			FormSupport formSupport) {
		// Implement the client validation here
		String keyword= constraintValue? "futur":"passé";
		String errorMessage= formatter.format(keyword);
		formSupport.addValidation(field, "future", errorMessage, constraintValue);
		javaScriptSupport.importJavaScriptLibrary(javascript);
	}
}
