package net.atos.mm.formation.tapestry.data;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import net.atos.mm.formation.tapestry.services.IUserManager;

/**
 * This class implements all the services on UserManager
 * 
 * @version $Id: UserManager.java,v 1.2 2008/01/24 16:08:37 a136316 Exp $
 */
public class UserManager implements IUserManager{
	
	private ArrayList users = new ArrayList(50);

	private ArrayList admins = new ArrayList();

	private ArrayList nonAdmins = new ArrayList();

	private HashMap portfolios = new HashMap(50);

	/**
	 * Not visible
	 */
	private static IUserManager singleInstance;
	

	/**
	 * 
	 * 
	 */
	public UserManager() {

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

	
	public void addUser(User user)
			throws net.atos.mm.formation.tapestry.data.AlreadyExistsException {

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

		} else {
			throw new IllegalArgumentException("Attribut User cannot be null.");
		}
	}

	/* (non-Javadoc)
	 * @see net.atos.mm.formation.tapestry.data.IUserManager#addPortfolioToUser(net.atos.mm.formation.tapestry.data.User, net.atos.mm.formation.tapestry.data.Portfolio)
	 */
	public void addPortfolioToUser(User user, Portfolio portfolio) {

		if (portfolio == null)
			return;
		user.getPortfolios().add(portfolio);
	}

	/* (non-Javadoc)
	 * @see net.atos.mm.formation.tapestry.data.IUserManager#getUserByLogin(java.lang.String)
	 */
	public User getUserByLogin(String login) {

		for (Iterator i = users.iterator(); i.hasNext();) {
			net.atos.mm.formation.tapestry.data.User user = (net.atos.mm.formation.tapestry.data.User) i
					.next();
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
	public static synchronized IUserManager getInstance() {
		if (singleInstance == null) {
			singleInstance = new UserManager();
		}
		return singleInstance;
	}

	/* (non-Javadoc)
	 * @see net.atos.mm.formation.tapestry.data.IUserManager#getAdmins()
	 */
	public ArrayList getAdmins() {
		return this.admins;
	}

	/* (non-Javadoc)
	 * @see net.atos.mm.formation.tapestry.data.IUserManager#getNonAdmins()
	 */
	public ArrayList getNonAdmins() {
		return this.nonAdmins;
	}
}
