package tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.traceManagement;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Trace;

/**
 * Session Bean implementation class TraceMan
 */
@Stateless
public class TraceMan implements TraceManRemote, TraceManLocal {
	@PersistenceContext
	EntityManager em;

    /**
     * Default constructor. 
     */
    public TraceMan() {
    }

	public void addTrace(Trace trace) {
		em.persist(trace);
	}
	
public void traceIt(String op, Object obj)	{
	Trace trace=new Trace();
	Date date=new Date();
	trace.setOperationType(op);
	trace.setOperationDate(date);
	trace.setOperationEntity(obj.getClass().getSimpleName());
	addTrace(trace);
}
	public void removeTrace(Trace trace) {
		em.remove(em.merge(trace));
	}

	public List<Trace> getAllTraces() {
		Query query=em.createQuery("Select t from Trace t");
		return query.getResultList();
	}
	public void clearAllTraces() {
		List<Trace> tracelist=getAllTraces();
		for(Trace trace:tracelist)
			removeTrace(trace);
	}

}
