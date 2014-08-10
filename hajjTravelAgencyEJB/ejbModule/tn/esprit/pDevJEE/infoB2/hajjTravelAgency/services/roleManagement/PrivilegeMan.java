package tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.roleManagement;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Privilege;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.traceManagement.TraceManLocal;

/**
 * Session Bean implementation class PrivilegeMan
 */
@Stateless
public class PrivilegeMan implements PrivilegeManRemote {
	@PersistenceContext
	EntityManager em;
	@EJB
	TraceManLocal traceman;

	public PrivilegeMan() {
	}

	@Override
	public void createPrivilege(Privilege privilege) {
		em.persist(privilege);
		
		traceman.traceIt("ADD", privilege);

	}

	@Override
	public void updatePrivilege(Privilege privilege) {
		traceman.traceIt("UPDATE", privilege);

	}

	@Override
	public void deletePrivilege(Privilege privilege) {
		em.remove(em.merge(privilege));
		traceman.traceIt("DELETE", privilege);

	}

	@Override
	public Privilege getPrivilegeById(String idPrivilege) {
		Privilege privilege = em.find(Privilege.class, idPrivilege);
		return privilege;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Privilege> getAllPrivileges() {
		Query query = em.createQuery("select p from Privilege p");
		return query.getResultList();
	}

}
