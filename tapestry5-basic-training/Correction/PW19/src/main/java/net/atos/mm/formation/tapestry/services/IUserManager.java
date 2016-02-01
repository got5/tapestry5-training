package net.atos.mm.formation.tapestry.services;

import java.util.List;

import net.atos.mm.formation.tapestry.data.AlreadyExistsException;
import net.atos.mm.formation.tapestry.data.Portfolio;
import net.atos.mm.formation.tapestry.data.User;

public interface IUserManager {

	/**
	 * @param user
	 * @throws formation.AlreadyExistsException
	 */
	public abstract void addUser(User user) throws AlreadyExistsException;

	/**
	 * @param user
	 * @param portfolio
	 */
	public abstract void addPortfolioToUser(User user, Portfolio portfolio);

	/**
	 * @param login
	 * @return User
	 */
	public abstract User getUserByLogin(String login);

	public abstract List<User> getAdmins();

	public abstract List<User> getNonAdmins();

}