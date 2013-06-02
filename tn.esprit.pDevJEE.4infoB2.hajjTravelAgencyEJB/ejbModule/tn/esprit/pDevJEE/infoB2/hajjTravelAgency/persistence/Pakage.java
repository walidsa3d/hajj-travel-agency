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
 * Entity implementation class for Entity: Pakage
 *
 */
@Entity

public class Pakage implements Serializable {

	
	private int idPackage;
	private String namePackage;
	private String capacityPackage;
	private long pricePackage;
	private Date startDate;
	private Date endDate;
	private Hotel hotelMecca;
	private Hotel hotelMedina;
	private Flight oneWayFlight;
	private Flight returnFlight;
	private String categoryPackage;
	private static final long serialVersionUID = 1L;

	public Pakage() {
		super();
	}  
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getIdPackage() {
		return this.idPackage;
	}

	public void setIdPackage(int idPackage) {
		this.idPackage = idPackage;
	}   
	public String getNamePackage() {
		return this.namePackage;
	}

	public void setNamePackage(String namePackage) {
		this.namePackage = namePackage;
	}   
	public String getCapacityPackage() {
		return this.capacityPackage;
	}

	public void setCapacityPackage(String capacityPackage) {
		this.capacityPackage = capacityPackage;
	}   
	public long getPricePackage() {
		return this.pricePackage;
	}

	public void setPricePackage(long pricePackage) {
		this.pricePackage = pricePackage;
	}
	/**
	 * @return the hotelMecca

	 */
	@ManyToOne
	@JoinColumn(name="hotelMecca")
	public Hotel getHotelMecca() {
		return hotelMecca;
	}
	/**
	 * @param hotelMecca the hotelMecca to set
	 */
	public void setHotelMecca(Hotel hotelMecca) {
		this.hotelMecca = hotelMecca;
	}
	/**
	 * @return the hotelMedina

	 */
	@ManyToOne
	@JoinColumn(name="hotelMedina")
	public Hotel getHotelMedina() {
		return hotelMedina;
	}
	/**
	 * @param hotelMedina the hotelMedina to set
	 */
	public void setHotelMedina(Hotel hotelMedina) {
		this.hotelMedina = hotelMedina;
	}
	/**
	 * @return the oneWayFlight

	 */
	@ManyToOne
	@JoinColumn(name="oneWayFlight")
	public Flight getOneWayFlight() {
		return oneWayFlight;
	}
	/**
	 * @param oneWayFlight the oneWayFlight to set
	 */
	public void setOneWayFlight(Flight oneWayFlight) {
		this.oneWayFlight = oneWayFlight;
	}
	/**
	 * @return the returnFlight


	 */
	@ManyToOne
	@JoinColumn(name="returnFlight")	
	public Flight getReturnFlight() {
		return returnFlight;
	}
		/**
	 * @param returnFlight the returnFlight to set
	 */
	public void setReturnFlight(Flight returnFlight) {
		this.returnFlight = returnFlight;
	}
		/**
		 * @return the startDate
		 */
	@Temporal(TemporalType.DATE)
		public Date getStartDate() {
			return startDate;
		}
		/**
		 * @param startDate the startDate to set
		 */
		public void setStartDate(Date startDate) {
			this.startDate = startDate;
		}
		/**
		 * @return the endDate
		 */
		@Temporal(TemporalType.DATE)
		public Date getEndDate() {
			return endDate;
		}
		/**
		 * @param endDate the endDate to set
		 */
		public void setEndDate(Date endDate) {
			this.endDate = endDate;
		}
	
		/**
		 * @return the categoryPackage
		 */
		public String getCategoryPackage() {
			return categoryPackage;
		}
		/**
		 * @param categoryPackage the categoryPackage to set
		 */
		public void setCategoryPackage(String categoryPackage) {
			this.categoryPackage = categoryPackage;
		}
   
}
