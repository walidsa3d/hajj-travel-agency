package tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.messageService;

import javax.ejb.Remote;

@Remote
public interface MServiceRemote {
	public void sendMail(String name, String email, String username, String password);


}
