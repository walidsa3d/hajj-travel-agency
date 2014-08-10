package tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.airTransportManagement;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Airport;

@Remote
public interface AirportManRemote {
	public void addAirport(Airport airport);
	public void removeAirport(int idAirport);
	public void updateAirport(Airport airport);
	public Airport findAirportById(int idAirport);
	public List<Airport> getAllAirports();	

}
