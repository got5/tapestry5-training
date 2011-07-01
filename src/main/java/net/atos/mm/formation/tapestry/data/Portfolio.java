/** 
 * AtosOrigin Multimedia
 */
/* <a-zone id="history"> */
/*
 * $Id: Portfolio.java,v 1.1 2009/11/23 14:53:55 a160688 Exp $
 * 
 * $Log: Portfolio.java,v $
 * Revision 1.1  2009/11/23 14:53:55  a160688
 * Added TP0 to restore the project when needed
 *
 * Revision 1.1  2008/06/17 15:25:38  fr25373
 * MAVENisation
 *
 * Revision 1.1  2007/12/26 14:50:35  a136316
 * Creation d'un projet Template Tapestry 5 pour la formation
 *
 * Revision 1.4  2005/08/31 08:47:31  cmortelette
 * Add admin rigths to the user
 *
 * Revision 1.3  2003/10/29 10:30:49  nkhadri
 * MAJ Tps FW3
 *
 * Revision 1.2  2003/10/24 13:47:21  cvandaele
 * regenerate formation sources
 *
 * Revision 1.1  2003/04/30 10:13:04  fniquet
 * initial
 *
 */
/* </a-zone> */

package net.atos.mm.formation.tapestry.data;

import java.util.Date;

/* <a-zone id="imports"> */
/* </a-zone> */

/**
 * 
 * @version $Id: Portfolio.java,v 1.1 2009/11/23 14:53:55 a160688 Exp $
 */
public class Portfolio/* <a-zone id="extends"> *//* </a-zone> */implements
		java.io.Serializable/* <a-zone id="implements"> *//* </a-zone> */{

	/* <a-zone id="fields"> */
	/* </a-zone> */

	/**
	 * 
	 */
	private String label;

	/**
	 * 
	 */
	private double amount;

	/**
	 * 
	 */
	private Date bookingDate;

	/* <a-zone id="constructors"> */
	/* </a-zone> */

	/**
	 * Set
	 * 
	 * @param label
	 *            the new value of
	 */
	public void setLabel(String label) {
		/* <a-zone id="setLabel(String)"> */
		this.label = label;
		/* </a-zone> */
	}

	/**
	 * Return
	 * 
	 * @return String
	 */
	public String getLabel() {
		/* <a-zone id="getLabel()"> */
		return this.label;
		/* </a-zone> */
	}

	/**
	 * Set
	 * 
	 * @param amount
	 *            the new value of
	 */
	public void setAmount(double amount) {
		/* <a-zone id="setAmount(float)"> */
		this.amount = amount;
		/* </a-zone> */
	}

	/**
	 * Return
	 * 
	 * @return float
	 */
	public double getAmount() {
		/* <a-zone id="getAmount()"> */
		return this.amount;
		/* </a-zone> */
	}

	/**
	 * Set
	 * 
	 * @param bookingDate
	 *            the new value of
	 */
	public void setBookingDate(Date bookingDate) {
		/* <a-zone id="setBookingDate(Date)"> */
		this.bookingDate = bookingDate;
		/* </a-zone> */
	}

	/**
	 * Return
	 * 
	 * @return Date
	 */
	public Date getBookingDate() {
		/* <a-zone id="getBookingDate()"> */
		return this.bookingDate;
		/* </a-zone> */
	}
	/* <a-zone id="methods"> */
	/* </a-zone> */

}
