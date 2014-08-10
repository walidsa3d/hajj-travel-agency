package tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.airTransportManagement;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Flight;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.traceManagement.TraceManLocal;

/**
 * Session Bean implementation class FlightMan
 */
@Stateless
public class FlightMan implements FlightManRemote {
	@PersistenceContext
	EntityManager em;
	@EJB
	TraceManLocal traceman;

    /**
     * Default constructor. 
     */
    public FlightMan() {
    }

	@Override
	public void addFlight(Flight flight) {
		em.persist(flight);
				traceman.traceIt("ADD", flight);

		
	}

	@Override
	public void removeFlight(Flight flight) {
		em.remove(em.merge(flight));
				traceman.traceIt("DELETE", flight);

	}

	@Override
	public void updateFlight(Flight flight) {
		em.merge(flight);
				traceman.traceIt("UPDATE", flight);

	}

	@Override
	public Flight findFlightById(int idFlight) {
return em.find(Flight.class, idFlight);
}

	@Override
	public List<Flight> getAllFlights() {
		Query query=em.createQuery("Select f from Flight f");
		return query.getResultList();
	}

	@Override
	public List<Flight> getFlightsByType(String flightType) {
		Query q = em.createQuery("SELECT f FROM Flight f WHERE f.typeFlight = :ft");
		q.setParameter("ft",flightType);
		return q.getResultList();
	}

}
