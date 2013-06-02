package tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity implementation class for Entity: Flight
 *
 */
@Entity

public class Flight implements Serializable {

	
	private int idFlight;
	private long numberFlight;
	private String typeFlight;
	private Date dateFlight;
	private Plane flightPlane;
	private Airport flightAirport;	
	private static final long serialVersionUID = 1L;

	public Flight() {
		super();
	}  
	
	public Flight(long numberFlight, String typeFlight, Date dateFlight,
			Plane flightPlane, Airport flightAirport) {
		super();
		this.numberFlight = numberFlight;
		this.typeFlight = typeFlight;
		this.dateFlight = dateFlight;
		this.flightPlane = flightPlane;
		this.flightAirport = flightAirport;
	}

	@Id    
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getIdFlight() {
		return this.idFlight;
	}

	public void setIdFlight(int idFlight) {
		this.idFlight = idFlight;
	}   
	/**
	 * @return the numberFlight
	 */
	public long getNumberFlight() {
		return numberFlight;
	}
	/**
	 * @param numberFlight the numberFlight to set
	 */
	public void setNumberFlight(long numberFlight) {
		this.numberFlight = numberFlight;
	}
	public String getTypeFlight() {
		return this.typeFlight;
	}

	public void setTypeFlight(String typeFlight) {
		this.typeFlight = typeFlight;
	} 
	@Temporal(TemporalType.DATE)
	public Date getDateFlight() {
		return this.dateFlight;
	}

	public void setDateFlight(Date dateFlight) {
		this.dateFlight = dateFlight;
	}
	
	/**
	 * @return the flightPlane

	 */
	
	@ManyToOne
	@JoinColumn(name="idPlane")
	public Plane getFlightPlane() {
		return flightPlane;
	}
	/**
	 * @param flightPlane the flightPlane to set
	 */
	public void setFlightPlane(Plane flightPlane) {
		this.flightPlane = flightPlane;
	}
	/**
	 * @return the flightAirport

	 */
	@ManyToOne
	@JoinColumn(name="idAirport")
	public Airport getFlightAirport() {
		return flightAirport;
	}
	/**
	 * @param flightAirport the flightAirport to set
	 */
	public void setFlightAirport(Airport flightAirport) {
		this.flightAirport = flightAirport;
	}

	@Override
	public String toString() {
		return numberFlight + "";
	}
	
   
}
