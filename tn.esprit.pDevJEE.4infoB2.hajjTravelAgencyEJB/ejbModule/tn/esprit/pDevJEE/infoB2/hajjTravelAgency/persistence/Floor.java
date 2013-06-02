package tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * Entity implementation class for Entity: Floor
 *
 */
@Entity

public class Floor implements Serializable {

	
	private int idFloor;
	private int numberFloor;
	private Hotel floorHotel;
	private List<Room> rooms=new ArrayList<Room>();
	private static final long serialVersionUID = 1L;

	public Floor() {
		super();
	}   
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getIdFloor() {
		return this.idFloor;
	}

	public void setIdFloor(int idFloor) {
		this.idFloor = idFloor;
	}   
	public int getNumberFloor() {
		return this.numberFloor;
	}

	public void setNumberFloor(int numberFloor) {
		this.numberFloor = numberFloor;
	}
	/**
	 * @return the floorHotel

	 */
	@ManyToOne
	@JoinColumn(name="idHotel")
	public Hotel getFloorHotel() {
		return floorHotel;
	}
	/**
	 * @param floorHotel the floorHotel to set
	 */
	public void setFloorHotel(Hotel floorHotel) {
		this.floorHotel = floorHotel;
	}
	/**
	 * @return the rooms

	 */
	@OneToMany(mappedBy="roomFloor", cascade=CascadeType.ALL)
	public List<Room> getRooms() {
		return rooms;
	}
	/**
	 * @param rooms the rooms to set
	 */
	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}
	@Override
	public String toString() {
		return numberFloor+"";
	}
	
   
}
