package net.atos.mm.formation.tapestry.services;

import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.ioc.MappedConfiguration;

public class AppModule {

	public static void contributeApplicationDefaults(
			MappedConfiguration<String, String> configuration) {

		configuration.add(SymbolConstants.START_PAGE_NAME, "Welcome");
		
		configuration.add(SymbolConstants.COMPRESS_WHITESPACE, "false");

		configuration.add(SymbolConstants.PRODUCTION_MODE, "false");

	}
}
