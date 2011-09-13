package net.atos.mm.formation.tapestry.pages;


import net.atos.mm.formation.tapestry.data.User;
import net.atos.mm.formation.tapestry.data.UserManager;

import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
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
	@SessionState
	private User loggedUser;

	/**
	 * Use to store the user from the login form
	 */
	@Property
	private User user;
	
	/** Used to store in session the number of validation error */
	@Property
	@Persist
	private int count;
	
	/**
	 * Used to have a reference on the form component
	 */
	@InjectComponent
	private BeanEditForm verifyForm;
	
	@OnEvent(EventConstants.ACTIVATE)
	public void activateManager() {
		if(manager==null) manager = UserManager.getInstance();
		
		if(getMaxAttemptsExceeded()) {
			verifyForm.clearErrors();
			verifyForm.recordError("You have done more than three attempts to upload illegal files.");
			verifyForm.recordError("The Form is disabled for this session");
		}
	}

	/**
	 * This method implements the login process. Verify if the user exists and
	 * if his password is correct.
	 * 
	 * @return
	 */
	@OnEvent(EventConstants.SUCCESS)
	public Object verifyUser() {

		if(user.getLogin()==null) 
		{
			verifyForm.recordError("Vous devez saisir le login!!");
		}
		if(user.getPassword()==null) 
		{
			verifyForm.recordError("Vous devez saisir le password!!");
		}
		
		if(!verifyForm.getHasErrors()){
			
			String errorMsg = "Wrong password or user doesn't exist...";
			
			User ttlUser = manager.getUserByLogin(user.getLogin());
			
			if (ttlUser != null) {
				// Verify User Password
				if (user.getPassword().compareTo(ttlUser.getPassword()) == 0) {
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

			
		} 
		else count++;
		
		return this;
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