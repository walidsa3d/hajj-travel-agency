package tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * Entity implementation class for Entity: Pilgrim.
 */
@Entity
public class Pilgrim implements Serializable {

	
	
	
	/** The pilgrim first name. */
	private String pilgrimFirstName;

	/** The pilgrim last name. */
	private String pilgrimLastName;

	/** The pilgrim birth date. */
	private Date pilgrimBirthDate;

	/** The pilgrim gender. */
	private String pilgrimGender;

	/** The pilgrim address. */
	private String pilgrimAddress;
	

	/** The pilgrim phone. */
	private int pilgrimPhone;
	
	/** The Pilgrim email. */
	private String PilgrimEmail;
	
	/** The cin. */
	private int pilgrimCin;
	private int pilgrimPassport;
	private Pilgrim mahrem;
	private PGroup pilgrimGroup;
	private User owner;
		
	

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new pilgrim.
	 */
	public Pilgrim() {
		super();
	}

	public Pilgrim(String pilgrimFirstName, String pilgrimLastName,
			Date pilgrimBirthDate, String pilgrimGender, String pilgrimAddress,
			int pilgrimPhone, String pilgrimEmail, int pilgrimCin, int pilgrimPassport) {
		super();
		this.pilgrimFirstName = pilgrimFirstName;
		this.pilgrimLastName = pilgrimLastName;
		this.pilgrimBirthDate = pilgrimBirthDate;
		this.pilgrimGender = pilgrimGender;
		this.pilgrimAddress = pilgrimAddress;
		this.pilgrimPhone = pilgrimPhone;
		this.PilgrimEmail = pilgrimEmail;
		this.pilgrimCin=pilgrimCin;
		this.pilgrimPassport=pilgrimPassport;
	}


	/**
	 * Gets the pilgrim first name.
	 * 
	 * @return the pilgrim first name
	 */
	public String getPilgrimFirstName() {
		return this.pilgrimFirstName;
	}

	/**
	 * Sets the pilgrim first name.
	 * 
	 * @param pilgrimFirstName
	 *            the new pilgrim first name
	 */
	public void setPilgrimFirstName(String pilgrimFirstName) {
		this.pilgrimFirstName = pilgrimFirstName;
	}

	/**
	 * Gets the pilgrim last name.
	 * 
	 * @return the pilgrim last name
	 */
	public String getPilgrimLastName() {
		return this.pilgrimLastName;
	}

	/**
	 * Sets the pilgrim last name.
	 * 
	 * @param pilgrimLastName
	 *            the new pilgrim last name
	 */
	public void setPilgrimLastName(String pilgrimLastName) {
		this.pilgrimLastName = pilgrimLastName;
	}

	/**
	 * Gets the pilgrim birth date.
	 * 
	 * @return the pilgrim birth date
	 */
	public Date getPilgrimBirthDate() {
		return this.pilgrimBirthDate;
	}

	/**
	 * Sets the pilgrim birth date.
	 * 
	 * @param pilgrimBirthDate
	 *            the new pilgrim birth date
	 */
	public void setPilgrimBirthDate(Date pilgrimBirthDate) {
		this.pilgrimBirthDate = pilgrimBirthDate;
	}

	/**
	 * @return the pilgrimGender
	 */
	public String getPilgrimGender() {
		return pilgrimGender;
	}

	/**
	 * Sets the pilgrim gender.
	 * 
	 * @param pilgrimGender
	 *            the new pilgrim gender
	 */
	public void setPilgrimGender(String pilgrimGender) {
		this.pilgrimGender = pilgrimGender;
	}

	/**
	 * Gets the pilgrim address.
	 * 
	 * @return the pilgrim address
	 */
	public String getPilgrimAddress() {
		return this.pilgrimAddress;
	}

	/**
	 * Sets the pilgrim address.
	 * 
	 * @param pilgrimAddress
	 *            the new pilgrim address
	 */
	public void setPilgrimAddress(String pilgrimAddress) {
		this.pilgrimAddress = pilgrimAddress;
	}

	/**
	 * Gets the pilgrim phone.
	 * 
	 * @return the pilgrim phone
	 */
	public int getPilgrimPhone() {
		return this.pilgrimPhone;
	}

	/**
	 * Sets the pilgrim phone.
	 * 
	 * @param pilgrimPhone
	 *            the new pilgrim phone
	 */
	public void setPilgrimPhone(int pilgrimPhone) {
		this.pilgrimPhone = pilgrimPhone;
	}

	/**
	 * @return the pilgrim Email

	 */
	@Column(unique=true)
	public String getPilgrimEmail() {
		return PilgrimEmail;
	}

	/**
	 * @param pilgrimEmail the pilgrim Email to set
	 */
	public void setPilgrimEmail(String pilgrimEmail) {
		this.PilgrimEmail = pilgrimEmail;
	}

	/**
	 * @return the cin

	 */
	@Id
	public int getPilgrimCin() {
		return pilgrimCin;
	}

	/**
	 * @param cin the cin to set
	 */
	public void setPilgrimCin(int cin) {
		this.pilgrimCin = cin;
	}

	/**
	 * @return the passport

	 */
	@Column(unique=true)
	public int getPilgrimPassport() {
		return pilgrimPassport;
	}

	/**
	 * @param passport the passport to set
	 */
	public void setPilgrimPassport(int passport) {
		this.pilgrimPassport = passport;
	}


	/**
	 * @return the mahrem

	 */
	@OneToOne
	@JoinColumn(name="idMahrem")
	public Pilgrim getMahrem() {
		return mahrem;
	}

	/**
	 * @param mahrem the mahrem to set
	 */
	public void setMahrem(Pilgrim mahrem) {
		this.mahrem = mahrem;
	}







	/**
	 * @return the pilgrimGroup
	 */
	@ManyToOne
	@JoinColumn(name="idGroup")
	public PGroup getPilgrimGroup() {
		return pilgrimGroup;
	}

	/**
	 * @param pilgrimGroup the pilgrimGroup to set
	 */
	public void setPilgrimGroup(PGroup pilgrimGroup) {
		this.pilgrimGroup = pilgrimGroup;
	}

	/**
	 * @return the owner
	 */
	@ManyToOne
	@JoinColumn(name="idOwner")
	public User getOwner() {
		return owner;
	}

	/**
	 * @param owner the owner to set
	 */
	public void setOwner(User owner) {
		this.owner = owner;
	}

	@Override
	public String toString() {
		return pilgrimFirstName +" "+ pilgrimLastName;
	}

}
