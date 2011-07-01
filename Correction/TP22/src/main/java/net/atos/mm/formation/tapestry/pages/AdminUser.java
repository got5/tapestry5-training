package net.atos.mm.formation.tapestry.pages;

import java.util.ArrayList;

import net.atos.mm.formation.tapestry.data.User;
import net.atos.mm.formation.tapestry.services.IUserManager;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.PropertyConduit;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.PageLoaded;
import org.apache.tapestry5.annotations.Retain;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.BeanModelSource;
import org.apache.tapestry5.services.PropertyConduitSource;

public class AdminUser {

	/**
	 * Used to stored the authenticated user after authentication or
	 * registration
	 */
	@SessionState
	private User loggedUser;

	/** Created by Tapestry to check if the loggedUser has been set in session */
	private boolean loggedUserExists;

	@Inject
	private IUserManager manager;

	private User currentUser;

	@Inject
	private BeanModelSource _beanModelSource;

	@Inject
	private ComponentResources _resources;

	@Inject
	private PropertyConduitSource _propertyConduitSource;

	@Retain
	private BeanModel _model;

	@InjectPage
	private EditUser editUserPage;
	
	@PageLoaded
	void pageLoaded() {

		_model = _beanModelSource.createDisplayModel(User.class, _resources.getMessages());

		// Create a new property

		PropertyConduit propCdt = _propertyConduitSource.create(User.class,
				"login");
		_model.add("deleteUser", propCdt);

		// Modify the property appearance

		_model.get("deleteUser").dataType("String");

		_model.get("deleteUser").sortable(false);

	}

	public BeanModel getModel() {
		return _model;
	}

	public ArrayList<User> getUserLst() {
		return manager.getUsers();
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	@OnEvent(EventConstants.ACTIVATE)
	public Object verifyAdminRights(){
		if(loggedUserExists){
			if(!loggedUser.getAdminRights()){
				return Login.class;
			}
		}
		return null;
	}
	
	@OnEvent(value = EventConstants.ACTION, component="deleteUser")
	public void removeUser(String login) {
		manager.removeUser(login);
	}

	@OnEvent(value = EventConstants.ACTION, component="editUser")
	public Object editUser(String login) {
		User tteUser = manager.getUserByLogin(login);
		if(tteUser != null) {
			editUserPage.setEditableUser(tteUser);
			return editUserPage;
		}else{
			return null;
		}
	}
}
