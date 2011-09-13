package net.atos.mm.formation.tapestry.data;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;


/**
 * This class implements all the services on UserManager
 * 
 * @version $Id: UserManager.java,v 1.1 2009/11/23 14:53:55 a160688 Exp $
 */
public class UserManager{
	
	private ArrayList users = new ArrayList(50);

	private ArrayList admins = new ArrayList();

	private ArrayList nonAdmins = new ArrayList();

	private HashMap portfolios = new HashMap(50);

	
	/**
	 * Not visible
	 */
	private static UserManager singleInstance;
	

	/**
	 * 
	 * 
	 */
	private UserManager() {

		User defaultUser = new User();
		defaultUser.setLogin("tapestry");
		defaultUser.setPassword("password");
		defaultUser.setAge(30);
		defaultUser.setEmail("tapestry@domain.com");
		try {
			addUser(defaultUser);
			
		} catch (AlreadyExistsException aeEx) {
			System.out.println(aeEx.getMessage());
		}
	

	}

	/**
	 * 
	 * 
	 * @param user
	 * @throws formation.AlreadyExistsException
	 */
	public void addUser(User user) throws AlreadyExistsException {

		if (user != null) {

			// Verify is user exists
			if (getUserByLogin(user.getLogin()) != null) {
				throw new AlreadyExistsException(
						"Another user has the same login !");
			}

			users.add(user);
			// add several initial portfolios
			Collection portfolios = user.getPortfolios();
			for (int i = 0; i < 10; i++) {
				Portfolio pf = new Portfolio();
				pf.setLabel("Portfolio n°" + i);
				pf.setAmount((i + 1) * 100);
				pf.setBookingDate(new Date(System.currentTimeMillis() + 10000
						* i));
				portfolios.add(pf);
			}
			// add several initial actions the user can do
			Collection actions = user.getActions();

			Action addPorfolio = new Action();
			addPorfolio.setLibelle("Add a new portfolio");
			addPorfolio.setUrl("portfolio/add");
			
			if (user.getAdminRights()) {
				admins.add(user);
			} else {
				nonAdmins.add(user);
			}
			actions.add(addPorfolio);
			//actions.add(uploadPhoto);
			

		} else {
			throw new IllegalArgumentException("Attribut User cannot be null.");
		}
	}

	/**
	 * 
	 * 
	 * @param user
	 * @param portfolio
	 */
	public void addPortfolioToUser(User user, Portfolio portfolio) {

		if (portfolio == null)
			return;
		user.getPortfolios().add(portfolio);
		
	}

	/**
	 * 
	 * 
	 * @param login
	 * @return User
	 */
	public User getUserByLogin(String login) {

		for (Iterator i = users.iterator(); i.hasNext();) {
			User user = (User) i.next();
			if (user.getLogin().equals(login)) {
				return user;
			}
		}
		return null;

	}

	/**
	 * Singleton UserManager
	 * 
	 * @return An instance of {@link UserManager}
	 */
	public static synchronized UserManager getInstance() {
		if (singleInstance == null) {
			singleInstance = new UserManager();
		}
		return singleInstance;
	}


	public ArrayList getAdmins() {
		return this.admins;
	}

	public ArrayList getNonAdmins() {
		return this.nonAdmins;
	}
}
