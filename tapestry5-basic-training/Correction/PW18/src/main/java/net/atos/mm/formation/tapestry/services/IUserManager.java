package net.atos.mm.formation.tapestry.services;

import java.util.ArrayList;

import net.atos.mm.formation.tapestry.data.AlreadyExistsException;
import net.atos.mm.formation.tapestry.data.Portfolio;
import net.atos.mm.formation.tapestry.data.User;

public interface IUserManager {

	/**
	 * 
	 * 
	 * @param user
	 * @throws formation.AlreadyExistsException
	 */
	public abstract void addUser(User user)
			throws net.atos.mm.formation.tapestry.data.AlreadyExistsException;

	/**
	 * 
	 * 
	 * @param user
	 * @param portfolio
	 */
	public abstract void addPortfolioToUser(User user, Portfolio portfolio);

	/**
	 * 
	 * 
	 * @param login
	 * @return User
	 */
	public abstract User getUserByLogin(String login);

	public abstract ArrayList getUsers();
	
	public abstract void removeUser(String login);
	
	/* <a-zone id="methods"> */
	public abstract ArrayList getAdmins();

	public abstract ArrayList getNonAdmins();
	/* </a-zone> */

}