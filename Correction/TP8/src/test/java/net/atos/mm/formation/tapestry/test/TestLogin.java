package net.atos.mm.formation.tapestry.test;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import org.apache.tapestry5.dom.Document;
import org.apache.tapestry5.dom.Element;
import org.apache.tapestry5.test.PageTester;

public class TestLogin extends TestCase
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

	public void testLoginPage()
	{
		
		Document doc = pageTester.renderPage("login");

		Element loginForm = doc.getElementById("verifyForm");
		Map<String, String> fieldValues = new HashMap<String, String>();
		fieldValues.put("login", "tapestry");
		fieldValues.put("password", "pass");

		// Verify error handling
		doc = pageTester.submitForm(loginForm, fieldValues);
		assertNotNull(doc);
		assertTrue(doc.getRootElement().getChildMarkup().contains("Wrong password or user doesn't exist..."));

		// Verify we are on main page
		loginForm = doc.getElementById("verifyForm");
		fieldValues.put("password", "password");
		doc = pageTester.submitForm(loginForm, fieldValues);
		assertNotNull(doc);
		assertTrue(doc.getRootElement().getChildMarkup().contains("Welcome Mister") || doc.getRootElement().getChildMarkup().contains("Bienvenue Monsieur"));

	}

}
