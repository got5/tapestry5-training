package net.atos.mm.formation.tapestry.components;

import java.util.Locale;

import net.atos.mm.formation.tapestry.base.BaseComponent;

import org.apache.tapestry5.Asset;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.Symbol;
import org.apache.tapestry5.services.AssetSource;
import org.apache.tapestry5.services.PersistentLocale;
import org.slf4j.Logger;

public class Country extends BaseComponent{

	@Inject
	private PersistentLocale persistentLocaleService;
	
	@Inject
	@Symbol("tapestry.supported-locales")
	private String supportedLocales;
	
	@Inject
	private AssetSource assetSource;
	
	@Property
	private String locale;
	
	@Inject
	private Logger logger;
	
	public Asset getFlag(){
		return assetSource.getAsset(null, "context:static/images/flag.png", new Locale(locale));
	}
	
	@OnEvent("action")
	public void changeLocale(String country){
		
		// Implement here the mechanism to change the user local
		logger.info("Country Component : Modify local to "+country);
		if("fr".equals(country) ) {
			persistentLocaleService.set(Locale.FRENCH);	
		}else{
			if("en".equals(country)) {
				persistentLocaleService.set(Locale.ENGLISH);
			}else{
				logger.warn("Country Component : Locale not supported");
			}
		}
	}

	public String[] getSupportedLocales() {
		return supportedLocales.split(",");
	}

}
