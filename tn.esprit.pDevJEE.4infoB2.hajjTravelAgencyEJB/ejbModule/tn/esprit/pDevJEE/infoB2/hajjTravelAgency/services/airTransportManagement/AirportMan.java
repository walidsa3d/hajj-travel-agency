package tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.airTransportManagement;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Airport;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.traceManagement.TraceManLocal;

/**
 * Session Bean implementation class AirportMan
 */
@Stateless
public class AirportMan implements AirportManRemote {
	@PersistenceContext
	EntityManager em;
	@EJB
	TraceManLocal traceman;

    /**
     * Default constructor. 
     */
    public AirportMan() {
   }

	@Override
	public void addAirport(Airport airport) {
		
try {
	em.persist(airport);
	traceman.traceIt("ADD", airport);
} catch (Exception e) {
	e.printStackTrace();
}		

	}

	@Override
	public void removeAirport(int idAirport) {
try {
	em.remove(em.merge(findAirportById(idAirport)));
			traceman.traceIt("DELETE", new Airport());
} catch (Exception e) {
	e.printStackTrace();
}

	}

	@Override
	public void updateAirport(Airport airport) {
try {
	em.merge(airport);	
			traceman.traceIt("UPDATE", airport);
} catch (Exception e) {
	e.printStackTrace();
}

	}

	@Override
	public Airport findAirportById(int idAirport) {
		return em.find(Airport.class, idAirport);
	}

	@Override
	public List<Airport> getAllAirports() {
       Query query=em.createQuery("Select a from Airport a");
    		   return query.getResultList();
	}
    

}
