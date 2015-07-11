package net.atos.mm.formation.tapestry.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.corelib.components.Form;

import net.atos.mm.formation.tapestry.data.User;
import net.atos.mm.formation.tapestry.data.UserManager;

/**
 * @author a136316
 * 
 */
public class Register {
	
	private UserManager manager;
	
	@SessionState
	private User loggedUser;	
	
	/**
	 * Used to have a reference on the form component
	 */
	@Component(id = "registerForm")
	private Form registerForm;	
	
	@Validate("required,minLength=5")
	@Property
	private String login;

	@Validate("required,minLength=6")
	@Property
	private String password;

	@Validate("required,regexp=.*@.*")
	@Property
	private String email;

	@Property
	private int age;

	@Property
	private boolean admin;	

	/**
	 * Used to create a UserManager instance on page activation.
	 *
	 */
	@OnEvent(value=EventConstants.ACTIVATE)
	public void activateManager() {
		manager = UserManager.getInstance();
	}

	/**
	 * Used to control if the user already exists just before form submission.
	 *
	 * @return Register Page if user already exists
	 */
	@OnEvent(value=EventConstants.VALIDATE, component="registerForm")
	public void verifyIfUserAlreadyExists() {
		if (manager != null) {
			User ttcUser = manager.getUserByLogin(login);
			if (ttcUser != null) {
				registerForm.recordError("User already exists.");
			}
		} else {
			registerForm.recordError("User Manager unavailable");
		}
	}

	/**
	 * Used to process form submission.
	 *
	 * @return Register Page on creation failure, Main page on success
	 */
	@OnEvent(value=EventConstants.SUCCESS, component="registerForm")
	public Object createUser() {
		User user = new User();
		user.setLogin(login);
		user.setPassword(password);
		user.setEmail(email);
		user.setAge(age);
		user.setAdminRights(admin);

		// Try to add a new user
		if (manager != null) {
			try {
				// Add user
				manager.addUser(user);
				
				// Set application state logged user
				loggedUser = user;
			} catch (Exception ex) {
				registerForm.recordError("Error while adding user.");
				return Register.class;
			}
		} else {
			registerForm.recordError("User Manager unavailable");
			return this;
		}

		return Main.class;
	}
	
	
	@OnEvent(value = "provideCompletions")
	public List<String> provideDomainNameCompletion(String email) {

		if(email.contains("@")){
			ArrayList<String> result = new ArrayList<String>();
			String prefix = email.substring(0,email.indexOf("@"));
			
			result.add(prefix+"@atosworldline.be");
			result.add(prefix+"@atosworldline.fr");
			result.add(prefix+"@atosworldline.com");
			
			return result;
		}

		return null;
		
	}	

}
