package tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.pilgrimManagement;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Pilgrim;

@Local
public interface PilgrimManLocal {
	public void createPilgrim(Pilgrim pilgrim);

	public void updatePilgrim(Pilgrim pilgrim);


	public Pilgrim getPilgrimById(int idPilgrim);

	public List<Pilgrim> getAllPilgrims();

	public List<Pilgrim> getPilgrimsByGender(String pilgrimGender);

	public void deletePilgrim(int idPilgrim);

}
