package net.atos.mm.formation.tapestry.services;

import net.atos.mm.formation.tapestry.services.FutureDateValidator;

import org.apache.tapestry5.Validator;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.services.AssetSource;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;


public class AppModule {

	public static void contributeFieldValidatorSource(
			MappedConfiguration<String, Validator> configuration, final JavaScriptSupport renderSupport, 
			final AssetSource assetSource) {
	
	}
	
	public static void contributeComponentMessagesSource(
			OrderedConfiguration<String> configuration) {
	
	}
}
