package tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.pilgrimManagement;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Pilgrim;

@Remote
public interface PilgrimManRemote {
	public void createPilgrim(Pilgrim pilgrim);

	public void updatePilgrim(Pilgrim pilgrim);


	public Pilgrim getPilgrimById(int idPilgrim);

	public List<Pilgrim> getAllPilgrims();

	public List<Pilgrim> getPilgrimsByGender(String pilgrimGender);

	void deletePilgrim(int idPilgrim);

}
