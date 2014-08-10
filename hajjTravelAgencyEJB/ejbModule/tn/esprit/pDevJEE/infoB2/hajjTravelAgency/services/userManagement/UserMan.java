package tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.userManagement;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Role;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.User;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.messageService.MServiceLocal;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.traceManagement.TraceManLocal;

@Stateless

public class UserMan implements UserManRemote, UserManLocal {
	@PersistenceContext
	EntityManager em;
	@EJB
	TraceManLocal traceman;
	@EJB
	MServiceLocal msl;
	private static final Random RANDOM = new SecureRandom();

	public UserMan() {

	}

	@Override
	public void createPilgrimUser(User user) {
		user.setUserName(this.generateUsername(user));
		user.setUserPassword(this.generatePassword(user));
		user.setUserRole(new Role("pilgrim",null));
		em.persist(user);
		msl.sendMail(
				user.getNameUser(),
				user.getUserEmail(),
		user.getUserName(),
		user.getUserPassword());
		traceman.traceIt("ADD", user);

	}
	public void createUser(User user) {
		em.persist(user);
		traceman.traceIt("ADD", user);
	}

	@Override
	public void updateUser(User user) {
		em.merge(user);
		traceman.traceIt("UPDATE", user);

	}

	@Override
	public void deleteUser(String idUser) {
		em.remove(em.merge(getUserById(idUser)));
		traceman.traceIt("DELETE", new User());


	}

	@Override
	public User getUserById(String idUser) {
		User user = em.find(User.class, idUser);
		return user;
	}

	@Override
	public List<User> getAllUsers() {
		Query query = em.createQuery("select u from User u");
		return query.getResultList();
	}
	public String generatePassword(User user) {
		String userPassword = "";
		String letters = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ23456789+@";

		for (int i = 0; i < 8; i++) {
			int index = (int) (RANDOM.nextDouble() * letters.length());
			userPassword += letters.substring(index, index + 1);
		}

		return userPassword;
	}

	public String generateUsername(User user) {
		String userName = user.getUserEmail().split("@")[0];
						return userName;
	}
	public User authenticate(String login, String password) {
		User found = null;
		String jpql = "select u from User u where u.userName=:login and u.userPassword=:password";
		Query query = em.createQuery(jpql);
		query.setParameter("login", login);
		query.setParameter("password", password);
		try{
			found = (User) query.getSingleResult();
		}catch(Exception ex){
		}
		return found;
	}

}
