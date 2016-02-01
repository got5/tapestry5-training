package net.atos.mm.formation.tapestry.services;

import net.atos.mm.formation.tapestry.data.UserManager;

import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.Validator;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.ioc.Resource;
import org.apache.tapestry5.ioc.annotations.Value;
import org.apache.tapestry5.services.AssetSource;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;
import org.got5.tapestry5.jquery.JQueryComponentConstants;
import org.got5.tapestry5.jquery.JQuerySymbolConstants;


public class AppModule {

	
	public static void contributeApplicationDefaults(MappedConfiguration<String, String> conf){
		
		conf.add(SymbolConstants.START_PAGE_NAME, "Welcome");
		conf.add(SymbolConstants.COMPRESS_WHITESPACE, "false");
		conf.add(SymbolConstants.PRODUCTION_MODE, "false");
		conf.add(SymbolConstants.SUPPORTED_LOCALES, "fr,en");
//		conf.add(JQuerySymbolConstants.SUPPRESS_PROTOTYPE, "false");
//		conf.add(JQuerySymbolConstants.JQUERY_ALIAS, "$1");
		
		
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
	
	public IUserManager buildUserManager(){
		return new UserManager();
	}
	
}
