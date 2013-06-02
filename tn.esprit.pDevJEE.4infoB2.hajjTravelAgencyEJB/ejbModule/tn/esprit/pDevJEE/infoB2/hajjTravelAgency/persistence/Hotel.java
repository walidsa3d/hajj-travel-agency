package tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Entity implementation class for Entity: Hotel
 *
 */
@Entity

public class Hotel implements Serializable {

	
	private int idHotel;
	private String nameHotel;
	private String locationHotel;
	private String addressHotel;
	private long phoneHotel;
	private List<Floor> floorsHotel=new ArrayList<Floor>();
	private static final long serialVersionUID = 1L;

	public Hotel() {
		super();
	}   
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getIdHotel() {
		return this.idHotel;
	}

	public void setIdHotel(int idHotel) {
		this.idHotel = idHotel;
	}   
	public String getNameHotel() {
		return this.nameHotel;
	}

	public void setNameHotel(String nameHotel) {
		this.nameHotel = nameHotel;
	}   
	public String getLocationHotel() {
		return this.locationHotel;
	}

	public void setLocationHotel(String locationHotel) {
		this.locationHotel = locationHotel;
	}   
	public String getAddressHotel() {
		return this.addressHotel;
	}

	public void setAddressHotel(String addressHotel) {
		this.addressHotel = addressHotel;
	}
	/**
	 * @return the phoneHotel
	 */
	public long getPhoneHotel() {
		return phoneHotel;
	}
	/**
	 * @param phoneHotel the phoneHotel to set
	 */
	public void setPhoneHotel(long phoneHotel) {
		this.phoneHotel = phoneHotel;
	}
	/**
	 * @return the floorsHotel
	 */
	@OneToMany(mappedBy="floorHotel",cascade=CascadeType.ALL)
	public List<Floor> getFloorsHotel() {
		return floorsHotel;
	}
	/**
	 * @param floorsHotel the floorsHotel to set
	 */
	public void setFloorsHotel(List<Floor> floorsHotel) {
		this.floorsHotel = floorsHotel;
	}
	@Override
	public String toString() {
		return nameHotel;
	}
	
   
}
