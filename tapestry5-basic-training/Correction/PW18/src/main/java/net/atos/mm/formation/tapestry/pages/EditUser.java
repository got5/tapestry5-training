package net.atos.mm.formation.tapestry.pages;

import net.atos.mm.formation.tapestry.data.User;
import net.atos.mm.formation.tapestry.services.IUserManager;

import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 * @author a136316
 * 
 */
public class EditUser {

	@Inject
	private IUserManager manager;

	@SessionState
	private User loggedUser;

	private String login;

	private String password;

	private String email;

	private int age;

	private boolean admin;

	@Persist
	private User editableUser;

	@Component(id = "editUserForm")
	private Form editUserForm;

	public User getEditableUser() {
		return editableUser;
	}

	public void setEditableUser(User editableUser) {
		this.editableUser = editableUser;
	}

	@OnEvent(value = "success", component = "editUserForm")
	public Object modifyUser() {

		User user = new User();
		user.setPassword(password);
		user.setEmail(email);
		user.setAge(age);
		user.setAdminRights(admin);

		return AdminUser.class;
		
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	@Validate("required,regexp=.*@.*")
	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	@Validate("required,minLength=5")
	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	@Validate("required,minLength=6")
	public void setPassword(String password) {
		this.password = password;
	}

}
