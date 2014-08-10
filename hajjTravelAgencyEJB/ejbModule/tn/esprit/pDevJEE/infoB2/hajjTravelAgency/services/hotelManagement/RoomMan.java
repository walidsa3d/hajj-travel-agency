package tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.hotelManagement;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Room;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.traceManagement.TraceManLocal;

/**
 * Session Bean implementation class RoomMan
 */
@Stateless
public class RoomMan implements RoomManRemote {
@PersistenceContext
EntityManager em;
@EJB
	TraceManLocal traceman;
    /**
     * Default constructor. 
     */
    public RoomMan() {
    }
	@Override
	public void addRoom(Room room) {
		em.persist(room);
				traceman.traceIt("ADD", room);

	}
	@Override
	public void deleteRoom(int idRoom) {
		em.remove(em.merge(em.find(Room.class, idRoom)));
		traceman.traceIt("DELETE", new Room());
	}
	
	@Override
	public void updateRoom(Room room) {
		em.merge(room);
		traceman.traceIt("UPDATE", room);
	}
	@Override
	public Room findRoomById(int idRoom) {
		return em.find(Room.class, idRoom);
	}
	@Override
	public List<Room> getAllRooms() {
		Query query=em.createQuery("Select r from Room r");
		return query.getResultList();
	}

	
	

}
