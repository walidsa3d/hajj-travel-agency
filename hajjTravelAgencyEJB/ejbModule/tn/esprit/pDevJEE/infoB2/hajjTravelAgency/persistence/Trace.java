package tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity implementation class for Entity: Trace
 *
 */
@Entity

public class Trace implements Serializable {

	
	private int operationId;
	private Date operationDate;
	private String operationType;
	private String operationEntity;
	private static final long serialVersionUID = 1L;

	public Trace() {
		super();
	}  
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getOperationId() {
		return this.operationId;
	}

	public void setOperationId(int operationId) {
		this.operationId = operationId;
	}   
	@Temporal(TemporalType.TIMESTAMP)
	public Date getOperationDate() {
		return this.operationDate;
	}

	public void setOperationDate(Date operationDate) {
		this.operationDate = operationDate;
	}  
	  
	public String getOperationType() {
		return this.operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}   
	public String getOperationEntity() {
		return this.operationEntity;
	}

	public void setOperationEntity(String operationEntity) {
		this.operationEntity = operationEntity;
	}
   
}
