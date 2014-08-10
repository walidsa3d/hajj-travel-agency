package login;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.User;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.userManagement.UserManLocal;


@ManagedBean
@ViewScoped
public class RegisterBean {
	@EJB
	private UserManLocal usl;
	private User user=new User();

	public RegisterBean() {
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	public String register() {
		String navigateTo=null;
		usl.createUser(user);
		navigateTo="/pages/login.jsf";
		return navigateTo;
	}

}
