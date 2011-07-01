package net.atos.mm.formation.tapestry.base;

import org.apache.tapestry5.Asset;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.RenderSupport;
import org.apache.tapestry5.annotations.AfterRenderTemplate;
import org.apache.tapestry5.annotations.BeforeRenderTemplate;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Path;
import org.apache.tapestry5.annotations.SupportsInformalParameters;
import org.apache.tapestry5.ioc.annotations.Inject;


@SupportsInformalParameters
public class BaseComponent {

	/**
	 * The element to render. If not null, then the loop will render the
	 * indicated element around its body (on each pass through the loop). The
	 * default is derived from the component template.
	 */
	@Parameter(value = "prop:componentResources.elementName", defaultPrefix = "literal")
	private String elementName;
	
	@Inject
	@Path("component.css")
	private Asset flagStyle;

	@Environmental
	private RenderSupport support;

	@Inject
	private ComponentResources resources;
	
	/**
	 * Used to add the component stylesheet
	 */
	@BeginRender
	public void addBaseCssFile(MarkupWriter writer) {
		support.addStylesheetLink(flagStyle, null);
	}

	@BeforeRenderTemplate
	public void renderElementName(MarkupWriter writer) {
		if (elementName != null) {
			writer.element(elementName);
			resources.renderInformalParameters(writer);
		}
	}

	@AfterRenderTemplate
	public void afterRenderElementName(MarkupWriter writer) {
		if (elementName != null)
			writer.end();
	}
	
}
