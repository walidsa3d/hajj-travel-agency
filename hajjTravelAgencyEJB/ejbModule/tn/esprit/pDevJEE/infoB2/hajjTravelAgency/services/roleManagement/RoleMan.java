package tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.roleManagement;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Role;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.traceManagement.TraceManLocal;

/**
 * Session Bean implementation class RoleMan
 */
@Stateless
public class RoleMan implements RoleManRemote {
	@PersistenceContext
	EntityManager em;
	@EJB
	TraceManLocal traceman;

	/**
	 * Default constructor.
	 */
	public RoleMan() {
	}

	@Override
	public void createRole(Role role) {
		em.persist(role);
		traceman.traceIt("ADD", role);
	}

	@Override
	public void updateRole(Role role) {
		em.merge(role);
		
		traceman.traceIt("UPDATE", role);
	}

	@Override
	public void deleteRole(String id) {
		em.remove(em.merge(getRoleById(id)));
				traceman.traceIt("DELETE", new Role());
		
	}

	@Override
	public Role getRoleById(String nameRole) {
		Role role = em.find(Role.class, nameRole);
		return role;
	}

	@Override
	public List<Role> getAllRoles() {
		Query query = em.createQuery("select r from Role r");
		return query.getResultList();
	}

}
