package tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.hotelManagement;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Floor;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Hotel;

@Remote
public interface FloorManRemote {
public void addFloor(Floor floor);
public void deleteFloor(int idFloor);
public void updateFloor(Floor floor);
public Floor findFloorById(int idFloor);
public List<Floor> getAllFloors();
public List<Floor> getFloorsByHotel(Hotel hotel);
}
