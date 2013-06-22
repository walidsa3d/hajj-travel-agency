package login;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.User;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.userManagement.UserManLocal;


@ManagedBean(name="authbean")
@SessionScoped

public class LoginBean implements Serializable{

@EJB
UserManLocal usl;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8708984531000886887L;
	private String username;
	private String password;
	private String message;
	private User user = new User();
	private boolean loggedIn = false;
	private String userType = "";


	private List<User> users;

	public LoginBean() {
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	public String authentify() {
		String navigateTo = null;
		User found = usl.authenticate(user.getUserName(), user.getUserPassword());
		if (found != null) {
			user = found;
			loggedIn = true;
			if (user.getUserRole().getNameRole().equals("pilgrim")) {
				userType="pilgrim";
				navigateTo = "/pages/pilgrimprofile.jsf";
			}
			if (user.getUserRole().getNameRole().equals("groupleader")) {
				userType="groupleader";
				navigateTo = "/pages/glprofile.jsf";
			}
			
		} else {
			FacesMessage message = new FacesMessage("Bad credentials!");
			FacesContext.getCurrentInstance().addMessage("login_form:login_submit", message);
			loggedIn = false;
			navigateTo =null;
		}
		return navigateTo;

	}
	public String logout() {
		String navigateTo = null;
		loggedIn = false;
		user = new User();
		userType="";
		navigateTo = "/pages/login.jsf";
		return navigateTo;
		
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

}
