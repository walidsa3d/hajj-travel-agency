package tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.hotelManagement;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Room;

@Remote
public interface RoomManRemote {
	public void addRoom(Room room);
	public void deleteRoom(int idRoom);
	public void updateRoom(Room room);
	public Room findRoomById(int idRoom);
	public List<Room> getAllRooms();

}
