package tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * The Class Privilege.
 */
@Entity
public class Privilege implements Serializable {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new privilege.
	 */
	public Privilege() {

	}

	/** The name privilege. */
	private String namePrivilege;

	/** The description privilege. */
	private String descriptionPrivilege;

	/**
	 * Gets the name privilege.
	 * 
	 * 
	 * @return the name privilege
	 */
	@Id
	public String getNamePrivilege() {
		return namePrivilege;
	}

	/**
	 * Sets the name privilege.
	 * 
	 * @param namePrivilege
	 *            the new name privilege
	 */
	public void setNamePrivilege(String namePrivilege) {
		this.namePrivilege = namePrivilege;
	}

	/**
	 * Gets the description privilege.
	 * 
	 * @return the description privilege
	 */
	public String getDescriptionPrivilege() {
		return descriptionPrivilege;
	}

	/**
	 * Sets the description privilege.
	 * 
	 * @param descriptionPrivilege
	 *            the new description privilege
	 */
	public void setDescriptionPrivilege(String descriptionPrivilege) {
		this.descriptionPrivilege = descriptionPrivilege;
	}

	@Override
	public String toString() {
		return namePrivilege;
	}
	

}
