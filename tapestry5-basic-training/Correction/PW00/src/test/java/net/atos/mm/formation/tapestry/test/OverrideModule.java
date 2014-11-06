package net.atos.mm.formation.tapestry.test;

import net.atos.kawwaportal.components.KawwaConstants;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.ioc.annotations.Symbol;
import org.apache.tapestry5.services.MarkupRenderer;
import org.apache.tapestry5.services.MarkupRendererFilter;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

public class OverrideModule {
    public  void contributeMarkupRenderer(
            OrderedConfiguration<MarkupRendererFilter> configuration,
            final @Symbol(KawwaConstants.KAWWA_INCLUDE_STACK) Boolean flag, final JavaScriptSupport js){

        configuration.override("injectKawwaStylesheet", new MarkupRendererFilter() {
            public void renderMarkup(MarkupWriter writer, MarkupRenderer renderer) {
                if(flag) {
                    js.importStack(KawwaConstants.STACK_ID);
                }


                renderer.renderMarkup(writer);            }
        });

    }
}
