package net.atos.mm.formation.tapestry.pages.portfolio;

import net.atos.mm.formation.tapestry.data.Portfolio;
import net.atos.mm.formation.tapestry.data.User;
import net.atos.mm.formation.tapestry.data.UserManager;



public class AddPortfolio {

	/** Used to add the created portfolio to the user */
	private UserManager manager;

	/** Used to get the logged user from session */
	private User loggedUser;

	/** Used to verify if a user has logged in */
	private boolean loggedUserExists;

	/** Used to get the portfolio form */
	private Portfolio portfolio;

	/**
	 * Used to create a User Manager instance on page activation.
	 */
	public void activateManager() {
		// Create a UserManager Instance
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
	public Object addPortfolioToUser() {
		// Add portfolio to user or redirect the user to index page if not
		// logged in
		return null;
	}

}
