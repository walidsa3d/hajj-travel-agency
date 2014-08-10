package tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.pilgrimManagement;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.PGroup;

/**
 * Session Bean implementation class GroupMan
 */
@Stateless
public class GroupMan implements GroupManRemote, GroupManLocal {
	@PersistenceContext
	EntityManager em;

    /**
     * Default constructor. 
     */
    public GroupMan() {
    }

	@Override
	public void addGroup(PGroup group) {
		em.persist(group);
	}

	@Override
	public void removeGroup(PGroup group) {
		em.remove(em.merge(group));
	}

	@Override
	public void updateGroup(PGroup group) {
		em.merge(group);
	}

	@Override
	public PGroup findGroupById(int idGroup) {
		return em.find(PGroup.class, idGroup);
	}

	@Override
	public List<PGroup> getAllGroups() {
		Query query=em.createQuery("Select g from PGroup g");
		return query.getResultList();	}

}
