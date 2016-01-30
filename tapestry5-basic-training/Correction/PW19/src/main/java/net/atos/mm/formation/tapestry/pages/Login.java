package net.atos.mm.formation.tapestry.pages;


import net.atos.mm.formation.tapestry.data.User;
import net.atos.mm.formation.tapestry.services.IUserManager;

import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.BeanEditForm;
import org.apache.tapestry5.ioc.annotations.Inject;

public class Login {

	/**
	 * Used to store a UserManager instance on the page instance
	 */
	@Inject
	private IUserManager manager;

	/**
	 * This variable is used to set a reference on the authenticated after
	 * verify process
	 */
	@SessionState
	private User loggedUser;

	/**
	 * Use to store the user from the login form
	 */
	@Property
	private User user;
	
	/** Used to store in session the number of validation error */
	@Persist
	@Property
	private int count;
	
	/**
	 * Used to have a reference on the form component
	 */
	@InjectComponent
	private BeanEditForm verifyForm;

	@OnEvent(EventConstants.ACTIVATE)
	public void activateManager() {
	}

	/**
	 * This method implements the login process. Verify if the user exists and
	 * if his password is correct.
	 * 
	 * @return
	 */
	@OnEvent(value=EventConstants.SUBMIT)
	public Object verifyUser() {

		if(user.getLogin()==null || user.getPassword() == null) 
		{
			verifyForm.recordError("Vous devez saisir le login e le password!!");
			count++;
			return this;
		}
			
		User ttlUser = manager.getUserByLogin(user.getLogin());
		
		if (ttlUser == null || !ttlUser.getPassword().equals(user.getPassword())){
			this.count++;
			verifyForm.recordError("Wrong password or user doesn't exist...");
			return this;
		}
		
		this.loggedUser= ttlUser;
		return Main.class;
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