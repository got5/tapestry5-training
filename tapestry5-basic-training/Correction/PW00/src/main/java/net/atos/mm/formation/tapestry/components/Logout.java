package net.atos.mm.formation.tapestry.components;

import net.atos.mm.formation.tapestry.base.BaseComponent;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Service;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.Session;

public class Logout extends BaseComponent {

	/** Logout event that is trigger by this component */
	public static final String LOGOUT = "logout";

	@Inject
	@Service("Request")
	private Request request;

	@Inject
	private ComponentResources resources;

	@Parameter(required = true)
	private String indexPage;

	@OnEvent("action")
	public String logout(Object[] context) {

		// Invalidate Session
		Session session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}

		// Trigger the logout event and the get back to the start page
		resources.triggerEvent(LOGOUT, context, null);

		return indexPage;

	}

}
