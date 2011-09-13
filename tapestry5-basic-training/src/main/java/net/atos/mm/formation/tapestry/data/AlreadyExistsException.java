/** 
 * AtosOrigin Multimedia
 */
/* <a-zone id="history"> */
/*
 * $Id: AlreadyExistsException.java,v 1.1 2009/11/23 14:53:55 a160688 Exp $
 * 
 * $Log: AlreadyExistsException.java,v $
 * Revision 1.1  2009/11/23 14:53:55  a160688
 * Added TP0 to restore the project when needed
 *
 * Revision 1.1  2008/06/17 15:25:38  fr25373
 * MAVENisation
 *
 * Revision 1.1  2007/12/26 14:50:35  a136316
 * Creation d'un projet Template Tapestry 5 pour la formation
 *
 * Revision 1.3  2005/08/31 08:47:31  cmortelette
 * Add admin rigths to the user
 *
 * Revision 1.2  2003/10/24 16:13:55  cvandaele
 * update
 *
 */
/* </a-zone> */

package net.atos.mm.formation.tapestry.data;

/* <a-zone id="imports"> */
/* </a-zone> */

/**
 * 
 * @version $Id: AlreadyExistsException.java,v 1.1 2009/11/23 14:53:55 a160688 Exp $
 */
public class AlreadyExistsException extends Exception/* <a-zone id="extends"> *//* </a-zone> *//*
																								 * <a-zone
																								 * id="implements">
																								 *//* </a-zone> */{

	/* <a-zone id="fields"> */
	/* </a-zone> */

	/**
	 * Default constructor.
	 * 
	 */
	public AlreadyExistsException() {

	}

	/**
	 * Constructs an <code>Exception</code> with the specified detail message.
	 * 
	 * @param s
	 *            the detail message.
	 */
	public AlreadyExistsException(java.lang.String s) {

		super(s);

	}
	/* <a-zone id="constructors"> */
	/* </a-zone> */
	/* <a-zone id="methods"> */
	/* </a-zone> */

}
