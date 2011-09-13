package net.atos.mm.formation.tapestry.services;

import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.services.AssetSource;
import org.apache.tapestry5.services.MarkupRenderer;
import org.apache.tapestry5.services.MarkupRendererFilter;
import org.apache.tapestry5.services.PersistentLocale;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

/**
 * This module is automatically included as part of the Tapestry IoC Registry, it's a good place to
 * configure and extend Tapestry, or to place your own service definitions.
 */
public class AppModule
{
    public static void contributeApplicationDefaults(
            MappedConfiguration<String, String> configuration)
    {
    
    	configuration.add(SymbolConstants.SUPPORTED_LOCALES, "en");

        configuration.add(SymbolConstants.PRODUCTION_MODE, "false");

        configuration.add(SymbolConstants.APPLICATION_VERSION, "1.0-SNAPSHOT");
    }
    
    public void contributeMarkupRenderer(OrderedConfiguration<MarkupRendererFilter> configuration, 
    		final JavaScriptSupport javaScriptSupport, 
    		final AssetSource assetSource, 
    		final PersistentLocale locale)
    {
    	MarkupRendererFilter addDefaultAssets = new MarkupRendererFilter()
        {
            public void renderMarkup(MarkupWriter writer, MarkupRenderer renderer)
            {
                
                javaScriptSupport.importJavaScriptLibrary(assetSource.getContextAsset("static/js/formation.js", locale.get()));
                
                javaScriptSupport.importStylesheet(assetSource.getContextAsset("static/css/formation.css", locale.get()));
                
            	renderer.renderMarkup(writer);

            }
        };
        
        configuration.add("defaultAssets", addDefaultAssets, "after:JavaScriptSupport");
    }
    
}
