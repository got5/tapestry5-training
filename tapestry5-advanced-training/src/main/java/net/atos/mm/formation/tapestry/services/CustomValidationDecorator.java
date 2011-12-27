package net.atos.mm.formation.tapestry.services;

import org.apache.tapestry5.Field;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.ValidationDecorator;
import org.apache.tapestry5.ValidationTracker;
import org.apache.tapestry5.dom.Element;
import org.apache.tapestry5.services.Environment;

/**
 * Change the rendering of the inputs when they are in error.
 */
public class CustomValidationDecorator implements ValidationDecorator{

	private final Environment environment; 

    private final MarkupWriter markupWriter; 

    public CustomValidationDecorator(Environment environment, 
    		MarkupWriter markupWriter) { 
            this.environment = environment; 
            this.markupWriter = markupWriter; 
    } 
    
    private boolean inError(Field field){
    	ValidationTracker tracker = getTracker();
    	return tracker.inError(field);
    }
    private ValidationTracker getTracker() { 
        return environment.peekRequired(ValidationTracker.class); 
    }   

	public void beforeLabel(Field field) {
				
	}

	public void insideLabel(Field field, Element labelElement) {
		
	}

	public void afterLabel(Field field) {
			
	}

	public void beforeField(Field field) {
			
	}

	public void insideField(Field field) {
		
		if(field==null) return;
		
		if(inError(field)) {
			markupWriter.getElement().addClassName("k-field-error");
			
		}
	}

	public void afterField(Field field) {
	}

}
