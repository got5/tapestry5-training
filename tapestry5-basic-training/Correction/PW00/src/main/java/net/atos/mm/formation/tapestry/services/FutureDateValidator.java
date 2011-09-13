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
		//TODO
		return null;
	}

	public Class<Date> getValueType() {
		//TODO
		return null;
	}

	public String getMessageKey() {
		//TODO
		return null;
	}

	public void validate(Field field, Boolean constraintValue,
			MessageFormatter formatter, Date value) throws ValidationException {
		//TODO
	}

	public boolean isRequired() {
		//TODO
		return null;
	}

	public void render(Field field, Boolean constraintValue,
			MessageFormatter formatter, MarkupWriter writer,
			FormSupport formSupport) {
		
		//formSupport.addValidation(field, "future", formatter.format(field.getLabel()), constraintValue);
		//javaScriptSupport.importJavaScriptLibrary(javascript);
	}
}
