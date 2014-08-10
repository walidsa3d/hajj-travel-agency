package tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Entity implementation class for Entity: Company
 *
 */
@Entity

public class Company implements Serializable {

	
	
	private int idCompany;
	private String nameCompany;
	private String addressCompany;
	private String emailCompany;
	private String phone;
	private List<Plane> planes;
	private static final long serialVersionUID = 1L;

	public Company() {
		super();
	}   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getIdCompany() {
		return this.idCompany;
	}

	public void setIdCompany(int idCompany) {
		this.idCompany = idCompany;
	}   
	public String getNameCompany() {
		return this.nameCompany;
	}

	public void setNameCompany(String nameCompany) {
		this.nameCompany = nameCompany;
	}   
	public String getAddressCompany() {
		return this.addressCompany;
	}

	public void setAddressCompany(String addressCompany) {
		this.addressCompany = addressCompany;
	}   
	public String getEmailCompany() {
		return this.emailCompany;
	}

	public void setEmailCompany(String emailCompany) {
		this.emailCompany = emailCompany;
	}   
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * @return the planes

	 */
	@OneToMany(mappedBy="planeCompany")
	public List<Plane> getPlanes() {
		return planes;
	}
	/**
	 * @param planes the planes to set
	 */
	public void setPlanes(List<Plane> planes) {
		this.planes = planes;
	}
	@Override
	public String toString() {
		return nameCompany;
	}
   
}
