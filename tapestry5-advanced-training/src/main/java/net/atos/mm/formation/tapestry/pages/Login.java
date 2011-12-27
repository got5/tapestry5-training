package net.atos.mm.formation.tapestry.pages;

import net.atos.mm.formation.tapestry.data.User;
import net.atos.mm.formation.tapestry.services.SecurityManager;

import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.Field;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.PasswordField;
import org.apache.tapestry5.corelib.components.TextField;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.internal.util.InternalUtils;

public class Login {
	
	@SessionState
	private User loggedUser;
	
	@Property
	private String login;
	
	@Property
	private String password;
	
	@Inject
	private SecurityManager manager;
	
	@Component
	private Form login_form;
	
	@Component 
	private TextField user_login;
	
	@Component 
	private PasswordField user_password;
	
	@OnEvent(value=EventConstants.VALIDATE)
	public void checkIfCorrectUser(){
		if(InternalUtils.isBlank(login) || InternalUtils.isBlank(password)){
			login_form.clearErrors();
			
			if(InternalUtils.isBlank(login)) 
				login_form.recordError(user_login, "Please fill all the Login input !!");
			if(InternalUtils.isBlank(password)) 
				login_form.recordError(user_password, "Please fill all the Password input !!");
		}
		else if(!manager.isUserCorrect(login, password)) {
			login_form.clearErrors();
			login_form.recordError("This user does not exist");
		}
	}
	
	@OnEvent(value=EventConstants.SUCCESS)
	public String saveUserInSession(){
		loggedUser = manager.getUser(login, password);
		return "Index";
	}
	
}
