package net.atos.mm.formation.tapestry.services.impl;

import net.atos.mm.formation.tapestry.data.User;
import net.atos.mm.formation.tapestry.services.SecurityManager;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderRequestParameters;
import org.apache.tapestry5.services.RequestGlobals;

public class SecurityManagerImpl implements SecurityManager {

	@Inject 
	private RequestGlobals requestGlobals;

	public boolean isUserAuthenticated(PageRenderRequestParameters parameters) {
		
		if(parameters.getLogicalPageName().equals("Login"))
			return true;
		else {
			
			Object user = requestGlobals.getHTTPServletRequest().getSession().getAttribute("sso:net.atos.mm.formation.tapestry.data.User");
			if(user!=null)
				return true;
		}
		return false;
	}

	public boolean isUserCorrect(String login, String password) {
		
		if("tapestry".equals(login) && "password".equals(password)){
			return true;
		}
		return false;
	}

	public User getUser(String login, String password) {
		if("tapestry".equals(login)){
			return new User(login, password);
		}
		return null;
	}
}
