package net.atos.mm.formation.tapestry.pages;


import net.atos.mm.formation.tapestry.data.User;
import net.atos.mm.formation.tapestry.data.UserManager;

import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;

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
	 * Use to store the current user login
	 */
	@Property
	private String login;

	/**
	 * Use to store the password of the current user
	 */
	@Property
	private String password;
	
	/** Used to store in session the number of validation error */
	private int count;
	
	/**
	 * Used to have a reference on the form component
	 */
	private Form verifyForm;

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
	 * Used by the form on failure to increment the failure counter and get back
	 * to the input upload form if a validation error occures
	 * 
	 * @return The verifyForm page
	 */
	public Object countFailure() {
		return null;
	}
	
	/**
	 * Used to verify if the upload feature must be disabled
	 * 
	 * @return true if the number of failed attempts exceeds 3, false otherwise
	 */
	public boolean getMaxAttemptsExceeded() {
		return count >= 3;
	}
}