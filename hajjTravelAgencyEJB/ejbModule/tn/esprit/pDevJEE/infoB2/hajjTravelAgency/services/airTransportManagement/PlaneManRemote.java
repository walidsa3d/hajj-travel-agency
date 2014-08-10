package tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.airTransportManagement;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Plane;

@Remote
public interface PlaneManRemote {
	public void addPlane(Plane plane);
	public void removePlane(Plane plane);
	public void updatePlane(Plane plane);
	public Plane getPlaneById(int idPlane);
	public List<Plane> getAllPlanes();

}
