package tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.airTransportManagement;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Flight;

@Remote
public interface FlightManRemote {
	public void addFlight(Flight flight);
	public void removeFlight(Flight flight);
	public void updateFlight(Flight flight);
	public Flight findFlightById(int idFlight);
	public List<Flight> getAllFlights();
	public List<Flight> getFlightsByType(String flightType);
	

}
