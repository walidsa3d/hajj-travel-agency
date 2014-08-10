package tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Entity implementation class for Entity: Plane
 *
 */
@Entity

public class Plane implements Serializable {

	
	
	private int idPlane;
	private String namePlane;
	private String typePlane;
	private Company planeCompany;
	private static final long serialVersionUID = 1L;

	public Plane() {
		super();
	}   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getIdPlane() {
		return this.idPlane;
	}

	public void setIdPlane(int idPlane) {
		this.idPlane = idPlane;
	}   
	public String getNamePlane() {
		return this.namePlane;
	}

	public void setNamePlane(String namePlane) {
		this.namePlane = namePlane;
	}   
	public String getTypePlane() {
		return this.typePlane;
	}

	public void setTypePlane(String typePlane) {
		this.typePlane = typePlane;
	}
	/**
	 * @return the planeCompany
	 */
	@ManyToOne
	@JoinColumn(name="planeCompany")
	public Company getPlaneCompany() {
		return planeCompany;
	}
	/**
	 * @param planeCompany the planeCompany to set
	 */
	public void setPlaneCompany(Company planeCompany) {
		this.planeCompany = planeCompany;
	}
	@Override
	public String toString() {
		return namePlane;
	}

   
}
