package tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Entity implementation class for Entity: User
 * 
 */
@Entity
public class User implements Serializable {

	private String nameUser;
	private String userEmail;
	private String userName;
	private String userPassword;
	private Role userRole;
	private static final long serialVersionUID = 1L;

	public User() {
		super();
	}
	

	public User(String userEmail, String nameUser) {
		super();
		this.userEmail = userEmail;
		this.nameUser=nameUser;
	}


	/**
	 * @return the nameUser
	 */
	public String getNameUser() {
		return nameUser;
	}


	/**
	 * @param nameUser the nameUser to set
	 */
	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}


	/**
	 * @return the userEmail
	 */
	@Id
	public String getUserEmail() {
		return userEmail;
	}

	/**
	 * @param userEmail the userEmail to set
	 */
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userNameStaff) {
		this.userName = userNameStaff;
	}

	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String passwordStaff) {
		this.userPassword = passwordStaff;
	}

	@ManyToOne
	@JoinColumn(name = "userRole")
	public Role getUserRole() {
		return this.userRole;
	}

	public void setUserRole(Role role) {
		this.userRole = role;
	}

}
