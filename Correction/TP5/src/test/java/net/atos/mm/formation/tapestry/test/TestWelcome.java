package net.atos.mm.formation.tapestry.test;

import junit.framework.TestCase;

import org.apache.tapestry5.dom.Document;
import org.apache.tapestry5.test.PageTester;

public class TestWelcome extends TestCase
{

	private PageTester pageTester;

	@Override
	protected void setUp() throws Exception
	{
		String appPackage = "net.atos.mm.formation.tapestry";
		String appName = "app";
		pageTester = new PageTester(appPackage, appName, "src/main/webapp");
		super.setUp();
	}

	public void testWelcomePage()
	{
		
		Document doc = pageTester.renderPage("welcome");

		assertNotNull(doc);
		assertTrue(doc.getRootElement().getChildMarkup().contains("Bienvenue") || 
				   doc.getRootElement().getChildMarkup().contains("Welcome"));
	}

}
