package net.atos.mm.formation.tapestry.services;

import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.Validator;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.ioc.Resource;
import org.apache.tapestry5.ioc.annotations.Value;
import org.apache.tapestry5.services.AssetSource;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

public class AppModule {

	public static void contributeApplicationDefaults(
			MappedConfiguration<String, String> configuration) {

		configuration.add(SymbolConstants.START_PAGE_NAME, "Welcome");
		
		configuration.add(SymbolConstants.COMPRESS_WHITESPACE, "false");
		
		configuration.add(SymbolConstants.PRODUCTION_MODE, "false");
		
		configuration.add(SymbolConstants.SUPPORTED_LOCALES,"en,fr");
	}
	
	public static void contributeFieldValidatorSource(
			MappedConfiguration<String, Validator> configuration, 
      final JavaScriptSupport renderSupport, 
			final AssetSource assetSource) {
		configuration.add("future", new FutureDateValidator(renderSupport, assetSource));
	}
	
	public static void contributeComponentMessagesSource(
			OrderedConfiguration<Resource> configuration,
			@Value("net/atos/mm/formation/tapestry/services/FutureDateValidator.properties")
			Resource catalog) {
		configuration.add("future",catalog);
	}
}
