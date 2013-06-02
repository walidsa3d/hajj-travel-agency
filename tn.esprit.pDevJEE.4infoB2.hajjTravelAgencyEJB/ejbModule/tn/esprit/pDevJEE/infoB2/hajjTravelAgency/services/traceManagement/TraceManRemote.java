package tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.traceManagement;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Trace;

@Remote
public interface TraceManRemote {
	public void addTrace(Trace trace);
	public void removeTrace(Trace trace);
	public List<Trace> getAllTraces();
	public void clearAllTraces();

}
