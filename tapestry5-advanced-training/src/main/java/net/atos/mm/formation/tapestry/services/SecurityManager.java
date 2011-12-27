package net.atos.mm.formation.tapestry.services;

import net.atos.mm.formation.tapestry.data.User;

import org.apache.tapestry5.services.PageRenderRequestParameters;

public interface SecurityManager {
	
	boolean isUserAuthenticated(PageRenderRequestParameters parameters);
	boolean isUserCorrect(String login, String password);
	User getUser(String login, String password);
}
