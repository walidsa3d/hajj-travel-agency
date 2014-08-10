package tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.traceManagement;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Trace;

@Local
public interface TraceManLocal {
	public void addTrace(Trace trace);
	public void removeTrace(Trace trace);
	public List<Trace> getAllTraces();
	public void traceIt(String op, Object obj);

}
