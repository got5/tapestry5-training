package net.atos.mm.formation.tapestry.services;

import java.io.IOException;

import net.atos.mm.formation.tapestry.services.impl.SecurityManagerImpl;

import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.ValidationDecorator;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.services.AssetSource;
import org.apache.tapestry5.services.Environment;
import org.apache.tapestry5.services.MarkupRenderer;
import org.apache.tapestry5.services.MarkupRendererFilter;
import org.apache.tapestry5.services.PageRenderRequestFilter;
import org.apache.tapestry5.services.PageRenderRequestHandler;
import org.apache.tapestry5.services.PageRenderRequestParameters;
import org.apache.tapestry5.services.PersistentLocale;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.Response;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;
import org.apache.tapestry5.services.javascript.StylesheetLink;

/**
 * This module is automatically included as part of the Tapestry IoC Registry, it's a good place to
 * configure and extend Tapestry, or to place your own service definitions.
 */
public class AppModule
{
    public static void contributeApplicationDefaults(
            MappedConfiguration<String, String> configuration)
    {
    
    	configuration.add(SymbolConstants.SUPPORTED_LOCALES, "en,fr");	
    	configuration.add(SymbolConstants.PRODUCTION_MODE, "false");
        configuration.add(SymbolConstants.APPLICATION_VERSION, "1.0-SNAPSHOT");
    }
    
	public static void bind(ServiceBinder binder) {
		binder.bind(SecurityManager.class, SecurityManagerImpl.class);
	}
	
    public void contributePageRenderRequestHandler(OrderedConfiguration<PageRenderRequestFilter> configuration,
            final SecurityManager securityManager, final Response response, final Request request)
	{
		PageRenderRequestFilter filter = new PageRenderRequestFilter()
		{ 
			public void handle(PageRenderRequestParameters parameters, PageRenderRequestHandler handler) throws IOException {
				
				if (!securityManager.isUserAuthenticated(parameters)){
					String url = response.encodeRedirectURL(request.getContextPath()+"/login");
					response.sendRedirect(url);
					return ;
				}
				handler.handle(parameters);	
			}
		};
		configuration.add("Authentification", filter);
	}
    
    /**
     * Todo JavaDoc
     */
    public void contributeMarkupRenderer(OrderedConfiguration<MarkupRendererFilter> configuration, 
    		final JavaScriptSupport javaScriptSupport, 
    		final AssetSource assetSource, 
    		final PersistentLocale locale,  
    		final Environment environment)
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
        MarkupRendererFilter validationDecorator = new MarkupRendererFilter() { 

            public void renderMarkup(MarkupWriter writer, MarkupRenderer renderer) 
            { 
                    ValidationDecorator decorator = new CustomValidationDecorator(environment, writer); 

                    environment.push(ValidationDecorator.class, decorator); 
            
                    renderer.renderMarkup(writer); 
                    
                    environment.pop(ValidationDecorator.class); 
                            
            } 
    	}; 
    	
        MarkupRendererFilter addBootstrap = new MarkupRendererFilter()
        {
            public void renderMarkup(MarkupWriter writer, MarkupRenderer renderer)
            {
                
                javaScriptSupport.importStylesheet(new StylesheetLink("http://twitter.github.com/bootstrap/1.3.0/bootstrap.min.css"));
                
            	renderer.renderMarkup(writer);

            }
        };
        
         
        configuration.add("Bootstrap", addBootstrap, "after:JavaScriptSupport");
        configuration.add("defaultAssets", addDefaultAssets, "after:Bootstrap");
        configuration.add("DefaultValidationDecorator", validationDecorator);
    }
}
