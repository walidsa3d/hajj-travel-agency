package tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.userManagement;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.User;

@Remote
public interface UserManRemote {
	 public void  createUser(User user);
	 public void updateUser(User user);
	 public void deleteUser(String idUser);
	 public User getUserById(String idUser);
	 public List<User> getAllUsers();
	 public void createPilgrimUser(User user);
	}


