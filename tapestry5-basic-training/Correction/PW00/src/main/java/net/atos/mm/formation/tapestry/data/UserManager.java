package net.atos.mm.formation.tapestry.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class UserManager {
	 private List<User> users = new ArrayList<User>(50);

		private List<User> admins = new ArrayList<User>();

		private List<User> nonAdmins = new ArrayList<User>();

		private static UserManager singleInstance;
		

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

		/**
		 * @param user
		 * @param portfolio
		 */
		public void addPortfolioToUser(User user, Portfolio portfolio) {

			if (portfolio == null)
				return;
			user.getPortfolios().add(portfolio);
			
		}

		/**
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


		public List<User> getAdmins() {
			return this.admins;
		}

		public List<User> getNonAdmins() {
			return this.nonAdmins;
		}

}
