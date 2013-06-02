package tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Entity implementation class for Entity: Room
 *
 */
@Entity

public class Room implements Serializable {

	
	private int idRoom;
	private int capacityRoom;
	private int numberRoom;
	private Floor roomFloor;
	private static final long serialVersionUID = 1L;

	public Room() {
		super();
	}   
	@Id   
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getIdRoom() {
		return this.idRoom;
	}

	public void setIdRoom(int idRoom) {
		this.idRoom = idRoom;
	}   

	/**
	 * @return the numberRoom
	 */
	public int getNumberRoom() {
		return numberRoom;
	}
	/**
	 * @param numberRoom the numberRoom to set
	 */
	public void setNumberRoom(int numberRoom) {
		this.numberRoom = numberRoom;
	}
	/**
	 * @return the roomFloor

	 */
	@ManyToOne
	@JoinColumn(name="roomFloor")	
	public Floor getRoomFloor() {
		return roomFloor;
	}
	/**
	 * @param roomFloor the roomFloor to set
	 */
	public void setRoomFloor(Floor roomFloor) {
		this.roomFloor = roomFloor;
	}
	/**
	 * @return the capacityRoom
	 */
	public int getCapacityRoom() {
		return capacityRoom;
	}
	/**
	 * @param capacityRoom the capacityRoom to set
	 */
	public void setCapacityRoom(int capacityRoom) {
		this.capacityRoom = capacityRoom;
	}
	@Override
	public String toString() {
		return numberRoom+"";
	}
   
}
