package net.atos.mm.formation.tapestry.services;

import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.ioc.MappedConfiguration;


public class AppModule {

	
	public static void contributeApplicationDefaults(MappedConfiguration<String, String> conf){
		
		conf.add(SymbolConstants.START_PAGE_NAME, "Welcome");
		conf.add(SymbolConstants.COMPRESS_WHITESPACE, "false");
		conf.add(SymbolConstants.PRODUCTION_MODE, "false");
		conf.add(SymbolConstants.SUPPORTED_LOCALES, "fr,en");
		
	}
}
