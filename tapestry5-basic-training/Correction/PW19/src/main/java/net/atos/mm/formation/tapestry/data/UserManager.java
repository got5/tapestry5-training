package net.atos.mm.formation.tapestry.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import net.atos.mm.formation.tapestry.services.IUserManager;

public class UserManager implements IUserManager {
	 private List<User> users = new ArrayList<User>(50);

		private List<User> admins = new ArrayList<User>();

		private List<User> nonAdmins = new ArrayList<User>();

		private static IUserManager singleInstance;
		

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

		/* (non-Javadoc)
		 * @see net.atos.mm.formation.tapestry.data.IUserManager#addUser(net.atos.mm.formation.tapestry.data.User)
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
					pf.setLabel("Portfolio " + i);
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
		public static synchronized IUserManager getInstance() {
			if (singleInstance == null) {
				singleInstance = new UserManager();
			}
			return singleInstance;
		}


		/* (non-Javadoc)
		 * @see net.atos.mm.formation.tapestry.data.IUserManager#getAdmins()
		 */
		public List<User> getAdmins() {
			return this.admins;
		}

		/* (non-Javadoc)
		 * @see net.atos.mm.formation.tapestry.data.IUserManager#getNonAdmins()
		 */
		public List<User> getNonAdmins() {
			return this.nonAdmins;
		}

}
