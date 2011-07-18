package net.atos.mm.formation.tapestry.pages;

import net.atos.mm.formation.tapestry.data.User;
import net.atos.mm.formation.tapestry.data.UserManager;

import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Retain;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.beaneditor.Validate;
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
	@SessionState
	private User loggedUser;

	/**
	 * Use to store the current user login
	 */
	private String login;

	/**
	 * Use to store the password of the current user
	 */
	private String password;

	/** Used to store in session the number of validation error */
	@Persist
	private int count;
	
	/**
	 * Used to have a reference on the form component
	 */
	@Component(id = "verifyForm")
	private Form verifyForm;

	@OnEvent(EventConstants.ACTIVATE)
	public void activateManager() {
		manager = UserManager.getInstance();
		
		if(getMaxAttemptsExceeded()) {
			verifyForm.clearErrors();
			verifyForm.recordError("You have done more than three attempts to upload illegal files.");
			verifyForm.recordError("Upload feature is disabled for this session");
		}
	}

	public String getLogin() {
		return login;
	}

	@Validate("required")
	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	@Validate("required")
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * This method implements the login process. Verify if the user exists and
	 * if his password is correct.
	 * 
	 * @return
	 */
	@OnEvent(value = EventConstants.SUCCESS, component = "verifyForm")
	public Object verifyUser() {

		String errorMsg = "Wrong password or user doesn't exist...";

		// Verify if user exists
		User ttlUser = manager.getUserByLogin(login);
		if (ttlUser != null) {
			// Verify User Password
			if (password.compareTo(ttlUser.getPassword()) == 0) {
				loggedUser = ttlUser;
				return Main.class;
			} else {
				this.count++;
				verifyForm.recordError(errorMsg);
			}
		} else {
			this.count++;
			verifyForm.recordError(errorMsg);
		}

		return this;

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