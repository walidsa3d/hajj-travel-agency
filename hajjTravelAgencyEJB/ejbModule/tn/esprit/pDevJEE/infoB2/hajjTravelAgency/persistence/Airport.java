package tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Entity implementation class for Entity: Airport
 *
 */
@Entity

public class Airport implements Serializable {

	
	

	private int idAirport;
	private String nameAirport;
	private String addressAirport;
	private String phone;
	private static final long serialVersionUID = 1L;

	public Airport() {
		super();
	}   
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getIdAirport() {
		return this.idAirport;
	}

	public void setIdAirport(int idAirport) {
		this.idAirport = idAirport;
	}   
	public String getNameAirport() {
		return this.nameAirport;
	}

	public void setNameAirport(String nameAirport) {
		this.nameAirport = nameAirport;
	}   
	public String getAddressAirport() {
		return this.addressAirport;
	}

	public void setAddressAirport(String addressAirport) {
		this.addressAirport = addressAirport;
	}   
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return nameAirport;
	}
   
}
