package tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.hotelManagement;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Floor;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Hotel;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.traceManagement.TraceManLocal;

/**
 * Session Bean implementation class FloorMan
 */
@Stateless
public class FloorMan implements FloorManRemote {
	@PersistenceContext
	EntityManager em;
	@EJB
	TraceManLocal traceman;

    /**
     * Default constructor. 
     */
    public FloorMan() {
    }

	@Override
	public void addFloor(Floor floor) {
		em.persist(floor);
				traceman.traceIt("ADD", floor);

	}

	@Override
	public void deleteFloor(int idFloor) {
		em.remove(em.merge(em.find(Floor.class, idFloor)));
		traceman.traceIt("DELETE", new Floor());
	}

	@Override
	public void updateFloor(Floor floor) {
		em.merge(floor);
		traceman.traceIt("UPDATE", floor);
	}

	@Override
	public Floor findFloorById(int idFloor) {
		return em.find(Floor.class, idFloor);
	}

	@Override
	public List<Floor> getAllFloors() {
		Query query=em.createQuery("Select f from Floor f");
		return query.getResultList();
	}

	@Override
	public List<Floor> getFloorsByHotel(Hotel hotel) {
		Query q = em.createQuery("SELECT f FROM Floor f WHERE f.floorHotel = :fh");
		q.setParameter("fh",  hotel);
		return q.getResultList();
	}

}
