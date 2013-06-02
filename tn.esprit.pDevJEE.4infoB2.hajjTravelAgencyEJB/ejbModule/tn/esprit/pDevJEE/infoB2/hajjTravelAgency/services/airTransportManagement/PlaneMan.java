package tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.airTransportManagement;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Plane;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.traceManagement.TraceManLocal;

/**
 * Session Bean implementation class PlaneMan
 */
@Stateless
public class PlaneMan implements PlaneManRemote {
	@PersistenceContext
	EntityManager em;
	@EJB
	TraceManLocal traceman;

    /**
     * Default constructor. 
     */
    public PlaneMan() {
    }

	@Override
	public void addPlane(Plane plane) {
em.persist(plane);	
		traceman.traceIt("ADD", plane);

	}

	@Override
	public void removePlane(Plane plane) {
em.remove(em.merge(plane));		
		traceman.traceIt("DELETE", plane);

	}

	@Override
	public void updatePlane(Plane plane) {
em.merge(plane);	
		traceman.traceIt("UPDATE", plane);

	}

	@Override
	public Plane getPlaneById(int idPlane) {
		return em.find(Plane.class, idPlane);
	}

	@Override
	public List<Plane> getAllPlanes() {
Query query=em.createQuery("Select p from Plane p");
return query.getResultList();
	}

}
