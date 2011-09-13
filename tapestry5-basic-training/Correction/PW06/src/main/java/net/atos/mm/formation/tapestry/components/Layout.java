package net.atos.mm.formation.tapestry.components;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;



public class Layout {
	
	@Parameter(required=true, defaultPrefix = BindingConstants.LITERAL)
	@Property
	private String title;
	
}
