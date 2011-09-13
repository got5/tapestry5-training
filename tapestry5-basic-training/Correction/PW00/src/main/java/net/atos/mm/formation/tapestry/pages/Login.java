package net.atos.mm.formation.tapestry.pages;


import net.atos.mm.formation.tapestry.data.User;
import net.atos.mm.formation.tapestry.data.UserManager;

import org.apache.tapestry5.corelib.components.BeanEditForm;

public class Login {

	/**
	 * Used to store a UserManager instance on the page instance
	 */
	private UserManager manager;

	/**
	 * This variable is used to set a reference on the authenticated after
	 * verify process
	 */
	private User loggedUser;

	/**
	 * Use to store the user from the login form
	 */
	private User user;
	
	/** Used to store in session the number of validation error */
	private int count;
	
	/**
	 * Used to have a reference on the form component
	 */
	private BeanEditForm verifyForm;

	public void activateManager() {
		// Instantiate UserManager
	}

	/**
	 * This method implements the login process. Verify if the user exists and
	 * if his password is correct.
	 * 
	 * @return
	 */
	public Object verifyUser() {

		// Verify if user exists
		// ...

		// Verify User Password
		// ...

		return null;

	}
	
	/**
	 * Used to verify if the login form must be disabled
	 * 
	 * @return true if the number of failed attempts exceeds 3, false otherwise
	 */
	public boolean getMaxAttemptsExceeded() {
		return count >= 3;
	}
}