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

	public Class<Boolean> getConstraintType() {
		return Boolean.class;
	}

	public Class<Date> getValueType() {
		return Date.class;
	}

	public String getMessageKey() {
		return "";
	}

	public void validate(Field field, Boolean constraintValue,
			MessageFormatter formatter, Date value) throws ValidationException {
		
		// Implement the server validation here
		// Throw a ValidationException if validation constraints has not been respected
		// Use the MessageFormatter parameter to format the error message
		
	}

	public boolean isRequired() {
		return false;
	}

	public void render(Field field, Boolean constraintValue,
			MessageFormatter formatter, MarkupWriter writer,
			FormSupport formSupport) {
			// Implement the client validation here
	}
}
