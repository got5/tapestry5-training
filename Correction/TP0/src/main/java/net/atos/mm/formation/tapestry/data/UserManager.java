/** 
 * AtosOrigin Multimedia
 */
/* <a-zone id="history"> */
/*
 * $Id: UserManager.java,v 1.1 2009/11/23 14:53:55 a160688 Exp $
 * 
 * $Log: UserManager.java,v $
 * Revision 1.1  2009/11/23 14:53:55  a160688
 * Added TP0 to restore the project when needed
 *
 * Revision 1.2  2009/07/24 14:10:15  a160688
 * Small fixes
 *
 * Revision 1.1  2008/06/17 15:25:38  fr25373
 * MAVENisation
 *
 * Revision 1.2  2008/01/24 16:08:28  a136316
 * Prise en compte des remarques de la premi�re formation
 *
 * Revision 1.1  2007/12/26 14:50:35  a136316
 * Creation d'un projet Template Tapestry 5 pour la formation
 *
 * Revision 1.8  2007/06/18 13:13:10  bverachten
 * Made a change for the TreeView exercise
 *
 * Revision 1.7  2005/08/31 08:47:31  cmortelette
 * Add admin rigths to the user
 *
 * Revision 1.6  2003/10/30 14:13:03  nkhadri
 * MAJ Tps FW3
 *
 * Revision 1.4  2003/10/24 16:13:55  cvandaele
 * update
 *
 * Revision 1.3  2003/10/24 13:47:21  cvandaele
 * regenerate formation sources
 *
 * Revision 1.2  2003/09/30 11:17:47  cvandaele
 * fix bugs
 *
 * Revision 1.1  2003/04/30 10:13:04  fniquet
 * initial
 *
 */
/* </a-zone> */

package net.atos.mm.formation.tapestry.data;

/* <a-zone id="imports"> */
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.slf4j.Logger;

/* </a-zone> */

/**
 * This class implements all the services on UserManager
 * 
 * @version $Id: UserManager.java,v 1.1 2009/11/23 14:53:55 a160688 Exp $
 */
public class UserManager/* <a-zone id="extends"> *//* </a-zone> *//*
																	 * <a-zone *
																	 * id
																	 * ="implements"
																	 * >
																	 *//*
																		 * </a-zone
																		 * >
																		 */{
	/* <a-zone id="fields"> */

	private ArrayList users = new ArrayList(50);

	private ArrayList admins = new ArrayList();

	private ArrayList nonAdmins = new ArrayList();

	private HashMap portfolios = new HashMap(50);

	/* </a-zone> */

	/**
	 * Not visible
	 */
	private static UserManager singleInstance;
	
	@Inject
	private Logger logger;
	
	/**
	 * 
	 * 
	 */
	private UserManager() {

		/* <a-zone id="UserManager()"> */
		User defaultUser = new User();
		defaultUser.setLogin("tapestry");
		defaultUser.setPassword("password");
		defaultUser.setAge(30);
		defaultUser.setEmail("tapestry@domain.com");
		try {
			addUser(defaultUser);
			logger.info("[UserManager]Default user tapestry/password has been added.");
		} catch (AlreadyExistsException aeEx) {
			logger.error(aeEx.getMessage());
		}
		/* </a-zone> */

	}

	/* <a-zone id="constructors"> */
	/* </a-zone> */

	/**
	 * 
	 * 
	 * @param user
	 * @throws formation.AlreadyExistsException
	 */
	public void addUser(User user) throws AlreadyExistsException {

		/* <a-zone id="addUser(User)"> */
		// add the user just created
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
			/* </a-zone> */

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

		/* <a-zone id="addPortfolioToUser(User,Portfolio)"> */
		if (portfolio == null)
			return;
		user.getPortfolios().add(portfolio);
		/* </a-zone> */
	}

	/**
	 * 
	 * 
	 * @param login
	 * @return User
	 */
	public User getUserByLogin(String login) {

		/* <a-zone id="getUserByLogin(String)"> */
		for (Iterator i = users.iterator(); i.hasNext();) {
			User user = (User) i.next();
			if (user.getLogin().equals(login)) {
				return user;
			}
		}
		return null;

		/* </a-zone> */
	}

	/**
	 * Singleton UserManager
	 * 
	 * @return An instance of {@link UserManager}
	 */
	public static synchronized UserManager getInstance() {
		/* <a-zone id="getInstance()"> */

		if (singleInstance == null) {
			singleInstance = new UserManager();
		}
		return singleInstance;

		/* </a-zone> */
	}

	/* <a-zone id="methods"> */
	public ArrayList getAdmins() {
		return this.admins;
	}

	public ArrayList getNonAdmins() {
		return this.nonAdmins;
	}
	/* </a-zone> */

}
