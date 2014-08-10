package tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.pilgrimManagement;

import java.util.List;

import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Pilgrim;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.User;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.traceManagement.TraceManLocal;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.userManagement.UserManLocal;

/**
 * Session Bean implementation class PilgrimMan
 */
@Stateless
public class PilgrimMan implements PilgrimManRemote, PilgrimManLocal {
	@PersistenceContext
	EntityManager em;
	@EJB
	TraceManLocal traceman;
	
	@EJB
	UserManLocal usl;

	/**
	 * Default constructor.
	 */
	public PilgrimMan() {
	}

	@Override
	@Asynchronous
	public void createPilgrim(Pilgrim pilgrim) {
		em.persist(pilgrim);
		traceman.traceIt("ADD", pilgrim);
		usl.createPilgrimUser(new User(pilgrim.getPilgrimEmail(), pilgrim.getPilgrimFirstName()+" "+pilgrim.getPilgrimLastName()));
		

	}

	@Override
	public void updatePilgrim(Pilgrim pilgrim) {
		em.merge(pilgrim);
		traceman.traceIt("UPDATE", pilgrim);

	}

	@Override
	public void deletePilgrim(int idPilgrim) {
		em.remove(em.merge(em.find(Pilgrim.class, idPilgrim)));
		traceman.traceIt("DELETE", new Pilgrim());

	}

	@Override
	public Pilgrim getPilgrimById(int idPilgrim) {
		Pilgrim pilgrim = em.find(Pilgrim.class, idPilgrim);
		return pilgrim;
	}

	@Override
	public List<Pilgrim> getAllPilgrims() {
		Query query = em.createQuery("select p from Pilgrim p");
		return query.getResultList();
	}

	@Override
	public List<Pilgrim> getPilgrimsByGender(String pilgrimGender) {
		Query query = em.createQuery(
				"Select p from Pilgrim p WHERE p.pilgrimGender= :pg")
				.setParameter("pg", "Male");
		return query.getResultList();
	}

}
