package tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.userManagement;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.User;


@Local
public interface UserManLocal {
	public void createUser(User user);
	 public void deleteUser(String idUser);	 
	 public void createPilgrimUser(User user);
	 public void updateUser(User user);
	 public List<User> getAllUsers();
	public User authenticate(String username, String password);

}
