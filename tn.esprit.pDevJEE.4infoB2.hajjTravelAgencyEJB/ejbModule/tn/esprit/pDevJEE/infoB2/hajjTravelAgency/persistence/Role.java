package tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 * The Class Role.
 */
@Entity
public class Role implements Serializable {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 5204589276191654766L;

	/** The name role. */
	private String nameRole;

	/** The description role. */
	private String descriptionRole;
	private List<Privilege> privileges = new ArrayList<Privilege>();

	/**
	 * Instantiates a new role.
	 */
	public Role() {

	}

	public Role(String nameRole, String descriptionRole) {
		super();
		this.nameRole = nameRole;
		this.descriptionRole = descriptionRole;
	}


	/**
	 * Gets the name role.
	 * 
	 * @return the name role
	 */
	@Id
	public String getNameRole() {
		return nameRole;
	}

	/**
	 * Sets the name role.
	 * 
	 * @param nameRole
	 *            the new name role
	 */
	public void setNameRole(String nameRole) {
		this.nameRole = nameRole;
	}

	/**
	 * Gets the description role.
	 * 
	 * @return the description role
	 */
	public String getDescriptionRole() {
		return descriptionRole;
	}

	/**
	 * Sets the description role.
	 * 
	 * @param descriptionRole
	 *            the new description role
	 */
	public void setDescriptionRole(String descriptionRole) {
		this.descriptionRole = descriptionRole;
	}

	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name = "Role_Priv", joinColumns = { @JoinColumn(name = "idRole") }, inverseJoinColumns = { @JoinColumn(name = "idPrivilege") })
	public List<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(List<Privilege> privileges) {
		this.privileges = privileges;
	}
	@Override
	public String toString() {
		return nameRole;
	}

}
