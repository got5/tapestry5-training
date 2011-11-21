package net.atos.mm.formation.tapestry.pages;

import java.text.SimpleDateFormat;

import net.atos.mm.formation.tapestry.data.Portfolio;
import net.atos.mm.formation.tapestry.data.User;

import org.apache.tapestry5.Block;
import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;

public class Main {

	/** Show the loop grid */
	private static final String LOOP = "loop";

	/** Show the Grid component */
	private static final String GRID = "grid";

	/**
	 * Used to have a reference on the authenticated user
	 */
	@SessionState
	@Property
	private User loggedUser;
	
	private boolean loggedUserExists;
	
	/**
	 * Used to store a reference on the simple grid view
	 */
	@Inject
	private Block simpleList;

	/**
	 * Used to store a reference on the advanced grid component view
	 */
	@Inject
	private Block advancedList;

	/**
	 * Used to store the grid display in user session
	 */
	@Persist
	private String mode;

	/**
	 * Used to store the row index number
	 */
	@Property
	private int index = 0;

	/**
	 * Used to store the currentPortfolio
	 */
	@Property
	private Portfolio currentPortfolio;

	/**
	 * Used to modify the display of "bookingDate"
	 */
	@Property
	private SimpleDateFormat dateFormat;

	/**
	 * Used to store
	 */
	
	private String rowClass;

	/**
	 * Used to verify if the user is logged on
	 * 
	 * @return the Index page if user doesn't exist in session, null otherwise
	 */
	@OnEvent(EventConstants.ACTIVATE)
	public Object assertUserExists() {
    
    dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		// Verify if user has logged in
		if (!loggedUserExists) {
			return Login.class;
		}

		return null;
	}
	/**
	 * This method is used for the zebra effect on the grid
	 * 
	 * @return "tbl1" or "tlb2" in function of "index modulo 2"
	 */
	public String getRowClass() {
		// Implement here the choice of CSS class used to display a row
		// Implement here the choice of CSS class used to display a row
		if (index % 2 == 0) {
			return "tbl1";
		} else {
			return "tbl2";
		}
	}

	public void setRowClass(String rowClass) {
		this.rowClass = rowClass;
	}

	/**
	 * This method is used by the delegate component to get the block to display
	 * 
	 */
	public Block getPorfoliosGrid() {

		if(GRID.equals(mode))
			return advancedList;
		return simpleList;
	}

	/**
	 * This method is used to setup the advanced view mode
	 * 
	 */
	@OnEvent(value=EventConstants.ACTION, component="advanced")
	public void selectAdvancedMode() {
		mode = GRID;
	}

	/**
	 * This method is used to setup the simple view mode
	 * 
	 */
	@OnEvent(value=EventConstants.ACTION, component="simple")
	public void selectSimpleMode() {
		mode = LOOP;
	}



}
