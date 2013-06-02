package tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * Entity implementation class for Entity: PGroup
 * 
 */
@Entity
public class PGroup implements Serializable {

	private int idGroup;
	private int groupNumber;
	private int groupMinSize;
	private int groupMaxSize;
	private List<Pilgrim> groupMembers;
	private Pilgrim subLeader;
	private static final long serialVersionUID = 1L;

	public PGroup() {
		super();
	}
	

	public PGroup(int groupNumber, int groupMinSize, int groupMaxSize) {
		super();
		this.groupNumber = groupNumber;
		this.groupMinSize = groupMinSize;
		this.groupMaxSize = groupMaxSize;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getIdGroup() {
		return this.idGroup;
	}

	public void setIdGroup(int idGroup) {
		this.idGroup = idGroup;
	}

	/**
	 * @return the groupNumber
	 */
	public int getGroupNumber() {
		return groupNumber;
	}

	/**
	 * @param groupNumber
	 *            the groupNumber to set
	 */
	public void setGroupNumber(int groupNumber) {
		this.groupNumber = groupNumber;
	}

	public int getGroupMinSize() {
		return this.groupMinSize;
	}

	public void setGroupMinSize(int groupMinSize) {
		this.groupMinSize = groupMinSize;
	}

	public int getGroupMaxSize() {
		return this.groupMaxSize;
	}

	public void setGroupMaxSize(int groupMaxSize) {
		this.groupMaxSize = groupMaxSize;
	}

	/**
	 * @return the groupMembers
	 */
	@OneToMany(mappedBy="pilgrimGroup")
	public List<Pilgrim> getGroupMembers() {
		return groupMembers;
	}

	/**
	 * @param groupMembers the groupMembers to set
	 */
	public void setGroupMembers(List<Pilgrim> groupMembers) {
		this.groupMembers = groupMembers;
	}

	/**
	 * @return the subLeader
	 */
	@OneToOne
	@JoinColumn(name="idSubLeader")
	public Pilgrim getSubLeader() {
		return subLeader;
	}

	/**
	 * @param subLeader the subLeader to set
	 */
	public void setSubLeader(Pilgrim subLeader) {
		this.subLeader = subLeader;
	}



}
