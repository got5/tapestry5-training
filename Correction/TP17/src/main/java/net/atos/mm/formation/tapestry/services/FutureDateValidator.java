package net.atos.mm.formation.tapestry.services;

import java.util.Date;

import org.apache.tapestry5.Asset;
import org.apache.tapestry5.Field;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.RenderSupport;
import org.apache.tapestry5.ValidationException;
import org.apache.tapestry5.Validator;
import org.apache.tapestry5.ioc.MessageFormatter;
import org.apache.tapestry5.services.AssetSource;
import org.apache.tapestry5.services.FormSupport;

public class FutureDateValidator implements Validator<Boolean, Date>{

	final private RenderSupport renderSupport;

	private Asset javascript; 
	
	public FutureDateValidator(RenderSupport renderSupport,AssetSource assetSource){ 
             
			this.renderSupport = renderSupport; 
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
		if(constraintValue && today.after(value))
			throw new ValidationException(formatter.format());
		else if(!constraintValue && today.before(value))
			throw new ValidationException(formatter.format());
		
	}

	public boolean isRequired() {
		return false;
	}

	public void render(Field field, Boolean constraintValue,
			MessageFormatter formatter, MarkupWriter writer,
			FormSupport formSupport) {
		
		formSupport.addValidation(field, "future", formatter.format(field.getLabel()), constraintValue);
		renderSupport.addScriptLink(javascript); 
	}
}
