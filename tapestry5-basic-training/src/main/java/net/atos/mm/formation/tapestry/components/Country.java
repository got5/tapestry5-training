package net.atos.mm.formation.tapestry.components;

import net.atos.mm.formation.tapestry.base.BaseComponent;

import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.slf4j.Logger;

public class Country extends BaseComponent {
	
	@Inject
	private Logger logger;
	
	@OnEvent("action")
	public void changeLocale(String country) {

		// Implement here the mechanism to change the user local
		logger.info("Country Component : Modify local to " + country);

	}

}
