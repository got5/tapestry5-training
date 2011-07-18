package net.atos.mm.formation.tapestry.pages.portfolio;

import net.atos.mm.formation.tapestry.data.Portfolio;
import net.atos.mm.formation.tapestry.data.User;
import net.atos.mm.formation.tapestry.data.UserManager;
import net.atos.mm.formation.tapestry.pages.Login;

import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.BeanEditForm;
import org.apache.tapestry5.corelib.components.Zone;

public class AddPortfolio {
	
	@InjectComponent
	private Zone maZone;
	
	/** Used to add the created portfolio to the user */
	private UserManager manager;

	/** Used to get the logged user from session */
	@SessionState
	private User loggedUser;

	/** Used to verify if a user has logged in */
	private boolean loggedUserExists;

	/** Used to get the portfolio form */
	private Portfolio portfolio;
	
	@Component(id="addPortfolioForm")
	private BeanEditForm addPortfolioForm;
	/**
	 * Used to create a User Manager instance on page activation.
	 */
	@OnEvent(EventConstants.ACTIVATE)
	public void activateManager() {
		manager = UserManager.getInstance();	
	}

	public Portfolio getPortfolio() {
		return portfolio;
	}

	public void setPortfolio(Portfolio portfolio) {
		this.portfolio = portfolio;
	}

	/**
	 * Used to process add portfolio form submission. Return Main page if the
	 * portfolio has been added successfully. Get back to the Index page if an
	 * error occurs.
	 * 
	 * @return
	 */
	@OnEvent(EventConstants.SUCCESS)
	public Object addPortfolioToUser() {
		// Add portfolio to user or redirect the user to index page if not
		// logged in
		if(loggedUserExists) {
			addPortfolioForm.clearErrors();
			manager.addPortfolioToUser(loggedUser, portfolio);
			return maZone.getBody();
		}else{
			return Login.class;	
		}
	}
	
	@OnEvent(EventConstants.FAILURE)
	public Object formOnFailure() {
		return this;
	}

}
