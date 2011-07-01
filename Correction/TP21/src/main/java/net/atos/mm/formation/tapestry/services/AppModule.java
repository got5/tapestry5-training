package net.atos.mm.formation.tapestry.services;

import net.atos.mm.formation.tapestry.data.UserManager;

import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.Validator;
import org.apache.tapestry5.RenderSupport;
import org.apache.tapestry5.Validator;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.services.AssetSource;

public class AppModule {

	public static void contributeApplicationDefaults(
			MappedConfiguration<String, String> configuration) {

		// Set here the code modify the start page name to "Welcome"
		configuration.add("tapestry.start-page-name", "Welcome");
		configuration.add("tapestry.supported-locales", "en,fr");
		
		configuration.add(SymbolConstants.COMPRESS_WHITESPACE, "false");
		configuration.add(SymbolConstants.PRODUCTION_MODE, "false");
	}

	public IUserManager buildUserManager(){
		return new UserManager();
	}
	
	public static void contributeFieldValidatorSource(
			MappedConfiguration<String, Validator> configuration, final RenderSupport renderSupport, 
			final AssetSource assetSource) {
		configuration.add("future", new FutureDateValidator(renderSupport, assetSource));
	}
	
	public static void contributeValidationMessagesSource(
			OrderedConfiguration<String> configuration) {
		configuration.add("future","net/atos/mm/formation/tapestry/services/FutureDateValidator.properties");
	}
	
}
