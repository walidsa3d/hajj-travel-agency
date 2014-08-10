package tn.esprit.pDevJEE.infoB2.hajjTravelAgencyClient.util;

import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Pilgrim;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.pilgrimManagement.PilgrimManRemote;

public class Delegate {

	private Object remote;

	public Object Del(Object obj) {
	if (obj instanceof Pilgrim)
		remote=(PilgrimManRemote) ServiceLocator.getInstance()
				.getRemoteInterface(
						"ejb:/tn.esprit.pDevJEE.4infoB2.hajjTravelAgencyEJB/PilgrimMan!"
								+ PilgrimManRemote.class.getCanonicalName());
		return remote;
	}

	public Delegate() {
		super();
	}
	

}
