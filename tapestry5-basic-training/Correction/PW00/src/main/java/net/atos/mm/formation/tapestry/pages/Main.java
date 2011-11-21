package net.atos.mm.formation.tapestry.pages;

import java.text.SimpleDateFormat;

import net.atos.mm.formation.tapestry.data.Portfolio;
import net.atos.mm.formation.tapestry.data.User;

import org.apache.tapestry5.Block;
import org.apache.tapestry5.annotations.Property;

public class Main {

	/** Show the loop grid */
	private static final String LOOP = "loop";

	/** Show the Grid component */
	private static final String GRID = "grid";

	/**
	 * Used to have a reference on the authenticated user
	 */
	@Property
	private User loggedUser;

	/**
	 * Used to store a reference on the simple grid view
	 */
	private Block simpleList;

	/**
	 * Used to store a reference on the advanced grid component view
	 */
	private Block advancedList;

	/**
	 * Used to store the grid display in user session
	 */
	private String mode;

	/**
	 * Used to store the row index number
	 */
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
	public Object assertUserExists() {
     
     dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		// Verify if user has logged in

		return null;
	}

	/**
	 * This method is used for the zebra effect on the grid
	 * 
	 * @return "tbl1" or "tlb2" in function of "index modulo 2"
	 */
	public String getRowClass() {
		// Implement here the choice of CSS class used to display a row
		return "";
	}

	public void setRowClass(String rowClass) {
		this.rowClass = rowClass;
	}

	/**
	 * This method is used by the delegate component to get the block to display
	 * 
	 */
	public Block getPorfoliosGrid() {

		// Check for the selected mode and return the appropriate block
		return null;

	}

	/**
	 * This method is used to setup the advanced view mode
	 * 
	 */
	public void selectAdvancedMode() {
	}

	/**
	 * This method is used to setup the simple view mode
	 * 
	 */
	public void selectSimpleMode() {
	}



}
