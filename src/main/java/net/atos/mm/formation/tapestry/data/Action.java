/** 
 * AtosOrigin Multimedia
 */
/* <a-zone id="history"> */
/*
 * $Id: Action.java,v 1.1 2009/11/23 14:53:55 a160688 Exp $
 * 
 * $Log: Action.java,v $
 * Revision 1.1  2009/11/23 14:53:55  a160688
 * Added TP0 to restore the project when needed
 *
 * Revision 1.1  2008/06/17 15:25:38  fr25373
 * MAVENisation
 *
 * Revision 1.1  2007/12/26 14:50:35  a136316
 * Creation d'un projet Template Tapestry 5 pour la formation
 *
 * Revision 1.1  2005/08/31 08:47:31  cmortelette
 * Add admin rigths to the user
 *
 */
/* </a-zone> */

package net.atos.mm.formation.tapestry.data;

/* <a-zone id="imports"> */
/* </a-zone> */

/**
 * 
 * @version $Id: Action.java,v 1.1 2009/11/23 14:53:55 a160688 Exp $
 */
public class Action/* <a-zone id="extends"> *//* </a-zone> */implements
		java.io.Serializable/* <a-zone id="implements"> *//* </a-zone> */{

	/* <a-zone id="fields"> */
	/* </a-zone> */

	/**
	 * 
	 */
	private String libelle;

	/**
	 * 
	 */
	private String url;

	/**
	 * 
	 */
	private boolean selected;

	/* <a-zone id="constructors"> */
	/* </a-zone> */

	/**
	 * Set
	 * 
	 * @param libelle
	 *            the new value of
	 */
	public void setLibelle(String libelle) {
		/* <a-zone id="setLibelle(String)"> */
		this.libelle = libelle;
		/* </a-zone> */
	}

	/**
	 * Return
	 * 
	 * @return String
	 */
	public String getLibelle() {
		/* <a-zone id="getLibelle()"> */
		return this.libelle;
		/* </a-zone> */
	}

	/**
	 * Set
	 * 
	 * @param url
	 *            the new value of
	 */
	public void setUrl(String url) {
		/* <a-zone id="setUrl(String)"> */
		this.url = url;
		/* </a-zone> */
	}

	/**
	 * Return
	 * 
	 * @return String
	 */
	public String getUrl() {
		/* <a-zone id="getUrl()"> */
		return this.url;
		/* </a-zone> */
	}

	/**
	 * Set
	 * 
	 * @param selected
	 *            the new value of
	 */
	public void setSelected(boolean selected) {
		/* <a-zone id="setSelected(boolean)"> */
		this.selected = selected;
		/* </a-zone> */
	}

	/**
	 * Return
	 * 
	 * @return boolean
	 */
	public boolean getSelected() {
		/* <a-zone id="getSelected()"> */
		return this.selected;
		/* </a-zone> */
	}
	/* <a-zone id="methods"> */
	/* </a-zone> */

}
