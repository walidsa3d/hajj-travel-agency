package tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.hotelManagement;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Hotel;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.traceManagement.TraceManLocal;

/**
 * Session Bean implementation class HotelMan
 */
@Stateless
public class HotelMan implements HotelManRemote {
	@PersistenceContext
	EntityManager em;
@EJB
	TraceManLocal traceman;

    /**
     * Default constructor. 
     */
    public HotelMan() {
    }

	@Override
	public void addHotel(Hotel hotel) {
		em.persist(hotel);
				traceman.traceIt("ADD", hotel);

		
	}

	@Override
	public void removeHotel(Hotel hotel) {
		em.remove(em.merge(hotel));
				traceman.traceIt("DELETE", hotel);

	}

	@Override
	public void updateHotel(Hotel hotel) {
		em.merge(hotel);
		traceman.traceIt("UPDATE", hotel);
	}

	@Override
	public List<Hotel> getAllHotels() {
		Query query=em.createQuery("Select h from Hotel h");
		return query.getResultList();
	}

	@Override
	public Hotel findHotelById(int idHotel) {
		return em.find(Hotel.class, idHotel);
	}

	@Override
	public List<Hotel> getHotelsByLocation(String location) {
		Query q = em.createQuery("SELECT h FROM Hotel h WHERE h.locationHotel = :hl");
		q.setParameter("hl",  location);
		return q.getResultList();
	}

}
